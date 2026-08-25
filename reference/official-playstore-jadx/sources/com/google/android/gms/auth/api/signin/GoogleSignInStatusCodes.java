package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.k;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class GoogleSignInStatusCodes extends k {
    public static final int SIGN_IN_CANCELLED = 12501;
    public static final int SIGN_IN_CURRENTLY_IN_PROGRESS = 12502;
    public static final int SIGN_IN_FAILED = 12500;

    private GoogleSignInStatusCodes() {
    }

    public static String getStatusCodeString(int i10) {
        switch (i10) {
            case SIGN_IN_FAILED /* 12500 */:
                return "A non-recoverable sign in failure occurred";
            case SIGN_IN_CANCELLED /* 12501 */:
                return "Sign in action cancelled";
            case SIGN_IN_CURRENTLY_IN_PROGRESS /* 12502 */:
                return "Sign-in in progress";
            default:
                return k.getStatusCodeString(i10);
        }
    }
}
