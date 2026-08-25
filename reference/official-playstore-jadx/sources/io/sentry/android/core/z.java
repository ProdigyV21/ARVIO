package io.sentry.android.core;

import io.sentry.y6;

/* JADX INFO: loaded from: classes5.dex */
public final class z implements io.sentry.transport.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y6 f16831a;

    public z(SentryAndroidOptions sentryAndroidOptions) {
        this.f16831a = sentryAndroidOptions;
    }

    @Override // io.sentry.transport.h
    public final boolean isConnected() {
        int i10 = y.f16827a[this.f16831a.getConnectionStatusProvider().M().ordinal()];
        return i10 == 1 || i10 == 2 || i10 == 3;
    }
}
