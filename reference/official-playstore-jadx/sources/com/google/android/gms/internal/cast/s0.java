package com.google.android.gms.internal.cast;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class s0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13597i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ t0 f13598l;

    public /* synthetic */ s0(t0 t0Var, int i10) {
        this.f13597i = i10;
        this.f13598l = t0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f13597i) {
            case 0:
                this.f13598l.c();
                break;
            default:
                t0 t0Var = this.f13598l;
                y0 y0Var = t0Var.f13609e;
                if (((androidx.mediarouter.media.k0) y0Var.f13692b) == null) {
                    y0Var.f13692b = androidx.mediarouter.media.k0.d((Context) y0Var.f13691a);
                }
                androidx.mediarouter.media.k0 k0Var = (androidx.mediarouter.media.k0) y0Var.f13692b;
                if (k0Var != null) {
                    k0Var.e(t0Var);
                }
                break;
        }
    }
}
