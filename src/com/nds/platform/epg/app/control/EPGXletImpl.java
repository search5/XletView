/*=================================================================
 *  Copyright (c) 2006 -2007: NDS Ltd.
 *
 *  P R O P R I E T A R Y        C O N F I D E N T I A L
 *
 *  The copyright of this document is vested in NDS Ltd. without
 *  whose prior written permission its contents must not be published,adapted
 *  or reproduced in any form or disclosed or issued to any third party.
 *=================================================================*/
package com.nds.platform.epg.app.control;

import java.rmi.RemoteException;

import net.beiker.xletview.util.Settings;

import com.acetel.cj.vod.ixc.SearchResultModel;

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
	return "01346692896";
    }

    public String getSubscriberID() throws RemoteException {
	//return "2685"; // Test STB
//	return "10000948"; // Temp STB
	return "50044519"; // Live STB
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

    public void returnVODSearchResult(SearchResultModel[] resultList) throws RemoteException {

    }

    public void backFromiAD() throws RemoteException {

    }

    public void confirmVODStop(int param) throws RemoteException {

    }

    public void notifyVODevent(int vodEvent) throws RemoteException {

    }

    @Override
    public void addStateChangeListener(EPGAppStateNotificationListener listener) throws RemoteException {
	// TODO Auto-generated method stub

    }

	@Override
	public void EPG_changeState(int state, int sourceid, int focus)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getSoImage() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCardInfoUpdateListener(CardInfoUpdateListener listener,
			int mask) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCardInfoUpdateLIstener(CardInfoUpdateListener listener)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSVODPackageSubscribed(byte[] fecm) throws RemoteException {
		// TODO Auto-generated method stub
	    Settings.reLoadUserFile();
	    String isSVODPackageSubscribed = Settings.getUserProperties("isSVODPackageSubscribed");
		return Boolean.parseBoolean(isSVODPackageSubscribed);
	}

	@Override
	public void changeToI2COMFullScreen() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backFromI2COMFullScreen() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestVODLinkFromI2COM(String ID, int folder)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backToI2COMFromVODLink() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backToVodChannel(String assetID) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isHDEpg() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
