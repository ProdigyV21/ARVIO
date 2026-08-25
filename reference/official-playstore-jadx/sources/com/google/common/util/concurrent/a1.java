package com.google.common.util.concurrent;

import java.util.concurrent.locks.AbstractOwnableSynchronizer;

/* JADX INFO: loaded from: classes4.dex */
public final class a1 extends AbstractOwnableSynchronizer implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b1 f14184i;

    public a1(b1 b1Var) {
        this.f14184i = b1Var;
    }

    public static void a(a1 a1Var, Thread thread) {
        a1Var.setExclusiveOwnerThread(thread);
    }

    @Override // java.lang.Runnable
    public final void run() {
    }

    public final String toString() {
        return this.f14184i.toString();
    }
}
