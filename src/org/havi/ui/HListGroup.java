/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Svedén
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.havi.ui;

import java.awt.Dimension;

public class HListGroup extends HVisible implements HItemValue{
    
    public static final int ITEM_NOT_FOUND       = -1;
    public static final int ADD_INDEX_END        = -1;
    public static final int DEFAULT_LABEL_WIDTH  = -1;    
    public static final int DEFAULT_LABEL_HEIGHT = -2;    
    public static final int DEFAULT_ICON_WIDTH   = -3;    
    public static final int DEFAULT_ICON_HEIGHT  = -4;
    
    public HListGroup(){
    }

    public HListGroup(HListElement[] items){
    }

    public HListGroup(HListElement[] items, int x, int y, int width, int height){
    }

    public void setLook(HLook hlook) throws HInvalidLookException{
    }

    public static void setDefaultLook(HListGroupLook look){
    }

    public static HListGroupLook getDefaultLook(){
        return (null);
    }

    public HListElement[] getListContent(){
        return (null);
    }

    public void setListContent(HListElement[] elements){
    }

    public void addItem(HListElement item, int index){
    }

    public void addItems(HListElement[] items, int index){
    }

    public HListElement getItem(int index){
        return (null);
    }

    public int getIndex (HListElement item){
        return (0);
    }

    public int getNumItems(){
        return (0);
    }

    public HListElement removeItem(int index){
        return null;
    }
    
    public void removeAllItems(){
    }

    public int getCurrentIndex(){
        return (0);
    }

    public HListElement getCurrentItem(){
        return (null);
    }

    public boolean setCurrentItem(int index){
        return (false);
    }

    public int[] getSelectionIndices(){
        return (null);
    }

    public HListElement[] getSelection(){
        return (null);
    }

    public void clearSelection(){
    }

    public int getNumSelected(){
        return (0);
    }

    public boolean getMultiSelection(){
        return (false);
    }

    public void setMultiSelection(boolean multi){
    }
    
    public void setItemSelected(int index, boolean sel){
    }

    public boolean isItemSelected(int index){
        return (false);
    }

    public int getScrollPosition(){
        return (0);
    }

    public void setScrollPosition(int scroll){
    }

    public Dimension getIconSize(){
        return (null);
    }

    public void setIconSize(Dimension size){
    }

    public Dimension getLabelSize(){
        return (null);
    }

    public void setLabelSize(Dimension size){
    }

    public void setMove(int keyCode, HNavigable target){
        return;
    }

    public HNavigable getMove(int keyCode){
        return(null);
    }

    public void setFocusTraversal(HNavigable up, HNavigable down, HNavigable left, HNavigable right){
        return;
    }

    public boolean isSelected(){
        return(false);
    }

    public void setGainFocusSound(HSound sound){
        return;
    }

    public void setLoseFocusSound(HSound sound){
        return;
    }

    public HSound getGainFocusSound(){
        return(null);
    }

    public HSound getLoseFocusSound(){
        return(null);
    }

    public void addHFocusListener(org.havi.ui.event.HFocusListener l){
        return;
    }

    public void removeHFocusListener(org.havi.ui.event.HFocusListener l){
        return;
    }

    public int[] getNavigationKeys(){
        return(null);
    }
     
    public void processHFocusEvent(org.havi.ui.event.HFocusEvent evt){
        return;
    }

    public int getOrientation(){
        return(0);
    }

    public void setOrientation(int orient){
        return;
    }

    public void addItemListener(org.havi.ui.event.HItemListener l){
        return;
    }

    public void removeItemListener(org.havi.ui.event.HItemListener l){
        return;
    }
    
    public void setSelectionSound(HSound sound){
        return;
    }
        
    public HSound getSelectionSound(){
        return(null);
    }
 
    public boolean getSelectionMode(){
        return(true);
    }

    public void setSelectionMode(boolean edit){
        return;
    }

    public void processHItemEvent(org.havi.ui.event.HItemEvent evt){
        return;
    }
}

