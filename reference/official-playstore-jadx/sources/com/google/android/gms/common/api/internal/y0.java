package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes4.dex */
public final class y0 extends com.google.android.gms.common.api.a0 implements com.google.android.gms.common.api.y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public y0 f12812a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f12813b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WeakReference f12814c;

    public y0(WeakReference weakReference) {
        com.google.android.gms.common.internal.t.j(weakReference, "GoogleApiClient reference must not be null");
        this.f12814c = weakReference;
        com.google.android.gms.common.api.q qVar = (com.google.android.gms.common.api.q) weakReference.get();
        new x0(this, qVar != null ? ((i0) qVar).f12756b.getLooper() : Looper.getMainLooper());
    }

    public final y0 a() {
        y0 y0Var;
        synchronized (this.f12813b) {
            y0Var = new y0(this.f12814c);
            this.f12812a = y0Var;
        }
        return y0Var;
    }

    public final void b(com.google.android.gms.common.api.v vVar) {
        synchronized (this.f12813b) {
        }
    }

    public final void c(Status status) {
        synchronized (this.f12813b) {
            d();
        }
    }

    public final void d() {
        synchronized (this.f12813b) {
        }
    }

    @Override // com.google.android.gms.common.api.y
    public final void onResult(com.google.android.gms.common.api.x xVar) {
        synchronized (this.f12813b) {
            if (!xVar.getStatus().d()) {
                c(xVar.getStatus());
                if (xVar instanceof com.google.android.gms.common.api.w) {
                    try {
                        ((com.google.android.gms.common.api.w) xVar).release();
                    } catch (RuntimeException e5) {
                        Log.w("TransformedResultImpl", "Unable to release ".concat(String.valueOf(xVar)), e5);
                    }
                }
            }
        }
    }
}
