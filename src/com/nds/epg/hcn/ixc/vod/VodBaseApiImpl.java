package com.nds.epg.hcn.ixc.vod;

import java.rmi.RemoteException;

import com.nds.epg.hcn.ixc.vod.VodBaseApi;

public class VodBaseApiImpl implements VodBaseApi{
	
	public VodBaseApiImpl() {
		
	}

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

	public void backToSearchMenu() throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	public void launchPopularContent() throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	public void launchSearchMenu() throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void notifyVodLaunching(boolean visible) throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void backToVodMovie() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backToVodTv() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showPromotion() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hidePromotion() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyVodWatching(boolean re,boolean rk, boolean ra) throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void backToHomeVodCategory(String categoryId, int focus) throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void backToVodHistory() throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void launchEventDetails(String headerId) throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void launchPasDetails(String headerId) throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void launchEpgMenu(String data, String param) throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void backToEpgState(int epgStateId, String param) throws RemoteException {
	    // TODO Auto-generated method stub
	    
	}

}
