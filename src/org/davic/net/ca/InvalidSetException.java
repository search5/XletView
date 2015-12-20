/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.davic.net.ca;

public class InvalidSetException extends CAException {

	public InvalidSetException() {
		super();
	}

	public InvalidSetException(String reason) {
		super(reason);
	}
}
