package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class s implements h1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final c1 f17847i;

    public s(c1 c1Var) {
        this.f17847i = c1Var;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        t.f17868a.set(this.f17847i);
    }
}
