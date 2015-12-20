/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;


public class Util {

    public static String normalizePath(String path){
        String s;
        s = path.replace('\\', File.separatorChar);
        s = s.replace('/', File.separatorChar);
        return s;        
    }
    


    public static Frame getParentFrame(Component component){
        Frame frame;
        Component parent = component.getParent();
        if(parent instanceof Frame == false){
            frame = getParentFrame(parent);                      
        }
        else{
            return (Frame)parent;  
        }
        return frame;
    }

    /**
     * Provides a safe and fast way of Integer.parseInt(String s);
     * @param s
     * @return An int that is at lease 0;
     */
    public static int parseInt(String s){
        int i = 0;
        try{
            i = Integer.parseInt(s.trim());    
        }
        catch(NumberFormatException e){
            Debug.error(e);            
        }
        return i;
    }


    /**
     * Centers the component
     * @param comp the Component to be centered
     */
    public static void center(Component comp){
        GraphicsConfiguration gc = comp.getGraphicsConfiguration();
        Rectangle bounds = gc.getBounds();
        int x = (int) (bounds.getWidth() - comp.getWidth()) / 2;
        int y = (int) (bounds.getHeight() - comp.getHeight()) / 2;
        comp.setLocation(x, y);
    }

    /**
     * Finds the absolute path to a file from the classpath 
     * @param object The object which classloader is used to retrieve the resource
     * @param path The relative path of the file
     * @return A String with the absolute path.
     * @throws NullPointerException If the file is not in the classpath
     */
    public static String getAbsolutePath(Class theClass, String path) throws NullPointerException{
        URL url = theClass.getClassLoader().getResource(path);
        if(url == null){
            throw new NullPointerException("the file " + path + " does not exist in the classpath");            
        }
        return URLDecoder.decode(url.getFile());
    }

    public static boolean isChildOf(Container possibleParent, Component comp){
    	
    	boolean result = false;
    	if(possibleParent != null && comp != null){
	    	Component parent = comp.getParent();
	    	if(parent == possibleParent){
	    		result = true;
	    	}
	    	else if(parent != null){
	    		result = isChildOf(possibleParent, parent);
	    	}
    	}
    	return result;
    }
}
