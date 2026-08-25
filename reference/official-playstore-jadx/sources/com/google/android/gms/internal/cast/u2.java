package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class u2 extends s2 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ r2 f13622c;

    public u2(r2 r2Var) {
        this.f13622c = r2Var;
    }

    @Override // com.google.android.gms.internal.cast.c3
    public final void zzd(int i10, com.google.android.gms.common.api.j jVar) {
        Logger logger = x2.f13682d;
        x2.f13682d.d("onError: %d", Integer.valueOf(i10));
        r2 r2Var = this.f13622c;
        r2Var.f13636a.a();
        r2Var.setResult(new w2(Status.f12672q));
    }

    @Override // com.google.android.gms.internal.cast.s2, com.google.android.gms.internal.cast.c3
    public final void zzf(com.google.android.gms.common.api.j jVar) {
        x2.f13682d.d("onDisconnected", new Object[0]);
        r2 r2Var = this.f13622c;
        r2Var.f13636a.a();
        r2Var.setResult(new w2(Status.f12670o));
    }
}
