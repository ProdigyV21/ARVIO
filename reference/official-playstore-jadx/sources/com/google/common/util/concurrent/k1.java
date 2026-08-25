package com.google.common.util.concurrent;

/* JADX INFO: loaded from: classes4.dex */
public final class k1 extends i implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Runnable f14210i;

    public k1(Runnable runnable) {
        runnable.getClass();
        this.f14210i = runnable;
    }

    @Override // com.google.common.util.concurrent.p
    public final String pendingToString() {
        return "task=[" + this.f14210i + "]";
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f14210i.run();
        } catch (Throwable th) {
            setException(th);
            throw th;
        }
    }
}
