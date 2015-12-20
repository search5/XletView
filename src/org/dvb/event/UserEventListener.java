/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.dvb.event ;

public interface UserEventListener extends java.util.EventListener {
    
	public void userEventReceived (UserEvent e) ;
}
