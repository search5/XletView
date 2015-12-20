/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

 */

package net.beiker.xletview.event;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.AWTEventListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import monitor.ixc.ApplicationCoordinator;
import net.beiker.xletview.media.ChannelManager;
import net.beiker.xletview.media.ScreenContainer;
import net.beiker.xletview.remotecontrol.RemoteControl;
import net.beiker.xletview.util.Debug;
import net.beiker.xletview.util.Util;
import net.beiker.xletview.xlet.XletManager;

import org.dvb.io.ixc.IxcRegistry;
import org.havi.ui.HActionable;
import org.havi.ui.HNavigable;
import org.havi.ui.event.HActionEvent;
import org.havi.ui.event.HFocusEvent;
import org.havi.ui.event.HRcEvent;
import org.ocap.ui.event.OCRcEvent;

import xjava.lang.System;

import com.castis.ixc.VODService;
import com.nds.epg.hcn.ixc.vod.VodBaseApi;
import com.nds.epg.hcn.ixc.vod.VodBaseApiImpl;
import com.nds.platform.epg.app.control.EPGXlet;
import com.nds.platform.epg.app.control.EPGXletImpl;

/**
 * 
 * @author Martin Sveden
 */
public class EventManager implements AWTEventListener {

    private static EventManager THE_INSTANCE;

    private boolean eventEnabled;

    private Component focusOwner;

    VODService vodService;

    private KeyboardFocusManager kfm;

    /**
     * 
     */
    public static EventManager getInstance() {
	if (THE_INSTANCE == null) {
	    THE_INSTANCE = new EventManager();
	}
	return THE_INSTANCE;
    }

    /**
     * 
     */
    private EventManager() {
	kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    }

    /**
     * Returns a new key code that is mapped with the incoming or the same as
     * the incoming if it was not mapped.
     * 
     * @param incoming
     *            The key code to convert
     * @return a key code
     */
    public int convertCode(int incoming) {
	int outgoing;
	switch (incoming) {

	case 112: // F1
	    outgoing = OCRcEvent.VK_COLORED_KEY_0;
	    break;
	case 113: // F2
	    outgoing = OCRcEvent.VK_COLORED_KEY_1;
	    break;
	case 114: // F3
	    outgoing = OCRcEvent.VK_COLORED_KEY_2;
	    break;
	case 115: // F4
	    outgoing = OCRcEvent.VK_COLORED_KEY_3;
	    break;
	case 116: // F5
	    outgoing = OCRcEvent.VK_F1;
	    break;
	case KeyEvent.VK_PAGE_UP:
	    outgoing = OCRcEvent.VK_CHANNEL_UP;
	    break;
	case KeyEvent.VK_PAGE_DOWN:
	    outgoing = OCRcEvent.VK_CHANNEL_DOWN;
	    break;
	case KeyEvent.VK_BACK_SLASH:
	    outgoing = OCRcEvent.VK_BACK;
	    break;
	case KeyEvent.VK_BACK_SPACE:
	    outgoing = OCRcEvent.VK_LAST;
	    break;
	case KeyEvent.VK_SPACE:
	    outgoing = OCRcEvent.VK_INFO;
	    break;
	case KeyEvent.VK_DELETE:
	    outgoing = OCRcEvent.VK_DELETE;
	    break;
	case KeyEvent.VK_X:
	    outgoing = OCRcEvent.VK_EXIT;
	    break;
	default:
	    outgoing = incoming;
	}
	return outgoing;
    }

    /**
     * 
     * 1. Passes event to org.dvb.event.EventManager that is in charge of
     * processing all java.awt.KeyEvent and org.dvb.event.UserEvent
     * 
     * 2. Fires HFocusEvent
     * 
     * 3. Handles emulator specific events from the "remote control" or other
     * input devices such as keyboard or mouse etc.
     */
    public void fireEvents(KeyEvent event) {
	if (event != null) {

	    // first trigger UserEvent and java.awt.KeyEvent
	    org.dvb.event.EventManager.getInstance().fireUserEvent(event.getSource(), event);

	    int keyCode = event.getKeyCode();

	    // do HFocusEvent
	    if (focusOwner != null && focusOwner instanceof HNavigable && event.getID() == KeyEvent.KEY_PRESSED) {
		// logger.debug("focusOwner=" + focusOwner);
		HNavigable nav = (HNavigable) focusOwner;

		HNavigable transferTo = nav.getMove(keyCode);
		HFocusEvent hEvent = null;
		if (transferTo != null) {
		    hEvent = new HFocusEvent(focusOwner, HFocusEvent.FOCUS_TRANSFER, keyCode);
		} else {
		    hEvent = new HFocusEvent(focusOwner, HFocusEvent.FOCUS_TRANSFER, HFocusEvent.NO_TRANSFER_ID);
		}
		// Debug.write(this, "hEvent=" + hEvent);

		nav.processHFocusEvent(hEvent);
		// Debug.write(this, "is HIcon? " + (focusOwner instanceof
		// HIcon) + ", nav=" + nav);

		if (focusOwner instanceof HActionable) {
		    HActionable act = (HActionable) focusOwner;
		    HActionEvent haEvent = new HActionEvent(act, HActionEvent.ACTION_PERFORMED, act.getActionCommand());
		    act.processHActionEvent(haEvent);
		}

	    }

	    // do emulator specific stuff
	    if (event.getID() == KeyEvent.KEY_PRESSED) {

		RemoteControl.getInstance().setPressed(keyCode);

	    } else if (event.getID() == KeyEvent.KEY_RELEASED) {

		RemoteControl.getInstance().setReleased(keyCode);
	    }

	}
    }

    public void fireRemoteEvent(KeyEvent event) {
	if (focusOwner != null) {
	    // Debug.write(this, "keyCode:" + keyCode + ", focusOwner:" +
	    // focusOwner.getClass().getName());
	    fireEvents(new KeyEvent(focusOwner, event.getID(), 0L, 0, event.getKeyCode()));
	} else {
	    fireEvents(event);
	}
    }

    /**
     * Sets the current focus owner in the screen.
     */
    private void setFocusOwner(Component c) {
	/*
	 * The focus owner has to be a child of the "tv screen".
	 */
	boolean ok = Util.isChildOf(ScreenContainer.getInstance(), c);
	if (ok) {
	    Debug.write(this, "new focus owner: " + c.toString());
	    focusOwner = c;
	}
    }

    public Component getFocusOwner() {
	return focusOwner;
    }

    public boolean isEventEnabled() {
	return eventEnabled;
    }

    public void setEventEnabled(boolean b) {
	eventEnabled = b;
    }

    public void eventDispatched(AWTEvent e) {
	// Component fo =
	// KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner
	// ();
	// Debug.write(this, "fo=" + fo);
	Object source = e.getSource();

	if (e instanceof KeyEvent) {
	    KeyEvent ke = (KeyEvent) e;
	    int keyCode = ke.getKeyCode();

	    // do emulator stuff
	    if (ke.getID() == KeyEvent.KEY_PRESSED) {
		RemoteControl.getInstance().setPressed(keyCode);

		System.out.println("keyCode : " + keyCode);

		if (ke.isControlDown()) { // CTRL
		    switch (keyCode) {
		    case 65: // A
			VodBaseApi vodBaseApiImpl = new VodBaseApiImpl();
			ApplicationCoordinator ac = new ApplicationCoordinator() {

			    @Override
			    public boolean stopUnboundApplication(int oid, int aid) throws RemoteException {
				// TODO Auto-generated method stub
				return false;
			    }

			    @Override
			    public boolean stopUnboundApplication(String name) throws RemoteException {
				// TODO Auto-generated method stub
				return false;
			    }

			    @Override
			    public boolean startUnboundApplication(int oid, int aid) throws RemoteException {
				// TODO Auto-generated method stub
				return false;
			    }

			    @Override
			    public boolean startUnboundApplication(String name) throws RemoteException {
				// TODO Auto-generated method stub
				return false;
			    }

			    @Override
			    public void requestTransition(int sourceId) throws RemoteException {
				// TODO Auto-generated method stub

			    }

			    @Override
			    public void requestTransition(int state, int sourceId) throws RemoteException {
				// TODO Auto-generated method stub

			    }
			};
			try {
			    IxcRegistry.bind(null, VodBaseApi.REMOTE_OBJECT_NAME, vodBaseApiImpl);

			    IxcRegistry.bind(null, ApplicationCoordinator.IXC_OBJECT_NAME, ac);
			} catch (AlreadyBoundException e4) {
			    // TODO Auto-generated catch block
			    e4.printStackTrace();
			}

			EPGXlet epgXlet = new EPGXletImpl();
			try {
			    IxcRegistry.bind(null, EPGXlet.RMI_APP_NAME, epgXlet);
			} catch (AlreadyBoundException e4) {
			    // TODO Auto-generated catch block
			    e4.printStackTrace();
			}

			System.out.println("Binding");
			break;

		    case 83:// S
			try {
			    vodService = (VODService) IxcRegistry.lookup(XletManager.getInstance().getXletContext(),
				    VODService.RMI_NAME);
			    vodService.showNewVODMenu();
			} catch (RemoteException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			} catch (NotBoundException e2) {
			    // TODO Auto-generated catch block
			    e2.printStackTrace();
			}
			break;

		    case 68:// D
			try {
				vodService = (VODService) IxcRegistry.lookup(XletManager.getInstance().getXletContext(),
					VODService.RMI_NAME);
				vodService.showVODMenu("0", "홈", 0);
				//vodService.notifyBackFromCouponShop();
			} catch (RemoteException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			} catch (NotBoundException e2) {
			    // TODO Auto-generated catch block
			    e2.printStackTrace();
			}
			break;

		    case 89:
			/*try {
				vodService = (VODService) IxcRegistry.lookup(XletManager.getInstance().getXletContext(),
						VODService.RMI_NAME);
				vodService.showVODChannelContent("www.hchoice.co.kr|M0169598LSG134909101", false , false);
			} catch (RemoteException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			} catch (NotBoundException e2) {
			    // TODO Auto-generated catch block
			    e2.printStackTrace();
			}*/
			
			try {
				vodService = (VODService) IxcRegistry.lookup(XletManager.getInstance().getXletContext(),
					VODService.RMI_NAME);
				vodService.showVODMenu("750630", "홈", 0);
				//vodService.notifyBackFromCouponShop();
			} catch (RemoteException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			} catch (NotBoundException e2) {
			    // TODO Auto-generated catch block
			    e2.printStackTrace();
			}
			
			break;

		    case 70:
			
			/*try {
				vodService = (VODService) IxcRegistry.lookup(XletManager.getInstance().getXletContext(),
						VODService.RMI_NAME);
//				vodService.showMyPageMenu();
			} catch (RemoteException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			} catch (NotBoundException e2) {
			    // TODO Auto-generated catch block
			    e2.printStackTrace();
			}
*/
			break;

		    case 67:
			try {
				vodService = (VODService) IxcRegistry.lookup(XletManager.getInstance().getXletContext(),
						VODService.RMI_NAME);
//				vodService.showHelpMenu();
			} catch (RemoteException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			} catch (NotBoundException e2) {
			    // TODO Auto-generated catch block
			    e2.printStackTrace();
			}

			break;

		    case 155:
			try {
				vodService = (VODService) IxcRegistry.lookup(XletManager.getInstance().getXletContext(),
						VODService.RMI_NAME);
				vodService.notifyHomeMenuShowingWhileVODWatching();
			} catch (RemoteException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			} catch (NotBoundException e2) {
			    // TODO Auto-generated catch block
			    e2.printStackTrace();
			}
			break;

		    case 127:
			try {
				vodService = (VODService) IxcRegistry.lookup(XletManager.getInstance().getXletContext(),
						VODService.RMI_NAME);
				vodService.notifyHomeMenuHidingWhileVODWatching();
			} catch (RemoteException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			} catch (NotBoundException e2) {
			    // TODO Auto-generated catch block
			    e2.printStackTrace();
			}
			break;
			
		    case 36:
			// Home
			try {
				vodService = (VODService) IxcRegistry.lookup(XletManager.getInstance().getXletContext(),
						VODService.RMI_NAME);
				//vodService.goToVODCategory("100286", -1);
//				vodService.goToVODCategory("104260", -1, egpParam, focusedSubView);
//				vodService.requestVODPlayStop();
			} catch (RemoteException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			} catch (NotBoundException e2) {
			    // TODO Auto-generated catch block
			    e2.printStackTrace();
			}
			break;
			
			// End
		    case 35:

			break;

		    default:
			break;
		    }
		}

		if (keyCode == HRcEvent.VK_CHANNEL_UP) {
		    Debug.write(this, "channel up");
		    ChannelManager.getInstance().nextChannel();
		} else if (keyCode == HRcEvent.VK_CHANNEL_DOWN) {
		    Debug.write(this, "channel down");
		    ChannelManager.getInstance().previousChannel();
		} else if (ke.getModifiers() == HRcEvent.CTRL_MASK && keyCode == HRcEvent.VK_R) {
		    XletManager.getInstance().reloadActiveXlet();
		} else if (ke.getModifiers() == HRcEvent.ALT_MASK && keyCode == HRcEvent.VK_F4) {
		    System.exit(0);
		}
	    }

	    int newKeyCode = convertCode(keyCode);
	    ke.setKeyCode(newKeyCode);

	    // consume if it's the focus owner
	    if (source == focusOwner) {
		ke.consume();
	    }

	    fireEvents(ke);

	}
	if (e instanceof FocusEvent) {
	    // Debug.write(this, "event=" + e);
	    // Debug.write(this, "eventDispatched-" + e);
	    FocusEvent fe = (FocusEvent) e;
	    Component c = fe.getComponent();

	    if (fe.getID() == FocusEvent.FOCUS_GAINED && c != null) {
		setFocusOwner(c);
	    }
	}

    }

}
