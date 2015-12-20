package com.alticast.navsuite.core;

import com.alticast.navsuite.core.bsi.BsiChannelDatabase;

public abstract class ChannelDatabase {

	public static ChannelDatabase getInstance() {
		return BsiChannelDatabase.getInstance();
	}
	
}
