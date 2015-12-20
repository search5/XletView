/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

 */

package net.beiker.xletview.media;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;

import net.beiker.xletview.util.Debug;
import xjavax.tv.media.AWTVideoSize;
import xjavax.tv.media.AWTVideoSizeControlImpl;

public class MediaPlayer implements ControllerListener, PropertyChangeListener {

    private static MediaPlayer THE_INSTANCE;

    private Player player;

    private Component visualComponent;

    private boolean playing;

    private Media media;

    private MediaPlayer() {
	// Media media = new Media(Settings.getProperty("path.home") +
	// Settings.getProperty("file.defaultbg"));
	// setMedia(media);
    }

    public static MediaPlayer getInstance() {
	if (THE_INSTANCE == null) {
	    THE_INSTANCE = new MediaPlayer();
	}
	return THE_INSTANCE;
    }

    void play() {
	if (player != null) {
	    playing = true;
	    player.realize();
	    player.start();
	    ScreenContainer.getInstance().repaint();
	}
    }

    public void stop() {
	if (player != null) {
	    player.stop();
	    player.close();
	    playing = false;
	}
    }

    public boolean isPlaying() {
	return playing;
    }

    /**
     * 
     * @param media
     *            the media to be played This method does nothing if @param
     *            media is already playing
     */
    void setMedia(Media media) {
	if (this.media != media) {

	    // stop
	    stop();

	    // set the media to be played
	    this.media = media;
	    if (media.getType() == Media.TYPE_IMAGE) {
		createImagePlayer(media.getPath());
	    } else if (media.getType() == Media.TYPE_VIDEO) {
		try {
		    createVideoPlayer(new URL("file:/" + media.getPath()));
		    // createVideoPlayer(media.getPath());
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		}
	    } else if (media.getType() == Media.TYPE_INVALID) {
		Debug.write(this, "media type is invalid");
	    }

	    // play
	    play();

	    // do other stuff

	}
    }

    private void createImagePlayer(String imagePath) {
	if (imagePath != null) {
	    ;
	    player = new ImagePlayer(imagePath);
	    visualComponent = player.getVisualComponent();
	    // setSize(AWTVideoSizeControlImpl.getInstance().getSize());
	    VideoLayer2.getInstance().removeAll();
	    VideoLayer2.getInstance().add(visualComponent);
	    // VideoLayer2.getInstance().validate();
	    // VideoLayer2.getInstance().repaint();
	    setSize(AWTVideoSizeControlImpl.getInstance().getSize());
	    // TV.getInstance().repaint();
	}
	play();
    }

    private void createVideoPlayer(URL videoUrl) {
	if (videoUrl != null) {
	    MediaLocator mediaLocator = null;
	    try {
		mediaLocator = new MediaLocator(videoUrl);
		Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));
		player = Manager.createPlayer(mediaLocator);
		player.addControllerListener(this);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	play();
    }

    public void controllerUpdate(ControllerEvent event) {

	ScreenContainer.getInstance().repaint();
	if (event instanceof RealizeCompleteEvent) {
	    Component comp;
	    if ((comp = player.getVisualComponent()) != null) {
		visualComponent = player.getVisualComponent();
		Debug.write(this, VideoLayer2.getInstance() + "");
		VideoLayer2.getInstance().removeAll();
		VideoLayer2.getInstance().add(comp);
		VideoLayer2.getInstance().validate();
		VideoLayer2.getInstance().repaint();
		setSize(AWTVideoSizeControlImpl.getInstance().getSize());
	    }
	} else if (event instanceof EndOfMediaEvent) {
	    // We've reached the end of the media; rewind and
	    // start over
	    player.setMediaTime(new Time(0));
	    player.start();
	}
    }

    public void setSize(AWTVideoSize size) {
	if (visualComponent != null) {
	    int videoX = size.getDestination().x;
	    int videoY = size.getDestination().y;
	    int videoWidth = size.getDestination().width;
	    int videoHeight = size.getDestination().height;
	    visualComponent.setBounds(videoX, videoY, videoWidth, videoHeight);
	    Debug.write(this, "setSize " + size);
	} else {
	    int videoX = size.getDestination().x;
	    int videoY = size.getDestination().y;
	    int videoWidth = size.getDestination().width;
	    int videoHeight = size.getDestination().height;
	    Debug.write(this, "MoviesetSize " + size);
	}
    }

    public Component getVisualComponent() {
	return visualComponent;
    }

    @Override
    public void propertyChange(PropertyChangeEvent pe) {

    }

}
