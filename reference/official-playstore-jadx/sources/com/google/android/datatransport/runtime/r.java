package com.google.android.datatransport.runtime;

/* JADX INFO: loaded from: classes4.dex */
public final class r implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Runnable f12605i;

    public r(Runnable runnable) {
        this.f12605i = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f12605i.run();
        } catch (Exception e5) {
            ac.b.B("Executor", "Background execution failure.", e5);
        }
    }
}
