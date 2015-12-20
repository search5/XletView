/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

 */

package org.dvb.io.ixc;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class IxcRegistry {

    private static Hashtable ixcTable = new Hashtable();

    private IxcRegistry() {
    }

    public static Remote lookup(xjavax.tv.xlet.XletContext xc, String path) throws NotBoundException, RemoteException {
	Remote remote = (Remote) ixcTable.get(parsePath(path));
	if (remote == null) 
	    throw new NotBoundException();
	else
	    return remote;
    }

    public static void bind(xjavax.tv.xlet.XletContext xc, String name, Remote obj) throws AlreadyBoundException {
	ixcTable.put(name, obj);
    }

    public static void unbind(xjavax.tv.xlet.XletContext xc, String name) throws NotBoundException {
    }

    public static void rebind(xjavax.tv.xlet.XletContext xc, String name, Remote obj) {
    }

    public static String[] list(xjavax.tv.xlet.XletContext xc) {
	return null;
    }

    private static String parsePath(String path) {
	StringTokenizer stringTokenizer = new StringTokenizer(path);
	String name = null;
	while (stringTokenizer.hasMoreElements()) {
	    name = stringTokenizer.nextToken("/");
	}
	return name;
    }
}
