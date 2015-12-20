package com.alticast.navsuite.svc;

import java.awt.Container;

import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HSceneTemplate;

public class OverlappedUIManager {

    private static OverlappedUIManager instance = new OverlappedUIManager();

    private UserEventController userEventController = null;

    public static OverlappedUIManager getInstance() {
	return instance;
    }

    private OverlappedUIManager() {
    }

    public Container createOverlappedDialog(int priority, OverlappedDialogHandler handler) {
	userEventController = new UserEventController(handler);
	userEventController.addUserEventListener();
	HSceneFactory hfactory = HSceneFactory.getInstance();
	HSceneTemplate hst = new HSceneTemplate();
	hst.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, new org.havi.ui.HScreenDimension(1, 1), HSceneTemplate.REQUIRED);
	hst.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, new org.havi.ui.HScreenPoint(0, 0), HSceneTemplate.REQUIRED);
	HScene root = hfactory.getBestScene(hst);
	if (root == null) {
	    root = HSceneFactory.getInstance().getDefaultHScene();
	}
	return root;
    }

    public Container createOverlappedWindow(int priority, OverlappedWindowHandler handler) {
	return null;
    }

    public void removeOverlappedUI(Container container1) {
    }

    public static boolean isSystemKey(UserEvent event, boolean includeFPArrowKey) {
	return false;
    }

    public static boolean isHotKey(UserEvent event) {
	return false;
    }

    public static boolean isNumberKey(UserEvent event) {
	return false;
    }

    public static boolean isFrontPanelEvent(UserEvent event) {
	return false;
    }
}