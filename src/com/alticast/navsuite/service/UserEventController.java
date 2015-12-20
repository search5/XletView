package com.alticast.navsuite.service;

import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.ocap.ui.event.OCRcEvent;

public class UserEventController implements UserEventListener {
    private UserEventRepository rep = null;

    private OverlappedDialogHandler handler = null;

    public UserEventController(OverlappedDialogHandler handler) {
	registerKeyEvent();
	this.handler = handler;
    }

    /**
     * 사용할 Key를 등록한다.
     */
    private void registerKeyEvent() {
	rep = new UserEventRepository("whatever");
	rep.addAllArrowKeys();
	rep.addAllNumericKeys();
	rep.addAllColourKeys();
	rep.addKey(OCRcEvent.VK_EXIT);
	rep.addKey(OCRcEvent.VK_LAST);
	rep.addKey(OCRcEvent.VK_ENTER);
	rep.addKey(OCRcEvent.VK_F1);
    }

    public void addUserEventListener() {
	EventManager.getInstance().addUserEventListener(this, rep);
    }

    /**
     * UserEventListener를 remove한다.
     */
    public void removeUserEventListener() {
	EventManager.getInstance().removeUserEventListener(this);
    }

    @Override
    public void userEventReceived(UserEvent e) {
	handler.handleKeyEvent(e);
    }
}
