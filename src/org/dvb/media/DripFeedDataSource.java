/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.dvb.media;

import javax.media.Time;

import java.io.IOException;

public class DripFeedDataSource extends javax.media.protocol.DataSource{
  
	public DripFeedDataSource() {}

	public void feed(byte[] clip_part) {}

	public java.lang.String getContentType() {return null;}

	public void connect() throws IOException {}

	public void disconnect() {}

	public void start() throws IOException {}

	public void stop() throws IOException {}

	public  Time getDuration() {return DURATION_UNKNOWN;};

	public Object[] getControls() {return null;}
	
	public Object getControl(String controlType) { return null;}

}

