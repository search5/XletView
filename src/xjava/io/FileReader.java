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

/**
 * 
 * @author Martin Sveden 
 */
public class FileReader extends java.io.FileReader {

	public static java.io.FileReader create(java.io.File f) throws FileNotFoundException{
		return new java.io.FileReader(File.getVirtualRoot() + f.getPath());
	}
	
	public static java.io.FileReader create(String fileName) throws FileNotFoundException{
		return new java.io.FileReader(File.getVirtualRoot() + fileName);
	}
	
    public FileReader(File f) throws FileNotFoundException{
        super(File.getVirtualRoot() + f.getPath());
    }

    public FileReader(String fileName) throws FileNotFoundException{
    	super(File.getVirtualRoot() + fileName);
    }
    
    public FileReader(FileDescriptor fd){
    	super(fd);
    }
}
