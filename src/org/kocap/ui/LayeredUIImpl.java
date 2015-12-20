package org.kocap.ui;

import java.awt.Rectangle;

public class LayeredUIImpl implements LayeredUI, LayeredUIInfo {

    private boolean isActive = false;

    private String name = null;

    private Rectangle bounds = null;

    private int priority = 0;

    public LayeredUIImpl(String name, int prioirty, Rectangle bounds) {
	this.name = name;
	this.priority = prioirty;
	this.bounds = bounds;
    }

    @Override
    public void activate() {
	isActive = true;
    }

    @Override
    public void deactivate() {
	isActive = false;
    }

    @Override
    public Rectangle getBounds() {
	return bounds;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public int getPriority() {
	return priority;
    }

    @Override
    public boolean isActive() {
	return isActive;
    }

    @Override
    public boolean isAdaptable() {
	return false;
    }

}
