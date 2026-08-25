package com.google.android.gms.internal.cast;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes4.dex */
public final class j9 extends u0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f13358m = AtomicReferenceFieldUpdater.newUpdater(p9.class, Thread.class, CmcdData.OBJECT_TYPE_AUDIO_ONLY);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f13359n = AtomicReferenceFieldUpdater.newUpdater(p9.class, p9.class, "b");

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f13360o = AtomicReferenceFieldUpdater.newUpdater(q9.class, p9.class, "o");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f13361p = AtomicReferenceFieldUpdater.newUpdater(q9.class, g9.class, "n");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f13362q = AtomicReferenceFieldUpdater.newUpdater(q9.class, Object.class, CmcdData.OBJECT_TYPE_MANIFEST);

    @Override // com.google.android.gms.internal.cast.u0
    public final g9 A(i9 i9Var) {
        return (g9) f13361p.getAndSet(i9Var, g9.f13262d);
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final boolean B(q9 q9Var, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = f13362q;
            if (atomicReferenceFieldUpdater.compareAndSet(q9Var, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(q9Var) == obj);
        return false;
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final void o(p9 p9Var, Thread thread) {
        f13358m.lazySet(p9Var, thread);
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final void t(p9 p9Var, p9 p9Var2) {
        f13359n.lazySet(p9Var, p9Var2);
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final boolean v(q9 q9Var, p9 p9Var, p9 p9Var2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = f13360o;
            if (atomicReferenceFieldUpdater.compareAndSet(q9Var, p9Var, p9Var2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(q9Var) == p9Var);
        return false;
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final boolean x(q9 q9Var, g9 g9Var, g9 g9Var2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = f13361p;
            if (atomicReferenceFieldUpdater.compareAndSet(q9Var, g9Var, g9Var2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(q9Var) == g9Var);
        return false;
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final p9 y(i9 i9Var) {
        return (p9) f13360o.getAndSet(i9Var, p9.f13543c);
    }
}
