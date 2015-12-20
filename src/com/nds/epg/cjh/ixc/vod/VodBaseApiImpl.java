package com.nds.epg.cjh.ixc.vod;

import java.rmi.RemoteException;

import com.acetel.cj.vod.ixc.SearchResultModel;
import com.nds.epg.cjh.ixc.vod.VodBaseApi;
import com.nds.platform.epg.app.control.EPGAppStateNotificationListener;
import com.nds.platform.epg.app.control.EPGXlet;

public class VodBaseApiImpl implements VodBaseApi{

	public void backToNewVod() throws RemoteException {
		System.out.println("VodBasApi Call");
		// TODO Auto-generated method stub
		
	}

	public void backToVodMenu() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("VodBasApi Call");
	}

	public void backToHelpMenu() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("VodBasApi Call");
	}

	public void backToMyPageMenu() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("VodBasApi Call");
	}

	public void backToVodChannel(String assetId) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("VodBasApi Call");
	}

	public void launchFavoriteMenu() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("VodBasApi Call");
	}

	public void notifyVodPlayStop(boolean stop) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("VodBasApi Call");
	}

	public void backToHotIssue(Object content) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("VodBasApi Call");
	}

	public void notifyVodWatching(boolean play) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("VodBasApi Call");
	}

	public void backToEpgState(int epgStateId) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("VodBasApi Call");
	}

	public void invalidateCouponCache() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("VodBasApi Call");
	}
	
	public void launchCouponMenu() throws RemoteException {
	    System.out.println("VodBasApi Call");
	}

	@Override
	public void backToSearchMenu() throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void launchPopularContent() throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void launchSearchMenu() throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void notifyVodLaunching(boolean visible) throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

}
