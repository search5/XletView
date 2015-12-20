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
public class FileOutputStream extends java.io.FileOutputStream{

	public static java.io.FileOutputStream create(java.io.File f) throws FileNotFoundException{
		//return new java.io.FileInputStream(f);
		return FileOutputStream.create(f.getPath());
	}
	
	public static java.io.FileOutputStream create(String fileName) throws FileNotFoundException{
		return new java.io.FileOutputStream(File.getVirtualRoot() + fileName);
	}
	
    public FileOutputStream(String name) throws FileNotFoundException {
        super(File.getVirtualRoot() + name);
    }
    
      public FileOutputStream(File file) throws FileNotFoundException {
        this(file.getPath());
      }

      


}
