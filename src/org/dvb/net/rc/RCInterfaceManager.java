/*
 This file is part of XleTView
 Copyright (C) 2003 Martin Sved�n
 This is free software, and you are
 welcome to redistribute it under
 certain conditions;
 See LICENSE document for details.


 */

package org.dvb.net.rc;

import java.net.*;

import org.davic.resources.*;
import java.util.Vector;

/**
 * 
 *
 * @version  7.11.03
 * @author 	 Bengt Skogvall
 * @statuscode 4
 */
public class RCInterfaceManager implements org.davic.resources.ResourceServer {

    private static RCInterfaceManager THE_INSTANCE;
    private RCInterface[] rcInterfaces;
    private Vector resourceStatusEventObjects;

    private RCInterfaceManager() {
        System.out.println("XleTView: instanciate RCInterfaceManager");
        
        /*
         * One interface for every kind to not break
         * any box specific Xlet code.
         */
        
        rcInterfaces = new RCInterface[7];
        rcInterfaces[0] = new ConnectionRCInterface();
        rcInterfaces[0].setType(RCInterface.TYPE_PSTN);
        rcInterfaces[1] = new ConnectionRCInterface();
        rcInterfaces[1].setType(RCInterface.TYPE_ISDN);
        rcInterfaces[2] = new ConnectionRCInterface();
        rcInterfaces[2].setType(RCInterface.TYPE_DECT);
        rcInterfaces[3] = new ConnectionRCInterface();
        rcInterfaces[3].setType(RCInterface.TYPE_CATV);
        rcInterfaces[4] = new ConnectionRCInterface();
        rcInterfaces[4].setType(RCInterface.TYPE_LMDS);
        rcInterfaces[5] = new ConnectionRCInterface();
        rcInterfaces[5].setType(RCInterface.TYPE_MATV);
        rcInterfaces[6] = new ConnectionRCInterface();
        rcInterfaces[6].setType(RCInterface.TYPE_RCS);  
        resourceStatusEventObjects = new Vector();
    }

    public static RCInterfaceManager getInstance() {
        System.out.println("XleTView: RCInterfaceManager getInstance");
        if (THE_INSTANCE == null) {
            THE_INSTANCE = new RCInterfaceManager();
        }
        return THE_INSTANCE;
    }

    public RCInterface[] getInterfaces() {
        System.out.println("XleTView: getInterfaces");
        return rcInterfaces;
    }

    public RCInterface getInterface(InetAddress addr) {
        return rcInterfaces[0];
    }

    public RCInterface getInterface(Socket s) {
        return rcInterfaces[0];
    }

    public RCInterface getInterface(URLConnection u) {
        return rcInterfaces[0];
    }

    public void addResourceStatusEventListener(ResourceStatusListener listener) {
        if (!resourceStatusEventObjects.contains(listener)) {
            resourceStatusEventObjects.add(listener);
        }
    }

    public void removeResourceStatusEventListener(ResourceStatusListener listener) {
        resourceStatusEventObjects.remove(listener);
    }

    protected void fireResorceStatusChanged(ResourceStatusEvent po) {
        // backwards so we get the last added
        for (int i = resourceStatusEventObjects.size() - 1; i > -1; i--) {
            ResourceStatusListener li = (ResourceStatusListener) resourceStatusEventObjects.get(i);
            li.statusChanged(po);
        }
    }
}