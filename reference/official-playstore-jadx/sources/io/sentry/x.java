package io.sentry;

import java.io.File;

/* JADX INFO: loaded from: classes4.dex */
public abstract class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c1 f18045a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ILogger f18046b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f18047c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r7 f18048d;

    public x(c1 c1Var, ILogger iLogger, long j10, int i10) {
        this.f18045a = c1Var;
        this.f18046b = iLogger;
        this.f18047c = j10;
        this.f18048d = new r7(new h(i10));
    }

    public abstract boolean a(String str);

    public abstract void b(File file, j0 j0Var);
}
