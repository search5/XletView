/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.davic.net.ca;

/**
 * 
 * 
 * @author Martin Sveden
 * @statuscode 4
 */
public class ModuleUnavailableException extends CAException {

	public ModuleUnavailableException() {
		super();
	}

	public ModuleUnavailableException(String reason) {
		super(reason);
	}

}
