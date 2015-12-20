/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.dvb.user ;

import java.util.Hashtable;
import java.util.Vector;


public abstract class Preference {   
    String name = null;
    String values[] = null;
    static final String separator = " #~@~# ";
    static boolean defaultNameHashInited = false;
    static Hashtable defaultNameHash = null;
    private String defaultNames[] = {
        "user language", "User Language", "audio language", "Audio Language", "parental rating", "Parental Rating", "user name", "User Name", "user address", "User Address", 
        "user @", "User @", "country code", "Country Code", "default font size", "Default Font Size", "audio format", "Audio Format", "audio precedence", "Audio Precedence", 
        "rc.dnsserver", "RC.DNSServer", "rc.password", "RC.Password", "rc.target", "RC.Target", "rc.username", "RC.UserName", "subtitle language", "Subtitle Language", 
        "subtitle on", "Subtitle On", "user.timezone", "user.timezone", "post code", "Post Code", "PPV Password Confirmation","off","Reminder Alarm Before","off"
    };

    protected Preference()
    {
        if(!defaultNameHashInited)
            initNameHash();
    }

    protected void initNameHash()
    {
        defaultNameHash = new Hashtable();
        for(int i = 0; i < defaultNames.length; i += 2)
            defaultNameHash.put(defaultNames[i], defaultNames[i + 1]);

        defaultNameHashInited = true;
    }

    protected String normalizeName(String s)
    {
        String s1 = s.toLowerCase();
        String s2 = (String)defaultNameHash.get(s1);
        return s2 != null ? s2 : s1;
    }

    public Preference(String s, String s1)
    {
        if(!defaultNameHashInited)
            initNameHash();
        name = normalizeName(s);
        values = new String[1];
        values[0] = s1;
    }

    public Preference(String s, String as[])
    {
        if(!defaultNameHashInited)
            initNameHash();
        name = normalizeName(s);
        values = as;
    }

    public synchronized void add(String s)
    {
        int i = findIndex(s, values);
        if(i != -1)
        {
            if(i == values.length - 1)
                return;
            int j = values.length;
            String as1[] = new String[j];
            if(i != 0)
                System.arraycopy(values, 0, as1, 0, i);
            System.arraycopy(values, i + 1, as1, i, j - i - 1);
            as1[j - 1] = values[i];
            values = as1;
            return;
        } else
        {
            String as[] = new String[values.length + 1];
            System.arraycopy(values, 0, as, 0, values.length);
            as[values.length] = s;
            values = as;
            return;
        }
    }

    public void add(String as[])
    {
        for(int i = 0; i < as.length; i++)
            add(as[i]);

    }

    public synchronized void add(int i, String s)
    {
        int j = findIndex(s, values);
        if(j != -1)
            remove(s);
        String as[] = new String[values.length + 1];
        if(i < 0)
            i = 0;
        if(i > values.length)
            i = values.length;
        System.arraycopy(values, 0, as, 0, i);
        as[i] = s;
        System.arraycopy(values, i, as, i + 1, values.length - i);
        values = as;
    }

    public synchronized String[] getFavourites()
    {
        return values;
    }

    public synchronized String getMostFavourite()
    {
        if(values.length > 0)
            return values[0];
        else
            return null;
    }

    public String getName()
    {
        return name;
    }

    public synchronized int getPosition(String s)
    {
        return findIndex(s, values);
    }

    private int findIndex(String s, String as[])
    {
        if(as == null)
            return -1;
        for(int i = 0; i < as.length; i++)
            if(as[i].equals(s))
                return i;

        return -1;
    }

    public synchronized boolean hasValue()
    {
        return values != null && values.length != 0;
    }

    public synchronized void remove(String s)
    {
        int i = findIndex(s, values);
        if(i == -1)
        {
            return;
        } else
        {
            String as[] = new String[values.length - 1];
            System.arraycopy(values, 0, as, 0, i);
            System.arraycopy(values, i + 1, as, i, values.length - i - 1);
            values = as;
            return;
        }
    }

    public synchronized void removeAll()
    {
        values = new String[0];
    }

    public void setMostFavourite(String s)
    {
        remove(s);
        add(0, s);
    }

    public synchronized String toString()
    {
        String s = name;
        if(values == null)
            return s;
        for(int i = 0; i < values.length; i++)
            s = s + " #~@~# " + values[i];

        return s;
    }

    protected synchronized void fromString(String s)
    {
        int i = 0;
        i = s.indexOf(" #~@~# ", i);
        if(i == -1)
        {
            values = new String[0];
            return;
        }
        name = s.substring(0, i);
        Vector vector = new Vector();
        int j = 0;
        do
        {
            j++;
            int k = i;
            if(i == -1)
                break;
            i = s.indexOf(" #~@~# ", i + " #~@~# ".length());
            String s1;
            if(i == -1)
                s1 = s.substring(k + " #~@~# ".length());
            else
                s1 = s.substring(k + " #~@~# ".length(), i);
            if(j == 1 && s1.length() == 1 && s1.equalsIgnoreCase(" "))
            {
                break;
            }
            vector.addElement(s1);
        } while(true);
        if(vector.size() == 0)
        {
            values = new String[0];
        } else
        {
            values = new String[vector.size()];
            vector.copyInto(values);
        }
    }

}






