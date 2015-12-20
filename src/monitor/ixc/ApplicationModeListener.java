// $Header$

/*
 *  ApplicationModeListener.java	$Revision$ $Date$
 *
 *  Copyright (c) 2004 Alticast Corp.
 *  All rights reserved. http://www.alticast.com/
 *
 *  This software is the confidential and proprietary information of
 *  Alticast Corp. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Alticast.
 */
package monitor.ixc;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <code>ApplicationModeListener</code> concerns application mode changes of
 * enhanced bound application.
 * 
 * @author $Author$
 * @version $Revision$
 * @since Charles CW Kwak, 2007. 2. 9
 */
public interface ApplicationModeListener extends Remote {
    /**
     * Notifies when enhanced bound application wants to change its mode to
     * new mode. <br>
     * 
     * Implementation object may refuse mode changes by returning false. 
     * When any implementation object refuses, the previous implementation 
     * object which already allows will be notified again with this function 
     * with current mode.
     * 
     * @param newMode new application mode which enhanced application wants to
     *      change to
     * @return <code>true</code> allows enhanced application changes to new
     *      application mode. <code>false</code> refuses changes and stops
     *      notification, and notifies this function again to previous
     *      notifiees with current application mode
     * @throws RemoteException when any exception occurs during processing 
     *      this function
     */
    public boolean modeChanged(int newMode) throws RemoteException;
}

/*
 * $Log$
 * Revision 1.1  2007/03/01 14:34:07  cwkwak
 * [monitor_cj2] initial version
 *
 */