/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.havi.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;

import net.beiker.xletview.media.ScreenContainer;
import net.beiker.xletview.util.Debug;

import org.havi.ui.event.HBackgroundImageListener;

/**
 * 
 * 
 * @author Martin Sveden
 * @statuscode 2
 * @comment not quite finished
 */
public class HBackgroundImage2 extends Component{

    private Image image;
    private int width;
    private int height;

	public HBackgroundImage2(String filename){
        //Debug.write(this, "fix this and classes involved with iframe stuff");
        if(filename.indexOf(".mpg") > -1){
            Debug.info(this, "Display of .mpg is not yet supported.\n" +
                "A workaround for now is to use a .jpg with the same name.");
            filename = filename.substring(0, filename.length() - 4) + ".jpg";
        }
        Debug.write(this, filename);  
        //filename = "amici/container/img/person_4.jpg";
        image = loadImage(filename, null, this);
        net.beiker.xletview.media.BackgroundLayer bl = net.beiker.xletview.media.BackgroundLayer.getInstance();
        bl.setBgImage(image);
        bl.repaint();
        //setBounds(0, 0, bl.getWidth(), bl.getHeight());
//        Component imgComponent = (Component) this;
//        bl.add(imgComponent);
//        setVisible(true);
//        repaint();
        //Debug.write(this, "constructor");        
	}

	public HBackgroundImage2(byte pixels[]){
	}

	public HBackgroundImage2(URL contents){
	}

	public void load(HBackgroundImageListener hbackgroundimagelistener){
	}

	public int getHeight(){
		return super.getHeight();
	}

	public int getWidth(){
		return super.getWidth();
	}

	public void flush(){
	}
    
    private Image loadImage(String name, URL url, Component component){
        MediaTracker mediatracker = new MediaTracker(component);
        xjava.awt.Toolkit toolkit = xjava.awt.Toolkit.getDefaultToolkit();
        Image image = null;
        if(name != null){
            
            // to see if the path is correct
            try{
                xjava.io.File f = new xjava.io.File(name);
                xjava.io.FileReader fr = new xjava.io.FileReader(f);
                image = toolkit.getImage(name);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(url != null){
            image = toolkit.getImage(url);
        }

        mediatracker.addImage(image, 0);
        try{
            mediatracker.waitForID(0);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return image;
    }    

    public void setBounds(HScreenRectangle r){
        int x = (int) r.x;
        int y = (int) r.y;
        int width = (int) r.width;
        int height = (int) r.height;
        super.setBounds(x, y, width, height);
    }

//    public void paint(Graphics g){        
//        if(image != null){
//            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//        }
//        
//    }
    
}
