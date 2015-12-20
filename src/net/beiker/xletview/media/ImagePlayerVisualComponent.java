/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.media;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import xjavax.tv.media.AWTVideoSize;

import net.beiker.xletview.ui.XContainer;
import net.beiker.xletview.util.Debug;

public class ImagePlayerVisualComponent extends XContainer {

    private ImageIcon icon;
    private Image image;
    private String imageUrl;

    public ImagePlayerVisualComponent(String imageUrl) {
        icon = new ImageIcon(imageUrl);
        Debug.write(this, imageUrl + " - " + icon);
        repaint();
    }

    public ImagePlayerVisualComponent(URL imageUrl) {        
        icon = new ImageIcon(imageUrl);
        Debug.write(this, imageUrl + " - " + icon);
        repaint();
    }
    
    /**
     * Flushes the image data
     */
    public void destroy(){
        icon.getImage().flush();
        icon = null;
        Debug.write(this, "destroy");
    }

    public void update(Graphics g){
        paint(g);
    }

    public void paint(Graphics g) {
    	//Debug.write(this, "paint, width height = " + getWidth() + "," + getHeight());
    	//Debug.write(this, "paint, icon = " + icon);
        //Debug.write(this, "paint, this = " + this);
    	if (icon != null) {            
            int x = getX();
            int y = getY();
            //Debug.write(this, "x=" + x + ", y=" + y);
            g.drawImage(icon.getImage(), x, y, getWidth(), getHeight(), this);
            
        }
//        Debug.write(this, "paint");
//        if (icon != null) {
//            
//            AWTVideoSizeControlImpl awtVideoSizeControl = AWTVideoSizeControlImpl.getInstance();
//            AWTVideoSize size                = awtVideoSizeControl.getSize();
//            int videoX          = size.getDestination().x;
//            int videoY          = size.getDestination().y;
//            int videoWidth      = size.getDestination().width;
//            int videoHeight     = size.getDestination().height;
//
//            g.drawImage(icon.getImage(), videoX, videoY, videoWidth, videoHeight, this);
//            
//        }
    }

}
