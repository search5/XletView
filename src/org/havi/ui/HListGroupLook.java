/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Svedén
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.havi.ui;

import java.awt.Dimension;

public class HListGroupLook implements HAdjustableLook{
    
	public HListGroupLook(){
    }
    
    public void showLook(java.awt.Graphics g, HVisible visible, int state){
        return;
    }

    public void widgetChanged (HVisible visible, HChangeData[] changes){
        return;
    }

    public Dimension getMinimumSize(HVisible visible){
        return(null);
    }

    public Dimension getPreferredSize(HVisible visible){
        return(null);
    }

    public Dimension getMaximumSize(HVisible visible){
        return(null);
    }

    public boolean isOpaque(HVisible visible){
        return(false);
    }

    public java.awt.Insets getInsets(HVisible visible){
        return(null);
    }

    public int hitTest(HOrientable component, java.awt.Point pt){
        return(0);
    }

    public java.lang.Integer getValue(HOrientable component, java.awt.Point pt){
        return(null);
    }

    public java.awt.Insets getElementInsets(){
		return (null);
    }
    
    public int getNumVisible(HVisible visible){
		return (0);
    }
}
