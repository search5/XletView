/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.alticast.navsuite.core.bsi;

import com.alticast.navsuite.core.Settings;

public class BsiSettings extends Settings
{

    public BsiSettings()
    {
    }

    /**
     * @deprecated Method setVideoRatio is deprecated
     */

    public native void setVideoRatio(int i);

    public int changeVideoRatio(int videoRatio)
    {
        return 0;
    }

    public native int getVideoRatio();

    public int[] getSupportedVideoRatios()
    {
        return new int[0];
    }

    public native int getVideoOutput();

    public native void setVideoOutput(int i);

    public native boolean isDigitalAudioOutputEnabled();

    public native void enableDigitalAudioOutput(boolean flag);

    public native void resetCableModem();

    public boolean checkPurchasePIN(String pin)
    {
    	if(pin.equals("2222"))
    		return true;
        return false;
    }

    public void setPurchasePIN(String s)
    {
    }
    
    public int getParentalRating() {
    	return 7;
    }

    /**
     * @deprecated Method announceDrawMetaData is deprecated
     */

    public native void announceDrawMetaData();

    public static final int VIDEO_RATIO_4_3 = 0;
    public static final int VIDEO_RATIO_4_3_LETTERBOX = 1;
    public static final int VIDEO_RATIO_4_3_SQUEEZED = 2;
    public static final int VIDEO_RATIO_16_9 = 3;
    public static final int VIDEO_RATIO_16_9_WIDE = 4;
    public static final int VIDEO_RATIO_16_9_ZOOM = 5;
    public static final int VIDEO_RATIO_16_9_PANORAMA = 6;
    public static final int VIDEO_RATIO_RESULT_SUCCESS = 0;
    public static final int VIDEO_RATIO_RESULT_NOT_SUPPORTED = 1;
    public static final int VIDEO_RATIO_RESULT_ERROR = 2;
    public static final int VIDEO_RATIO_RESULT_INVALID = 3;
    public static final int VIDEO_OUTPUT_COMPONENT = 0;
    public static final int VIDEO_OUTPUT_S_VIDEO = 1;
    public static final int RATING_ALL = 0;
    public static final int RATING_7 = 7;
    public static final int RATING_12 = 12;
    public static final int RATING_15 = 15;
    public static final int RATING_19 = 19;
	@Override
	public void setUserPIN(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkUserPIN(String s) {
		if(s.equals("1111"))
			return true;
		return false;
	}

	@Override
	public void setParentalRating(int i) {
		// TODO Auto-generated method stub
		
	}
}