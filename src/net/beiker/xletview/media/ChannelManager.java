/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/
package net.beiker.xletview.media;

import java.util.Vector;
import java.io.File;
import java.io.FileInputStream;

import net.beiker.xletview.app.AppGroup;
import net.beiker.xletview.util.*;

import net.n3.nanoxml.XMLWriter;
import net.n3.nanoxml.IXMLElement;
import net.n3.nanoxml.IXMLParser;
import net.n3.nanoxml.IXMLReader;
import net.n3.nanoxml.StdXMLReader;
import net.n3.nanoxml.XMLParserFactory;


/**
 * @author Martin Sveden
 */
public class ChannelManager {

    private static ChannelManager THE_INSTANCE;
    
    private int currentChannelNumber;
    private Vector channels;
    
    private ChannelManager(){        
        channels = new Vector();

        parse();
        
        // if there is no channels, add the default one
        if(channels.size() == 0){
            Media media = new Media(Util.getAbsolutePath(ChannelManager.class, Settings.getProperty("file.defaultbg")) );
            Channel channel = new Channel("XleTView Channel", media);
            channels.add(channel);
        }
        
        currentChannelNumber = 0;
    }
    
    public static ChannelManager getInstance(){
        if(THE_INSTANCE == null){
            THE_INSTANCE = new ChannelManager();
        }
        return THE_INSTANCE;
    }
    
    public void setChannel(int channel){        
        // check if it is a valid channel number        
        if(isValidNumber(channel)){
            Debug.write(this, "current channel is now " + ((Channel)channels.get(channel)).getName());
            currentChannelNumber = channel;
            Media media = ((Channel)channels.get(channel)).getMedia();
            MediaPlayer.getInstance().setMedia(media);            
        }        
        else{
            Debug.write(this, "not a valid channel number");
        }
    }
    
    public void nextChannel(){
        currentChannelNumber++;
        if(currentChannelNumber == channels.size()){
            currentChannelNumber = 0;
        }
        setChannel(currentChannelNumber);
    }
    
    public void previousChannel(){
        currentChannelNumber--;
        if(currentChannelNumber < 0){
            currentChannelNumber = channels.size() - 1;
        }
        setChannel(currentChannelNumber);        
    }
    
    /*
     * / checks if it is a valid channel number
     */
    private boolean isValidNumber(int i){
        boolean result = false;
        if(i > -1 && i < channels.size()){
            result = true;
        }        
        return result;
    }
    
    public int getCurrentChannel(){
        return currentChannelNumber;
    }
    
    /** 
     * @return a list of avaliable channels, convenient for debugging
     */
    public String getChannelList(){
        String s = "";
        for(int i = 0; i < channels.size(); i++){            
            Channel ch = (Channel) channels.get(i);
            s += "name=" + ch.getName() + ", media=" + ch.getMedia();
        }
        if(s.length() == 0){
            s = "no channels avaliable";
        }
        return s;
    }

    public void parse(){
        IXMLElement xml = null;
        try{
            IXMLParser parser = XMLParserFactory.createDefaultXMLParser();
            FileInputStream in = new FileInputStream(new File(Util.getAbsolutePath(ChannelManager.class, "config/channels.xml")));
            IXMLReader reader = new StdXMLReader(in);
            parser.setReader(reader);
            xml = (IXMLElement) parser.parse();  
            
            Vector v = xml.getChildren();
            for(int i = 0; i < v.size(); i++){
                IXMLElement element = (IXMLElement) v.elementAt(i);
                String name = "";
                String path = "";
                try {
                    name = ((IXMLElement) element.getChildrenNamed("NAME").get(0)).getContent();
                    path = ((IXMLElement) element.getChildrenNamed("MEDIA").get(0)).getContent();
                    
                    if(name != null && path != null && name.length() > 0 && path.length() > 0){
                        Media media = new Media(path);
                        Channel channel = new Channel(name, media);
                        channels.add(channel);
                    }
                    
                }
                catch (Exception e) {                    
                    Debug.error(e);
                }
                Debug.write(this, element.getName());
            }
                                      
        }
        catch(Exception e){
            Debug.error(e);            
        }
    }

}
