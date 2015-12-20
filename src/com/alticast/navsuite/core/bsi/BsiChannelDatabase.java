package com.alticast.navsuite.core.bsi;

import com.alticast.navsuite.core.Channel;
import com.alticast.navsuite.core.ChannelDatabase;

public class BsiChannelDatabase extends ChannelDatabase {
	public static BsiChannelDatabase getInstance() {
		return new BsiChannelDatabase();
	}
	
	public BsiChannelDatabase()
    {
    }

    public int[] getVodChannelTsIds()
    {
        return new int[0];
    }

    public int[] getVodChannelTsIds(boolean forced)
    {
    	int [] TSID = { 9111 , 1111,3333,4444,5555 ,5555 ,5555 ,5555 ,5555 ,5555 ,5555 ,5555 };
        return TSID;
    }

    public int getVodFrequency(int vodTsId)
    {
        return -1;
    }

    public Channel getMainMosaicChannel()
    {
        return null;
    }

    public Channel getNvodGuideChannel()
    {
        return null;
    }

    public Channel getDefaultChannel()
    {
        return null;
    }

    public Channel getSystemDefaultChannel()
    {
        return null;
    }

    public Channel findChannel(int sourceId)
    {
        return null;
    }
}
