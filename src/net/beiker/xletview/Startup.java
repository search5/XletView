/*
 * 
 * This file is part of XleTView Copyright (C) 2003 Martin Sveden
 * 
 * This is free software, and you are welcome to redistribute it under certain
 * conditions;
 * 
 * See LICENSE document for details.
 *  
 */

package net.beiker.xletview;

import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.beiker.xletview.util.CommandLine;
import net.beiker.xletview.util.Constants;
import net.beiker.xletview.util.Settings;
import net.beiker.xletview.util.Util;
import net.beiker.xletview.window.SplashWindow;
import net.beiker.xletview.window.TvWindow;
import net.beiker.xletview.xlet.XletManager;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;

public class Startup {

    SplashWindow splash;

    protected int minSplashMs = 2000;

    protected long start;

    protected long end;

    public Startup(String[] args) {
/*	try {
	    UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
	    UIManager.getLookAndFeelDefaults().put("ClassLoader", this.getClass().getClassLoader());
	} catch (UnsupportedLookAndFeelException exception) {
	    exception.printStackTrace();
	}
*/
	String xPath = "";
	String xName = "";

	int command = CommandLine.check(args);
	if (command == CommandLine.XLET_IS_SET) {
	    xPath = CommandLine.getXletPath();
	    xName = CommandLine.getXletName();
	}

	// create splash
	createSplash();

	// do the following on the gui thread
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		showSplash();
		start = System.currentTimeMillis();
	    }
	});

	// Debug.write(this, "xPath=" + xPath + ", xName=" + xName);

	splash.setMessage("Setting system variables...");

	setProperties();

	splash.setMessage("Initializing windows...");

	final TvWindow tvWin = new TvWindow();

	if (xPath.length() > 0 && xName.length() > 0) {
	    splash.setMessage("Setting xlet from command line...");
	    XletManager.getInstance().setXlet(xPath, xName);
	}

	splash.setMessage("Loading finished, starting...");

	// show prog and hide splash
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		end = System.currentTimeMillis();
		long diff = minSplashMs - (end - start);
		if (diff > 0) {
		    try {
			Thread.sleep(diff);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		// Debug.write(this, "total startup time=" +
		// (System.currentTimeMillis() - start) );
		tvWin.show();
		// ConsoleWindow.getInstance();
		// if
		// (Settings.getProperty("console.show").equalsIgnoreCase("true"))
		// {
		// ConsoleWindow.getInstance().show();
		// }
		hideSplash();
	    }
	});

    }

    protected void createSplash() {
	splash = new SplashWindow();
    }

    protected void showSplash() {
	splash.show();
    }

    protected void hideSplash() {
	splash.hideSplash();
	splash = null;
    }

    private void setProperties() {
	try {
	    System.out.println("setting properties...");
	    String settingsPath = Util.getAbsolutePath(Startup.class, Constants.PATH_SETTINGS);
	    File settingsFile = new File(settingsPath);
	    Settings.load(settingsFile);
	    String userSettingsPath = Util.getAbsolutePath(Startup.class, Settings.getProperty("user.settings"));
	    File userSettingsFile = new File (userSettingsPath);
	    Settings.loadUserFile(userSettingsFile);
	   
	    Settings.setProperty("path.home", Util.normalizePath(new File("").getAbsolutePath() + File.separator));
	    Settings.setProperty("file.settings", Util.normalizePath(Settings.getProperty("file.settings")));
	    Settings.setProperty("file.defaultbg", Util.normalizePath(Settings.getProperty("file.defaultbg")));
	    Settings.setProperty("user.settings", Util.normalizePath(Settings.getProperty("user.settings")));
	    
	    
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(0);
	}
	try {
	    // set Font size offest for Xlets
	    String s = Settings.getProperty("font.sizeoffset");
	    xjava.awt.Font.setOffset(Integer.parseInt(s));
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	}
    }
}
