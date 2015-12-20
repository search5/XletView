/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.xlet;

import java.io.IOException;
import java.util.Vector;

import net.beiker.xletview.classloader.XletClassLoader;
import net.beiker.xletview.download.DownloadEvent;
import net.beiker.xletview.download.DownloadEventListener;
import net.beiker.xletview.download.Downloader;
import net.beiker.xletview.media.BackgroundLayer;
import net.beiker.xletview.media.ScreenContainer;
import net.beiker.xletview.util.Constants;
import net.beiker.xletview.util.Debug;
import net.beiker.xletview.util.MemoryPrinter;

import org.havi.ui.HScene;

import xjavax.tv.graphics.TVContainer;
import xjavax.tv.util.TVTimerImpl;
import xjavax.tv.xlet.Xlet;
import xjavax.tv.xlet.XletContext;
import xjavax.tv.xlet.XletStateChangeException;

public class XletManager implements Runnable, DownloadEventListener{

    
	private static XletManager THE_INSTANCE;
    private Vector xletContexts;
    private XletContextImpl activeContext;
    private static ThreadGroup threadGroup;
    private HScene scene;
    
    private Class xletClass;
    private String xletHome;
    private String xletClassName;

    private ThreadGroup xletThreadGroup;
    private Thread xletThread;
    private Thread downloadThread;


    public XletContext getXletContext(){
    	return (XletContext)xletContexts.get(0);
    }
    public static XletManager getInstance() {
        if (THE_INSTANCE == null) {
            THE_INSTANCE = new XletManager();
        }
        return THE_INSTANCE;
    }

    private XletManager() {
        xletContexts = new Vector();
        xletThreadGroup = getThreadGroup();//new ThreadGroup(Thread.currentThread().getThreadGroup(), "xletThreadGroup");
    }

    public void setXlet(String xletHome, String xletClassName) {
        this.xletHome = xletHome;
        this.xletClassName = xletClassName;
        
        /*
         * The downloader that is/was experimental,
         * might be used in the future to
         * download the app to the "box"
         *  
         * downloadThread = new Thread(this);
         * downloadThread.start();
         */
        
        /*
         * If the downloader is not used this
         * goes here
         */
        runXlet();
    }

    private void runXlet() {
       

        destroyActiveXlet();

        // create the new xlet
        ClassLoader xletLoader = null;

        try{
            xletLoader = new XletClassLoader(xletHome);   
        	//MHPClassLoader mhpLoader = new MHPClassLoader(MHPClassLoader.MHP_CLASS_URLS);
        	//xletLoader = new XletClassLoader(xletHome, mhpLoader);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        Debug.info("loading Xlet... [" + xletClassName + "]");
        try {

            Debug.write(this, xletClassName);
            //xletClass = Class.forName(xletClassName, true, loader);
            xletClass = xletLoader.loadClass(xletClassName);
            xletThread = new Thread(xletThreadGroup, this, "xletThread-" + xletContexts.size());
            xletThread.start();
            Debug.info("XLET started... [" + xletClassName + "]");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            cleanup();
            Debug.info("Application not loaded!");
        }
        catch (Exception e) {
            e.printStackTrace();
            cleanup();
        }


    }

    public void reloadActiveXlet() {
        if (activeContext != null) {
        	Debug.info("reloading xlet " + xletHome + ", " + xletClassName);
            setXlet(xletHome, xletClassName);
        }
    }

    public void addScene(HScene scene) {
        this.scene = scene;
        ScreenContainer.getInstance().addToXletContainer(scene);
        //ScreenContainer.getInstance().getXletContainer().requestFocus();
        
    }

    public HScene getScene() {
        return scene;
    }

    public void run() {
        if(downloadThread != null){
            try {
                Downloader dwnLoader = new Downloader(Constants.PATH_APPDIR);
                dwnLoader.addDownloadEventListener(this);
                dwnLoader.download(xletHome);
            }
            catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
        }
        else if(xletThread != null){
            initXlet();
            if (activeContext != null) {
    
                resumeRequest(activeContext);
                /* keep the thread alive until destroy */
                synchronized (this) {
                    try {
                        wait();
                    }
                    catch (Exception ex) {
                        System.out.println("error: " + ex);
                    }
                }
            }
            Debug.write(this, "xxxxx" + xletThread.getName() + " just died xxxxx");
            xletThread = null;
        }
    }

    public void destroyActiveXlet() {
        // only if an Xlet is running
        if (activeContext != null) {
        	Debug.write(this, "CURRENT THREAD IS " + Thread.currentThread().getName());

        	Debug.write(this, "###############" + Thread.activeCount() + " threads active ###############");
            synchronized (this) {
                try {
                    notifyAll();
                }
                catch (Exception ex) {
                    System.out.println("error: " + ex);
                }
            }
            destroyXlet(activeContext);

            // check if sub threads are alive and if so try to kill them
            Thread[] threads = new Thread[Thread.activeCount()];
            int threadCount = Thread.enumerate(threads);
            for (int i = 0; i < threadCount; i++) {
                ThreadGroup group = threads[i].getThreadGroup();
                Debug.write(this, "subthread=" + threads[i].getName() + " in group=" + threads[i].getThreadGroup());
                //threads[i].stop();
                if (group == xletThreadGroup && threads[i] != xletThread) {
                    //logger.debug("subthread=" + threads[k].getName() +  " in group=" + threads[k].getThreadGroup());
                    //threads[k].interrupt();
                	Debug.write(this, threads[i] + " is still alive, trying to stop it... ");
                    threads[i] = null;
                    //threads[i].stop(); unsafe
                }
            }
            activeContext = null;
            Debug.write(this, "###############" + Thread.activeCount() + " threads active ###############");
        
            /*
             * Remove all UserEvent listeners, just to not 
             * have any references to this destroyed Xlet
             */
            org.dvb.event.EventManager.getInstance().removeAllUserEventListeners();
            
            /*
             * Remove all from the TVTimer queue
             */
            TVTimerImpl.getInstance().descheduleAll(this);
        }
        MemoryPrinter.print();
        System.err.println("running gc...");
        System.gc();
        System.err.println("after gc...");
        MemoryPrinter.print();
    }

    private void initXlet() {
        Object newObj = null;

        try {
            newObj = xletClass.newInstance();
            Xlet xlet = null;
            try {
                xlet = (Xlet) newObj;    
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
            XletContextImpl xci = new XletContextImpl(this, xlet);
            xletContexts.add(xci);
            xlet.initXlet(xci);
            activeContext = xci;
            xci.setState(XletContextImpl.INITIALIZED);
        }
        catch (Exception e) {
        	e.printStackTrace();
            cleanup();
        }
        catch (NoClassDefFoundError e) {
        	e.printStackTrace();
            cleanup();
            Debug.info("Application not loaded!");
        }
        catch (Error e) {
        	e.printStackTrace();
            cleanup();
        }

    }

    private void destroyXlet(XletContextImpl context) {
        //logger.debug("占�###占쏙옙" + Thread.currentThread().getName());

        if (activeContext != null) {
        	Debug.write(this, "About to destroy Xlet [" + context.getXlet().toString() + "]");
            TVContainer.getRootContainer(context).removeAll();
            Xlet xlet = activeContext.getXlet();
            try {
                xlet.destroyXlet(true);
            }
            catch (XletStateChangeException e) {
                //                logger.debug("###############\n###############\n###############\n###############\n");
                cleanup();
                /* XletStateChangeException - is thrown if the Xlet wishes to continue to execute 
                 * (Not enter the Destroyed  state). This exception is ignored if unconditional is equal to true.
                 * So, we ignore it.
                 * */
            }
            catch (NoClassDefFoundError e) {
                e.printStackTrace();
                cleanup();
            }
            catch (Exception e) {
                e.printStackTrace();
                cleanup();
            }
            BackgroundLayer.getInstance().removeAll();
            ScreenContainer.getInstance().repaint();
            //logger.debug("compcount=" + BackgroundLayer.getInstance().getComponentCount());
            TVContainer.getRootContainer(context).repaint();
            Debug.info("Xlet destroyed");
        }

        //        Thread[] threads = new Thread[Thread.activeCount()];
        //        int threadCount = Thread.enumerate(threads);
        //        for(int i = 0; i < threadCount; i++){            
        //            logger.debug("thread=" + threads[i].getThreadGroup().getName());
        ////            ThreadGroup tGroup = threads[i].getThreadGroup();
        ////            if(tGroup != null){
        ////                logger.debug("this group=" + Thread.currentThread().getThreadGroup() + ", thread's group" + threads[i].getThreadGroup());
        ////            }
        //        }
        //        System.out.println("");
        //        System.out.println("");
        //        xletThreadGroup.list();
        //        
        //        
        //      Thread[] threads = new Thread[xletThreadGroup.activeCount()];
        //      int threadCount = Thread.enumerate(threads);
        //      logger.debug("threadCount=" + threadCount);
        //      for(int i = 0; i < threadCount; i++){            
        //          logger.debug("thread=" + threads[i].getName());
        //      }        
        //        System.out.println("");
        //        System.out.println("");        

        Debug.write(this, "Threads running=" + Thread.activeCount());
    }

    void notifyDestroyed(XletContextImpl xc) {
        // remove the reference to the context
        xletContexts.remove(xc);

        // if the context was the active one, set it to null 
        if (activeContext == xc) {
            activeContext = null;
        }
        MemoryPrinter.print();
        System.err.println("running gc...");
        System.gc();
        System.err.println("after gc...");
        MemoryPrinter.print();
    }

    void notifyPaused(XletContextImpl xc) {
        if (xc == activeContext) {
            activeContext.getXlet().pauseXlet();
            activeContext.setState(XletContextImpl.PAUSED);
            activeContext = null;
        }
    }

    void resumeRequest(XletContextImpl xc) {
        if (activeContext.getState() == XletContextImpl.INITIALIZED || activeContext.getState() == XletContextImpl.PAUSED) {
            if (xc != activeContext) {
                activeContext.getXlet().pauseXlet();
                activeContext.setState(XletContextImpl.PAUSED);
                activeContext = null;
            }
            activeContext = xc;
            try {
            	
            	/*
            	 * request focus on the ScreenContainer, because
            	 * if a component in the previous loaded Xlet
            	 * had the focus no component has the focus right
            	 * now and the event mechanism doesn't work
            	 */  
            	ScreenContainer.getInstance().requestFocus();
                
            	activeContext.getXlet().startXlet();
                activeContext.setState(XletContextImpl.ACTIVE);
                
            }
            catch (XletStateChangeException e) {
                e.printStackTrace();
                cleanup();
            }
        }
        else if (xc.getState() == XletContextImpl.ACTIVE) {
        	Debug.write(this, "Xlet was already active");
        }
    }

    private void cleanup() {
    	Debug.write(this, "Something went wrong, cleaning up errors...");
        ScreenContainer.getInstance().getXletContainer().removeAll();
        ScreenContainer.getInstance().getXletContainer().repaint();
        xletContexts.removeAllElements();
        scene = null;
        activeContext = null;
        xletClass = null;
    }

    public ThreadGroup getThreadGroup() {
        if (threadGroup == null) {
            threadGroup = new ThreadGroup("xlet thread group") {
                public void uncaughtException(Thread t, Throwable e) {
                    if (!(e instanceof ThreadDeath)) {
                        e.printStackTrace();
                        System.err.println(">>>>> error <<<<<");
                        cleanup();
                    }
                }
            };
        }
        return threadGroup;
    }

    public void downloadUpdate(DownloadEvent e) {
        //logger.debug(e.getProcent() + "%" + ", file=" + e.getFileName() );  
        
        //logger.info(e.getFileName() + ", " + e.getProcent() + "% finished");
        ScreenContainer.showProgressBar();
        ScreenContainer.updateProgressBar(e.getProcent());
        if(e.getProcent() == 100){
            e.getDownloader().removeDownloadEventListener(this);
            
            // make sure the downloadThread is null so it's not downloaded twice
            downloadThread = null;            
            runXlet();
            ScreenContainer.hideProgressBar();
        }      
    }
}
