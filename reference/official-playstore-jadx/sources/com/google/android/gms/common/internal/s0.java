package com.google.android.gms.common.internal;

import android.os.Bundle;

/* JADX INFO: loaded from: classes4.dex */
public final class s0 extends k0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ f f12909g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(f fVar, int i10, Bundle bundle) {
        super(fVar, i10, bundle);
        this.f12909g = fVar;
    }

    @Override // com.google.android.gms.common.internal.k0
    public final boolean a() {
        this.f12909g.zzc.j(o3.b.f20727p);
        return true;
    }

    @Override // com.google.android.gms.common.internal.k0
    public final void b(o3.b bVar) {
        f fVar = this.f12909g;
        if (fVar.enableLocalFallback() && fVar.zzg()) {
            fVar.zzf(16);
        } else {
            fVar.zzc.j(bVar);
            fVar.onConnectionFailed(bVar);
        }
    }
}
