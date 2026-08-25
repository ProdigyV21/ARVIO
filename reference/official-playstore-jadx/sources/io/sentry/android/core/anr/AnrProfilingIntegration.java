package io.sentry.android.core.anr;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.activity.n;
import androidx.activity.s;
import io.sentry.ILogger;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.e0;
import io.sentry.android.core.h0;
import io.sentry.s1;
import io.sentry.u2;
import io.sentry.util.q;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes5.dex */
public class AnrProfilingIntegration implements s1, Closeable, e0, Runnable {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile d f16412r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public volatile SentryAndroidOptions f16414t;
    public volatile Handler x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public volatile Thread f16418y;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AtomicBoolean f16405i = new AtomicBoolean(true);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final n f16406l = new n(this, 19);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.util.b f16407m = new io.sentry.util.b();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.util.b f16408n = new io.sentry.util.b();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile long f16409o = SystemClock.uptimeMillis();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final AtomicInteger f16410p = new AtomicInteger();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public volatile a f16411q = a.IDLE;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public volatile ILogger f16413s = u2.f17942i;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public volatile Thread f16415u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public volatile boolean f16416v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public volatile boolean f16417w = false;

    public enum a {
        IDLE,
        SUSPICIOUS,
        ANR_DETECTED
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f16405i.set(false);
        h0.f16496o.l(this);
        Handler handler = this.x;
        if (handler != null) {
            handler.removeCallbacks(this.f16406l);
        }
        Thread thread = this.f16415u;
        if (thread != null) {
            synchronized (this) {
                notifyAll();
            }
            thread.interrupt();
        }
        SentryAndroidOptions sentryAndroidOptions = this.f16414t;
        io.sentry.util.a aVarA = this.f16408n.a();
        try {
            d dVar = this.f16412r;
            this.f16412r = null;
            aVarA.close();
            if (sentryAndroidOptions != null) {
                try {
                    sentryAndroidOptions.getExecutorService().submit(new s(this, dVar, 28));
                } catch (Throwable unused) {
                    this.f16413s.q(w5.WARNING, "Failed to submit AnrProfileManager close", new Object[0]);
                }
            }
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.android.core.e0
    public final void i() {
        if (this.f16405i.get()) {
            io.sentry.util.a aVarA = this.f16407m.a();
            try {
                if (this.f16417w) {
                    aVarA.close();
                    return;
                }
                this.f16417w = true;
                this.f16406l.run();
                Thread thread = this.f16415u;
                if (thread != null && thread.isAlive()) {
                    synchronized (this) {
                        notifyAll();
                    }
                }
                if (thread == null || !thread.isAlive()) {
                    Thread thread2 = new Thread(this, "AnrProfilingIntegration");
                    thread2.setDaemon(true);
                    thread2.start();
                    this.f16415u = thread2;
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
    }

    @Override // io.sentry.android.core.e0
    public final void j() {
        if (this.f16405i.get()) {
            io.sentry.util.a aVarA = this.f16407m.a();
            try {
                this.f16417w = false;
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
    }

    public final void k(Thread thread) {
        long jUptimeMillis = SystemClock.uptimeMillis() - this.f16409o;
        if (jUptimeMillis < 1000) {
            this.f16411q = a.IDLE;
            this.f16416v = false;
        }
        if (this.f16411q == a.IDLE && jUptimeMillis > 1000) {
            ILogger iLogger = this.f16413s;
            w5 w5Var = w5.DEBUG;
            if (iLogger.t(w5Var)) {
                this.f16413s.q(w5Var, "ANR: main thread is suspicious", new Object[0]);
            }
            this.f16411q = a.SUSPICIOUS;
            SentryAndroidOptions sentryAndroidOptions = this.f16414t;
            Double anrProfilingSampleRate = sentryAndroidOptions != null ? sentryAndroidOptions.getAnrProfilingSampleRate() : null;
            if (anrProfilingSampleRate != null && q.a().c() < anrProfilingSampleRate.doubleValue()) {
                this.f16416v = true;
            }
            if (this.f16416v) {
                this.f16410p.set(0);
                o().f16432i.clear();
            }
        }
        if (this.f16416v && (this.f16411q == a.SUSPICIOUS || this.f16411q == a.ANR_DETECTED)) {
            if (this.f16410p.get() < 151) {
                long jUptimeMillis2 = SystemClock.uptimeMillis();
                f fVar = new f(System.currentTimeMillis(), thread.getStackTrace());
                long jUptimeMillis3 = SystemClock.uptimeMillis() - jUptimeMillis2;
                ILogger iLogger2 = this.f16413s;
                w5 w5Var2 = w5.DEBUG;
                if (iLogger2.t(w5Var2)) {
                    this.f16413s.q(w5Var2, androidx.compose.material3.d.k(jUptimeMillis3, "AnrWatchdog: capturing main thread stacktrace took ", "ms"), new Object[0]);
                }
                if (this.f16405i.get()) {
                    this.f16410p.incrementAndGet();
                    o().f16432i.k(fVar);
                }
            } else {
                ILogger iLogger3 = this.f16413s;
                w5 w5Var3 = w5.DEBUG;
                if (iLogger3.t(w5Var3)) {
                    this.f16413s.q(w5Var3, "ANR: reached maximum number of collected stack traces, skipping further collection", new Object[0]);
                }
            }
        }
        if (this.f16411q != a.SUSPICIOUS || jUptimeMillis <= 4000) {
            return;
        }
        ILogger iLogger4 = this.f16413s;
        w5 w5Var4 = w5.DEBUG;
        if (iLogger4.t(w5Var4)) {
            this.f16413s.q(w5Var4, "ANR: main thread ANR threshold reached", new Object[0]);
        }
        this.f16411q = a.ANR_DETECTED;
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16414t = sentryAndroidOptions;
        this.f16413s = y6Var.getLogger();
        if (this.f16414t.isAnrProfilingEnabled()) {
            if (this.f16414t.getCacheDirPath() == null) {
                this.f16413s.q(w5.WARNING, "ANR Profiling is enabled but cacheDirPath is not set", new Object[0]);
                return;
            }
            Looper mainLooper = Looper.getMainLooper();
            this.f16418y = mainLooper.getThread();
            this.x = new Handler(mainLooper);
            xc.d.H("AnrProfiling");
            h0.f16496o.i(this);
        }
    }

    public final d o() {
        io.sentry.util.a aVarA = this.f16408n.a();
        try {
            if (this.f16412r == null) {
                SentryAndroidOptions sentryAndroidOptions = this.f16414t;
                ac.b.V(sentryAndroidOptions, "Options can't be null");
                String cacheDirPath = sentryAndroidOptions.getCacheDirPath();
                if (cacheDirPath == null) {
                    throw new IllegalStateException("cacheDirPath is required for ANR profiling");
                }
                File file = new File(cacheDirPath);
                e.b(file);
                this.f16412r = new d(sentryAndroidOptions, new File(file, "anr_profile"));
            }
            d dVar = this.f16412r;
            aVarA.close();
            return dVar;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Handler handler = this.x;
        Thread thread = this.f16418y;
        if (handler == null || thread == null) {
            return;
        }
        while (this.f16405i.get() && !Thread.currentThread().isInterrupted()) {
            try {
                try {
                    if (this.f16417w) {
                        k(thread);
                        handler.removeCallbacks(this.f16406l);
                        handler.post(this.f16406l);
                        Thread.sleep(66L);
                    } else {
                        synchronized (this) {
                            while (!this.f16417w && this.f16405i.get()) {
                                try {
                                    wait();
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                        }
                        this.f16406l.run();
                    }
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return;
                }
            } catch (Throwable th2) {
                this.f16413s.l(w5.WARNING, "Failed to execute AnrStacktraceIntegration", th2);
                return;
            }
        }
    }
}
