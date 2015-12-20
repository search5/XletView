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
 * @deprecated
 */
public interface ChannelStatusListener extends Remote
{
	/**
	 * @deprecated
	 */
	public static final int AV_OK = 0;
	/**
	 * @deprecated
	 */
	public static final int USER_BLOCKED = 1;
	/**
	 * @deprecated
	 */
	public static final int PR_BLOCKED = 2;
	/**
	 * @deprecated
	 */
	public static final int UNSUBSCRIBED_BLOCKED = 3;

	/**
	 * This method returns the status of current tuned channel
	 * 
	 * @param status
	 * @return 0 : A/V OK 1 : user blocked 2 : parental rating blocked 3 : unsubscribed blocked
	 * @deprecated
	 */
	public void notifyChannelStatus(int status) throws RemoteException;
}
