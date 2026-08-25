package com.google.android.gms.common.internal;

import android.os.Bundle;

/* JADX INFO: loaded from: classes4.dex */
public abstract class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Boolean f12882a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f12883b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ f f12884c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f12885d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Bundle f12886e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f f12887f;

    public k0(f fVar, int i10, Bundle bundle) {
        this.f12887f = fVar;
        Boolean bool = Boolean.TRUE;
        this.f12884c = fVar;
        this.f12882a = bool;
        this.f12883b = false;
        this.f12885d = i10;
        this.f12886e = bundle;
    }

    public abstract boolean a();

    public abstract void b(o3.b bVar);

    public final void c() {
        d();
        f fVar = this.f12884c;
        synchronized (fVar.zzj()) {
            fVar.zzj().remove(this);
        }
    }

    public final void d() {
        synchronized (this) {
            this.f12882a = null;
        }
    }
}
