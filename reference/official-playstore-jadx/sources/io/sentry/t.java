package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class t implements d1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f17868a = new ThreadLocal();

    @Override // io.sentry.d1
    public final h1 a(c1 c1Var) {
        c1 c1Var2 = get();
        f17868a.set(c1Var);
        return new s(c1Var2);
    }

    @Override // io.sentry.d1
    public final void close() {
        f17868a.remove();
    }

    @Override // io.sentry.d1
    public final c1 get() {
        return (c1) f17868a.get();
    }
}
