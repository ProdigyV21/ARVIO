package com.google.android.datatransport.runtime;

import java.io.Closeable;
import java.io.IOException;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class k implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Provider f12593i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public c3.g f12594l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Provider f12595m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public com.google.android.datatransport.runtime.scheduling.persistence.e f12596n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Provider f12597o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Provider f12598p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Provider f12599q;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        ((com.google.android.datatransport.runtime.scheduling.persistence.d) this.f12598p.get()).close();
    }
}
