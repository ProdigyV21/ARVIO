package io.sentry.util;

import io.sentry.h1;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements h1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b f17958i;

    public a(b bVar) {
        this.f17958i = bVar;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.f17958i.unlock();
    }
}
