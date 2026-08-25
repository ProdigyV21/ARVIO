package io.sentry.util.thread;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f17978a = Thread.currentThread().getId();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f17979b = new c();

    @Override // io.sentry.util.thread.a
    public final boolean a() {
        return f17978a == Thread.currentThread().getId();
    }

    @Override // io.sentry.util.thread.a
    public final String b() {
        return Thread.currentThread().getName();
    }

    @Override // io.sentry.util.thread.a
    public final long c() {
        return Thread.currentThread().getId();
    }
}
