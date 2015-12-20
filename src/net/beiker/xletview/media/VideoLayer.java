/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package net.beiker.xletview.media;

import java.awt.Component;
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

import net.beiker.xletview.ui.XContainer;
import net.beiker.xletview.util.Debug;
import xjavax.tv.locator.Locator;
import xjavax.tv.media.AWTVideoSize;
import xjavax.tv.media.AWTVideoSizeControlImpl;
import xjavax.tv.service.selection.ServiceContentHandler;
import xjavax.tv.service.selection.ServiceMediaHandler;

/**
 * Displayes the video, or an image symbolizing video
 */
public class VideoLayer extends XContainer implements Player, GainControl,GainChangeListener, Controller, Control, ControllerListener, ServiceContentHandler, ServiceMediaHandler, Locator{


    private static VideoLayer THE_INSTANCE;
    public static final char FS = File.separatorChar;
    private AWTVideoSizeControlImpl awtVideoSizeControl;
    private AWTVideoSize size;
    private Player player;
    private static String backgroundPath;

    private VideoLayer(){
        awtVideoSizeControl = AWTVideoSizeControlImpl.getInstance();
        size = awtVideoSizeControl.getDefaultSize();
        Debug.write(this, "constructor");
		
    }

    public static VideoLayer getInstance(){
        if(THE_INSTANCE == null){
            THE_INSTANCE = new VideoLayer();
        }
        return THE_INSTANCE;
        //return new VideoLayer();
    }

    /*
        This path must be set before the instance is
    */
    public static void setBackgroundPath(String path){
        backgroundPath = path;
    }
        
    private void createVideoPlayer(URL videoUrl){
		if(videoUrl != null){
			MediaLocator mediaLocator = null;
			try {
				mediaLocator = new MediaLocator(videoUrl);
				Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));
				player = Manager.createPlayer(mediaLocator);
				player.addControllerListener(this);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}    	
    }
    
    private void createImagePlayer(String imagePath){
		if(imagePath != null){
            Debug.write(this, "bg image added");
			player = new ImagePlayer(imagePath);
			add(player.getVisualComponent());
//			repaint();
//			Debug.write(this, "bg image added");
		}
    }

    // implementing Player --
    public Time getDuration(){
        return Duration.DURATION_UNKNOWN;
    }


    public Component getControlPanelComponent(){
        return null;
    }

    public GainControl getGainControl(){
        return this;
    }


    public Component getVisualComponent(){
        return this;
    }

    public void removeController(Controller controller){
        // do nothing for now
    }

    public void start(){
        if(player == null && backgroundPath != null){
			URL backgroundUrl = null;
            Debug.write(this, "background media: " + backgroundPath);
            
            boolean bgIsImage = (backgroundPath.toLowerCase().indexOf(".jpg") > -1)? true:false;
            
            if(bgIsImage){
				createImagePlayer(backgroundPath);
                Debug.write(this, "\n\nplayer=" + player +"\n\n");
            }
            else{
				try {
					backgroundUrl = new URL("file:/" + backgroundPath);
					createVideoPlayer(backgroundUrl);
					Debug.write(this, "background URL: " + backgroundUrl);
				} catch (Exception e) {
					Debug.error(e);
				}            	
            }        
        }
        if(player != null){
            player.realize();
            player.start();
        }
    }
    
    public void stop(){
        if(player != null){
            player.stop();
            removeAll();
            player = null;
        }
    }

    // implementing GainControl --
    public void addGainChangeListener(GainChangeListener gainchangelistener){
        // do nothing for now
    }

    public float getDB(){
        return 0f;
    }

    public float getLevel(){
        return 0f;
    }

    public boolean getMute(){
        return false;
    }

    public void removeGainChangeListener(GainChangeListener gainchangelistener){
        // do nothing for now
    }

    public float setDB(float f){
        return 0f;
    }

    public float setLevel(float f){
        return 0f;
    }

    public void setMute(boolean flag){
        // do nothing for now
    }
    // implementing GainControl ends //

    // implementing GainChangeListener --
    public void gainChange(GainChangeEvent gainchangeevent){

    }
    // implementing GainChangeListener ends //

    // implementing Controller --
    public void addControllerListener(ControllerListener controllerlistener){
        //
    }

    public void close(){
        //
    }

    public void deallocate(){
        //
    }

    public Control getControl(String s){
        if(s.equals("javax.tv.media.AWTVideoSizeControl")){
            //System.out.println("awtVideoSizeControl -> + " + awtVideoSizeControl);
            return awtVideoSizeControl; //AWTVideoSizeControlImpl.getInstance();
        }
        else{
            return null;
        }

    }

    public Control[] getControls(){
        Control[] c = new Control[0];
        return c;
    }

    public Time getStartLatency(){
        return new Time(0l);
    }

    public int getState(){
        return -1;
    }

    public int getTargetState(){
        return -1;
    }

    public void prefetch(){
        //
    }

    public void realize(){
        if(player != null){
            player.realize();
        }
    }

    public void removeControllerListener(ControllerListener controllerlistener){
        //
    }
    // implementing Controller ends //

    // implementing Control --


    public Component getControlComponent(){
        Component c = null;
        if(player != null){
            c = player.getControlPanelComponent();
        }
        return c;
    }
    // implementing Control ends //

    // implementing ControllerListener --
    public void controllerUpdate(ControllerEvent event){

        if (event instanceof RealizeCompleteEvent) {
            Component comp;
            if ((comp = player.getVisualComponent()) != null){
                comp.setSize(this.getSize().width, this.getSize().height);
                this.add(comp);
            }            
            validate();
        }
        else if (event instanceof EndOfMediaEvent){
            // We've reached the end of the media; rewind and
            // start over
            player.setMediaTime(new Time(0));
            player.start();
        }
    }
    // implementing ControllerListener //


    public long getMediaNanoseconds(){
        return 0l;
    }
    public Time getMediaTime(){
        return null;
    }
    public float getRate(){
        return 0f;
    }
    public Time getStopTime(){
        return null;
    }
    public Time getSyncTime(){
        return null;
    }
    public TimeBase getTimeBase(){
        return null;
    }
    public Time mapToTimeBase(Time time){
        return null;
    }
    public void setMediaTime(Time time){

    }
    public float setRate(float f){
        return 0f;
    }
    public void setStopTime(Time t){

    }
    public void setTimeBase(TimeBase timeBase){

    }

    public void syncStart(Time time){

    }
    public void setSource(javax.media.protocol.DataSource dataSource){

    }
    public void addController(Controller controller){

    }
    // implementing inherited interfaces which I don't know where they come from ends //

    // implementing ServiceContentHandler --
    public Locator[] getServiceContentLocators(){
        Locator[] l = new Locator[0];
        return l;
    }
    // implementing ServiceContentHandler ends //

    // implementing Locator --
    public boolean equals(Object obj){
        return false;
    }

    public boolean hasMultipleTransformations(){
        return false;
    }

    public int hashCode(){
        return -1;
    }

    public String toExternalForm(){
        return null;
    }

    public String toString(){
        return super.toString();
    }
    // implementing Locator ends  //

    public void update(Graphics g){
        g.clearRect(0, 0, getSize().width, getSize().height);
        paint(g);
    }

    public void paint(Graphics g){
        //Debug.write(this, "player="+player);
        if(player != null && player.getVisualComponent() != null){
            Component visComp = player.getVisualComponent();
            visComp.paint(g);
        } 
    }

}



