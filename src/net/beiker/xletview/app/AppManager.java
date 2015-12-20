/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.app;

import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

import net.beiker.xletview.util.Settings;
import net.beiker.xletview.util.Util;
import net.n3.nanoxml.IXMLElement;
import net.n3.nanoxml.IXMLParser;
import net.n3.nanoxml.IXMLReader;
import net.n3.nanoxml.StdXMLReader;
import net.n3.nanoxml.XMLParserFactory;

/**
 * @author Martin Sveden
 *  
 */
public class AppManager {

    private static AppManager THE_INSTANCE;
    private File appFile;
    private AppGroup defaultGroup;

    private AppManager(){        
	appFile = new File( Settings.getProperty("file.applications") ); 
//        appFile = new File( Util.getAbsolutePath(AppManager.class, Settings.getProperty("file.applications")) ); 
        defaultGroup = new AppGroup("Default group");
        parse();
    }
    
    public static AppManager getInstance(){
        if(THE_INSTANCE == null){
            THE_INSTANCE = new AppManager();
        }
        return THE_INSTANCE;
    }

    private void parse(){
        IXMLElement xml = null;
        try{
            IXMLParser parser = XMLParserFactory.createDefaultXMLParser();
            //FileInputStream in = new FileInputStream(new File("config/applications.xml"));
            
            FileInputStream in = new FileInputStream(appFile);
            IXMLReader reader = new StdXMLReader(in);
            parser.setReader(reader);
            xml = (IXMLElement) parser.parse();  
            if(xml != null){                
                resolve(xml, defaultGroup);              
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
//            Debug.write(this,"####");
//            Debug.error(this, "Error reading " + appFile.getPath());            
        }
    }
    
    private void resolve(IXMLElement element, AppGroup group){

        // get the subgroups of this group
        Vector subGroups = element.getChildrenNamed("GROUP");
        for(int i = 0; i < subGroups.size(); i++){
            IXMLElement elm = (IXMLElement)subGroups.get(i);
            AppGroup newGroup = new AppGroup(elm.getAttribute("NAME"));            
            group.addChild(newGroup);
            resolve(elm, newGroup);            
        }
        
        // get the applications in this group
        Vector apps = element.getChildrenNamed("APPLICATION");
        for(int i = 0; i < apps.size(); i++){
            
            App app = null;
            IXMLElement elm = (IXMLElement) apps.get(i);
            String name = "";
            String path = "";
            String xlet = "";
            try {
                name = ((IXMLElement) elm.getChildrenNamed("NAME").get(0)).getContent();
                path = ((IXMLElement) elm.getChildrenNamed("PATH").get(0)).getContent();
                xlet = ((IXMLElement) elm.getChildrenNamed("XLET").get(0)).getContent();                    
                
                app = new App(name, path, xlet);
                group.addApp(app);
//                projects.add(project);
            }
            catch (Exception e) {
                // we will have exceptions, the above is made that way so don't output the errors                
            }
        }
    }
    
    public AppGroup getDefaultGroup(){
        return defaultGroup;
    }
    
    public void update(){        
        AppWriter.write(appFile, defaultGroup);
        
    }
    
    public static void main(String[] args){
        AppManager.getInstance().parse();
        AppGroup defaultGroup = AppManager.getInstance().getDefaultGroup();
        
        for(int i = 0; i < defaultGroup.getApps().size(); i++){
            App app = (App) defaultGroup.getApps().get(i);            
        }
        for(int i = 0; i < defaultGroup.getSubGroups().size(); i++){
            AppGroup app = (AppGroup) defaultGroup.getSubGroups().get(i);
        }
//        Debug.write(AppManager.class, defaultGroup.getName());
//        Debug.write(AppManager.class, "" + defaultGroup.getSubGroups().size());
        
    }

}
