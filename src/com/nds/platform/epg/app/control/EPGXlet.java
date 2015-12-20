/* =================================================================
 * Copyright (c) 2006 -2010: NDS Ltd.
 * P R O P R I E T A R Y C O N F I D E N T I A L
 * The copyright of this document is vested in NDS Ltd. without
 * whose prior written permission its contents must not be published,adapted
 * or reproduced in any form or disclosed or issued to any third party.
 * =================================================================
 */
package com.nds.platform.epg.app.control;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 */
public interface EPGXlet extends Remote
{
	/**
	 * Smart Card ID 의 변경 여부를 notify 받고자 할때 사용하는 mask 값.<br>
	 */
	public static final int MASK_CARD_ID = 0x01;
	/**
	 * Subscriber ID 의 변경 여부를 notify 받고자 할때 사용하는 mask 값.<br>
	 */
	public static final int MASK_SUBS_ID = 0x02;
	/**
	 * SO Code 의 변경 여부를 notify 받고자 할때 사용하는 mask 값.<br>
	 */
	public static final int MASK_SO_CODE = 0x04;
	/**
	 * Region Bytes 의 변경 여부를 notify 받고자 할때 사용하는 mask 값.<br>
	 */
	public static final int MASK_REGION_BYTE = 0x08;
	/**
	 * CAS ID 의 변경 여부를 notify 받고자 할때 사용하는 mask 값.<br>
	 */
	public static final int MASK_CAS_ID = 0x10;

	/**
	 * Standby, 독립형 어플실행중에 해당하는 상태.<br>
	 */
	public static final int EPG_PAUSE = 1;
	/**
	 * 채널시정중인 상태.<br>
	 */
	public static final int EPG_TV_WATCHING = 2;
	/**
	 * VOD시청중인 상태.<br>
	 */
	public static final int VOD_WATCHING = 3;
	/**
	 * EPG 홈메뉴 실행중인 상태.<br>
	 */
	public static final int EPG_MENU = 4;
	/**
	 * VOD 홈메뉴 실행중인 상태.<br>
	 */
	public static final int VOD_MENU = 5;
	/**
	 * @deprecated
	 */
	public static final int PARAM_LCW = 6;
	/**
	 * @deprecated
	 */
	public static final int PARAM_FAV = 7;
	/**
	 * @deprecated
	 */
	public static final int PARAM_NULL = 8;
	/**
	 * @deprecated
	 */
	public static final int FOCUS_FIRST = 9;
	/**
	 * @deprecated
	 */
	public static final int FOCUS_VOD_CATEGORY = 10;
	/**
	 * @deprecated
	 */
	public static final int FOCUS_VOD_HELP = 11;
	/**
	 * @deprecated
	 */
	public static final int FOCUS_NULL = 12;
	/**
	 * @deprecated
	 */
	public static final int FOCUS_DATA_SERVICE = 13;
	/**
	 * @deprecated
	 */
	public static final int PARAM_KEYWORDSEARCH = 14;
	/**
	 * @deprecated
	 */
	public final static int EPG_HIDE = 0;

	/**
	 * @deprecated
	 */
	public final static int EPG_FIRSTDEPTH = 1;

	/**
	 * @deprecated
	 */
	public final static int EPG_SECONDDEPTH = 2;

	/**
	 * @deprecated
	 */
	public final static int EPG_THIRDDEPTH = 3;

	/**
	 * @deprecated
	 */
	public final static int EPG_FORTHDEPTH = 4;

	/**
	 * EPG Organization ID.<br>
	 */
	public static final String EPG_OID = "01000100";

	/**
	 * EPG Application ID.<br>
	 */
	public static final String EPG_AID = "3010";

	/**
	 * EPG RemoteApplicationName.
	 */
	public static final String RMI_APP_NAME = "NDSEPGXLET";

	/**
	 */
	public int VOD_LINK_ASSET = 1;

	/**
	 */
	public int VOD_LINK_FOLDER = 2;

	/**
	 * EPG 의 상태 전환을 요청한다. Monitor App 만 사용해야 한다.<br>
	 * 
	 * @param state
	 *            전환해야할 state
	 * @param sourceid
	 *            state 전환시 채널 전환이 필요한 경우, 해당 채널의 source ID. 채널 전환이 필요 없을 경우, 0
	 * @throws RemoteException
	 */
	public void EPG_changeState(int state, int sourceid, int focus) throws RemoteException;

	/**
	 * @deprecated
	 */
	public void EPG_VOD_changeState(int newState, int param, int focus) throws RemoteException;

	/**
	 * @deprecated
	 */
	public void showEpgMenu() throws RemoteException;

	/**
	 * @deprecated
	 */
	public void hideEpgMenu() throws RemoteException;

	/**
	 * EPG의 현재 state 를 얻어온다.<br>
	 */
	public int EPG_getState() throws RemoteException;

	/**
	 * @deprecated
	 */
	public boolean isPackageSubscribed(int packageID) throws RemoteException;

	/**
	 * @deprecated
	 */
	public String getCurrentParentalRating() throws RemoteException;

	/**
	 * Smart Card ID 를 리턴한다.<br>
	 * 0, null, 혹은 음수인 경우는 사용해서는 안된다.<br>
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String getSmartCardID() throws RemoteException;

	/**
	 * Subscriber ID 를 리턴한다.<br>
	 * 0, null, 혹은 음수인 경우는 사용해서는 안된다.<br>
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String getSubscriberID() throws RemoteException;

	/**
	 * CAS ID 를 리턴한다.<br>
	 * 0, null, 혹은 음수인 경우는 사용해서는 안된다.<br>
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String getCASID() throws RemoteException;

	/**
	 * Super CAS ID 를 리턴한다.<br>
	 * 0, null, 혹은 음수인 경우는 사용해서는 안된다.<br>
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String getSuperCASID() throws RemoteException;

	/**
	 * Region Bytes 를 리턴한다.<br>
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public byte[] getRegionBits() throws RemoteException;

	/**
	 * 특정 범위의 Region Bytes 를 리턴한다.<br>
	 * 
	 * @param from
	 *            시작 index
	 * @param to
	 *            끝 index
	 * @return
	 * @throws RemoteException
	 */
	public byte[] getRegionBits(int from, int to) throws RemoteException;

	/**
	 * SO Code 를 리턴한다.<br>
	 * 0, null, 혹은 음수인 경우는 사용해서는 안된다.<br>
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public int getSoNumber() throws RemoteException;

	/**
	 * SO Image(홈 아이콘)를 바이트로 리턴한다.<br>
	 * 
	 * @return byte array of Image
	 * @throws RemoteException
	 */
	public byte[] getSoImage() throws RemoteException;

	/**
	 * {@link CardInfoUpdateListener}를 등록 한다.<br>
	 * Listener 를 등록한 어플은, 종료될때 반드시 {@link #removeCardInfoUpdateLIstener(CardInfoUpdateListener)}를 호출해야 한다.<br>
	 * 
	 * @param listener
	 *            등록할 listener
	 * @param mask
	 *            notify 받고자 하는 정보의 mask 값. {@link #MASK_CARD_ID} | {@link #MASK_SUBS_ID} | {@link #MASK_SO_CODE} | {@link #MASK_REGION_BYTE} | {@link #MASK_CAS_ID} | {@link #MASK_PR}
	 * @throws RemoteException
	 */
	public void addCardInfoUpdateListener(CardInfoUpdateListener listener, int mask) throws RemoteException;

	/**
	 * {@link CardInfoUpdateListener}를 제거 한다.<br> {@link #addCardInfoUpdateListener(CardInfoUpdateListener, int)}로 listener 를 등록한 어플은, 종료될때 반드시 호출해야 한다.<br>
	 * 
	 * @param listener
	 *            제거할 listener
	 * @throws RemoteException
	 */
	public void removeCardInfoUpdateLIstener(CardInfoUpdateListener listener) throws RemoteException;

	/**
	 * 플래쉬에 저장되어 있는 iTV IFrame을 리턴한다. Monitor App 만 사용한다.<br>
	 * 
	 * @throws RemoteException
	 */
	public void displayLoadingIframe() throws RemoteException;

	/**
	 * {@link EPGAppStateNotificationListener}를 등록 한다.<br>
	 */
	public void addStateChangeListener(EPGAppStateNotificationListener listener) throws RemoteException;

	/**
	 * {@link EPGAppStateNotificationListener}를 제거 한다.<br>
	 */
	public void removeStateChangeListener(EPGAppStateNotificationListener listener) throws RemoteException;

	/**
	 * FECM에 해당하는 VOD 패키지의 권한 유무를 리턴한다.<br>
	 * 
	 * @param fecm
	 *            FECM of VOD package
	 * @return 권한이 있는 경우 true, 그렇지 않으면 false.
	 * @throws RemoteException
	 */
	public boolean isSVODPackageSubscribed(byte[] fecm) throws RemoteException;

	/**
	 * i2com 어플리케이션이 full screen(혹은 full screen에 준하는 다른) 형태로 전환될 때 호출한다.<br>
	 * 이때 EPG는 연동형 어플리케이션의 full mode 시나리오와 동일한 방식으로 처리하며 화면상에 표시되고 있는 MiniEPG 혹은 Home menu를 지운다.<br>
	 * 현재의 i2com 모듈은 미들웨어에의 API를 사용하여 상태 전환을 하고 있으나 추후 필요에 따라 본 API를 사용할 수 있다.<br>
	 * 
	 * @throws RemoteException
	 */
	public void changeToI2COMFullScreen() throws RemoteException;

	/**
	 * i2com 어플리케이션이 full screen형태에서 banner형태로 돌아갈 때 호출되는 API로써, 해당 API는 협의를 통해 deprecated되었다.<br>
	 * 대신에, i2com 어플리케이션이 home key나 ch+/- key에 의해 full mode에서 icon mode로 전환되는 시나리오에 대해,<br>
	 * 먼저 home key가 눌려진 경우 EPG에서는 IPGStatemanager.getInstance().notifyIPGUIDisplay() 와 applicationCoordinator.getInstance().restartBoundApplication()을 호출하고 Home menu 상태로 전환한다.<br>
	 * 그리고 ch +/- key가 눌려진 경우에는 i2com 어플리케이션이 직접 채널 전환 이벤트를 받아서 처리한다.<br>
	 * 
	 * @throws RemoteException
	 * @deprecated
	 */
	public void backFromI2COMFullScreen() throws RemoteException;

	/**
	 * i2com 어플리케이션이 VOD링크로 이동할 때 호출한다.<br>
	 * EPG는 VOD에게 해당 링크로의 이동을 요청하게 되며, 필요 시 state전환을 수행한다.<br>
	 * <H4>본 API는 I2COM 서비스에 대해 VOD 어플리케이션과의 정합이 이루어지기 전까지는 동작하지 않는다.</H4><br>
	 * 
	 * @param id
	 *            asset 혹은 folder ID
	 * @param folder
	 *            {@link #VOD_LINK_ASSET} 혹은 {@link #VOD_LINK_FOLDER}
	 * @throws RemoteException
	 */
	public void requestVODLinkFromI2COM(String ID, int folder) throws RemoteException;

	/**
	 * VOD 어플리케이션에서 i2com으로 돌아갈 때 호출한다.<br>
	 * VOD는 requestVODLinkFromI2COM(String, int)이 호출된 경우에만 호출하여야 한다.<br>
	 * EPG는 이전 i2com상태로 돌아가며 필요 시 state전환을 수행한다.<br>
	 * <H4>본 API는 I2COM 서비스에 대해 VOD 어플리케이션과의 정합이 이루어지기 전까지는 동작하지 않는다.</H4><br>
	 * 
	 * @throws RemoteException
	 */
	public void backToI2COMFromVODLink() throws RemoteException;

	/**
	 * @deprecated
	 */
	public void backToVodChannel(String assetID) throws RemoteException;

	/**
	 * EPG Client 가 HD EPG인지 확인.<br>
	 * 
	 * @return HD EPG인경우 true. HD EPG가 아닌경우, 본 method 가 지원되지 않기 때문에 NoSuchMethodError 를 catch 하여야 함.
	 * @throws RemoteException
	 */
	public boolean isHDEpg() throws RemoteException;
}
