package org.ocap.hardware;

public class Host {

    private static Host instance = new Host();
    
    public static Host getInstance() {
	return instance;
    }
    
    public void addPowerModeChangeListener(PowerModeChangeListener listener) {
	
    }
}
