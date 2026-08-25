package io.sentry.android.core.internal.util;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements io.sentry.transport.f, p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final d f16599i = new d();

    @Override // io.sentry.transport.f
    public long getCurrentTimeMillis() {
        return SystemClock.uptimeMillis();
    }
}
