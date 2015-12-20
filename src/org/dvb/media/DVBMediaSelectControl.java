/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.dvb.media;

import xjavax.tv.locator.InvalidLocatorException;
import xjavax.tv.service.selection.InvalidServiceComponentException;
import xjavax.tv.service.selection.InsufficientResourcesException;
import xjavax.tv.locator.Locator;

public interface DVBMediaSelectControl extends xjavax.tv.media.MediaSelectControl{
        
		public void selectServiceMediaComponents(Locator l) throws
                        InvalidLocatorException,InvalidServiceComponentException,
                        InsufficientResourcesException;
            
}

