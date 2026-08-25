package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class t1 implements x0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runtime f17869a = Runtime.getRuntime();

    @Override // io.sentry.x0
    public final void c() {
    }

    @Override // io.sentry.x0
    public final void d(n3 n3Var) {
        Runtime runtime = this.f17869a;
        n3Var.f17485b = Long.valueOf(runtime.totalMemory() - runtime.freeMemory());
    }
}
