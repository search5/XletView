package com.alticast.epg.app.control;

import java.rmi.RemoteException;


/**
 * Desription -
 * 
 * @author kirans @ Jun 8, 2004
 */
public class EPGXletImpl implements EPGXlet {

    public void EPG_changeState(int state, int sourceid) throws RemoteException {

    }

    public void EPG_VOD_changeState(int newState, int param, int focus) throws RemoteException {
    }

    public void showEpgMenu() throws RemoteException {
    }

    public void hideEpgMenu() throws RemoteException {
    }

    public int EPG_getState() throws RemoteException {
	return 1;
    }

    public boolean isPackageSubscribed(int packageID) throws RemoteException {
	return false;
    }

    public String getCurrentParentalRating() throws RemoteException {
	return "15";
    }

    public String getSmartCardID() throws RemoteException {
	return "683944";
    }

    public String getSubscriberID() throws RemoteException {
	//return "2685"; // Test STB
	return "2689"; // Temp STB
	//return "50044519"; // Live STB
    }

    public String getCASID() throws RemoteException {
	return null;
    }

    public String getSuperCASID() throws RemoteException {
	return "160890880";
    }

    public byte[] getRegionBits() throws RemoteException {
	return new byte[0];
    }

    public byte[] getRegionBits(int from, int to) throws RemoteException {
	return new byte[0];
    }

    public int getSoNumber() throws RemoteException {
	return 1;
    }

    public boolean checkPinCode(int pin) throws RemoteException {
	int pinCode = 1111;
	if(pinCode == pin)
	    return true;
	else
	    return false;
    }

    public void displayLoadingIframe() throws RemoteException {

    }

    public void removeStateChangeListener(EPGAppStateNotificationListener listener) throws RemoteException {

    }

    public void backFromiAD() throws RemoteException {

    }

    public void confirmVODStop(int param) throws RemoteException {

    }

    public void notifyVODevent(int vodEvent) throws RemoteException {

    }

	public void EPG_changeState(int state, int sourceid, int focus) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void addStateChangeListener(EPGAppStateNotificationListener listener) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
