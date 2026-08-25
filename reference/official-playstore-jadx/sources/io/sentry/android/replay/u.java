package io.sentry.android.replay;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final class u implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AtomicBoolean f17032i = new AtomicBoolean(false);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.util.b f17033l = new io.sentry.util.b();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.android.core.f0 f17034m = new io.sentry.android.core.f0(this, 1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final t f17035n = new t(this);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f17032i.set(true);
        this.f17034m.clear();
    }
}
