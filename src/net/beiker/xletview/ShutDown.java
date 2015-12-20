/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/
package net.beiker.xletview;

import java.io.IOException;

import net.beiker.xletview.download.Downloader;
import net.beiker.xletview.util.*;

/**
 * @author Martin Sveden
 * 
 */
public class ShutDown {

    public ShutDown(){        
//        Downloader dwnLoader;
//        try {
//            dwnLoader = new Downloader(Constants.PATH_APPDIR);
//            dwnLoader.flush();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        Settings.save();        
        System.exit(0);
        
    }

}
