package org.ocap.net;

import org.davic.net.InvalidLocatorException;

public class OcapLocator extends org.davic.net.Locator {

	public OcapLocator(String url) {
		super(url);
	}

	public OcapLocator(int a, int v, int c) throws InvalidLocatorException {
		super(String.valueOf(a));
	}

	public int getSourceID() {
	    // TODO Auto-generated method stub
	    return 0;
	}
}
