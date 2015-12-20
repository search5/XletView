/*

 This file is part of XleTView
 Copyright (C) 2003 Martin Sveden

 This is free software, and you are
 welcome to redistribute it under
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.classloader;

import java.net.*;
import java.util.*;
import java.io.*;

/**
 *
 *
 * @author Martin Sveden
 */
public class MainClassLoader extends URLClassLoader {

	
    private Hashtable loadedClasses;

    public MainClassLoader(URL[] urls) {
    	super(urls);
    	addUrls();

        loadedClasses = new Hashtable();
    }


    public void addClassPath(String classpath){
    	String[] s = classpath.split(File.pathSeparator);
    	
    	for (int i = 0; i < s.length; i++) {    		
    		try {
    			super.addURL(new File(s[i]).toURL());
    		} catch (MalformedURLException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    private URL[] addUrls() {

    	Vector paths = new Vector();

    	paths.add("xletview.jar");
    	paths.add("JMF2.1.1/lib/jmf.jar");
    	paths.add("jars/nanoxml-2.2.3.jar");
    	paths.add("jars/metouia.jar");
    	paths.add("jars/javassist.jar");
    	paths.add("jars/log4j-1.2.8.jar");

    	URL[] urls = new URL[paths.size()];

    	for (int i = 0; i < paths.size(); i++) {
    		String s = (String) paths.get(i);
    		File f = new File(s);
    		try {
    			//Debug.write(this, "" + f.toURL());
    			urls[i] = f.toURL();
    			super.addURL(urls[i]);
    		} catch (MalformedURLException e) {
    			e.printStackTrace();
    		}
    	}
    	return urls;
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
        if(theClass == null){
            newClass = true;
            theClass = findClass(name);
        }
        if(theClass == null){
            theClass = this.findSystemClass(name);
        }

        if(theClass == null){
            throw new ClassNotFoundException();
        }
        if(newClass && theClass != null){
        	//System.out.println("[MainClassLoader] loaded - " + name);
            loadedClasses.put(name, theClass);
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
