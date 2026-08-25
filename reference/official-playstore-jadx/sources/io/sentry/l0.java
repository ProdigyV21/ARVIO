package io.sentry;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class l0 implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f17430b;

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        switch (this.f17429a) {
            case 0:
                StringBuilder sb2 = new StringBuilder("SentryHostnameCache-");
                int i10 = this.f17430b;
                this.f17430b = i10 + 1;
                sb2.append(i10);
                Thread thread = new Thread(runnable, sb2.toString());
                thread.setDaemon(true);
                return thread;
            case 1:
                StringBuilder sb3 = new StringBuilder("SentryExecutorServiceThreadFactory-");
                int i11 = this.f17430b;
                this.f17430b = i11 + 1;
                sb3.append(i11);
                Thread thread2 = new Thread(runnable, sb3.toString());
                thread2.setDaemon(true);
                return thread2;
            case 2:
                StringBuilder sb4 = new StringBuilder("SentryReplayIntegration-");
                int i12 = this.f17430b;
                this.f17430b = i12 + 1;
                sb4.append(i12);
                Thread thread3 = new Thread(runnable, sb4.toString());
                thread3.setDaemon(true);
                return thread3;
            case 3:
                StringBuilder sb5 = new StringBuilder("SentryReplayPersister-");
                int i13 = this.f17430b;
                this.f17430b = i13 + 1;
                sb5.append(i13);
                Thread thread4 = new Thread(runnable, sb5.toString());
                thread4.setDaemon(true);
                return thread4;
            default:
                StringBuilder sb6 = new StringBuilder("SentryAsyncConnection-");
                int i14 = this.f17430b;
                this.f17430b = i14 + 1;
                sb6.append(i14);
                Thread thread5 = new Thread(runnable, sb6.toString());
                thread5.setDaemon(true);
                return thread5;
        }
    }
}
