package com.google.android.gms.common;

import android.content.Intent;

/* JADX INFO: loaded from: classes4.dex */
public class UserRecoverableException extends Exception {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Intent f12665i;

    public UserRecoverableException(Intent intent) {
        super("Google Play Services not available");
        this.f12665i = intent;
    }
}
