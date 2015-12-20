/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.util;

import java.io.*;

/**
 * @author Martin Sveden
 *
 */
public class Log extends OutputStream {
    
    /*
        Based on code by Greg Travis. You can find the original code
        and how to write a custom console here:
        http://www.developer.com/tech/article.php/630821
    */
    
    
    // we keep a buffer around for creating 1-char strings, to
    // avoid the potential horror of thousands of array allocations
    // per second
    private byte littlebuf[] = new byte[1];

//    private FileWriter fileWriter;
//    private FileReader fileReader;        
    private File file;

    public Log(File file){
        this.file = file;
        
        
        // create file if it doesn't exist
        if(!file.exists()){
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*
         * make sure it's empty, so only keeps the latest outpu
         * otherwise the file will get slow things down
         */  
        else{
            FileWriter fr;
            try {
                fr = new FileWriter(file);
                fr.write("");            
                fr.close();
            }
            catch (IOException e) {               
                e.printStackTrace();
            }

        }
        
//        try {
//            fileWriter = new FileWriter(file);
//            fileReader = new FileReader(file);
//            hookStandards();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }        
    }
    
    


    // Redirect output to the console
    public void write( int b ) throws IOException {
      littlebuf[0] = (byte)b;
      String s = new String( littlebuf, 0, 1 );
      print( s );
    }

    // Redirect output to the console
    public void write( byte b[] ) throws IOException {
      String s = new String( b, 0, b.length );
      print( s );
    }

    // Redirect output to the console
    public void write( byte b[], int off, int len ) throws IOException {
      String s = new String( b, off, len );
      print( s );
    }

    // nothing need be done here
    public void flush() throws IOException {
    }

    // nothing need be done here
    public void close() throws IOException {
    }
    
//    private void print(String s){
//        if(fileReader != null && fileWriter != null){
//            
//            try {
//                fileWriter.write(s);
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }            
//            //fr.close();
//        }
//
//    }

    public static void message(String s){
	
    }
    private void print(String s){
        try {
            FileInputStream fStream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fStream);
            String content = "";            
            while (in.available() != 0){                
                content += in.readLine() + System.getProperty("line.separator");
            }
            fStream.close();            
            in.close();
            
          
            
            FileWriter fr = new FileWriter(file);            
            String merged = content + s;
            
            fr.write(merged);            
            fr.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void hookStandards(){        
        PrintStream out = new PrintStream( this );
        System.setOut( out );
        System.setErr( out );
    }

}
