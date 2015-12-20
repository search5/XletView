/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Svedén
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.dvb.application ;

public class CurrentServiceFilter extends AppsDatabaseFilter {

	public CurrentServiceFilter() {
		super();
	}

	public boolean accept(AppID appid) { 
		return false;
	}

}
