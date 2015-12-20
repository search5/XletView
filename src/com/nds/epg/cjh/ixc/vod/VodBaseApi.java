/* =================================================================
 * Copyright (c) 2006 -2010: NDS Ltd.
 * P R O P R I E T A R Y C O N F I D E N T I A L
 * The copyright of this document is vested in NDS Ltd. without
 * whose prior written permission its contents must not be published,adapted
 * or reproduced in any form or disclosed or issued to any third party.
 * =================================================================
 */
package com.nds.epg.cjh.ixc.vod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * EPG - VOD �� IXC API�� ���� �ϰ� �ִ�.<br>
 */
public interface VodBaseApi extends Remote
{
	/**
	 * <code>lookup</code> �� ����� remote object name.<br>
	 */
	public static final String REMOTE_OBJECT_NAME = "NDS_CJH_EPG_VodBaseApi";

	/**
	 * �ֽſ�ȭ�� �޴����� EPGȨ�޴� 1depth(focus �� �ֽſ�ȭ����)�� ���ư����� ��û.<br>
	 * 
	 * @throws RemoteException
	 */
	public void backToNewVod() throws RemoteException;

	/**
	 * VOD �ٽú��� �޴����� EPGȨ�޴� 1depth(focus �� VOD �ٽú���)�� ���ư����� ��û.<br>
	 * 
	 * @throws RemoteException
	 */
	public void backToVodMenu() throws RemoteException;

	/**
	 * �̿�ȳ� �޴����� EPGȨ�޴� �ȳ� �� ���� 2depth(focus �� �̿�ȳ�)�� ���ư����� ��û.<br>
	 * 
	 * @throws RemoteException
	 */
	public void backToHelpMenu() throws RemoteException;

	/**
	 * ���������� �޴����� EPGȨ�޴� �ȳ� �� ���� 2depth(focus �� ����������)�� ���ư����� ��û.<br>
	 * 
	 * @throws RemoteException
	 */
	public void backToMyPageMenu() throws RemoteException;

	/**
	 * VOD ä�� ������ ���������� VOD ä�� ��ȭ���� ȭ������ ���ư����� ��û.<br>
	 * VOD ä�� ��ȭ ���� ȭ��(EPG)���� ����ڰ� ��ȭ�� �����Ͽ� ��ȭ��(VOD)���� �̵��� ����, ���� Ȥ�� ���� Ű� ���� ��ȭ���� ����Ǵ� ��� ȣ���Ѵ�.<br>
	 * �̶�, EPG�� ��ȭ ���� ȭ�鿡�� assetID �� �����Ϳ� ��Ŀ���� ǥ�� �Ѵ�.<br>
	 * 
	 * @param assetId
	 *            �������� ǥ�õ� VOD asset ID
	 * @throws RemoteException
	 */
	public void backToVodChannel(String assetId) throws RemoteException;

	/**
	 * �˻�������� VOD�� ������, �ǵ��� �ö� ȣ����.
	 * 
	 * @throws RemoteException
	 */
	public void backToSearchMenu() throws RemoteException;

	/**
	 * VOD��û��, �� ���� Ű�� ���� ���ã�� ���޴��� ����� �Ҷ� ȣ���Ѵ�.<br>
	 * 
	 * @throws RemoteException
	 */
	public void launchFavoriteMenu() throws RemoteException;

	/**
	 * VOD��û��, �� ���� Ű�� ���� �˻� Ʈ���Ÿ� ����� �Ҷ� ȣ���Ѵ�.<br>
	 * 
	 * @throws RemoteException
	 */
	public void launchSearchMenu() throws RemoteException;

	/**
	 * VOD��û��, �� ���� Ű�� ���� �α������� Ʈ���Ÿ� ����� �Ҷ� ȣ���Ѵ�.<br>
	 * 
	 * @throws RemoteException
	 */
	public void launchPopularContent() throws RemoteException;

	/**
	 * VOD �޴����� �����޴�(������)���� �̵��Ҷ� ȣ���Ѵ�.<br>
	 * 
	 * @throws RemoteException
	 */
	public void launchCouponMenu() throws RemoteException;

	/**
	 * EPG�� ��û�� VOD play ���� ��û�� ���� ó�� ����� �����Ѵ�.<br>
	 * 
	 * @param stop
	 *            VOD�� ���� ���� �Ǵ� ��� true, ����ڰ� ����Ȯ���� ����� ��� false
	 * @throws RemoteException
	 */
	public void notifyVodPlayStop(boolean stop) throws RemoteException;

	/**
	 * �ٸ� ������ ��û�� ���� VOD ȭ���� �߰ų�, �ش� ȭ���� ������� ȣ���Ѵ�.<br>
	 * 
	 * @param visible
	 *            true: �ٸ� ������ ��û�� ���� VOD ȭ���� �㶧<br>
	 *            false: �ٸ� ������ ��û�� ���� �� VODȭ���� �������<br>
	 * @throws RemoteException
	 */
	public void notifyVodLaunching(boolean visible) throws RemoteException;

	/**
	 * AV������ ��ȯ�� EPG���� �˷��ش�.<br>
	 * 
	 * @param play
	 *            true : AV -> VOD Watching ���� ���� ���<br>
	 *            false : VOD Watching -> AV �� ���� ���
	 * @throws RemoteException
	 */
	public void notifyVodWatching(boolean play) throws RemoteException;

	/**
	 * EPG���� Ư�� VOD ��ũ�� �̵��� ����, �ٽ� ���� ���·� �ǵ��� ���� ȣ���Ѵ�.<br>
	 * �ٸ�, �̿�ȳ�, �˻���� ��, �ش� ��ũ�� ���� ���� API�� ���� ���� �ű⿡ �ش��ϴ� backToXXXX()�� ����Ͽ��� �Ѵ�.<br>
	 * 
	 * @param epgStateId
	 *            EPG�� VOD ��ũ�� ��û�� ��� �Ѱ��� id ��
	 * @throws RemoteException
	 */
	public void backToEpgState(int epgStateId) throws RemoteException;

	/**
	 * VOD ���� ������ ���� ���, ȣ��ȴ�.<br>
	 * EPG �� ��ü������ cache �ϰ� �ִ� ���� �ܾ��� �����, �ʿ��� ��� ���������� ���� �ٽ� �޾ƿ´�.<br>
	 * 
	 * @throws RemoteException
	 */
	public void invalidateCouponCache() throws RemoteException;
}
