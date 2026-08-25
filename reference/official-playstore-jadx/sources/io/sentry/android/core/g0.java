package io.sentry.android.core;

import androidx.lifecycle.DefaultLifecycleObserver;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 implements DefaultLifecycleObserver {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f0 f16488i = new f0(this, 0);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ h0 f16489l;

    public g0(h0 h0Var) {
        this.f16489l = h0Var;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onCreate(androidx.lifecycle.y yVar) {
        androidx.lifecycle.e.a(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onDestroy(androidx.lifecycle.y yVar) {
        androidx.lifecycle.e.b(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onPause(androidx.lifecycle.y yVar) {
        androidx.lifecycle.e.c(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onResume(androidx.lifecycle.y yVar) {
        androidx.lifecycle.e.d(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStart(androidx.lifecycle.y yVar) {
        this.f16489l.f16500n = Boolean.FALSE;
        Iterator it = this.f16488i.iterator();
        while (it.hasNext()) {
            ((e0) it.next()).i();
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStop(androidx.lifecycle.y yVar) {
        this.f16489l.f16500n = Boolean.TRUE;
        Iterator it = this.f16488i.iterator();
        while (it.hasNext()) {
            ((e0) it.next()).j();
        }
    }
}
