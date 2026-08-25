package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/* JADX INFO: loaded from: classes4.dex */
public final class t1 extends u0 implements RunnableFuture {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile s1 f14237i;

    public t1(Callable callable) {
        this.f14237i = new s1(this, callable);
    }

    @Override // com.google.common.util.concurrent.p
    public final void afterDone() {
        s1 s1Var;
        super.afterDone();
        if (wasInterrupted() && (s1Var = this.f14237i) != null) {
            s1Var.c();
        }
        this.f14237i = null;
    }

    @Override // com.google.common.util.concurrent.p
    public final String pendingToString() {
        s1 s1Var = this.f14237i;
        if (s1Var == null) {
            return super.pendingToString();
        }
        return "task=[" + s1Var + "]";
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        s1 s1Var = this.f14237i;
        if (s1Var != null) {
            s1Var.run();
        }
        this.f14237i = null;
    }
}
