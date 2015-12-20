/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package net.beiker.xletview.xlet;


import net.beiker.xletview.media.ScreenContainer;
import net.beiker.xletview.util.Debug;
import xjavax.tv.service.selection.ServiceContextImpl;
import xjavax.tv.xlet.Xlet;
import xjavax.tv.xlet.XletContext;

public class XletContextImpl implements XletContext{
	public static final String CONTAINER = "javax.tv.xlet.container";
	public static final String SERVICE_CONTEXT = "javax.tv.xlet.service_context";
	public static final String ROOT_CONTAINER = "javax.tv.xlet.root_container";
	
    public static final int INITIALIZED = 0;
    public static final int ACTIVE     = 2;
    public static final int PAUSED      = 3;
    public static final int DESTROYED   = -1;
    private int state;
    
    private XletManager manager;
    private Xlet xlet;
    private ClassLoader classLoader;
    private String[] xletArgs = new String[]{""};

    public XletContextImpl(XletManager manager, Xlet xlet){
        this.manager = manager;
        this.xlet    = xlet;              
    }
    
    public XletContextImpl(XletManager manager, Object xlet){
        this.manager = manager;
        this.classLoader = xlet.getClass().getClassLoader();
        this.xlet    = (Xlet)xlet;  
    }

    /* (non-Javadoc)
     * @see xjavax.tv.xlet.XletContext#getXletProperty(java.lang.String)
     */
    public Object getXletProperty(String key){
        Debug.write(this, "getXletProperty(" + key + ")");
        if(key.equals(XletContext.ARGS)){
        	return xletArgs;        	
        }
        else if(key.equals(CONTAINER)){
            return ScreenContainer.getInstance().getXletContainer();
        }
        else if(key.equals(SERVICE_CONTEXT)){
            return ServiceContextImpl.getInstance();
        }
        else
            return null;
    }

    /* (non-Javadoc)
     * @see xjavax.tv.xlet.XletContext#notifyDestroyed()
     */
    public void notifyDestroyed(){
        setState(DESTROYED);
        manager.notifyDestroyed(this);
    }
    
    /* (non-Javadoc)
     * @see xjavax.tv.xlet.XletContext#notifyPaused()
     */
    public void notifyPaused(){
        setState(PAUSED);
        manager.notifyPaused(this);
    }
    
    /* (non-Javadoc)
     * @see xjavax.tv.xlet.XletContext#resumeRequest()
     */
    public void resumeRequest(){
        manager.resumeRequest(this);
    }
    
    int getState() {
        return state;
    }

    void setState(int state) {
        this.state = state;
    }
    
    Xlet getXlet(){
        Debug.write(this, "xlet=" + xlet);
        return xlet;
    }
    

}
