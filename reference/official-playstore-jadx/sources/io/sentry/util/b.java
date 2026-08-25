package io.sentry.util;

import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends ReentrantLock {
    public final a a() {
        lock();
        return new a(this);
    }
}
