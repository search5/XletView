// $Header$

/*
 *  ApplicationCoordinator.java	$Revision$ $Date$
 *
 *  Copyright (c) 2004 Alticast Corp.
 *  All rights reserved. http://www.alticast.com/
 *
 *  This software is the confidential and proprietary information of
 *  Alticast Corp. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Alticast.
 */
package monitor.ixc;

import java.rmi.Remote;
import java.rmi.RemoteException;

//import com.nds.platform.epg.app.control.EPGXlet;

/**
 * <code>ApplicationCoordinator</code> provides APIs to request external state
 * transition or handle unbound applications.
 * 
 * @author $Author$
 * @version $Revision$
 * @since Charles CW Kwak, 2007. 2. 9
 */
public interface ApplicationCoordinator extends Remote {
    /**
     * IXC stub object name
     */
    public static final String IXC_OBJECT_NAME = "ApplicationCoordinator";

    /**
     * External state constant indicating AV state
     */
    public static final int STATE_AV = 0x00010000;
    /**
     * External state constant indicating ITV state 
     */
    public static final int STATE_ITV = 0x00020000;

    /**
     * Requests the external state transition. It handles state transition
     * asynchronously, so the completion of function call does not mean 
     * that of state transition.
     * 
     * @param state the complete state value which may include only external
     *      state or both external state and iEPG state. For instance, if 
     *      iEPG requests to change to Home menu state, <code>state</code>
     *      will be ({@link #STATE_AV} | {@link EPGXlet#EPG_MENU}). However if iEPG
     *      requests to change to iTV state, <code>state</code> will be
     *      {@link #STATE_ITV}.      
     * @param sourceId the source ID of the channel to select in new state.
     * @throws RemoteException when any exception occurs during processing 
     *      this function
     * @see #requestTransition(int)
     */
    public void requestTransition(int state, int sourceId)
        throws RemoteException;

    /**
     * Requests the external state transition. It handles state transition
     * asynchronously, so the completion of function call does not mean
     * that of state transition. 
     * 
     * <br>The state to transit to is determined by service of source ID. 
     * That is, if source ID specifies normal AV, PPV, or audio service,
     * state will be determined as TV viewing state. And if source ID 
     * specifies data broadcast service, state will be determined by ITV state.
     * 
     * @param sourceId the source ID of the channel to select and it also
     *      determines new state to transit to. If it's not valid source ID,
     *      it does nothing.
     * @throws RemoteException when any exception occurs during processing this
     *      function
     * @see #requestTransition(int, int)
     */
    public void requestTransition(int sourceId) throws RemoteException;

    /**
     * Starts unbound application of the given name, which should be specified 
     * in application_name_descriptor of XAIT.
     * 
     * @param name the unbound application name. It should be specified in
     *      application_name_descriptor of XAIT.
     * @return <code>false</code> if it fails to find application of the 
     *      specified name
     * @throws RemoteException when any exception occurs during processing
     *      this function
     */
    public boolean startUnboundApplication(String name) throws RemoteException;

    /**
     * Starts unbound application of the given application ID, which should be 
     * specified in XAIT.
     * 
     * @param oid Organization ID of the unbound application
     * @param aid Application ID of the unbound application
     * @return <code>false</code> if it fails to find application of the
     *      specified application ID.
     * @throws RemoteException when any exception occurs during processing
     *      this function
     */
    public boolean startUnboundApplication(int oid, int aid)
        throws RemoteException;

    /**
     * Stops unbound application of the given name, which should be specified
     * in application_name_descriptor of XAIT.
     * 
     * @param name the unbound application name. It should be specified in
     *      application_name_descriptor of XAIT.
     * @return <code>false</code> if it fails to find applicatin of the 
     *      specified name.
     * @throws RemoteException when any exception occurs during processing
     *      this function
     */
    public boolean stopUnboundApplication(String name) throws RemoteException;
    
    /**
     * Stops unbound application of the given application ID, which should be 
     * specified in XAIT.
     * 
     * @param oid Organization ID of the unbound application
     * @param aid Application ID of the unbound application
     * @return <code>false</code> if it fails to find application of the
     *      specified application ID.
     * @throws RemoteException when any exception occurs during processing
     *      this function
     */
    public boolean stopUnboundApplication(int oid, int aid)
        throws RemoteException;
}

/*
 * $Log$
 * Revision 1.6  2007/04/17 15:37:36  cwkwak
 * [monitor_cj2] 연동형 어플리케이션이 독립형 채널로 이동하는 interface를 제공하기
 * 위해서 requestTransition(int)를 추가
 *
 * Revision 1.5  2007/03/28 14:10:33  cwkwak
 * [monitor_cj2] javadoc 수정
 *
 * Revision 1.4  2007/03/23 08:45:16  cwkwak
 * [monitor_cj2] javadoc 수정
 *
 * Revision 1.3  2007/03/23 08:22:48  cwkwak
 * [monitor_cj2] 다음 사항 수정
 * 1. EPGXlet revised version으로 update
 * 2. startUnboundApp() 구현
 * #비고: 여전히 이렇게 구동시킨 application을 상태 바뀔 때 죽이는 것 구현 필요.
 *
 * Revision 1.2  2007/03/20 14:44:06  cwkwak
 * [monitor_cj2] 1차적으로 완성
 *
 * Revision 1.1  2007/03/01 14:34:07  cwkwak
 * [monitor_cj2] initial version
 *
 */