package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Status;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class z2 extends e3 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ f3 f13703c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ a3 f13704d;

    public z2(a3 a3Var, p2 p2Var) {
        this.f13703c = p2Var;
        Objects.requireNonNull(a3Var);
        this.f13704d = a3Var;
    }

    @Override // com.google.android.gms.internal.cast.f3
    public final void s(int i10, com.google.android.gms.common.api.j jVar) {
        a3.f13164d.d("onRemoteDisplayEnded", new Object[0]);
        f3 f3Var = this.f13703c;
        if (f3Var != null) {
            f3Var.s(i10, jVar);
        }
        CastRemoteDisplay.CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks = this.f13704d.f13165a;
        if (castRemoteDisplaySessionCallbacks != null) {
            castRemoteDisplaySessionCallbacks.onRemoteDisplayEnded(new Status(i10, null, null, null));
        }
    }
}
