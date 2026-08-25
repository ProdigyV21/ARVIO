package com.google.android.gms.internal.cast;

import java.util.concurrent.locks.AbstractOwnableSynchronizer;

/* JADX INFO: loaded from: classes4.dex */
public final class s9 extends AbstractOwnableSynchronizer implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final z9 f13603i;

    public /* synthetic */ s9(z9 z9Var) {
        this.f13603i = z9Var;
    }

    public final /* synthetic */ void a(Thread thread) {
        setExclusiveOwnerThread(thread);
    }

    @Override // java.lang.Runnable
    public final void run() {
    }

    public final String toString() {
        return this.f13603i.toString();
    }
}
