package com.castis.ixc;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * HD UI VOD 서비스를 위한 인터페이스
 * 
 * @version 1.0
 * @author castis
 * 
 */
public interface VODServiceManager extends Remote {
    
    /** RMI APP NAME */
    public static final String RMI_APP_NAME = "VODServiceManager";

    /**
     * 최신영화관 메뉴 표시 요청
     * 
     * @throws RemoteException
     */
    public void showNewVODMenu() throws RemoteException;
    
    /**
     * TV 다시보기 메뉴 표시 요청
     * 
     * @throws RemoteException
     */
    public void showVODMenu() throws RemoteException;

    /**
     * 서비스안내 표시 요청
     * 
     * @throws RemoteException
     */
    public void showHelpMenu() throws RemoteException;

    /**
     * 트리거 검색 요청
     * 
     * @throws RemoteException
     */
    public void launchQuickSearch() throws RemoteException;
    
    /**
     * 안내및설정의 마이페이지 표시 요청
     * 
     * @throws RemoteException
     */
    public void showMyPageMenu() throws RemoteException;
    
    /**
     * VOD Channel 의 컨텐츠 상세정보 표시 요청
     * 
     * @param assetID
     *          요청하는 컨텐츠 ID
     * @param isParentalControlChecked
     *          시청연령제한 인증 여부
     * @throws RemoteException
     */
    public void showVODChannelContent(String assetID, boolean isParentalControlChecked) throws RemoteException;

    /**
     * VOD 검색 결과에서 선택된 컨텐츠 정보 표시 요청
     * 
     * @param assetID
     *          요청하는 컨텐츠 ID
     * @param isParentalControlChecked
     *          시청연령제한 인증 여부
     * @throws RemoteException
     */
    public void showVODSearchedContent(String assetID, boolean isParentalControlChecked) throws RemoteException;

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
     * 성인인증 사용여부 변경시 알려줌
     * 
     * @param isAdultCheck
     * 		성인인증 사용여부
     * @throws RemoteException
     */
    public void notifyAdultCheckChanging(boolean isAdultCheck) throws RemoteException;
    
    /**
     * 시리즈 연속보기 시청여부 변경시 알려줌
     * 
     * @param isUsing
     * 		사용여부
     * @throws RemoteException
     */
    public void notifySeriesPlayOptionChanging(boolean isUsing) throws RemoteException;
    
    /**
     * 외부에서 쿠폰금액이 변경될 경우 알려줌
     *  
     * @throws RemoteException
     */
    public void updateCouponCash() throws RemoteException;
    
    /**
     * VOD 에서 쿠폰충전소 진입후 VOD 로 되돌아 올 경우 알려줌
     * 
     * @throws RemoteException
     */
    public void notifyBackFromCouponStation() throws RemoteException;
    
    /**
     * 퀵메뉴 안내 표시여부 변경시 알려줌
     * 
     * @param isDisplaying
     * 		표시여부
     * @throws RemoteException
     */
    public void notifyQuickMenuDisplayChanging(boolean isDisplaying) throws RemoteException;
    
    /**
     * VOD 재생 중 종료 팝업을 띄우도록 요청
     *  
     * @throws RemoteException
     */
    public void requestVODPlayStop() throws RemoteException;
    

    /**
     * VOD 카테고리 이동 요청
     * 
     * @param categoryID
     * 		카테고리ID
     * @param epgStateId
     * 		EPG State ID
     * @throws RemoteException
     */
    public void goToVODCategory(String categoryID, int epgStateId) throws RemoteException;
    
    /**
     * VOD 컨텐츠 이동 요청
     * 
     * @param assetID
     * 		Asset ID
     * @param epgStateId
     * 		EPG State ID
     * @throws RemoteException
     */
    public void goToVODContent(String assetID, int epgStateId) throws RemoteException;
    
}
