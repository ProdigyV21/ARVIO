package io.sentry.exception;

import ac.b;
import io.sentry.protocol.m;

/* JADX INFO: loaded from: classes5.dex */
public final class ExceptionMechanismException extends RuntimeException {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final m f17239i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Throwable f17240l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Thread f17241m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f17242n;

    public ExceptionMechanismException(m mVar, Throwable th, Thread thread, boolean z) {
        this.f17239i = mVar;
        b.V(th, "Throwable is required.");
        this.f17240l = th;
        this.f17241m = thread;
        this.f17242n = z;
    }
}
