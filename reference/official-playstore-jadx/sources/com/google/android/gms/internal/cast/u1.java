package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;

/* JADX INFO: loaded from: classes4.dex */
public final class u1 extends Cast.Listener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ v1 f13621a;

    public u1(v1 v1Var) {
        this.f13621a = v1Var;
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onVolumeChanged() {
        this.f13621a.a();
    }
}
