/*
 * 
 * This file is part of XleTView Copyright (C) 2003 Martin Sved�n
 * 
 * This is free software, and you are welcome to redistribute it under certain
 * conditions;
 * 
 * See LICENSE document for details.
 *  
 */

package net.beiker.xletview;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import net.beiker.xletview.classloader.MainClassLoader;
import net.beiker.xletview.util.CommandLine;
import net.beiker.xletview.util.Constants;

/**
 * 
 * 
 * @author Martin Sveden
 */
public class Main {

	public static void main(String[] args) {
		int command = CommandLine.check(args);
		if(command == CommandLine.EXIT){
			System.exit(0);
		}
		
		System.out.println(Constants.DISCLAIMER_MESSAGE);
				
		URLClassLoader systemLoader = (URLClassLoader)Main.class.getClassLoader();
		MainClassLoader loader = new MainClassLoader(systemLoader.getURLs());

		Object emulatorClassLoader = null;
		try {	
			Class dynamicClass = loader.loadClass("net.beiker.xletview.classloader.EmulatorClassLoader");
			Class[] constructorArgumentTypes = { URL[].class };
			Constructor classConstructor = dynamicClass.getConstructor(constructorArgumentTypes);
			Object[] constructorArgs = { loader.getURLs() };
			emulatorClassLoader = classConstructor.newInstance(constructorArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {	
			Class dynamicClass = Class.forName("net.beiker.xletview.Startup", false, (ClassLoader)emulatorClassLoader);
			Class[] constructorArgumentTypes = { String[].class };
			Constructor classConstructor = dynamicClass.getConstructor(constructorArgumentTypes);
			Object[] constructorArgs = { args };
			classConstructor.newInstance(constructorArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
