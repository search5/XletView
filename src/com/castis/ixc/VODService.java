package com.castis.ixc;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * HD UI VOD 서비스를 위한 인터페이스
 * 
 * @version 1.1
 * @author castis
 *
 */
public interface VODService extends Remote{

    public static final String RMI_NAME = "VODService";
    
    /**
     * 최신VOD 메뉴 표시 요청
     * @throws RemoteException
     */
    public void showNewVODMenu() throws RemoteException;
    
    
    /**
     * TV 다시보기 메뉴 표시 요청
     * 
     * @throws RemoteException
     */
    
    public void showVODMenu(String categoryId , String categoryName , int epgFocusIndex) throws RemoteException;

    /**
     * VOD Channel 의 컨텐츠 상세정보 표시 요청
     * 
     * @param assetID
     *          요청하는 컨텐츠 ID
     * @param isParentalControlChecked
     *          시청연령제한 인증 여부
     * @param isPlayingDirectly
     * 		바로재생 요청 여부
     *  
     * @throws RemoteException
     */
    public void showVODChannelContent(String assetID, boolean isParentalControlChecked, boolean isPlayingDirectly) throws RemoteException;

    /**
     * 화면에 표시된 VOD 서비스를 닫도록 요청
     *  
     * @throws RemoteException
     */
    public void hideVODService() throws RemoteException;

    /**
     * VOD 재생 중에 HomeMenu 가 표시될 경우 알려줌
     * 
     * @throws RemoteException
     */
    public void notifyHomeMenuShowingWhileVODWatching() throws RemoteException;
    
    /**
     * VOD 재생 중에 HomeMenu 가 사라질 경우 알려줌
     *  
     * @throws RemoteException
     */
    public void notifyHomeMenuHidingWhileVODWatching() throws RemoteException;
    
    /**
     * VOD 재생 중 종료 팝업을 띄우도록 요청
     *  
     * @throws RemoteException
     */
    public void requestVODPlayingStopPopup() throws RemoteException;

    /**
     * 외부에서 쿠폰금액이 변경될 경우 알려줌
     *  
     * @throws RemoteException
     */
    public void updateCouponCash() throws RemoteException;
    
    /**
     * VOD 에서 쿠폰샵 진입후 VOD 로 되돌아 올 경우 알려줌
     * 
     * @throws RemoteException
     */
    public void notifyBackFromCouponShop() throws RemoteException;
   
    /**
     * VOD 카테고리 이동 요청
     * 
     * @param categoryID
     * 		카테고리ID
     * @param epgStateId
     * 		EPG State ID
	 * @param epgParam
     * @throws RemoteException
     */
    public void goToVODCategory(String categoryID, int epgStateId , String epgParam , boolean focusedSubview) throws RemoteException;
	
	
    /**
     * VOD 컨텐츠 이동 요청
     * 
     * @param assetID
     * 		Asset ID
     * @param epgStateId
     * 		EPG State ID
     * @throws RemoteException
     */
    public void goToVODContent(String assetID, int epgStateId , String epgParam) throws RemoteException;
    
    /**
     * 자막가림 사용여부 변경시 알려줌
     * 
     * @param isUsing
     * 		사용여부
     * @throws RemoteException
     */
    public void notifyNoSubtitleChanging(boolean isUsing) throws RemoteException;
    
    /**
     * 시청연령제한 변경시 알려줌
     * 
     * @throws RemoteException
     */
    public void notifyParentalRatingChanging() throws RemoteException;
    
    
    /**
     * VOD보관함 이동
     * @throws RemoteException
     */
    public void showMyVODList() throws RemoteException;

    
    /**
     * EPG Menu에서 돌아올 때 
     * @throws RemoteException
     */
    public void notifyBackFromEpgMenu() throws RemoteException;
    
    /**
     * VOD 재생요청 
     * @throws RemoteException
     */
    
    public void playVODContent(String assetID ,int epgStateId , String epgParam) throws RemoteException;
	
    /**
     * 묶음상품 상세 이동 요청  
     * @throws RemoteException
     */
     
    public void showBundleProduct(String productId , int epgStateId , String epgParam) throws RemoteException;
	
    /**
     * 검색에서 ContentGroup 상세 이동 요청
     * @throws RemoteException
     */
    
    public void showContentGroup(String contentGroupId , int epgStateId , String epgParam) throws RemoteException;
}
