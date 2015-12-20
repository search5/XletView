package com.alticast.navsuite.core.bsi;

import java.awt.Color;
import java.util.Date;

import net.beiker.xletview.util.Settings;

public class BsiSystemInfo {
    public BsiSystemInfo() {
    }

    public static String getMainSoftwareVersion() {
	return "";
    }

    public static native String getSerialNumber();

    public static int getTuneStatus() {
	return 0;
    }

    public static int getTuneSignalLevel() {
	return 0;
    }

    public static int getTuneSignalQuality() {
	return 0;
    }

    public static String getMiddlewareProviderName() {
	return null;
    }

    public static String getMiddlewareVersion() {
	return "1.6.1.1";
    }

    public static String getNavigatorProviderName() {
	return "Alticast";
    }

    public static String getNavigatorVersion() {
	return "";
    }

    public static native String getManufacturerId();

    public static native String getManufacturerName();

    public static native String getHardwareVersion();

    public static String getModelNumber() {
	return "";
    }

    public static native String getLoaderVersion();

    public static native String getManufacturerSoftwareVersion();

    public static Date getUpdatedDate() {
	return new Date();
    }

    public static byte[] hexToBytes(String str) {
	if (str == null) {
	    return null;
	} else if (str.length() < 2) {
	    return null;
	} else {
	    int len = str.length() / 2;
	    byte[] buffer = new byte[len];
	    for (int i = 0; i < len; i++) {
		buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
	    }
	    return buffer;
	}
    }

    public static byte[] getHostMacAddress() {
	Settings.reLoadUserFile();
	String macAddress = Settings.getUserProperties("macAddress");
	macAddress = macAddress.replace(":", "");
/*	macAddress=macAddress.substring(0, 1) + macAddress.substring(3, 4) + macAddress.substring(6, 7) + macAddress.substring(9, 10) + 
	macAddress.substring(12, 13) + 
	macAddress.substring(15, 16);
*/	return hexToBytes(macAddress);
	
/*	return new byte[] { Byte.valueOf(macAddress.substring(0, 1)),

	Byte.valueOf(macAddress.substring(3, 4)),

	Byte.valueOf(macAddress.substring(6, 7)),

	Byte.valueOf(macAddress.substring(9, 10)),

	Byte.valueOf(macAddress.substring(12, 13)),

	Byte.valueOf(macAddress.substring(15, 16)) };
*/
	// return new byte[]{0x00,0x11,0x22,0x33,0x44,0x55};
    }

    public static byte[] getPODMacAddress() {
	return null;
    }

    public static int getCableModemOperationMode() {
	return 0;
    }

    public static boolean isCableModemServerConnected() {
	return false;
    }

    public static int getCableModemDsgCommunicationStatus() {
	return 0;
    }

    public static int getCableModemReceptionSensitivity() {
	return 0;
    }

    public static String getOSVersion() {
	return null;
    }

    public static final int TUNE_STATUS_LOCKED = 0;

    public static final int TUNE_STATUS_UNLOCKED = 1;

    public static final int TUNE_STATUS_SCANNING = 2;

    public static final int CM_OP_MODE_DOCSIS = 0;

    public static final int CM_OP_MODE_DSG = 1;

    public static final int CM_DSG_COMM_INVALID = -1;

    public static final int CM_DSG_COMM_OFF = 0;

    public static final int CM_DSG_COMM_ON = 1;

    public static final Color DEFAULT_BG_COLOR = new Color(0, 0, 0);
}
