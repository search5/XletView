/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.dvb.application;

import java.util.*;

public interface AppStateChangeEventListener extends EventListener{
    public void stateChange(AppStateChangeEvent evt);
}
