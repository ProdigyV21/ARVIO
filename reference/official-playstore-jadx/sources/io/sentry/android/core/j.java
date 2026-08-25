package io.sentry.android.core;

import android.os.SystemClock;
import android.system.Os;
import android.system.OsConstants;
import io.sentry.ILogger;
import io.sentry.n3;
import io.sentry.w5;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements io.sentry.x0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ILogger f16651g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f16645a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f16646b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f16647c = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f16648d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f16649e = 1.0E9d / 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final File f16650f = new File("/proc/self/stat");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f16652h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Pattern f16653i = Pattern.compile("[\n\t\r ]");

    public j(ILogger iLogger) {
        ac.b.V(iLogger, "Logger is required.");
        this.f16651g = iLogger;
    }

    @Override // io.sentry.x0
    public final void c() {
        this.f16652h = true;
        this.f16647c = Os.sysconf(OsConstants._SC_CLK_TCK);
        this.f16648d = Os.sysconf(OsConstants._SC_NPROCESSORS_CONF);
        this.f16649e = 1.0E9d / this.f16647c;
        this.f16646b = e();
    }

    @Override // io.sentry.x0
    public final void d(n3 n3Var) {
        if (this.f16652h) {
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            long j10 = jElapsedRealtimeNanos - this.f16645a;
            this.f16645a = jElapsedRealtimeNanos;
            long jE = e();
            long j11 = jE - this.f16646b;
            this.f16646b = jE;
            n3Var.f17484a = Double.valueOf(((j11 / j10) / this.f16648d) * 100.0d);
        }
    }

    public final long e() {
        String strM;
        ILogger iLogger = this.f16651g;
        try {
            strM = qb.d.M(this.f16650f);
        } catch (IOException e5) {
            this.f16652h = false;
            iLogger.l(w5.WARNING, "Unable to read /proc/self/stat file. Disabling cpu collection.", e5);
            strM = null;
        }
        if (strM != null) {
            String[] strArrSplit = this.f16653i.split(strM.trim());
            try {
                long j10 = Long.parseLong(strArrSplit[13]);
                long j11 = Long.parseLong(strArrSplit[14]);
                return (long) ((j10 + j11 + Long.parseLong(strArrSplit[15]) + Long.parseLong(strArrSplit[16])) * this.f16649e);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e6) {
                iLogger.l(w5.ERROR, "Error parsing /proc/self/stat file.", e6);
            }
        }
        return 0L;
    }
}
