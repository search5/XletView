/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.ClassPool;
import javassist.CodeConverter;
import javassist.CtClass;
import javassist.NotFoundException;
import net.beiker.xletview.util.Debug;



/**
 * 
 * 
 * @author Martin Sveden
 */
public class EmulatorClassLoader extends MainClassLoader {
    
	
	private Hashtable loadedClasses;
	
	private ClassPool pool;
	private ClassMap emulatorClassMap;
	
    
    public EmulatorClassLoader(URL[] urls) {
    	super(urls);
        loadedClasses = new Hashtable();
        
        pool = new ClassPool(null);
        emulatorClassMap = new XletClassMap();

//        for (int i = 0; i < urls.length; i++) {
//        	try {
//        		String ext = urls[i].toExternalForm();
//        		String formatted = ext.substring(ext.indexOf("/") + 1);
//        		pool.appendClassPath(formatted);
//        	} catch (NotFoundException e) {
//        		e.printStackTrace();
//        	}
//        }
        
    }

    /*
     *  (non-Javadoc)
     * @see java.lang.ClassLoader#loadClass(java.lang.String)
     */
    public Class loadClass(String name) throws ClassNotFoundException {
    	name = name.replaceAll("/", ".");
    	
        Class theClass = null;
        
        boolean newClass = false;
        theClass = getLoadedClass(name);
        
        /*
         * We don't want this class to load itself
         * so we let the system loader do that
         */
        if (name.equals(getClass().getName())) {
        	System.out.println(name+ " equals " + getClass().getName() );
        	theClass = super.findSystemClass(name);
        	newClass = true;
        }
        
        if(theClass == null){            
            try{
            	theClass = findClass(name);
            	newClass = true;        		
            }
            catch(ClassNotFoundException e){    
            	// do nothing
            }
        }
        
        if(theClass == null){	
            theClass = this.findSystemClass(name);
        }
        
        if(theClass == null){
            throw new ClassNotFoundException();
        }
        if(newClass && theClass != null){
        	//System.out.println("[EmulatorClassLoader] loaded - " + name);
        	
            loadedClasses.put(name, theClass);
            //Debug.write(this, "loadedClass  - " + name);
        }
        return theClass;
    }

    /**
     * Returns a class if previously loaded by
     * this classloader.
     */
    private Class getLoadedClass(String name){
        return (Class)loadedClasses.get(name);
    }

    /**
     * Finds the class and modifies the bytecode if necessary.
     */
//    protected Class findClass(String name) throws ClassNotFoundException {        
//    	try {
//    		CtClass cc = pool.get(name);
//
//    		Debug.write(this, "CHANGING BYTECODE IN " + name);
//    		cc.replaceClassName(emulatorClassMap);
//    		
//    		
//    		byte[] b = pool.write(name);
//
//    		return super.defineClass(name, b, 0, b.length);
//    	}
//    	catch (NotFoundException e) {
//    		System.out.println("errof finding " + name);
//    		
//    		return super.findClass(name);
//    		//throw new ClassNotFoundException();
//    	}
//    	catch (IOException e) {
//    		throw new ClassNotFoundException();
//    	}
//    	catch (CannotCompileException e) {            
//    		throw new ClassNotFoundException();
//    	}
//    }
    
    protected Class findClass(String name) throws ClassNotFoundException {       
        Class theClass = null;
        try {
            theClass = super.findClass(name);
        }
        catch (ClassNotFoundException e) {            
        }
        return theClass;
    }

}
