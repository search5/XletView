package com.alticast.navsuite.core.bsi;

public class BsiOptionalControl {

    private static BsiOptionalControl bsiOptionalControl;
    
    public static BsiOptionalControl getInstance(){
	if(bsiOptionalControl == null){
	    bsiOptionalControl = new BsiOptionalControl();
	}
	return bsiOptionalControl;
    }
    
    public void setAppState(int state){
	
    }
    
    public void enableMacrovision(boolean result){
	
    }
}
