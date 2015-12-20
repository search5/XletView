/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Svedén
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.dvb.ui;

import org.havi.ui.HVisible;
import java.awt.Insets;

/**
 * 
 * 
 * @author Martin Sveden
 * @statuscode 4
 * @comment rendering and the listener stuff is still to implement
 */
public class DVBTextLayoutManager implements org.havi.ui.HTextLayoutManager {
    
	public static final int HORIZONTAL_START_ALIGN = 1;
    public static final int HORIZONTAL_END_ALIGN = 2;
    public static final int HORIZONTAL_CENTER = 3;
    public static final int VERTICAL_START_ALIGN = 4;
    public static final int VERTICAL_END_ALIGN = 5;
    public static final int VERTICAL_CENTER = 6;
    public static final int LINE_ORIENTATION_HORIZONTAL = 10;
    public static final int LINE_ORIENTATION_VERTICAL = 11;
    public static final int START_CORNER_UPPER_LEFT = 20;
    public static final int START_CORNER_UPPER_RIGHT = 21;
    public static final int START_CORNER_LOWER_LEFT = 22;
    public static final int START_CORNER_LOWER_RIGHT = 23;
    
    private int horizontalAlign;
    private int verticalAlign;
    private int lineOrientation;
    private int startCorner;
    private boolean textWrap;
    private int lineSpace;
    private int letterSpace;
    private int horizontalTabSpace;
    private Insets insets;
    
	public  DVBTextLayoutManager() {
    }

    public  DVBTextLayoutManager(int horizontalAlign, 
				 int verticalAlign,
				 int lineOrientation,
				 int startCorner,
				 boolean wrap,
				 int linespace,
				 int letterspace,
				 int horizontalTabSpace) {
    }
    
    public void setHorizontalAlign(int horizontalAlign) {
    	this.horizontalAlign = horizontalAlign;
    }
    
    public void setVerticalAlign(int verticalAlign) {
    	this.verticalAlign = verticalAlign;
    }

    public void setLineOrientation(int lineOrientation) {
    	this.lineOrientation = lineOrientation;
    }

    public void setStartCorner(int startCorner) {
    	this.startCorner = startCorner;
    }

    public void setTextWrapping(boolean wrap) {
    	this.textWrap = wrap;
    }

	public void setLineSpace(int lineSpace) {
		this.lineSpace = lineSpace;
    }

    public void setLetterSpace(int letterSpace) {
    	this.letterSpace = letterSpace;
    }

    public void setHorizontalTabSpacing(int horizontalTabSpace) {
    	this.horizontalTabSpace = horizontalTabSpace;
    }

    public int getHorizontalAlign() {
		return horizontalAlign;
    }

    public int getVerticalAlign() {
		return verticalAlign;
    }

    public int getLineOrientation() {
		return lineOrientation;
    }

    public int getStartCorner() {
		return startCorner;
    }

    public boolean getTextWrapping() {
		return textWrap;
    }

    public int getLineSpace() {
		return lineSpace;
    }

    public int getLetterSpace() {
		return letterSpace;
    }

    public int getHorizontalTabSpacing() {
		return horizontalTabSpace;
    }

    public void setInsets(Insets insets) {
    	this.insets = insets;
    }

	public Insets getInsets() {
        return insets;
    }  

    public void addTextOverflowListener(TextOverflowListener listener) { 
    }

	public void removeTextOverflowListener(TextOverflowListener listener) { 
    }

	public void render(String markedUpString, java.awt.Graphics g, HVisible v, java.awt.Insets insets){
	}
	
}









