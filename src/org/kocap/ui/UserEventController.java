package org.kocap.ui;

import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.ocap.ui.event.OCRcEvent;

public class UserEventController implements UserEventListener {

    private LayeredKeyHandler handler = null;

    private UserEventRepository rep = null;

    public UserEventController(LayeredKeyHandler handler) {
	registerKeyEvent();
	this.handler = handler;
    }

    private void registerKeyEvent() {
	rep = new UserEventRepository("whatever");
	rep.addAllArrowKeys();
	rep.addAllNumericKeys();
	rep.addAllColourKeys();
	rep.addKey(OCRcEvent.VK_EXIT);
	rep.addKey(607);
	rep.addKey(OCRcEvent.VK_BACK);// 608
	rep.addKey(OCRcEvent.VK_ENTER);
	rep.addKey(OCRcEvent.VK_INFO);
	rep.addKey(OCRcEvent.VK_DELETE);
	rep.addKey(OCRcEvent.VK_PAGE_UP);
	rep.addKey(OCRcEvent.VK_PAGE_DOWN);
	rep.addKey(OCRcEvent.VK_RESERVE_2);
	rep.addKey(OCRcEvent.VK_RESERVE_3);
	rep.addKey(OCRcEvent.VK_RESERVE_1);
	rep.addKey(OCRcEvent.VK_GUIDE);
	rep.addKey(OCRcEvent.VK_MENU);
	rep.addKey(OCRcEvent.VK_SETTINGS);

	rep.addKey(OCRcEvent.VK_PAUSE); // 일시정지
	rep.addKey(OCRcEvent.VK_STOP); // 정지
	rep.addKey(OCRcEvent.VK_GO_TO_START); // 앞으로 챕터 이동
	rep.addKey(OCRcEvent.VK_REWIND); // 되감기
	rep.addKey(OCRcEvent.VK_PLAY); // 재생
	rep.addKey(OCRcEvent.VK_FAST_FWD); // 빨리 감기
	rep.addKey(OCRcEvent.VK_GO_TO_END); // 뒤로 챕터 이동

    }

    public void addUserEventListener() {
	EventManager.getInstance().addUserEventListener(this, rep);
    }

    public void removeUserEventListener() {
	EventManager.getInstance().removeUserEventListener(this);
    }

    @Override
    public void userEventReceived(UserEvent e) {
	handler.handleKeyEvent(e);
    }
}
