package org.kocap.ui;

import java.awt.Rectangle;
import java.util.Hashtable;

public class LayeredUIManagerImpl extends LayeredUIManager {

    private UserEventController userEventController = null;

    private Hashtable hashTable = new Hashtable();

    @Override
    public LayeredUI createLayeredDialog(String name, int priority, boolean adaptable, LayeredWindow layeredwindow, Rectangle rectangle,
	    LayeredKeyHandler layeredkeyhandler) {

	LayeredUIImpl layerdUI = new LayeredUIImpl(name, priority, rectangle);
	hashTable.put(name, layerdUI);

	return layerdUI;
    }

    @Override
    public LayeredUI createLayeredDialog(String name, int priority, boolean adaptable, LayeredWindow layeredwindow, Rectangle rectangle,
	    LayeredKeyHandler layeredkeyhandler, int j) {
	return null;
    }

    @Override
    public LayeredUI createLayeredKeyHandler(String name, int priority, LayeredKeyHandler layeredkeyhandler) {
	userEventController = new UserEventController(layeredkeyhandler);
	userEventController.addUserEventListener();
	LayeredUIImpl layerdUI = new LayeredUIImpl(name, priority, null);
	return layerdUI;
    }

    @Override
    public LayeredUI createLayeredKeyHandler(String name, int priority, boolean flag, LayeredKeyHandler layeredkeyhandler) {
	userEventController = new UserEventController(layeredkeyhandler);
	userEventController.addUserEventListener();
	LayeredUIImpl layerdUI = new LayeredUIImpl(name, priority, null);
	return layerdUI;
    }

    @Override
    public LayeredUI createLayeredKeyHandler(String s, int i, LayeredKeyHandler layeredkeyhandler, int j) {
	return null;
    }

    @Override
    public LayeredUI createLayeredWindow(String s, int i, boolean flag, LayeredWindow layeredwindow, Rectangle rectangle) {
	return null;
    }

    @Override
    public LayeredUI createLayeredWindow(String s, int i, boolean flag, LayeredWindow layeredwindow, Rectangle rectangle, int j) {
	return null;
    }

    @Override
    public void disposeLayeredUI(LayeredUI layeredui) {

    }

    @Override
    public LayeredUIInfo[] getAllLayeredUIInfos() {
	return null;
    }

    @Override
    public LayeredUI[] getAllLayeredUIs() throws SecurityException {
	return null;
    }

}
