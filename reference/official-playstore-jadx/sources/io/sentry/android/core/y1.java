package io.sentry.android.core;

import android.app.ApplicationExitInfo;
import android.content.Context;
import io.sentry.j5;
import io.sentry.w5;
import j$.time.Instant;
import j$.time.format.DateTimeFormatter;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public final class y1 implements l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SentryAndroidOptions f16828a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final androidx.loader.app.d f16829b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f16830c;

    public y1(Context context, SentryAndroidOptions sentryAndroidOptions) {
        this.f16828a = sentryAndroidOptions;
        this.f16829b = new androidx.loader.app.d(sentryAndroidOptions);
        this.f16830c = context;
    }

    @Override // io.sentry.android.core.l0
    public final Long a() {
        return io.sentry.android.core.cache.e.k(this.f16828a, "last_tombstone_report", "Tombstone");
    }

    @Override // io.sentry.android.core.l0
    public final String b() {
        return "Tombstone";
    }

    @Override // io.sentry.android.core.l0
    public final boolean c() {
        return this.f16828a.isReportHistoricalTombstones();
    }

    @Override // io.sentry.android.core.l0
    public final m0 d(ApplicationExitInfo applicationExitInfo, boolean z) {
        SentryAndroidOptions sentryAndroidOptions = this.f16828a;
        try {
            InputStream traceInputStream = applicationExitInfo.getTraceInputStream();
            if (traceInputStream == null) {
                sentryAndroidOptions.getLogger().q(w5.WARNING, "No tombstone InputStream available for ApplicationExitInfo from %s", DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(applicationExitInfo.getTimestamp())));
                return null;
            }
            io.sentry.android.core.internal.tombstone.c cVar = new io.sentry.android.core.internal.tombstone.c(traceInputStream, sentryAndroidOptions.getInAppIncludes(), sentryAndroidOptions.getInAppExcludes(), this.f16830c.getApplicationInfo().nativeLibraryDir);
            try {
                j5 j5VarI = cVar.i();
                cVar.close();
                long timestamp = applicationExitInfo.getTimestamp();
                j5VarI.z = k2.c.n(timestamp);
                x1 x1Var = new x1(sentryAndroidOptions.getFlushTimeoutMillis(), sentryAndroidOptions.getLogger(), timestamp, z);
                io.sentry.j0 j0VarK = qb.l.k(x1Var);
                try {
                    j5 j5VarF = f(timestamp, j5VarI, j0VarK);
                    if (j5VarF != null) {
                        j5VarI = j5VarF;
                    }
                } catch (Throwable th) {
                    sentryAndroidOptions.getLogger().q(w5.WARNING, "Failed to merge native event with tombstone, continuing without merge: %s", th.getMessage());
                }
                return new m0(j5VarI, j0VarK, x1Var);
            } finally {
            }
        } catch (Throwable th2) {
            sentryAndroidOptions.getLogger().q(w5.WARNING, "Failed to parse tombstone from %s: %s", DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(applicationExitInfo.getTimestamp())), th2.getMessage());
            return null;
        }
    }

    @Override // io.sentry.android.core.l0
    public final int e() {
        return 5;
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x012c, code lost:
    
        r13.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x01bb A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final io.sentry.j5 f(long r23, io.sentry.j5 r25, io.sentry.j0 r26) {
        /*
            Method dump skipped, instruction units count: 988
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.y1.f(long, io.sentry.j5, io.sentry.j0):io.sentry.j5");
    }
}
