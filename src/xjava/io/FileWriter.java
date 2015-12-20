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
public class FileWriter extends java.io.FileWriter{


	public FileWriter(String fileName) throws IOException {
		super(File.getVirtualRoot() + fileName);
	}

	public FileWriter(String fileName, boolean append) throws IOException {
		super(File.getVirtualRoot() + fileName, append);
	}

	public FileWriter(File file) throws IOException {
		super(File.getVirtualRoot() + file.getPath());
	}

	public FileWriter(FileDescriptor fd) {
		super(fd);
	}

}	
