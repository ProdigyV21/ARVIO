package com.google.android.gms.auth.api.identity;

import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
public final class zbb {
    private String zba;

    private zbb() {
    }

    public static final zbb zbc(zbc zbcVar) {
        String strZbb = zbcVar.zbb();
        zbb zbbVar = new zbb();
        if (strZbb != null) {
            t.f(strZbb);
            zbbVar.zba = strZbb;
        }
        return zbbVar;
    }

    public final zbb zba(String str) {
        t.f(str);
        this.zba = str;
        return this;
    }

    public final zbc zbb() {
        return new zbc(this.zba);
    }

    public /* synthetic */ zbb(zba zbaVar) {
    }
}
