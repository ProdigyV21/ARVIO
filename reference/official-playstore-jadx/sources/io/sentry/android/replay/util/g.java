package io.sentry.android.replay.util;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f17052i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Runnable f17053l;

    public g(String str, Runnable runnable) {
        this.f17052i = str;
        this.f17053l = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f17053l.run();
    }
}
