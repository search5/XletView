package com.alticast.epg.app.control;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * EPGXlet interface
 * @author TaeHo
 *
 */
public interface EPGXlet extends Remote {

	//EPG state 정의
	/** EPG가 pause상태 */
	public static final int EPG_PAUSE = 1;
	/** AV상태 (미니 epg가 표시되는 경우 포함) */
	public static final int EPG_TV_WATCHING = 2;
	/** VOD 시청중 */
	public static final int VOD_WATCHING = 3;
	/** 통합UI 어플이 화면을 보여주는 상태 */
	public static final int EPG_MENU = 4;
	/** VOD 어플에서 VOD 메뉴나 VOD 상세화면을 보여주는 상태 */
	public static final int VOD_MENU = 5;
	
	// parameter definition 
	/** LCW로 채널이동이 필요할때 */
	public static final int PARAM_LCW = 6;
	/** 선호채널 관련 */
	public static final int PARAM_FAV = 7;
	/** 특정값을 넘기지 않는 경우 */
	public static final int PARAM_NULL = 8;
	/** Quick 메뉴로 돌아갈때 */
	public static final int PARAM_QUICK = 14;
	
	// focus 정의
	/** 사용하지 않음 */
	public static final int FOCUS_FIRST = 9;
	/** VOD 메뉴에 있던 경우 */
	public static final int FOCUS_VOD_CATEGORY = 10;
	/** 이용안내 메뉴에 있던 경우 */
	public static final int FOCUS_VOD_HELP = 11;
	/** 특정값을 넘기지 않는 경우 */
	public static final int FOCUS_NULL = 12;
	public static final int FOCUS_DATA_SERVICE = 13;
	/** VOD 검색에 있던 경우 */
	public static final int FOCUS_VOD_SEARCH = 15;
	/** VOD 구매목록에 있던 경우 */
	public static final int FOCUS_VOD_PURCHASELISt = 16;
	/** Quick메뉴의 신규영화에 있던 경우 */
	public static final int FOCUS_QUICK_NEW = 17;
	
	// EPG depth 정의 - 사용되지 않음
	public static final int EPG_HIDE = 0;
	public static final int EPG_FIRSTDEPTH = 1;
	public static final int EPG_SECONDDEPTH = 2;
	public static final int EPG_THIRDDEPTH = 3;
	public static final int EPG_FORTHDEPTH = 4;
	
	
	public static final String RMI_APP_NAME = "EPGXLET";
	
	/**
	 * 통합UI의 현재 state를 반환한다.
	 * @return 현재 state
	 * @throws RemoteException
	 */
	public int EPG_getState() throws RemoteException;
	
	/** monitory Application에서 STB상태에 따라 통합UI Application의  state를 변화시키거나 채널 selection을 요청할때 사용된다.
	 * @param state : 변경될 새로운 EPG state. 정의된 state중 하나의 값을 준다.
	 * @param sourceid : 이동할 채널의 sourceID
	 * @param focus : 부가 포커스 정보. 
	 */
	public void EPG_changeState(int state,int sourceid, int focus ) throws RemoteException;
	/**
	 * 통합UI application과 VOD Application간에 화면전환이 필요할 때 사용된다.
	 * @param newState 변경될 새로운 EPG State
	 * @param param 추가로 전달할 변수로 PARAM_LCW or PARAM_QUICK가 존재
	 * @param focus 이동하고자 하는 메뉴를 나타내는 focus
	 * @throws RemoteException
	 */
	public void EPG_VOD_changeState(int newState, int param, int focus) throws RemoteException;
	
	/**
	 * VOD 어플리케이션만 화면에 보여지는 상태에서 통합UI 어플리케이션과 VOD 어플리케이션이 동시에 보여져야 하는 경우 
	 * 호출된다
	 * @throws RemoteException
	 */
	public void showEpgMenu() throws RemoteException;
	
	/**
	 * VOD 어플리케이션과 통합UI 어플리케이션이 동시에 보여지던 상황에서 VOD 어플리케이션만 보여지는 상황이 될 경우 호출된다
	 * @throws RemoteException
	 */
	public void hideEpgMenu() throws RemoteException;
	
	/**
	 * 로딩 아이프레임을 그리려고 할때 모니터 어플리케이션에서 호출한다.
	 * @throws RemoteException
	 */
	public void displayLoadingIframe() throws RemoteException;
	
	/**
	 * EPGAppStateNotificationListener을 등록한다.
	 * @param listener
	 * @throws RemoteException
	 */
	public void addStateChangeListener(EPGAppStateNotificationListener listener) throws RemoteException;
	
	/**
	 * EPGAppStateNotificationListener을 제거한다.
	 * @param listener
	 * @throws RemoteException
	 */
	public void removeStateChangeListener(EPGAppStateNotificationListener listener) throws RemoteException;
	
	
	
	
	
	
	
}
