package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f14200a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f14201b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f14202c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f14203d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f14204e;

    public e(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.f14200a = atomicReferenceFieldUpdater;
        this.f14201b = atomicReferenceFieldUpdater2;
        this.f14202c = atomicReferenceFieldUpdater3;
        this.f14203d = atomicReferenceFieldUpdater4;
        this.f14204e = atomicReferenceFieldUpdater5;
    }

    @Override // com.google.common.util.concurrent.a
    public final boolean a(p pVar, d dVar, d dVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f14203d;
            if (atomicReferenceFieldUpdater.compareAndSet(pVar, dVar, dVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(pVar) == dVar);
        return false;
    }

    @Override // com.google.common.util.concurrent.a
    public final boolean b(p pVar, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f14204e;
            if (atomicReferenceFieldUpdater.compareAndSet(pVar, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(pVar) == obj);
        return false;
    }

    @Override // com.google.common.util.concurrent.a
    public final boolean c(p pVar, o oVar, o oVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f14202c;
            if (atomicReferenceFieldUpdater.compareAndSet(pVar, oVar, oVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(pVar) == oVar);
        return false;
    }

    @Override // com.google.common.util.concurrent.a
    public final d d(p pVar) {
        return (d) this.f14203d.getAndSet(pVar, d.f14196d);
    }

    @Override // com.google.common.util.concurrent.a
    public final o e(p pVar) {
        return (o) this.f14202c.getAndSet(pVar, o.f14225c);
    }

    @Override // com.google.common.util.concurrent.a
    public final void f(o oVar, o oVar2) {
        this.f14201b.lazySet(oVar, oVar2);
    }

    @Override // com.google.common.util.concurrent.a
    public final void g(o oVar, Thread thread) {
        this.f14200a.lazySet(oVar, thread);
    }
}
