/*
 * 
 * This file is part of XleTView Copyright (C) 2003 Martin Svedén
 * 
 * This is free software, and you are welcome to redistribute it under certain
 * conditions;
 * 
 * See LICENSE document for details.
 *  
 */

package org.dvb.lang;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.ClassPool;
import javassist.CodeConverter;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import net.beiker.xletview.classloader.XletCodeConverter;
import net.beiker.xletview.util.Debug;

/**
 * @author Martin Sveden
 * @statuscode 3
 * @comment Have to fix the loading of DVBGraphics stuff, a bit buggy now.
 */
public abstract class DVBClassLoader extends java.security.SecureClassLoader {
	
	private URL[] urls;
	private ClassLoader parent;
	private Hashtable loaded;
	private ClassPool pool;
	private ClassMap xletClassMap;


	public DVBClassLoader(URL[] urls) {
		this.urls = urls;
		this.parent = getClass().getClassLoader();
		loaded = new Hashtable();
		pool = new ClassPool(null);
		xletClassMap = new net.beiker.xletview.classloader.XletClassMap();
		addUrls(urls);
		
		pool.insertClassPath(new LoaderClassPath(parent));
	}

	public DVBClassLoader(URL[] urls, ClassLoader parent) {
		this(urls);
		this.parent = parent;
	}
	
	private void addUrls(URL[] urls){
		for (int i = 0; i < urls.length; i++) {
			try {
				pool.appendClassPath(urls[i].getPath());
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static DVBClassLoader newInstance(URL[] urls) {
		return new DVBClassLoaderImpl(urls);
	}

	public static DVBClassLoader newInstance(URL[] urls, ClassLoader parent) {
		return new DVBClassLoaderImpl(urls, parent);
	}

	public Class loadClass(String name) throws ClassNotFoundException {
		name = name.replaceAll("/", ".");
		
//		logger.debug("loading - " + name);
		Class theClass = null;		
		boolean newClass = false;
		
		// check if it's already loaded by this loader
		theClass = getLoadedClass(name);

		
		if (theClass == null) {
			try {
				/*
				 * try to load the class with the parent classloader
				 */
				if (parent != null) {
					theClass = parent.loadClass(name);					
				}

			} catch (ClassNotFoundException e) {
				// do nothing
			}
		}
		
		if (theClass == null) {
 			try{
				theClass = findClass(name);
				newClass = true;        		
			}
			catch(ClassNotFoundException e){    
				// do nothing
			}
		}
		

		if(theClass == null){
			/*
			 * The class is still not found.
			 * Throw an Exception
			 */
			throw new ClassNotFoundException("not found -> " + name);
		}
		else if(newClass){
			// it wasn't previously loaded
			loaded.put(name, theClass);		
			//Debug.write(this, "name=" + name);
		}        
		return theClass;

	}

	private Class getLoadedClass(String name) {
		return (Class) loaded.get(name);
	}

	/*
	 * not used at the moment since we use the
	 * ClassPool instead
	 */
/*	private byte[] getClassData(String name) {
		byte[] bytes = null;

		String fileName = name + ".class";
		File classFile = null;
		for (int i = 0; i < urls.length; i++) {
			
			String dirPath = urls[i].toExternalForm().replaceAll("file:/", "");
			
			classFile = new File(dirPath, fileName);
			Debug.write(this, classFile.getPath());
			if (classFile.exists()) {
				break;
			}
		}
		if (classFile != null) {
			try {
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				FileInputStream fis = new FileInputStream(classFile);
				int size = 0;
				try {
					for (;;) {
						int len = fis.read(buffer);
						size += len;

						if (len < 0) {
							baos.flush();
							baos.close();
							break;
						}
						baos.write(buffer, 0, len);
					}
					bytes = baos.toByteArray();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
			}

		}

		return bytes;
	}
*/
	
	protected Class findClass(String name) throws ClassNotFoundException {
		try {
			CtClass cc = pool.get(name);

			cc.replaceClassName(xletClassMap);
			CodeConverter conv = new XletCodeConverter();
			cc.instrument(conv);
			
			/*String[] graphicsMethods =
			{ "paint", "paintAll", "print", "printAll", "update" };
			for (int i = 0; i < graphicsMethods.length; i++) {
				try {
					CtMethod m = cc.getDeclaredMethod(graphicsMethods[i]);
					m.insertBefore(
					"{ $1 = org.dvb.ui.DVBGraphics.getDVBGraphics($1);}");
					Debug.write(this, "changed paint method in " + name);
				} catch (Exception e) {
					// do nothing
				}
				
			}*/
			
			byte[] b = pool.write(name);

			return super.defineClass(name, b, 0, b.length);
		}
		catch (NotFoundException e) {
			throw new ClassNotFoundException();
		}
		catch (IOException e) {
			throw new ClassNotFoundException();
		}
		catch (CannotCompileException e) {            
			throw new ClassNotFoundException();
		}
	}
	
	
	

}
