package com.google.android.gms.auth.api;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class zbc {
    protected Boolean zba;
    protected String zbb;

    public zbc() {
        this.zba = Boolean.FALSE;
    }

    public final zbc zba(String str) {
        this.zbb = str;
        return this;
    }

    public zbc(zbd zbdVar) {
        this.zba = Boolean.FALSE;
        zbd.zbb(zbdVar);
        this.zba = Boolean.valueOf(zbdVar.zbc);
        this.zbb = zbdVar.zbd;
    }
}
