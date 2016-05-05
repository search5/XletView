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
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import net.beiker.xletview.classloader.MainClassLoader;
import net.beiker.xletview.util.CommandLine;
import net.beiker.xletview.util.Constants;

/**
 * 
 * 
 * @author Martin Sveden
 */
public class Main {

	public static ClassLoader loadEmulatorClass() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

		URLClassLoader systemLoader = (URLClassLoader)Main.class.getClassLoader();

		URL[] urls = systemLoader.getURLs();
		ArrayList<URL> arraylist = new ArrayList<URL>();
		for(int i = 0; i < urls.length ; i++){
			if(urls[i].getPath().contains("/jre/") || urls[i].getPath().contains("/java/") ){

			}else {
				arraylist.add(urls[i]);
			}
		}

		URL[] urlArray = new URL[arraylist.size()];
		for(int i = 0; i < arraylist.size(); i ++){
			urlArray[i] = arraylist.get(i);
			System.out.println("URL : "+urlArray[i].getPath());
		}

		MainClassLoader loader = new MainClassLoader(urlArray);





		Object emulatorClassLoader = null;
		Class dynamicClass = loader.loadClass("net.beiker.xletview.classloader.EmulatorClassLoader");
		Class[] constructorArgumentTypes = { URL[].class };
		Constructor classConstructor = dynamicClass.getConstructor(constructorArgumentTypes);
		Object[] constructorArgs = { loader.getURLs() };
		emulatorClassLoader = classConstructor.newInstance(constructorArgs);

		return (ClassLoader)emulatorClassLoader;

	}


	public static void loadClass(String[] args , ClassLoader emulatorClassLoader){
		try {
			Class dynamicClass = Class.forName("net.beiker.xletview.Startup", false, emulatorClassLoader);
			Class[] constructorArgumentTypes = { String[].class };
			Constructor classConstructor = dynamicClass.getConstructor(constructorArgumentTypes);
			Object[] constructorArgs = { args };
			classConstructor.newInstance(constructorArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int command = CommandLine.check(args);
		if(command == CommandLine.EXIT){
			System.exit(0);
		}
		System.out.println(Constants.DISCLAIMER_MESSAGE);
		ClassLoader classLoader = loadEmulatorClass();
		loadClass(args , classLoader);

	}

}
