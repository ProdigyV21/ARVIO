package io.sentry.transport;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class m implements Future {
    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return true;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        throw new CancellationException();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return true;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return true;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j10, TimeUnit timeUnit) {
        throw new CancellationException();
    }
}
