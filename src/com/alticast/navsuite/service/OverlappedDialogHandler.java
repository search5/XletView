package com.alticast.navsuite.service;

import org.dvb.event.UserEvent;

public interface OverlappedDialogHandler
    extends OverlappedUIHandler
{

    public abstract boolean handleKeyEvent(UserEvent userevent);
}