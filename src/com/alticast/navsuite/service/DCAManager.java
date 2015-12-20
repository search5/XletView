package com.alticast.navsuite.service;

public class DCAManager {
    private static DCAManager instance;
    public static DCAManager getInstance(){
	if(instance == null){
	    instance = new DCAManager();
	}
	
	return instance;
    }
    
    public void enableDCA(boolean result){
	
    }
    
}
