/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.ui;

import java.awt.*;

import net.beiker.xletview.util.Debug;
import net.beiker.xletview.util.Constants;
import net.beiker.xletview.util.Util;
import net.beiker.xletview.util.Settings;

/**
 * This layout puts the children in the center.
 * @author Martin Sveden
 *
 */
public class CenterLayout implements LayoutManager{

    public void addLayoutComponent(String s, Component c) {
        // do nothing        
    }

    public void removeLayoutComponent(Component c) {
        // do nothing   
    }

    public Dimension preferredLayoutSize(Container c) {
        return new Dimension(Util.parseInt(Settings.getProperty("tv.screenwidth")), Util.parseInt(Settings.getProperty("tv.screenheight")));
    }    

    public Dimension minimumLayoutSize(Container c) {
        return new Dimension(Util.parseInt(Settings.getProperty("tv.screenwidth")), Util.parseInt(Settings.getProperty("tv.screenheight")));
    }

    public void layoutContainer(Container parent) {
        Rectangle bounds = parent.getBounds();    
        for(int i = 0; i < parent.getComponentCount(); i++){
            Component comp = parent.getComponent(i);    
            int x = (int) (bounds.getWidth() - comp.getWidth()) / 2;
            int y = (int) (bounds.getHeight() - comp.getHeight()) / 2;            
            comp.setLocation(x, y);
    
        }        
    }

}
