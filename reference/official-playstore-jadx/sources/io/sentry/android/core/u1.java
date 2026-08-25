package io.sentry.android.core;

import io.sentry.e6;
import io.sentry.f3;
import io.sentry.h3;
import io.sentry.z4;
import java.util.Date;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class u1 implements io.sentry.w0, io.sentry.android.core.internal.util.o {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final long f16780h = TimeUnit.SECONDS.toNanos(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e6 f16781i = new e6(new Date(0), 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f16782a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.q f16784c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile String f16785d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.util.b f16783b = new io.sentry.util.b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TreeSet f16786e = new TreeSet(new androidx.compose.ui.node.d(8));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ConcurrentSkipListSet f16787f = new ConcurrentSkipListSet();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f16788g = 16666666;

    public u1(SentryAndroidOptions sentryAndroidOptions, io.sentry.android.core.internal.util.q qVar) {
        this.f16784c = qVar;
        this.f16782a = sentryAndroidOptions.isEnablePerformanceV2() && sentryAndroidOptions.isEnableFramesTracking();
    }

    public static long f(z4 z4Var) {
        if (z4Var instanceof e6) {
            return z4Var.d(f16781i);
        }
        return System.nanoTime() - ((System.currentTimeMillis() * 1000000) - z4Var.i());
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01ec A[Catch: all -> 0x0126, TRY_LEAVE, TryCatch #2 {all -> 0x0126, blocks: (B:78:0x0150, B:80:0x015a, B:82:0x015e, B:84:0x0166, B:90:0x0173, B:95:0x0182, B:99:0x018d, B:101:0x0199, B:104:0x01a5, B:106:0x01af, B:107:0x01b9, B:102:0x019e, B:108:0x01bb, B:110:0x01ec, B:58:0x00e5, B:62:0x010b, B:66:0x0116, B:68:0x011a, B:70:0x0121), top: B:142:0x00e5 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0170  */
    @Override // io.sentry.w0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(io.sentry.k1 r36) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 607
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.u1.a(io.sentry.k1):void");
    }

    @Override // io.sentry.w0
    public final void b(io.sentry.k1 k1Var) {
        String str;
        if (!this.f16782a || (k1Var instanceof f3) || (k1Var instanceof h3)) {
            return;
        }
        io.sentry.util.a aVarA = this.f16783b.a();
        try {
            this.f16786e.add(k1Var);
            if (this.f16785d == null) {
                io.sentry.android.core.internal.util.q qVar = this.f16784c;
                if (qVar.f16638q) {
                    String strB = qb.d.B();
                    qVar.f16637p.put(strB, this);
                    qVar.c();
                    str = strB;
                } else {
                    str = null;
                }
                this.f16785d = str;
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.w0
    public final void clear() {
        io.sentry.util.a aVarA = this.f16783b.a();
        try {
            if (this.f16785d != null) {
                this.f16784c.b(this.f16785d);
                this.f16785d = null;
            }
            this.f16787f.clear();
            this.f16786e.clear();
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.android.core.internal.util.o
    public final void e(long j10, long j11, long j12, long j13, boolean z, boolean z5, float f10) {
        ConcurrentSkipListSet concurrentSkipListSet = this.f16787f;
        if (concurrentSkipListSet.size() > 3600) {
            return;
        }
        long j14 = (long) (f16780h / ((double) f10));
        this.f16788g = j14;
        if (z || z5) {
            concurrentSkipListSet.add(new t1(j10, j11, j12, j13, z, z5, j14));
        }
    }
}
