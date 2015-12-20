package org.kocap.ui;

import java.awt.Rectangle;

public abstract class LayeredUIManager {

    private static LayeredUIManagerImpl layeredUIManagerImpl = new LayeredUIManagerImpl();

    public LayeredUIManager() {
    }

    public static synchronized LayeredUIManager getInstance() {
	return layeredUIManagerImpl;
    }

    public abstract LayeredUI createLayeredDialog(String s, int i, boolean flag, LayeredWindow layeredwindow, Rectangle rectangle,
	    LayeredKeyHandler layeredkeyhandler);

    public abstract LayeredUI createLayeredDialog(String s, int i, boolean flag, LayeredWindow layeredwindow, Rectangle rectangle,
	    LayeredKeyHandler layeredkeyhandler, int j);

    public abstract LayeredUI createLayeredWindow(String s, int i, boolean flag, LayeredWindow layeredwindow, Rectangle rectangle);

    public abstract LayeredUI createLayeredWindow(String s, int i, boolean flag, LayeredWindow layeredwindow, Rectangle rectangle, int j);

    public abstract LayeredUI createLayeredKeyHandler(String s, int i, LayeredKeyHandler layeredkeyhandler);

    public abstract LayeredUI createLayeredKeyHandler(String s, int i, boolean flag, LayeredKeyHandler layeredkeyhandler);

    public abstract LayeredUI createLayeredKeyHandler(String s, int i, LayeredKeyHandler layeredkeyhandler, int j);

    public abstract void disposeLayeredUI(LayeredUI layeredui);

    public abstract LayeredUIInfo[] getAllLayeredUIInfos();

    public abstract LayeredUI[] getAllLayeredUIs() throws SecurityException;
}