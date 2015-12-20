/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.dvb.user ;

public final class GeneralPreference extends Preference {   
   
   public GeneralPreference (String name) throws IllegalArgumentException {
       super.name = name;
       super.values = new String[]{};
   }

}
