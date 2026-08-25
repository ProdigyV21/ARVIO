package io.sentry.android.core;

import android.os.Handler;
import androidx.lifecycle.ProcessLifecycleOwner;
import io.sentry.ILogger;
import io.sentry.u2;
import io.sentry.w5;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public final class h0 implements Closeable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final h0 f16496o = new h0();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile g0 f16498l;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.util.b f16497i = new io.sentry.util.b();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final r0 f16499m = new r0();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile Boolean f16500n = null;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        o();
    }

    public final void i(e0 e0Var) {
        io.sentry.util.a aVarA = this.f16497i.a();
        try {
            k(u2.f17942i);
            if (this.f16498l != null) {
                this.f16498l.f16488i.add(e0Var);
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

    public final void j(ILogger iLogger) {
        g0 g0Var = this.f16498l;
        if (g0Var != null) {
            try {
                ProcessLifecycleOwner.f3185s.f3191p.addObserver(g0Var);
            } catch (Throwable th) {
                this.f16498l = null;
                iLogger.l(w5.ERROR, "AppState failed to get Lifecycle and could not install lifecycle observer.", th);
            }
        }
    }

    public final void k(ILogger iLogger) {
        if (this.f16498l != null) {
            return;
        }
        try {
            ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.f3185s;
            this.f16498l = new g0(this);
            if (io.sentry.android.core.internal.util.e.f16600a.a()) {
                j(iLogger);
                return;
            }
            r0 r0Var = this.f16499m;
            ((Handler) r0Var.f16735a).post(new androidx.activity.s(this, iLogger, 25));
        } catch (ClassNotFoundException unused) {
            iLogger.q(w5.WARNING, "androidx.lifecycle is not available, some features might not be properly working,e.g. Session Tracking, Network and System Events breadcrumbs, etc.", new Object[0]);
        } catch (Throwable th) {
            iLogger.l(w5.ERROR, "AppState could not register lifecycle observer", th);
        }
    }

    public final void l(e0 e0Var) {
        io.sentry.util.a aVarA = this.f16497i.a();
        try {
            if (this.f16498l != null) {
                this.f16498l.f16488i.remove(e0Var);
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

    public final void o() {
        if (this.f16498l == null) {
            return;
        }
        io.sentry.util.a aVarA = this.f16497i.a();
        try {
            g0 g0Var = this.f16498l;
            this.f16498l.f16488i.clear();
            this.f16498l = null;
            aVarA.close();
            if (io.sentry.android.core.internal.util.e.f16600a.a()) {
                if (g0Var != null) {
                    ProcessLifecycleOwner.f3185s.f3191p.removeObserver(g0Var);
                }
            } else {
                r0 r0Var = this.f16499m;
                ((Handler) r0Var.f16735a).post(new androidx.activity.n(this, g0Var));
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
}
