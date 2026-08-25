package com.google.common.util.concurrent;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final class s1 extends b1 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Callable f14235m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ t1 f14236n;

    public s1(t1 t1Var, Callable callable) {
        this.f14236n = t1Var;
        callable.getClass();
        this.f14235m = callable;
    }

    @Override // com.google.common.util.concurrent.b1
    public final void a(Throwable th) {
        this.f14236n.setException(th);
    }

    @Override // com.google.common.util.concurrent.b1
    public final void b(Object obj) {
        this.f14236n.set(obj);
    }

    @Override // com.google.common.util.concurrent.b1
    public final boolean d() {
        return this.f14236n.isDone();
    }

    @Override // com.google.common.util.concurrent.b1
    public final Object e() {
        return this.f14235m.call();
    }

    @Override // com.google.common.util.concurrent.b1
    public final String f() {
        return this.f14235m.toString();
    }
}
