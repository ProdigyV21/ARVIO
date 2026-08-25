package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes4.dex */
public final class s extends t7.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicIntegerFieldUpdater f14232a;

    public s(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
        this.f14232a = atomicIntegerFieldUpdater;
    }

    @Override // t7.a
    public final int o(n0 n0Var) {
        return this.f14232a.decrementAndGet(n0Var);
    }
}
