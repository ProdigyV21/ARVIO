package com.google.firebase.concurrent;

/* JADX INFO: loaded from: classes4.dex */
public final class n implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Runnable f14336i;

    public n(Runnable runnable) {
        this.f14336i = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f14336i.run();
    }

    public final String toString() {
        return this.f14336i.toString();
    }
}
