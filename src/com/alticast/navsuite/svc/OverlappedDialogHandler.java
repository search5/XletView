package com.alticast.navsuite.svc;

import org.dvb.event.UserEvent;

public interface OverlappedDialogHandler
    extends OverlappedUIHandler
{

    public abstract boolean handleKeyEvent(UserEvent userevent);
}