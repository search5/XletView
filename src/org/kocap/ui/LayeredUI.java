package org.kocap.ui;

public interface LayeredUI extends LayeredUIInfo {

    public static final int GRAPHICS_DEFAULT = 0;

    public static final int GRAPHICS_720_480 = 1;

    public static final int GRAPHICS_960_540 = 2;

    public void activate();

    public void deactivate();
}