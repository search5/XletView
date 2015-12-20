/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Svedén
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.dvb.application;

public final class AppsControlPermission extends java.security.BasicPermission {

	public AppsControlPermission() {
		super("");
	}

	public AppsControlPermission(String name, String actions){
		super(name);
	}

	public String getActions() { return null;}

	public boolean implies(java.security.Permission permission) {return true;}

	public boolean equals(Object obj) { return true;}

	public int hashCode() { return 0 ;}


}
