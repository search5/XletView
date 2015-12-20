/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/
 
package xjava.io;

import java.io.*;

import net.beiker.xletview.util.Debug;

/**
 * 
 * @author Martin Sveden 
 */
public class FileInputStream extends java.io.FileInputStream {

	public static java.io.FileInputStream create(java.io.File f) throws FileNotFoundException{
		//return new java.io.FileInputStream(f);
		return FileInputStream.create(f.getPath());
	}
	
	public static java.io.FileInputStream create(String fileName) throws FileNotFoundException{
		System.out.println("#########################\n###########creating FileInputStream ");
		return new java.io.FileInputStream(File.getVirtualRoot() + fileName);
	}
	
    public FileInputStream(String name) throws FileNotFoundException {
        super(File.getVirtualRoot() + name);
    }
    
      public FileInputStream(File file) throws FileNotFoundException {
        this(file.getPath());
      }

      


}
