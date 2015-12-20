package com.acetel.cj.vod.ixc;


import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * @author jay
 * @version 2.0
 * it defines the remote interface with iEPG through IXC 
 */

public interface VODXlet extends Remote {

	/**
	 * the state that VOD service is not working on STB.  the related GUIs will disappear.
	 * Just some monitoring module like section filtering will be run in background.
	 */
	public static final int VOD_PAUSE = 0;
	
	/**
	 * External state for VOD_MENU, it indicates that VOD client display a specific VOD menu
	 * on screen like VOD category and asset list
	 * finally, it means the VOD state not in VOD content watching state 
	 */
	public static final int VOD_MENU = 1;
	
	/** 
	 * External state for VOD_WATCHING it indicates that VOD client provides VOD streaming service 
	 * 
	 */
	public static final int VOD_WATCHING = 2;
	
	
	/**
	 * it will be used with showVODMenu() for iEPG to call the VOD menu page 
	 */
	public static final int VOD_CATEGORY_MENU = 11;
	
	/**
	 * it will be used with showVODMenu() for iEPG to call the VOD Help page
	 */
	public static final int VOD_HELP_MENU = 12;
	
	/**
	 * it will be used with showVODMenu() for EPG to call the VOD 'premier' page
	 */
	public static final int VOD_PREMIERE = 13;
	/**
	 * it will be used with showVODMenu() for EPG to call the VOD PVR Help page
	 */
	public static final int VOD_PVR_HELP = 14;
	/**
	 * it will be used with showVODMenu() for EPG to call the VOD TOP 10 page
	 */
	public static final int VOD_TOP_VOD = 15;
	/**
	 * the constant for default color scheme
	 */
	public static final int COLOR_SCHEME_BLACK = 0;
	/**
	 * @deprecated
	 */
	public static final int COLOR_SCHEME_BLUE = 1;
	/**
	 * @deprecated
	 */
	public static final int COLOR_SCHEME_PINK = 2;
	
	/*
	 * search type option
	 */
	public static final int SEARCH_SYLLABLE = 1;
	public static final int SEARCH_SUBSTRING = 2;
	public static final int SEARCH_ALPHABET = 3;
	
	/*
	 * VODLink type
	 */
	public static final int TYPE_FOLDER = 1;
	public static final int TYPE_ASSET = 2;
	public static final int TYPE_CHANNEL_VOD = 3;
	
	/*
	 * ApplicationID
	 */
	public static final int APP_ID_IAD = 3020;
	public static final int APP_ID_EPG = 3000;
	public static final int APP_ID_EVENT_PORTAL = 861;
	
	/**
	 * it indicates the state 'HomeMenu' is just displayed while VOD watching
	 */
	public static final int EPG_MENU_START_WHILE_VOD_WATCHING = 10; 
	/*
	 * it indicates the state EPG close the menu navigation without service selection while VOD watching 
	 * VOD��û�� EPG�� ������ȯ(service selection)���� EPG�޴��׺���̼� ������ �� ȣ��
	 * (EPG�� ������Ű�� �޾��� �� VODwatching���� ���ƿ��� ���� �����ʿ���)
	 */
	public static final int EPG_MENU_END_WHILE_VOD_WATCHING = 11;  

	/**
	 * The name of VOD client under which the remote object was exported.
	 */
	public static final String RMI_APP_NAME = "VODXLET";
	
	
	/**
	 * the constant for coupon menu #1 : ����
	 */
	public final static int COUPON_MENU_SHOP = 1;
	/**
	 * the constant for coupon menu #2 : �������� 
	 */
	public final static int COUPON_MENU_MY_COUPON = 2;
	/**
	 * the constant for coupon menu #3 : ������
	 */
	public final static int COUPON_MENU_REGISTER = 3;
	/**
	 * subscriber purchased coupon in the coupon shop
	 */
	public final static int RESULT_COUPON_PURCHASED = 4;
	/**
	 * subscriber didn't purchase any coupon in the coupon shop
	 */
	public final static int RESULT_NOTHING_DONE = 5; 
	/**
	 * this method will be called by iEPG to launch VOD menu
	 * @param index to move to a specific menu for VOD service
	 * @throws RemoteException
	 */
	public void showVODMenu(int index) throws RemoteException;
	
	/**
	 * this method will be called by iEPG to hide VOD menu
	 * it can be called at anywhere in VOD working state by user selection
	 * @throws RemoteException
	 */
	public void hideVODMenu() throws RemoteException;
	
	/**
	 * when other application like EPG wants to know the current VOD state, it will be called.
	 * @return it return the current VOD external state
	 * @throws RemoteException
	 */
	public int getVODState() throws RemoteException;
	
	/**
	 * it can be called by iEPG to stop the request of VOD content search in progress
	 * After its call, VOD client will stop the requested thread and return nothing.
	 * @throws RemoteException
	 */
	public void cancelVODSearch() throws RemoteException;
	
	/**
	 * @deprecated
	 * @param option  there are 3 options can be selected, the available range of this option is from 1 to 3
	 * option 1 : black (default), 2-blue, 3-pink
	 * in fact, VOD client will just respond to the requested option value
	 * @return VOD client will return true when VOD complete the change of the requested color scheme
	 * @throws RemoteException
	 */
	public boolean changeColorScheme(int colorOption) throws RemoteException;
	
	/**
	 * @param searchType there 3 option for seach type, but VOD client will support 2 options according to CJ business model
	 * 1 - syllable search(������ �˻�) ,  2 - subString search (���� Ű���� �˻�) 
	 * @param searchStr the string to be searched
	 * in case of syllable search, iEPG should put the pre-defined syllable like '��', '��'
	 * @param startIndex valid values for the startIndex is any number that is greater than 0 and less than or equal to the endIndex 
	 * @param endIndex if the endIndex is greater than the total available results then the number of results returned will be equal to total results
	 * @throws RemoteException
	 */
	public void requestVODSearch(int searchType, String searchStr, int startIndex, int endIndex) throws RemoteException;
	
	/**
	 * @param SearchResultModel  iEPG will give SearchResultModel object for selectedAsset to VOD client application.
	 * @throws RemoteException
	 */
	public void requestVODContent(SearchResultModel selectedAssetj) throws RemoteException;
	
	
	/**
	 * EPG will notify the change of adultCheck when user changed its value.
	 * VOD will check the changed value when EPG calls this method.
	 * @throws RemoteException
	 */
	public void  adultCheckChanged() throws RemoteException;
	
	
	/**
	 * @deprecated
	 * EPG will notify the change of userRating when user changed its value
	 * VOD will checked the changed value when EPG calls this method. 
	 * @throws RemoteException
	 */
//	public void userRatingChanged() throws RemoteException;
	
	/**
	 * EPG will call this method when EPG needs to notify epgEvent regarding stateChange to VOD client
	 * @param epgEvent  EPG_MENU_START_WHILE_VOD_WATCHING or EPG_MENU_END_WHILE_VOD_WATCHING
	 */
	public void notifyEPGevent(int epgEvent) throws RemoteException;
	
	
	/**
	 * EPG will call this method when EPG needs to do another service selection while VODwatching
	 */
	public void requestVODStop() throws RemoteException;
	

	
	/**
	 * EPG will call this method when user will select the banner service linked with VOD service.
	 * @param id ProviderAssetId or FolderId of a specific category 
	 * @param type TYPE_FOLDER =1, TYPE_ASSET = 2;
	 * @throws RemoteException
	 */
	public void requestVODLink(String id, int type) throws RemoteException;
	

	/**
	 * EPG will call this method whenever seriesPlay option will be changed by user.
	 * @throws RemoteException
	 */
	public void seriesPlayOptionChanged() throws RemoteException;


	/**
	 * it will be called when user will come back from the requested menu in coupon menus
	 * @param menu the location to be go back
	 * @throws RemoteException
	 */
	public void backFromCouponMenu(int menu) throws RemoteException;
	
	/**
	 * it will be called when user is going to move back to VOD UI page after the behavior in the coupon shop
	 * @param result it provides the result if subscriber purchase a coupon in coupon shop or not 
	 * @throws RemoteException
	 */
	public void backFromCouponShop(int result) throws RemoteException;
	
	/**
	 * it will be called by EPG when EPG needs to notify the fact that coupon data has been changed by any user actions in other UI like EPG or coupon shop
	 */
	public void invalidateCouponCache() throws RemoteException;
	/**
	 * it will be called by EPG when user will select to move directly to the linked vod UI page with the vod category coupon, provided on the pop-up message has just displayed on TV screen
	 * @param couponType  coupon type
	 * @param vod_id folderID or asset ID
	 * @throws RemoteException
	 */
	public void useVodCoupon(int couponType, String vod_id) throws RemoteException;
	
	/**
	 *
	 */
	/**
	 *  STB application will call this method with the given applicationID when EPG needs another service selection while VODwatching
	 * @param applicationID pre-defined AppID
	 * @throws RemoteException
	 */
	public void requestVODStop(int applicationID) throws RemoteException;
	
	/**
	 * it allows STB application to call vod service with the given parameters.  Based on the the given  type and vod_id, vod client will try to move into the requested destination.
	 * @param applicationID the APP_ID value of STB application that calls this ixc method;  APP_ID is the value that is defined and assigned by CJ HV.
	 * @param type TYPE_FOLDER =1, TYPE_ASSET = 2; TYPE_CHANNEL_VOD = 3; 
	 * @param vod_id  asset id or category id(FolderId) linked with VOD service on running.  
	 * @throws RemoteException
	 */
	public void requestVODLink(String applicationID, int type, String vod_id) throws RemoteException;
	
//	/**
//	 * 
//	 * EPG will ask if there is the event coupon published for the given asset  
//	 * @param providerAssetId
//	 * @return
//	 * @throws RemoteException
//	 */
//	public boolean hasEventCoupon(String providerAssetId) throws RemoteException;
	/**
	 * it is the asynchronous method to request Vod content information. 
	 * Asynchronous response is returned by calling a callback method on an object instance that implements the VodResponseListener interface.
	 * @param providerAssetId
	 * @throws RemoteException
	 */
//	public void requestVodContentInfo(String providerAssetId, VodResponseListener vodResponseListener) throws RemoteException;
	

	
}
