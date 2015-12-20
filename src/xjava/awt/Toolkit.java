/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package xjava.awt;

import java.awt.*;
import java.util.Properties;
import java.awt.peer.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.datatransfer.Clipboard;
import java.awt.event.AWTEventListener;
import java.net.URL;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import net.beiker.xletview.util.Debug;

/**
 * Used to replace java.awt.Toolkit
 * 
 * @author Martin Sveden
 */
public class Toolkit {

    private static Toolkit thisToolkit;

    private Toolkit() {
    }

    /**
     * @see java.awt.Toolkit#getScreenSize()
     */
    public Dimension getScreenSize() {
        return java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     * @see java.awt.Toolkit#getScreenResolution()
     */
    public int getScreenResolution() {
        return java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
    }

    /**
     * @see java.awt.Toolkit#getColorModel()
     */
    public ColorModel getColorModel() {
        return java.awt.Toolkit.getDefaultToolkit().getColorModel();
    }

    /**
     * @see java.awt.Toolkit#getFontList()
     */
    public String[] getFontList() {
        return java.awt.Toolkit.getDefaultToolkit().getFontList();
    }

    /**
     * @see java.awt.Toolkit#getFontMetrics(java.awt.Font font)
     */
    public FontMetrics getFontMetrics(java.awt.Font font) {
        return java.awt.Toolkit.getDefaultToolkit().getFontMetrics(font);
    }

    /**
     * @see java.awt.Toolkit#sync()
     */
    public void sync() {
        java.awt.Toolkit.getDefaultToolkit().sync();
    }

    /**
     * @see java.awt.Toolkit#getDefaultToolkit()
     */
    public static Toolkit getDefaultToolkit() {
        if (thisToolkit == null) {
            thisToolkit = new Toolkit();
        }
        return thisToolkit;
    }

    /**
     * Modified to find images with relative relative path with a virtual root
     * @see java.awt.Toolkit#getImage(String filename)
     */
    public Image getImage(String filename) {
        Debug.write(this, "Virtual getImage(" + xjava.io.File.getVirtualRoot() + filename + ")");
        return java.awt.Toolkit.getDefaultToolkit().getImage(xjava.io.File.getVirtualRoot() + filename);
    }

    /**
     * @see java.awt.Toolkit#getImage(URL url)
     */
    public Image getImage(URL url) {
        return java.awt.Toolkit.getDefaultToolkit().getImage(url);
    }

    /**
     * @see java.awt.Toolkit#prepareImage(Image image, int width, int height, ImageObserver observer)
     */
    public boolean prepareImage(Image image, int width, int height, ImageObserver observer) {
        return java.awt.Toolkit.getDefaultToolkit().prepareImage(image, width, height, observer);
    }

    /**
     * @see java.awt.Toolkit#checkImage(Image image, int width, int height, ImageObserver observer) {
     */
    public int checkImage(Image image, int width, int height, ImageObserver observer) {
        return java.awt.Toolkit.getDefaultToolkit().checkImage(image, width, height, observer);
    }
    
    /**
     * @see java.awt.Toolkit#gcreateImage(ImageProducer producer)
     */
    public Image createImage(ImageProducer producer) {
        return java.awt.Toolkit.getDefaultToolkit().createImage(producer);
    }

    /**
     * @see java.awt.Toolkit#createImage(byte[] imagedata)
     */
    public Image createImage(byte[] imagedata) {
        return createImage(imagedata, 0, imagedata.length);
    }
    
    /**
     * @see java.awt.Toolkit#createImage(byte[] imagedata)
     */
    public Image createImage(String filename) {
    	return java.awt.Toolkit.getDefaultToolkit().createImage(filename);
    }

    /**
     * @see java.awt.Toolkit#createImage(byte[] imagedata, int imageoffset, int imagelength)
     */
    public Image createImage(byte[] imagedata, int imageoffset, int imagelength) {
        return java.awt.Toolkit.getDefaultToolkit().createImage(imagedata, imageoffset, imagelength);
    }
    
    /**
     * @see java.awt.Toolkit#getPrintJob(Frame frame, String jobtitle, Properties props)
     */
    public PrintJob getPrintJob(Frame frame, String jobtitle, Properties props) {
        return java.awt.Toolkit.getDefaultToolkit().getPrintJob(frame, jobtitle, props);
    }
    
    /**
     * @see java.awt.Toolkit#beep()
     */
    public void beep() {
        java.awt.Toolkit.getDefaultToolkit().beep();
    }
    
    /**
     * @see java.awt.Toolkit#getSystemClipboard()
     */
    public Clipboard getSystemClipboard() {
        return java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
    }
    
    /**
     * @see java.awt.Toolkit#getMenuShortcutKeyMask()
     */
    public int getMenuShortcutKeyMask() {
        return java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    }

    /*
     * The methods below exist since 1.2(not part of MHP) but are here
     * because they must be if the JVM is newer than 1.1.8 
     */

    public static String getProperty(String key, String defaultValue) {
        return java.awt.Toolkit.getProperty(key, defaultValue);
    }

    public final EventQueue getSystemEventQueue() {
        return java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue();
    }

    public void addAWTEventListener(AWTEventListener listener, long eventMask) {
        java.awt.Toolkit.getDefaultToolkit().addAWTEventListener(listener, eventMask);
    }

    public void removeAWTEventListener(AWTEventListener listener) {
        java.awt.Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
    }

}
