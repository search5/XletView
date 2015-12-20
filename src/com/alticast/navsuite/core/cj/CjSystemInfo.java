package com.alticast.navsuite.core.cj;

import java.awt.Color;
import java.util.Date;

public class CjSystemInfo {

    public static final int TUNE_STATUS_LOCKED = 0;

    public static final int TUNE_STATUS_UNLOCKED = 1;

    public static final int TUNE_STATUS_SCANNING = 2;

    public static final Color DEFAULT_BG_COLOR = new Color(0, 0, 0);

    public static final int STB_SMT2500C = 0;

    public static final int STB_OC2X00 = 1;

    public static final int CA_ATTR_AUTHORIZED = 0;

    public static final int CA_ATTR_PURCHASABLE = 1;

    public static final int CA_ATTR_PURCHASED = 2;

    public static final int CA_ATTR_PREVIEW = 3;

    public static final int CA_ATTR_PARENTAL_BLOCKED = 4;

    public static final int CA_STATE_NA = 0;

    public static final int CA_STATE_CLEAN = 1;

    public static final int CA_STATE_TRUE = 2;

    public static final int CA_STATE_FALSE = 3;

    public CjSystemInfo() {
    }

    public static String getMainSoftwareVersion() {
	return "1.0.14";
    }

    public static native String getSerialNumber();

    public static int getTuneStatus() {
	return 0;
    }

    public static String getMiddlewareVersion() {
	return "1.0.14";
    }

    public static String getNavigatorVersion() {
	return "1.1.3.060830";
    }

    public static native String getManufacturerId();

    public static native String getHardwareVersion();

    public static native String getModelNumber();

    public static native String getLoaderVersion();

    public static native String getManufacturerSoftwareVersion();

    public static Date getUpdatedDate() {
	return null;
    }

    public static byte[] getHostMacAddress() {
    	//return new byte[] {0x00,0x16,0x32,(byte)0xb5,(byte)0x9f,0x7f}; // {0x00,0x11,0x22,0x33,0x44,0x55};
    //00:16:32:b5:9f:7f
	return new byte[]{0x00,0x11,0x22,0x33,0x44,0x55};
    }

    public static final boolean hasVideoOutputSelector() {
	return false;
    }

    public static native String getPowerStatusString();

    public static native String getBootStatusString();

    public static native int getRAMTotalSize();

    public static native int getROMTotalSize();

    public static native int getFlashTotalSize();

    public static native String getCableModemStatusString();

    public static native String getMacAddressInfoString();

    public static native String getNetworkAddressInfoString();

    public static native String getFATStatusString();

    public static native int getCurrentChannelCAProperty(int i);

    public static long getTotalNativeMemory() {
	return 0L;
    }

    public static long getFreeNativeMemory() {
	return 0L;
    }

}