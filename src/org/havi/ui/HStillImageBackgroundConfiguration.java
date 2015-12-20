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

/**
 * 
 * 
 * @author Martin Sveden
 * @statuscode 2
 * @comment partially implemented
 */
public class HStillImageBackgroundConfiguration extends HBackgroundConfiguration{

    protected HStillImageBackgroundConfiguration(){
    }

    public void displayImage(HBackgroundImage image) throws java.io.IOException,
           org.havi.ui.HPermissionDeniedException,
           org.havi.ui.HConfigurationException{
//               HScreenRectangle r = new HScreenRectangle(0f, 0f, 720f, 480f);
//               displayImage(image, r);
    }

    public void displayImage(HBackgroundImage image, HScreenRectangle r) throws java.io.IOException,
           org.havi.ui.HPermissionDeniedException,
           org.havi.ui.HConfigurationException{
/*               net.beiker.xletview.media.BackgroundLayer bl = net.beiker.xletview.media.BackgroundLayer.getInstance();
               
            //   Component imgComponent = (Component) image;               
               bl.removeAll(); 
              // if(bl.getComponent(0) != imgComponent){
                   bl.add(image);
                   	
               //}               
               image.setBounds(r);
               image.setVisible(true);*/
    }

    public void setColor(java.awt.Color color) throws org.havi.ui.HPermissionDeniedException,
           org.havi.ui.HConfigurationException{
    }
}
