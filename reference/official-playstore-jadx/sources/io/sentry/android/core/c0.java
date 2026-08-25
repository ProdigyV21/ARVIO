package io.sentry.android.core;

/* JADX INFO: loaded from: classes5.dex */
public final class c0 implements l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SentryAndroidOptions f16454a;

    public c0(SentryAndroidOptions sentryAndroidOptions) {
        this.f16454a = sentryAndroidOptions;
    }

    @Override // io.sentry.android.core.l0
    public final Long a() {
        return io.sentry.android.core.cache.e.k(this.f16454a, "last_anr_report", "ANR");
    }

    @Override // io.sentry.android.core.l0
    public final String b() {
        return "ANR";
    }

    @Override // io.sentry.android.core.l0
    public final boolean c() {
        return this.f16454a.isReportHistoricalAnrs();
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x00dc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[Catch: all -> 0x0029, DONT_GENERATE, FINALLY_INSNS, SYNTHETIC, TRY_LEAVE, TryCatch #6 {all -> 0x0029, blocks: (B:6:0x0015, B:10:0x0024, B:26:0x004f, B:60:0x00e4, B:59:0x00e1, B:56:0x00dc, B:8:0x001b, B:16:0x0030, B:25:0x004c, B:54:0x00d9, B:53:0x00d6), top: B:91:0x0015, inners: #0, #7 }] */
    @Override // io.sentry.android.core.l0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final io.sentry.android.core.m0 d(android.app.ApplicationExitInfo r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.c0.d(android.app.ApplicationExitInfo, boolean):io.sentry.android.core.m0");
    }

    @Override // io.sentry.android.core.l0
    public final int e() {
        return 6;
    }
}
