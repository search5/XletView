/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Svedén
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package net.beiker.xletview.io;


import java.io.File;

public class FileInfo{

    private File file;

    public FileInfo(File file){
        this.file = file;
    }

    public String getPath(){
        System.out.println("-->" + file.getPath());
        return file.getPath();
    }

    public String toString(){
        String name = file.getName();
        if(name.length() < 1) name = file.getPath();
        return name;
    }

}
