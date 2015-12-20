/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package net.beiker.xletview.util;

import java.util.Hashtable;

import xjavax.tv.util.TVTimerImpl;


public class Debug{

    private static boolean debug = true;
    private static boolean info  = true;

    private static Hashtable forcedDebugClasses = new Hashtable();
    static{
    	/*
    	 * The classes specified here will have 
    	 * debug output even if the debug flag
    	 * is set to false
    	 */
    	
    	//forcedDebugClasses.put("xjavax.tv.util.TVTimerImpl$Q", "");
    	//forcedDebugClasses.put("xjavax.tv.util.TVTimerImpl$TimerThread", "");
    	//forcedDebugClasses.put("org.havi.ui.HStaticText", "");
    	
    }
    
    /**
     * Outputs a debug message to the standard output stream.
     */
    public static void write(Object obj, String str){
        if(debug || forcedDebugClasses.containsKey(obj.getClass().getName())){        
            write(obj, str, "Debug");
        }
    }

    /**
     * Outputs an info message to the standard output stream.
     */
    public static void info(Object obj, String str){
        if(info){
            write(obj, str, "INFO");
        }
    }

    public static void info(String str){
        System.err.println("[" + Constants.TITLE + "]-INFO->" + str);
    }

    private static void write(Object obj, String str, String type){
        Class objClass = obj.getClass();
        String className = objClass.getName();        
        System.out.println("[" + Constants.TITLE + "]-" + type + "->" + className + ":" + str);
    }

    public static void error(Exception e){
        e.printStackTrace();
    }

    public static void error(String str){
        System.err.println("[" + Constants.TITLE + "]-ERROR->" + str);
    }

    public static void error(Object obj, String str){
        Class objClass = obj.getClass();
        String className = objClass.getName();
        System.err.println("[" + Constants.TITLE + "]-ERROR->" + className + ":" + str);
    }

    private static String getSpaces(int stringLength, int lengthWeWant){
        String s = "";
            for(int i = 0; i < (lengthWeWant - stringLength); i++){
                s += " ";
            }
        return s;
    }

}
