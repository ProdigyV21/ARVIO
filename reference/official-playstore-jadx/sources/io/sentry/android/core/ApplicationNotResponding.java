package io.sentry.android.core;

/* JADX INFO: loaded from: classes5.dex */
final class ApplicationNotResponding extends RuntimeException {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Thread f16335i;

    public ApplicationNotResponding(String str) {
        super(str);
        this.f16335i = null;
    }

    public ApplicationNotResponding(String str, Thread thread) {
        super(str);
        ac.b.V(thread, "Thread must be provided.");
        this.f16335i = thread;
        setStackTrace(thread.getStackTrace());
    }
}
