package com.google.android.gms.common.internal;

/* JADX INFO: loaded from: classes4.dex */
public final class zzaf extends Exception {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o3.b f12946i;

    public zzaf(o3.b bVar) {
        t.a("ResolvableConnectionException can only be created with a connection result containing a resolution.", (bVar.f20729l == 0 || bVar.f20730m == null) ? false : true);
        this.f12946i = bVar;
    }
}
