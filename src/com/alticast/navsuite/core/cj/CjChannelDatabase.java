package com.alticast.navsuite.core.cj;

import com.alticast.navsuite.core.ChannelDatabase;

public class CjChannelDatabase extends ChannelDatabase{

	private static CjChannelDatabase instance = new CjChannelDatabase();
	public static ChannelDatabase getInstance() {
		return instance;
	}

	public int getNodeGroup() {
		return 8;
	}

}
