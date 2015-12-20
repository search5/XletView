/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   Settings.java

package com.alticast.navsuite.core;

import com.alticast.navsuite.core.bsi.BsiSettings;


public abstract class Settings
{

    public Settings()
    {
    }

    public static Settings getInstance()
    {
        return new BsiSettings();
    }

    public abstract void setUserPIN(String s);

    public abstract boolean checkUserPIN(String s);

    public abstract void setParentalRating(int i);

    public abstract int getParentalRating();
}