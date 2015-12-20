/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Svedén
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.havi.ui;

import java.io.PrintStream;
import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;

import net.beiker.xletview.util.Console;
import net.beiker.xletview.util.Debug;



/**
 * 
 * @author Cristian Suazo
 * @statuscode 4 
 */
public class HSound extends java.lang.Object{
		
	private boolean isLooping;	
	private Player player;

	private MediaControllerListner playerListner;
	
	// Handles events that are created by the mediaplayer
	private class MediaControllerListner implements ControllerListener{		
		public void controllerUpdate(ControllerEvent event){
								
			if (event instanceof EndOfMediaEvent){			
				// check if the sound clip should be looped
				if (isLooping){
					player.setMediaTime(new Time(0));
					player.start();					
				}
				else
					stop();
			}		
		}
	}
    
	public HSound(){
		isLooping = false; // used for when the method loop() is called.		
		
		// create listner for the player
		playerListner = new MediaControllerListner();		
    }

    public void load(String location) throws java.io.IOException, java.lang.SecurityException{
    	load(new URL(location));
    }
    
    // SecurityException has not been implemented.
    public void load(java.net.URL contents) throws java.io.IOException, java.lang.SecurityException{
    	// create the new player 
    	try{
    		player = Manager.createPlayer(contents);
    	}
    	catch(NoPlayerException e){
    		e.printStackTrace();
    	}
    }
    
    public void set(byte data[]){
    	Debug.info(this, "Has no implementation, so calling this will not do anything." );
    }

    public void play(){
    	// add listner to the player
    	if (player != null){
    		isLooping = false;
        	player.addControllerListener(playerListner);    	
        	player.start();	
    	}    	   
    }

    public void stop(){
    	if (player != null){
    		player.removeControllerListener(playerListner);
        	player.stop();        	
    	}    	
    }

    public void loop(){
    	isLooping = true;    	
    	play();
    }

    public void dispose(){
    	if (player != null){
    		player.removeControllerListener(playerListner);        
        	player.close();
        	player.deallocate();	
    	} 
    }
    
}
