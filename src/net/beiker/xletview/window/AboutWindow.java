/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package net.beiker.xletview.window;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.net.URL;
import net.beiker.xletview.util.Debug;

public class AboutWindow extends JDialog implements HyperlinkListener{

    public AboutWindow(Frame owner){
        super(owner, false);

        //JPanel container = new JPanel();

        URL webPage = null;
        String html = "";
        try{
            webPage = new java.net.URL("http://xletview.sourceforge.net/client/aboutwindow.html");
            webPage.openStream();

           java.io.BufferedReader in = new java.io.BufferedReader(
                        new java.io.InputStreamReader(webPage.openStream())
                        );

            String inputLine;

            while ((inputLine = in.readLine()) != null){
                html += inputLine;
            }

            in.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }



        JTextPane htmlPanel = new JTextPane();
        htmlPanel.setPreferredSize(new Dimension(550, 500));
        htmlPanel.setContentType( "text/html" );
        htmlPanel.setEditable(false);
        htmlPanel.setText(html);
        htmlPanel.addHyperlinkListener(this);

        JScrollPane scroll = new JScrollPane(htmlPanel);

        setContentPane(scroll);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                doClose();
            }
        });

        pack();
        //setSize(100, 100);
        GraphicsConfiguration gc = this.getGraphicsConfiguration();
        Rectangle bounds = gc.getBounds();
        int x = (int) ( bounds.getWidth() - this.getWidth() ) /2 ;
        int y = (int) ( bounds.getHeight() - this.getHeight() ) /2;
        setLocation(x, y );
        show();
    }

    public void hyperlinkUpdate(HyperlinkEvent e){
        HyperlinkEvent.EventType eventType = e.getEventType();
        if(eventType == HyperlinkEvent.EventType.ACTIVATED){
            Debug.write(this, "" + e.getURL());
            try{
                String url = e.getURL().toString();
                Debug.write(this, "url = " + url);
                String[] s = {"C:\\Program Files\\Internet Explorer\\iexplore.exe", url};
                Runtime.getRuntime().exec(s);
            }
            catch(Exception ex){
                Debug.error(ex);
            }
        }
    }

    private void doClose(){
        this.dispose();
    }

}
