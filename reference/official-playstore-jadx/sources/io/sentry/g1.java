package io.sentry;

import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes4.dex */
public interface g1 {
    Future a(long j10, Runnable runnable);

    void b(long j10);

    void c();

    boolean isClosed();

    Future submit(Runnable runnable);
}
