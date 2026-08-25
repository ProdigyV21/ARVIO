package androidx.lifecycle;

import java.io.Closeable;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements Closeable, ka.k0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d7.j f3222i;

    public d(d7.j jVar) {
        this.f3222i = jVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        ka.m0.g(this.f3222i, null);
    }

    @Override // ka.k0
    public final d7.j getCoroutineContext() {
        return this.f3222i;
    }
}
