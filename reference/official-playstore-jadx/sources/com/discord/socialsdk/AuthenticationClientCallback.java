package com.discord.socialsdk;

/* JADX INFO: loaded from: classes4.dex */
public class AuthenticationClientCallback {
    private long nativeContext;

    public AuthenticationClientCallback(long j10) {
        this.nativeContext = j10;
    }

    private native void onAuthorizationComplete(long j10, String str, String str2, String str3);

    public void finalize() {
        nativeFinalize(this.nativeContext);
    }

    public native void nativeFinalize(long j10);

    public void onAuthorizationComplete(String str, String str2, String str3) {
        onAuthorizationComplete(this.nativeContext, str, str2, str3);
    }
}
