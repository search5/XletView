/*

\ This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

 */

package org.dvb.user;

import java.io.IOException;
import java.util.Hashtable;

public class UserPreferenceManager {

    private Hashtable ht = null;

    private static final UserPreferenceManager instance = new UserPreferenceManager();

    private String upName[] = { "VOD History" , "Reminder Alarm Before", "PPV Password Confirmation"};

    private UserPreferenceManager() {
	ht = new Hashtable();
	for (int i = 0; i < upName.length; i++) {
	    String s = upName[i];
	    ht.put(s, new String[] {"0ff"});
	}
    }

    public static UserPreferenceManager getInstance() {
	return instance;
    }

    public void read(Preference p) {
	System.out.println("p.getName() : " + p.getName());
	Object obj = ht.get(p.getName());
	if(obj != null) {
	    p.removeAll();
	    p.add((String[]) obj);
	}
    }

    public void read(Preference p, Facility facility) {
    }

    public void write(Preference p) throws UnsupportedPreferenceException, IOException {
	System.out.println(p.getName());
	System.out.println(p.getFavourites().length);
	ht.put(p.getName(), p.getFavourites());
    }

    public void addUserPreferenceChangeListener(UserPreferenceChangeListener l) {
    }

    public void removeUserPreferenceChangeListener(UserPreferenceChangeListener l) {
    }

}
