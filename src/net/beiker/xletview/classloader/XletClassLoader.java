/*
 * 
 * This file is part of XleTView Copyright (C) 2003 Martin Sveden
 * 
 * This is free software, and you are welcome to redistribute it under certain
 * conditions;
 * 
 * See LICENSE document for details.
 *  
 */

package net.beiker.xletview.classloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.ClassPool;
import javassist.CodeConverter;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import net.beiker.xletview.util.Debug;
import net.beiker.xletview.util.Settings;
import net.beiker.xletview.util.Util;

/**
 * Loads the classes used in an Xlet. Each Xlet has their own instance of this
 * Classloader. This classloader also changes the bytecode of the Xlet classes
 * to get behaviours that better simulate the platform.
 * 
 * @author Martin Sveden
 */
public final class XletClassLoader extends ClassLoader {

	private Hashtable loadedClasses;
	private ClassPool pool;
	private ClassLoader parent;
	private ClassMap xletClassMap;

	private XletClassLoader() {
		parent = this.getClass().getClassLoader();
		pool = new ClassPool(null);
		loadedClasses = new Hashtable();
		xletClassMap = new XletClassMap();

		/*
		 * Get the extra classpaths defined in the settings file. The reason
		 * this classloader is loading these classes is that they need to be
		 * bytecode manipulated
		 */
		String extraClassPath = Settings.getProperty("extra.classpath");

		String[] s = extraClassPath.split(File.pathSeparator);

		for (int i = 0; i < s.length; i++) {
			try {
				pool.insertClassPath(Util.getAbsolutePath(XletClassLoader.class,s[i]));
			} catch (NotFoundException e) {
				System.err.println(
					"The extra classpath " + s[i] + " not found");

			}
		}

		pool.insertClassPath(new LoaderClassPath(parent));

	}

	//public XletClassLoader(URL[] urls, ClassLoader parent, String
	// virtualRoot) throws NotFoundException {
	public XletClassLoader(String virtualRoot) throws NotFoundException {
		this();

		// the root of the Xlet
		pool.insertClassPath(virtualRoot);

		/*
		 * set the root of the Xlet for the custom File object
		 */
		xjava.io.File.setVirtualRoot(virtualRoot);

	}

	public void addUrls(URL[] urls) {
		for (int i = 0; i < urls.length; i++) {
			try {
				String ext = urls[i].toExternalForm();
				String formatted = ext.substring(ext.indexOf("/") + 1);
				pool.appendClassPath(formatted);
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.ClassLoader#loadClass(java.lang.String)
	 */
	public Class loadClass(String name) throws ClassNotFoundException {

		name = name.replaceAll("/", ".");
		//    	logger.debug("loading - " + name);
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
			/*
			 * It's one of the Xlet's classes
			 */
			try {
				theClass = findClass(name);
				newClass = true;
			} catch (ClassNotFoundException e) {
				// do nothing
			}
		}

		if (theClass == null) {
			/*
			 * The class is still not found. Throw an Exception
			 */
			throw new ClassNotFoundException();
		} else if (newClass) {
			// it wasn't previously loaded
			loadedClasses.put(name, theClass);
			//            logger.debug("loadedClass - " + name + ", " + this);
		}
		return theClass;

	}

	/**
	 * Returns a class if previously loaded by this classloader.
	 */
	private Class getLoadedClass(String name) {
		return (Class) loadedClasses.get(name);
	}

	/**
	 * Finds the class and modifies the bytecode if necessary.
	 */
	protected Class findClass(String name) throws ClassNotFoundException {
		try {
			CtClass cc = pool.get(name);

			Debug.write(this, "CHANGING BYTECODE IN " + name);
			cc.replaceClassName(xletClassMap);

			// convert code
			CodeConverter conv = new XletCodeConverter();
			cc.instrument(conv);

//			/*
//			 * Since all graphics objects in an MHP platform are DVBGraphics we
//			 * modify the methods that take a Graphics object as argument
//			 */
//			String[] graphicsMethods =
//				{ "paint", "paintAll", "print", "printAll", "update" };
//			for (int i = 0; i < graphicsMethods.length; i++) {
//				try {
//					CtMethod m = cc.getDeclaredMethod(graphicsMethods[i]);
//					m.insertBefore(
//						"{ $1 = org.dvb.ui.DVBGraphics.getDVBGraphics($1);}");
//				} catch (Exception e) {
//					// do nothing
//				}
//			}

			byte[] b = pool.write(name);
			Class theClass = null;
			try {
				theClass = super.defineClass(name, b, 0, b.length);
			} catch (SecurityException e) {
				// do nothing
			}
			return theClass;
		} catch (NotFoundException e) {
			throw new ClassNotFoundException();
		} catch (IOException e) {
			throw new ClassNotFoundException();
		} catch (CannotCompileException e) {
			throw new ClassNotFoundException();
		}
	}

}
