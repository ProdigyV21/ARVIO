package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Looper;

/* JADX INFO: loaded from: classes4.dex */
public final class r0 implements b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13573a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f13574b;

    public /* synthetic */ r0(Object obj, int i10) {
        this.f13573a = i10;
        this.f13574b = obj;
    }

    @Override // com.google.android.gms.internal.cast.b0
    public final void zza() {
        switch (this.f13573a) {
            case 0:
                ((t0) this.f13574b).b();
                break;
            default:
                l2 l2Var = (l2) this.f13574b;
                l2Var.c();
                l2Var.b();
                break;
        }
    }

    @Override // com.google.android.gms.internal.cast.b0
    public final void zzb() {
        switch (this.f13573a) {
            case 0:
                t0 t0Var = (t0) this.f13574b;
                t0Var.getClass();
                t0.f13604f.d("Stopping RouteDiscovery.", new Object[0]);
                t0Var.f13607c.clear();
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    new androidx.loader.content.j(Looper.getMainLooper(), 2).post(new s0(t0Var, 1));
                } else {
                    y0 y0Var = t0Var.f13609e;
                    if (((androidx.mediarouter.media.k0) y0Var.f13692b) == null) {
                        y0Var.f13692b = androidx.mediarouter.media.k0.d((Context) y0Var.f13691a);
                    }
                    androidx.mediarouter.media.k0 k0Var = (androidx.mediarouter.media.k0) y0Var.f13692b;
                    if (k0Var != null) {
                        k0Var.e(t0Var);
                    }
                }
                break;
            default:
                l2 l2Var = (l2) this.f13574b;
                l2Var.c();
                l2Var.a();
                break;
        }
    }
}
