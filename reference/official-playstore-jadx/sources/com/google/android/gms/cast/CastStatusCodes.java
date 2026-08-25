package com.google.android.gms.cast;

import a0.c;
import com.google.android.gms.common.api.k;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class CastStatusCodes extends k {
    public static final int APPLICATION_NOT_FOUND = 2004;
    public static final int APPLICATION_NOT_RUNNING = 2005;
    public static final int AUTHENTICATION_FAILED = 2000;
    public static final int CANCELED = 2002;
    public static final int DEVICE_CONNECTION_SUSPENDED = 2016;
    public static final int ERROR_CAST_PLATFORM_INCOMPATIBLE = 2110;
    public static final int ERROR_CAST_PLATFORM_NOT_CONNECTED = 2113;
    public static final int ERROR_DEVICE_ID_FLAGS_NOT_SET = 2115;
    public static final int ERROR_HOST_NOT_ALLOWED = 2112;
    public static final int ERROR_NO_CAST_CONFIGURATION = 2114;
    public static final int ERROR_SERVICE_CREATION_FAILED = 2200;
    public static final int ERROR_SERVICE_DISCONNECTED = 2201;
    public static final int ERROR_STOPPING_SERVICE_FAILED = 2202;
    public static final int ERROR_URL_INSEURE = 2111;
    public static final int FAILED = 2100;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 14;
    public static final int INVALID_REQUEST = 2001;
    public static final int MEDIA_ERROR = 2104;
    public static final int MESSAGE_SEND_BUFFER_TOO_FULL = 2007;
    public static final int MESSAGE_TOO_LARGE = 2006;
    public static final int NETWORK_ERROR = 7;
    public static final int NOT_ALLOWED = 2003;
    public static final int REPLACED = 2103;
    public static final int SUCCESS = 0;
    public static final int TIMEOUT = 15;
    public static final int UNKNOWN_ERROR = 13;

    private CastStatusCodes() {
    }

    public static String getStatusCodeString(int i10) {
        if (i10 >= -999 && i10 <= 999) {
            return i10 != 0 ? i10 != 7 ? i10 != 14 ? i10 != 15 ? k.getStatusCodeString(i10) : "TIMEOUT" : "INTERRUPTED" : "NETWORK_ERROR" : "SUCCESS";
        }
        if (i10 >= 2000 && i10 <= 2049) {
            if (i10 == 2015) {
                return "TCP_PROBER_FAIL_TO_VERIFY_DEVICE";
            }
            switch (i10) {
                case 2000:
                    return "AUTHENTICATION_FAILED";
                case 2001:
                    return "INVALID_REQUEST";
                case 2002:
                    return "CANCELED";
                case 2003:
                    return "NOT_ALLOWED";
                case 2004:
                    return "APPLICATION_NOT_FOUND";
                case 2005:
                    return "APPLICATION_NOT_RUNNING";
                case 2006:
                    return "MESSAGE_TOO_LARGE";
                case 2007:
                    return "MESSAGE_SEND_BUFFER_TOO_FULL";
                default:
                    Locale locale = Locale.ROOT;
                    return c.i(i10, "Common cast status code ");
            }
        }
        if (i10 >= 2050 && i10 <= 2059) {
            Locale locale2 = Locale.ROOT;
            return c.i(i10, "Cast controller status code ");
        }
        if (i10 >= 2100 && i10 <= 2109) {
            Locale locale3 = Locale.ROOT;
            return c.i(i10, "Media control channel status code ");
        }
        if (i10 >= 2150 && i10 <= 2169) {
            Locale locale4 = Locale.ROOT;
            return c.i(i10, "Cast session status code ");
        }
        if (i10 >= 2200 && i10 <= 2219) {
            Locale locale5 = Locale.ROOT;
            return c.i(i10, "Cast remote display status code ");
        }
        if (i10 >= 2250 && i10 <= 2299) {
            Locale locale6 = Locale.ROOT;
            return c.i(i10, "Cast socket status code ");
        }
        if (i10 >= 2300 && i10 <= 2309) {
            Locale locale7 = Locale.ROOT;
            return c.i(i10, "Cast service status code ");
        }
        if (i10 >= 2310 && i10 <= 2319) {
            Locale locale8 = Locale.ROOT;
            return c.i(i10, "Endpoint switch status code ");
        }
        if (i10 >= 2350 && i10 <= 2359) {
            Locale locale9 = Locale.ROOT;
            return c.i(i10, "Cast multizone device status code ");
        }
        if (i10 >= 2400 && i10 <= 2419) {
            Locale locale10 = Locale.ROOT;
            return c.i(i10, "Cast relay casting status code ");
        }
        if (i10 >= 2450 && i10 <= 2469) {
            Locale locale11 = Locale.ROOT;
            return c.i(i10, "Cast nearby casting status code ");
        }
        if (i10 >= 2420 && i10 <= 2439) {
            Locale locale12 = Locale.ROOT;
            return c.i(i10, "Remote connection status code ");
        }
        if (i10 >= 2470 && i10 <= 2479) {
            Locale locale13 = Locale.ROOT;
            return c.i(i10, "Cast application status code ");
        }
        if (i10 < 2490 || i10 > 2499) {
            Locale locale14 = Locale.ROOT;
            return c.i(i10, "Unknown cast status code ");
        }
        Locale locale15 = Locale.ROOT;
        return c.i(i10, "Cast media loading status code ");
    }
}
