package com.google.common.util.concurrent;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b f14185c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f14186d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f14187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Throwable f14188b;

    static {
        if (p.GENERATE_CANCELLATION_CAUSES) {
            f14186d = null;
            f14185c = null;
        } else {
            f14186d = new b(null, false);
            f14185c = new b(null, true);
        }
    }

    public b(Throwable th, boolean z) {
        this.f14187a = z;
        this.f14188b = th;
    }
}
