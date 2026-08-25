package com.google.android.gms.common.internal;

import android.os.Bundle;

/* JADX INFO: loaded from: classes4.dex */
public final class u implements d, b, c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static u f12917b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final v f12918c = new v(false, 0, false, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f12919a;

    public /* synthetic */ u(Object obj) {
        this.f12919a = obj;
    }

    public static synchronized u d() {
        try {
            if (f12917b == null) {
                f12917b = new u();
            }
        } catch (Throwable th) {
            throw th;
        }
        return f12917b;
    }

    @Override // com.google.android.gms.common.internal.c
    public void a(o3.b bVar) {
        ((com.google.android.gms.common.api.internal.o) this.f12919a).a(bVar);
    }

    @Override // com.google.android.gms.common.internal.b
    public void b(Bundle bundle) {
        ((com.google.android.gms.common.api.internal.g) this.f12919a).onConnected();
    }

    @Override // com.google.android.gms.common.internal.b
    public void c(int i10) {
        ((com.google.android.gms.common.api.internal.g) this.f12919a).c(i10);
    }

    @Override // com.google.android.gms.common.internal.d
    public void j(o3.b bVar) {
        f fVar = (f) this.f12919a;
        if (bVar.d()) {
            fVar.getRemoteService(null, fVar.getScopes());
        } else if (fVar.zzl() != null) {
            fVar.zzl().a(bVar);
        }
    }
}
