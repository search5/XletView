package org.kocap.ui;

import java.awt.Rectangle;

public interface LayeredUIInfo {

    public String getName();

    public int getPriority();

    public boolean isAdaptable();

    public Rectangle getBounds();

    public boolean isActive();
}