package com.google.android.gms.internal.auth;

import android.net.Uri;

/* JADX INFO: loaded from: classes4.dex */
public abstract class b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t.e f12997a = new t.e(0);

    public static synchronized Uri a() {
        t.e eVar = f12997a;
        Uri uri = (Uri) eVar.get("com.google.android.gms.auth_account");
        if (uri != null) {
            return uri;
        }
        Uri uri2 = Uri.parse("content://com.google.android.gms.phenotype/".concat(String.valueOf(Uri.encode("com.google.android.gms.auth_account"))));
        eVar.put("com.google.android.gms.auth_account", uri2);
        return uri2;
    }
}
