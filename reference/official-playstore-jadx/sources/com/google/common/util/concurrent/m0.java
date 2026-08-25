package com.google.common.util.concurrent;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public final class m0 extends b1 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final s0 f14212m = s0.f14233i;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ n0 f14213n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final androidx.loader.content.g f14214o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ n0 f14215p;

    public m0(n0 n0Var, androidx.loader.content.g gVar) {
        this.f14215p = n0Var;
        this.f14213n = n0Var;
        this.f14214o = gVar;
    }

    @Override // com.google.common.util.concurrent.b1
    public final void a(Throwable th) {
        n0 n0Var = this.f14213n;
        n0Var.f14224p = null;
        if (th instanceof ExecutionException) {
            n0Var.setException(((ExecutionException) th).getCause());
        } else if (th instanceof CancellationException) {
            n0Var.cancel(false);
        } else {
            n0Var.setException(th);
        }
    }

    @Override // com.google.common.util.concurrent.b1
    public final void b(Object obj) {
        this.f14213n.f14224p = null;
        this.f14215p.set(obj);
    }

    @Override // com.google.common.util.concurrent.b1
    public final boolean d() {
        return this.f14213n.isDone();
    }

    @Override // com.google.common.util.concurrent.b1
    public final Object e() {
        this.f14214o.call();
        return null;
    }

    @Override // com.google.common.util.concurrent.b1
    public final String f() {
        return this.f14214o.toString();
    }
}
