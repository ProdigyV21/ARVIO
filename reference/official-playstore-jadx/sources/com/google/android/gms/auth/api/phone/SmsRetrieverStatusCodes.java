package com.google.android.gms.auth.api.phone;

import com.google.android.gms.common.api.k;

/* JADX INFO: loaded from: classes4.dex */
public final class SmsRetrieverStatusCodes extends k {
    public static final int API_NOT_AVAILABLE = 36501;
    public static final int PLATFORM_NOT_SUPPORTED = 36500;
    public static final int USER_PERMISSION_REQUIRED = 36502;

    private SmsRetrieverStatusCodes() {
    }

    public static String getStatusCodeString(int i10) {
        switch (i10) {
            case PLATFORM_NOT_SUPPORTED /* 36500 */:
                return "PLATFORM_NOT_SUPPORTED";
            case API_NOT_AVAILABLE /* 36501 */:
                return "API_NOT_AVAILABLE";
            case USER_PERMISSION_REQUIRED /* 36502 */:
                return "USER_PERMISSION_REQUIRED";
            default:
                return k.getStatusCodeString(i10);
        }
    }
}
