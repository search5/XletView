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
public class File extends java.io.File{
//public class File {
    
    private static String virtualRoot = "";
    private String path;
    
    public File(File parent, String child){
    	super(parent, child);
    }
    
    public File(String parent, String child){
    	super(parent, child);
    }
    
    public File(String s){        
        super(s);
        path = s;
        System.out.println("xjava.io.File - " + s);
    }
    
    public String getAbsolutePath(){
        //return super.getAbsolutePath();
        return virtualRoot + path; 
    }
    
    public static void setVirtualRoot(String root){
	
	System.out.println("Call me who??? "+root);
	
        if(!root.endsWith("\\") || !root.endsWith("/")){
            root += java.io.File.separator;
        }
        File.virtualRoot = root;
    }
    
    public static String getVirtualRoot(){
        return File.virtualRoot;
    }

}
