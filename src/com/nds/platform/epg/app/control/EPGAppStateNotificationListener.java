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
import java.util.EventListener;

/**
 */
public interface EPGAppStateNotificationListener extends EventListener, Remote
{

	/**
	 * EPG 의 state 변환을 알려 준다.<br>
	 * 
	 * @param newState
	 * @throws RemoteException
	 */
	public void notifyEPGStateChange(int newState) throws RemoteException;

}
