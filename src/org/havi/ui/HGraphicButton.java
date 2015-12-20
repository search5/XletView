/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Svedén
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.havi.ui;

import java.awt.Image;

import net.beiker.xletview.helper.HActionableHelper;
import net.beiker.xletview.util.Debug;

/**
 * 
 * 
 * @author Martin Sveden
 * @statuscode 4
 */
public class HGraphicButton extends HIcon implements HActionable{    

	
	private HActionableHelper helper;
	
	private static HGraphicLook defaultHLook    = new HGraphicLook();
	
    public HGraphicButton(){
    	super();
    	init();    	
    }

    public HGraphicButton(Image image){
    	super(image);
    	init();
    }
    
    public HGraphicButton(Image image, int x, int y, int width, int height){
    	super(image, x, y, width, height);
    	init();
    }

    public HGraphicButton(Image imageNormal, Image imageFocused, Image imageActioned, int x, int y, int width, int height){
    	super(imageNormal, x, y, width, height);
    	setGraphicContent(imageFocused, HVisible.FOCUSED_STATE);
    	setGraphicContent(imageActioned, HVisible.ACTIONED_STATE);
    	init();
    }

    public HGraphicButton(Image imageNormal, Image imageFocused,Image imageActioned){
    	this(imageNormal, imageFocused, imageActioned, 0, 0, 0, 0);
    }
    // constructors end //
    
    private void init(){
    	helper = new HActionableHelper(this);
    	Debug.write(this, "HGraphicButton - init");
    }

    public static void setDefaultLook(HGraphicLook hlook){
    	HGraphicButton.defaultHLook = hlook;
    }

    public static HGraphicLook getDefaultLook(){
        return HGraphicButton.defaultHLook;
    }

//    public void setMove(int keyCode, HNavigable target){
//    	helper.setMove(keyCode, target);
//    }

//    public HNavigable getMove(int keyCode){
//    	return helper.getMove(keyCode);
//    }

//    public void setFocusTraversal(HNavigable up, HNavigable down, HNavigable left, HNavigable right){
//    	helper.setFocusTraversal(up, down, left, right);
//    }

//    public boolean isSelected(){
//    	return helper.isSelected();
//    }

//    public void setGainFocusSound(HSound sound){
//    	helper.setGainFocusSound(sound);
//    }

//    public void setLoseFocusSound(HSound sound){
//    	helper.setLoseFocusSound(sound);
//    }

//    public HSound getGainFocusSound(){
//    	return helper.getGainFocusSound();
//    }

//    public HSound getLoseFocusSound(){
//    	return helper.getLoseFocusSound();
//    }

//    public synchronized void addHFocusListener(org.havi.ui.event.HFocusListener listener){
//    	helper.addHFocusListener(listener);
//    }

//    public synchronized void removeHFocusListener(org.havi.ui.event.HFocusListener listener){
//    	helper.removeHFocusListener(listener);
//    }

//    public int[] getNavigationKeys(){
//    	return helper.getNavigationKeys();
//    }

    /*
     Overloaded from HVisible, is true for HNavigable
     */
//    public boolean isFocusTraversable() {
//    	return true;
//    }
    
    /**
     * Since the Component will not get focus unless there is
     * a FocusListener registered we "secretly" add one in the
     * helper.
     * We don't want this secret one to be returned when someone
     * asks for the FocusListener objects.
     * This mehod overrides Component.getFocusListeners()
     * and takes care of that.
     */
//    public synchronized FocusListener[] getFocusListeners(){
//    	return helper.getFocusListeners();
//    }
    

//    public void processFocusEvent(FocusEvent e){
//    	super.processFocusEvent(e);
//    	HFocusEvent event = new HFocusEvent(this, e.getID());
//    	processHFocusEvent(event);
//    	
//    }

    
//    public void processHFocusEvent(HFocusEvent evt) {
//    	int state = getInteractionState();
//    	int newState = helper.getHFocusEventResult(evt);
//    	Debug.write(this, "---processHFocusEvent " + evt);
//    	if(state != newState){
//    		setInteractionState(newState);
//    	}
//    }

    public void addHActionListener(org.havi.ui.event.HActionListener listener){
        helper.addHActionListener(listener);
    }

    public void removeHActionListener(org.havi.ui.event.HActionListener listener){
        helper.removeHActionListener(listener);
    }

    public void setActionCommand(String command){
        helper.setActionCommand(command);
    }

    public void setActionSound(HSound sound){
    	helper.setActionSound(sound);
    }

    public HSound getActionSound(){
        return helper.getActionSound();
    }

    public void processHActionEvent(org.havi.ui.event.HActionEvent evt){
    	//Debug.write(this, "processHActionEvent");
    	int state = getInteractionState();
    	int newState = helper.getHActionEventResult(evt);
    	
    	if(state != newState){
    		setInteractionState(newState);
    	}
    }

    public java.lang.String getActionCommand(){
        return helper.getActionCommand();
    }
}
