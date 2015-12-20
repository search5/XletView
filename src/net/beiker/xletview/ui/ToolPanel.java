/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package net.beiker.xletview.ui;

import java.awt.AWTEventMulticaster;
import java.awt.Container;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.beiker.xletview.event.EventManager;
import net.beiker.xletview.util.Debug;
import net.beiker.xletview.xlet.XletManager;

import org.havi.ui.event.HRcEvent;

/**
 *  Description of the Class
 *
 *@author     beiker
 *@created    den 11 maj 2003
 */
public class ToolPanel extends Container implements  MouseListener {

    private static ToolPanel THE_INSTANCE;
    private JPanel contentPane;
    private java.util.EventListener eventListener;
    private Object[] keyListeners;
	private Component listenerComponent;

    public static ToolPanel getInstance() {
        if (THE_INSTANCE == null) {
            THE_INSTANCE = new ToolPanel();
        }
        return THE_INSTANCE;
    }

    public Dimension getPreferredSize(){
        return new Dimension(140, 40);
    }


    /**
     *  Constructor for the ButtonPanel object
     */
    private ToolPanel() {
        
        Cursor hand = new Cursor(Cursor.HAND_CURSOR);

        // numbers -->
        Container numCont = new Container();
        GridLayout numGL = new GridLayout(1, 4);
        numGL.setHgap(2);
        numGL.setVgap(2);
        numCont.setLayout(numGL);

        KeyButton one = new KeyButton(this.getClass().getResource("/img/button_1.png"), HRcEvent.VK_1);
        one.addMouseListener(this);
        numCont.add(one);

        KeyButton two = new KeyButton(this.getClass().getResource("/img/button_2.png"), HRcEvent.VK_2);
        two.addMouseListener(this);
        numCont.add(two);

        KeyButton three = new KeyButton(this.getClass().getResource("/img/button_3.png"), HRcEvent.VK_3);
        three.addMouseListener(this);
        numCont.add(three);

        KeyButton four = new KeyButton(this.getClass().getResource("/img/button_4.png"), HRcEvent.VK_4);
        four.addMouseListener(this);
        numCont.add(four);

        
        setLayout(new FlowLayout());
        add(numCont);

    }



    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    public void mousePressed(MouseEvent arg0) {
        XletManager xletManager = XletManager.getInstance();
        xletManager.destroyActiveXlet();
        //xletManager.setXlet("com.mindonmove.mhptester.MHPTesterXlet");
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

}
