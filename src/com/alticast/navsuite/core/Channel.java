/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.alticast.navsuite.core;

import javax.tv.locator.Locator;

public abstract class Channel
{

    public Channel()
    {
    }

    public abstract String getName();

    public abstract String getLongName();

    public abstract Locator getLocator();

    public abstract int getNumber();

    public abstract int getMinorNumber();

    public abstract String getNumberString();

    public abstract String getProviderName();

    public abstract int getType();

    protected abstract void setType(int i);

    protected abstract void setTimeShiftedChannels(Channel achannel[]);

    public abstract Channel[] getTimeShiftedChannels();

    public abstract boolean belongsToRing(String s);

    public int compare(Channel ch)
    {
        if(getNumber() > ch.getNumber())
            return 1;
        if(getNumber() < ch.getNumber())
            return -1;
        if(getMinorNumber() > ch.getMinorNumber())
            return 1;
        return getMinorNumber() >= ch.getMinorNumber() ? 0 : -1;
    }

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public static final int TYPE_UNKNOWN = -1;
}