/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.media;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;

import javax.media.Control;
import javax.media.Controller;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Duration;
import javax.media.EndOfMediaEvent;
import javax.media.GainChangeEvent;
import javax.media.GainChangeListener;
import javax.media.GainControl;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.media.TimeBase;
import xjavax.tv.locator.Locator;
import xjavax.tv.media.AWTVideoSize;
import xjavax.tv.service.selection.ServiceContentHandler;
import xjavax.tv.service.selection.ServiceMediaHandler;

import net.beiker.xletview.ui.Img;
import net.beiker.xletview.ui.XContainer;

import net.beiker.xletview.util.Debug;

/**
 * Displayes the video, or an image symbolizing video
 */
public class VideoLayer2 extends XContainer {

    private static VideoLayer2 THE_INSTANCE;
    
    private VideoLayer2() {        
    }

    public static VideoLayer2 getInstance() {
        if (THE_INSTANCE == null) {
            THE_INSTANCE = new VideoLayer2();
            //THE_INSTANCE.setLayout(null);
            THE_INSTANCE.setLayout(null);
        }
        return THE_INSTANCE;
    }

    /**
     * Overrides the super implementation so
     * it's only possible to add one Component
     * 
     */
    public Component add(Component comp){
        Component added = null;
        if(getComponentCount() < 1){
            added = super.add(comp);
        }
        else{
            try {
                throw new Exception("Component already added, can only contain one component");
            }
            catch (Exception e) {
                e.printStackTrace();
                //System.err.println(e.getMessage());
            }
        }
        return added;
    }
    
    public Component add(Component comp, int index){
        return add(comp);
    }
    
    public Component add(String name, Component comp){
        return add(comp);
    }

    //    public void setSize(AWTVideoSize size){
    //        int videoX          = size.getDestination().x;
    //        int videoY          = size.getDestination().y;
    //        int videoWidth      = size.getDestination().width;
    //        int videoHeight     = size.getDestination().height;
    //        setBounds(videoX, videoY, videoWidth, videoHeight);  
    //        validate();      
    //    }

    //    public void paint(Graphics g){
    //    	Debug.write(this, "paint");
    //    	Debug.write(this, "this = " + this);
    //        super.paint(g);
    //    }

    public void paint(Graphics g) {
        for (int i = 0; i < getComponentCount(); i++) {
            getComponent(i).paint(g);
        }
        super.paint(g);
    }
}
