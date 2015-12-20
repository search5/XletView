/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Svedén
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.davic.net.dvb;

import org.davic.net.InvalidLocatorException;

/**
 * 
 * 
 * @author Martin Sveden
 * @statuscode 2
 * @comment fix the constructor that takes a string so it handles arrays of component tags
 */
public class DvbLocator extends org.davic.net.Locator {

	private String textualServiceIdentifier;
	
	private int orgNetworkId;
	private int trasportStreamId;
	private int serviceId;
	
	private int eventId = -1;
	private int[] componentTags;
	
	private String filePath;
	
	public static void main(String[] args) {
		DvbNetworkBoundLocator netLoc = null;
		DvbLocator loc = null;
		try {
			loc = new DvbLocator("dvb://1.2.3.4;55");
			netLoc = new DvbNetworkBoundLocator(loc, 12);
			
		} catch (InvalidLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("toExternalForm - " + loc.toExternalForm());
		System.out.println("netLoc.getOriginalNetworkId() - " + netLoc.getNetworkId());
		
	}
	
	private DvbLocator() {
		super(null);
	}

	public DvbLocator(String url) throws InvalidLocatorException {
		super(url);
		if(url.startsWith("dvb://") == false){
			throw new InvalidLocatorException("The string does not start with 'dvb://'");
		}
		
		textualServiceIdentifier = url;
		
		String s = url.substring(6);// crop "dvb://"
		System.out.println(s);
		
		
		String[] ids = s.split("\\.");
		System.out.println("ids.length=" + ids.length);
		/* 
		 * check that the locator contains
		 * Original Network ID, Transport Stream ID and Service ID
		 */ 
		if(ids.length < 2){			
			throw new InvalidLocatorException("Original Network ID and/or Transport Stream ID");
		}
		
		/*
		 * check for event id
		 */
		String[] tmp = ids[ids.length - 1].split("\\;");		
		if(tmp.length > 1){
			ids[ids.length - 1] = tmp[0];
			
			eventId = getIntFromParameter(tmp[1]);//Integer.parseInt(tmp[1]);
						
		}
		System.out.println(ids[ids.length - 1]);
		System.out.println("eventId=" + eventId);
		
		/*
		 * check for component tag id 
		 */
		if(ids.length == 4){
			componentTags = new int[1];
			int componentTag = getIntFromParameter(ids[3]);//Integer.parseInt(ids[3]);
			System.out.println("componentTag=" + componentTag);
			componentTags[0] = componentTag; 
		
		}
		
		/*
		 * set Original Network ID, Transport Stream ID and Service ID
		 */
		
			orgNetworkId = getIntFromParameter(ids[0]);//Integer.parseInt(ids[0]);
			trasportStreamId = getIntFromParameter(ids[1]);//Integer.parseInt(ids[1]);
			serviceId = getIntFromParameter(ids[2]);//Integer.parseInt(ids[2]);
		
		
		
		
	}

	public DvbLocator(int onid, int tsid) throws InvalidLocatorException {
		super(null);
		orgNetworkId = onid;
		trasportStreamId = tsid;
	}

	public DvbLocator(int onid, int tsid, int serviceId) throws InvalidLocatorException {
		this(onid, tsid);
		this.serviceId = serviceId;
	}

	public DvbLocator(int onid, int tsid, int serviceid, int eventid) throws InvalidLocatorException {
		this(onid, tsid, serviceid);
		this.eventId = eventid;
	}

	public DvbLocator(int onid, int tsid, int serviceid, int eventid, int componenttag) throws InvalidLocatorException {
		this(onid, tsid, serviceid, eventid);
		componentTags = new int[1];
		componentTags[0] = componenttag;
		
	}

	public DvbLocator(int onid, int tsid, int serviceid, int eventid, int[] componenttags) throws InvalidLocatorException {
		this(onid, tsid, serviceid, eventid);
		componentTags = componenttags;
	}

	public DvbLocator(int onid, int tsid, int serviceid, int eventid, int[] componenttags, String filePath) throws InvalidLocatorException {
		this(onid, tsid, serviceid, eventid, componenttags);
		this.filePath = filePath;
	}


	public int getOriginalNetworkId() {
	    return orgNetworkId;
	}

	public int getTransportStreamId() {
	    return trasportStreamId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public int[] getComponentTags() {
		return componentTags;
	}

	public int getEventId() {
		return eventId;
	}
  
	public String getFilePath() {
		return filePath;
	}

    public String getTextualServiceIdentifier() {
		return textualServiceIdentifier;
	}
    
    private int getIntFromParameter(String strInt) throws InvalidLocatorException{
    	int result = -1;
    	try {
    		result = Integer.parseInt(strInt);
    	} catch (NumberFormatException e) {
    		throw new InvalidLocatorException("invalid parameter");
    	}
    	return result;
    }
    
    public String toExternalForm(){
    	String result = "dvb://" + orgNetworkId + "." + trasportStreamId + "." + serviceId;
    	if(componentTags != null && componentTags.length > 0){
    		for (int i = 0; i < componentTags.length; i++) {
				result += "." + componentTags[i];
			}
    	}
    	
    	if(eventId > -1){
    		result += ";" + eventId;
    	}
    	
    	if(filePath != null){
    		result += filePath;
    	}
    	return result;
    }
}






