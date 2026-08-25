package io.sentry.android.core;

import android.os.Debug;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.ILogger;
import io.sentry.n3;
import io.sentry.w5;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final File f16757b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f16758c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f16761f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.q f16762g;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.util.j f16766l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ILogger f16767m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f16756a = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Future f16759d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public File f16760e = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayDeque f16763h = new ArrayDeque();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayDeque f16764i = new ArrayDeque();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayDeque f16765j = new ArrayDeque();
    public final HashMap k = new HashMap();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile boolean f16768n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final io.sentry.util.b f16769o = new io.sentry.util.b();

    public u(String str, int i10, io.sentry.android.core.internal.util.q qVar, io.sentry.util.j jVar, ILogger iLogger) {
        ac.b.V(str, "TracesFilesDirPath is required");
        this.f16757b = new File(str);
        this.f16758c = i10;
        ac.b.V(iLogger, "Logger is required");
        this.f16767m = iLogger;
        this.f16766l = jVar;
        ac.b.V(qVar, "SentryFrameMetricsCollector is required");
        this.f16762g = qVar;
    }

    public final androidx.mediarouter.media.l0 a(List list, boolean z) {
        io.sentry.util.a aVarA = this.f16769o.a();
        try {
            if (!this.f16768n) {
                this.f16767m.q(w5.WARNING, "Profiler not running", new Object[0]);
                aVarA.close();
                return null;
            }
            try {
                Debug.stopMethodTracing();
            } finally {
                try {
                } catch (Throwable th) {
                }
            }
            this.f16768n = false;
            this.f16762g.b(this.f16761f);
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            long elapsedCpuTime = Process.getElapsedCpuTime();
            if (this.f16760e == null) {
                this.f16767m.q(w5.ERROR, "Trace file does not exists", new Object[0]);
                aVarA.close();
                return null;
            }
            if (!this.f16764i.isEmpty()) {
                this.k.put("slow_frame_renders", new io.sentry.profilemeasurements.a("nanosecond", this.f16764i));
            }
            if (!this.f16765j.isEmpty()) {
                this.k.put("frozen_frame_renders", new io.sentry.profilemeasurements.a("nanosecond", this.f16765j));
            }
            if (!this.f16763h.isEmpty()) {
                this.k.put("screen_frame_rates", new io.sentry.profilemeasurements.a("hz", this.f16763h));
            }
            b(list);
            Future future = this.f16759d;
            if (future != null) {
                future.cancel(true);
                this.f16759d = null;
            }
            androidx.mediarouter.media.l0 l0Var = new androidx.mediarouter.media.l0(jElapsedRealtimeNanos, elapsedCpuTime, z, this.f16760e, this.k);
            aVarA.close();
            return l0Var;
        } finally {
        }
    }

    public final void b(List list) {
        long jElapsedRealtimeNanos = (SystemClock.elapsedRealtimeNanos() - this.f16756a) - TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        if (list != null) {
            ArrayDeque arrayDeque = new ArrayDeque(list.size());
            ArrayDeque arrayDeque2 = new ArrayDeque(list.size());
            ArrayDeque arrayDeque3 = new ArrayDeque(list.size());
            synchronized (list) {
                try {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        n3 n3Var = (n3) it.next();
                        long j10 = n3Var.f17487d;
                        long j11 = j10 + jElapsedRealtimeNanos;
                        Double d4 = n3Var.f17484a;
                        Long l10 = n3Var.f17485b;
                        Long l11 = n3Var.f17486c;
                        if (d4 != null) {
                            arrayDeque3.add(new io.sentry.profilemeasurements.b(Long.valueOf(j11), d4, j10));
                        }
                        if (l10 != null) {
                            arrayDeque.add(new io.sentry.profilemeasurements.b(Long.valueOf(j11), l10, j10));
                        }
                        if (l11 != null) {
                            arrayDeque2.add(new io.sentry.profilemeasurements.b(Long.valueOf(j11), l11, j10));
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (!arrayDeque3.isEmpty()) {
                this.k.put("cpu_usage", new io.sentry.profilemeasurements.a("percent", arrayDeque3));
            }
            if (!arrayDeque.isEmpty()) {
                this.k.put("memory_footprint", new io.sentry.profilemeasurements.a("byte", arrayDeque));
            }
            if (arrayDeque2.isEmpty()) {
                return;
            }
            this.k.put("memory_native_footprint", new io.sentry.profilemeasurements.a("byte", arrayDeque2));
        }
    }

    public final t c() {
        String strB;
        io.sentry.util.a aVarA = this.f16769o.a();
        try {
            int i10 = this.f16758c;
            if (i10 == 0) {
                this.f16767m.q(w5.WARNING, "Disabling profiling because intervaUs is set to %d", Integer.valueOf(i10));
                aVarA.close();
                return null;
            }
            if (this.f16768n) {
                this.f16767m.q(w5.WARNING, "Profiling has already started...", new Object[0]);
                aVarA.close();
                return null;
            }
            this.f16760e = new File(this.f16757b, qb.d.B().concat(".trace"));
            this.k.clear();
            this.f16763h.clear();
            this.f16764i.clear();
            this.f16765j.clear();
            io.sentry.android.core.internal.util.q qVar = this.f16762g;
            s sVar = new s(this);
            if (qVar.f16638q) {
                strB = qb.d.B();
                qVar.f16637p.put(strB, sVar);
                qVar.c();
            } else {
                strB = null;
            }
            this.f16761f = strB;
            try {
                io.sentry.util.j jVar = this.f16766l;
                if (jVar != null) {
                    this.f16759d = ((io.sentry.g1) jVar.g()).a(30000L, new androidx.activity.n(this, 16));
                }
            } catch (RejectedExecutionException e5) {
                this.f16767m.l(w5.ERROR, "Failed to call the executor. Profiling will not be automatically finished. Did you call Sentry.close()?", e5);
            }
            this.f16756a = SystemClock.elapsedRealtimeNanos();
            Date dateM = k2.c.m();
            long elapsedCpuTime = Process.getElapsedCpuTime();
            try {
                Debug.startMethodTracingSampling(this.f16760e.getPath(), 3000000, this.f16758c);
                this.f16768n = true;
                t tVar = new t(this.f16756a, elapsedCpuTime, dateM);
                aVarA.close();
                return tVar;
            } catch (Throwable th) {
                a(null, false);
                this.f16767m.l(w5.ERROR, "Unable to start a profile: ", th);
                this.f16768n = false;
                aVarA.close();
                return null;
            }
        } finally {
        }
    }
}
