package net.beiker.xletview.event;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import net.beiker.xletview.util.Debug;

/**
 * 
 * @author Martin Sveden
 */
public class FocusListenerDummy implements FocusListener{

	private static final FocusListenerDummy THE_INSTANCE = new FocusListenerDummy();
	
	public static final FocusListenerDummy getInstance(){
		return THE_INSTANCE;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	public void focusGained(FocusEvent e) {
		Debug.write(this, "focusGained-" + e);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	public void focusLost(FocusEvent e) {
		//Debug.write(this, "focusLost-" + e);		
	}

}
