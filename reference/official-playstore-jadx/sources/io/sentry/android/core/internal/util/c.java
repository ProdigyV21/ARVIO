package io.sentry.android.core.internal.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.SystemClock;
import io.sentry.ILogger;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.e0;
import io.sentry.android.core.h0;
import io.sentry.android.core.q0;
import io.sentry.android.core.r0;
import io.sentry.o0;
import io.sentry.w5;
import io.sentry.y6;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements o0, e0 {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static volatile ConnectivityManager f16586w;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16588i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final y6 f16589l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final r0 f16590m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d f16591n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ArrayList f16592o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.util.b f16593p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public volatile b f16594q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile NetworkCapabilities f16595r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public volatile Network f16596s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public volatile long f16597t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final AtomicBoolean f16598u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final io.sentry.util.b f16585v = new io.sentry.util.b();
    public static final io.sentry.util.b x = new io.sentry.util.b();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final ArrayList f16587y = new ArrayList();
    public static final int[] z = {1, 0, 3, 2};
    public static final int[] A = new int[2];

    public c(Context context, r0 r0Var, SentryAndroidOptions sentryAndroidOptions) {
        d dVar = d.f16599i;
        this.f16593p = new io.sentry.util.b();
        this.f16597t = 0L;
        this.f16598u = new AtomicBoolean(false);
        Context applicationContext = context.getApplicationContext();
        this.f16588i = applicationContext != null ? applicationContext : context;
        this.f16589l = sentryAndroidOptions;
        this.f16590m = r0Var;
        this.f16591n = dVar;
        this.f16592o = new ArrayList();
        int[] iArr = A;
        iArr[0] = 12;
        iArr[1] = 16;
        R(new a(this, 1));
        h0.f16496o.i(this);
    }

    public static ConnectivityManager G(Context context, ILogger iLogger) {
        if (f16586w != null) {
            return f16586w;
        }
        io.sentry.util.a aVarA = f16585v.a();
        try {
            if (f16586w != null) {
                ConnectivityManager connectivityManager = f16586w;
                aVarA.close();
                return connectivityManager;
            }
            f16586w = (ConnectivityManager) context.getSystemService("connectivity");
            if (f16586w == null) {
                iLogger.q(w5.INFO, "ConnectivityManager is null and cannot check network status", new Object[0]);
            }
            ConnectivityManager connectivityManager2 = f16586w;
            aVarA.close();
            return connectivityManager2;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static boolean O(Context context, ILogger iLogger, r0 r0Var, b bVar) {
        r0Var.getClass();
        if (Build.VERSION.SDK_INT < 24) {
            iLogger.q(w5.DEBUG, "NetworkCallbacks need Android N+.", new Object[0]);
            return false;
        }
        ConnectivityManager connectivityManagerG = G(context, iLogger);
        if (connectivityManagerG == null) {
            return false;
        }
        if (!xc.d.o0(context)) {
            iLogger.q(w5.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
            return false;
        }
        try {
            connectivityManagerG.registerDefaultNetworkCallback(bVar);
            return true;
        } catch (Throwable th) {
            iLogger.l(w5.WARNING, "registerDefaultNetworkCallback failed", th);
            return false;
        }
    }

    public static boolean k(Context context, ILogger iLogger, r0 r0Var, ConnectivityManager.NetworkCallback networkCallback) {
        r0Var.getClass();
        if (Build.VERSION.SDK_INT < 24) {
            iLogger.q(w5.DEBUG, "NetworkCallbacks need Android N+.", new Object[0]);
            return false;
        }
        if (!xc.d.o0(context)) {
            iLogger.q(w5.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
            return false;
        }
        io.sentry.util.a aVarA = x.a();
        try {
            f16587y.add(networkCallback);
            aVarA.close();
            return true;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String x(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities.hasTransport(3)) {
            return "ethernet";
        }
        if (networkCapabilities.hasTransport(1)) {
            return "wifi";
        }
        if (networkCapabilities.hasTransport(0)) {
            return "cellular";
        }
        return null;
    }

    @Override // io.sentry.o0
    public final void G0(o0.b bVar) {
        io.sentry.util.a aVarA = this.f16593p.a();
        try {
            this.f16592o.remove(bVar);
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

    @Override // io.sentry.o0
    public final o0.a M() {
        this.f16591n.getClass();
        if (SystemClock.uptimeMillis() - this.f16597t >= 120000) {
            V(null);
        }
        return o();
    }

    public final void R(Runnable runnable) {
        y6 y6Var = this.f16589l;
        try {
            y6Var.getExecutorService().submit(runnable);
        } catch (Throwable th) {
            y6Var.getLogger().l(w5.ERROR, "AndroidConnectionStatusProvider submit failed", th);
        }
    }

    public final void T(boolean z5) {
        io.sentry.util.a aVarA = this.f16593p.a();
        if (z5) {
            try {
                this.f16592o.clear();
            } catch (Throwable th) {
                try {
                    aVarA.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        b bVar = this.f16594q;
        this.f16594q = null;
        if (bVar != null) {
            Context context = this.f16588i;
            ILogger logger = this.f16589l.getLogger();
            ConnectivityManager connectivityManagerG = G(context, logger);
            if (connectivityManagerG != null) {
                try {
                    connectivityManagerG.unregisterNetworkCallback(bVar);
                } catch (Throwable th3) {
                    logger.l(w5.WARNING, "unregisterNetworkCallback failed", th3);
                }
            }
        }
        this.f16595r = null;
        this.f16596s = null;
        this.f16597t = 0L;
        aVarA.close();
        this.f16589l.getLogger().q(w5.DEBUG, "Network callback unregistered", new Object[0]);
    }

    public final void V(NetworkCapabilities networkCapabilities) {
        io.sentry.util.a aVarA = this.f16593p.a();
        try {
            if (networkCapabilities != null) {
                this.f16595r = networkCapabilities;
            } else {
                if (!xc.d.o0(this.f16588i)) {
                    this.f16589l.getLogger().q(w5.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
                    this.f16595r = null;
                    this.f16591n.getClass();
                    this.f16597t = SystemClock.uptimeMillis();
                    aVarA.close();
                    return;
                }
                this.f16590m.getClass();
                ConnectivityManager connectivityManagerG = G(this.f16588i, this.f16589l.getLogger());
                if (connectivityManagerG != null) {
                    Network activeNetwork = connectivityManagerG.getActiveNetwork();
                    this.f16595r = activeNetwork != null ? connectivityManagerG.getNetworkCapabilities(activeNetwork) : null;
                } else {
                    this.f16595r = null;
                }
            }
            this.f16591n.getClass();
            this.f16597t = SystemClock.uptimeMillis();
            this.f16589l.getLogger().q(w5.DEBUG, "Cache updated - Status: " + o() + ", Type: " + y(), new Object[0]);
        } catch (Throwable th) {
            try {
                this.f16589l.getLogger().l(w5.WARNING, "Failed to update connection status cache", th);
                this.f16595r = null;
                this.f16591n.getClass();
                this.f16597t = SystemClock.uptimeMillis();
            } catch (Throwable th2) {
                try {
                    aVarA.close();
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
                throw th2;
            }
        }
        aVarA.close();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        R(new a(this, 0));
    }

    @Override // io.sentry.android.core.e0
    public final void i() {
        if (this.f16594q != null) {
            return;
        }
        R(new a(this, 3));
    }

    @Override // io.sentry.android.core.e0
    public final void j() {
        if (this.f16594q == null) {
            return;
        }
        R(new a(this, 2));
    }

    public final void l() {
        if (q0.i() && this.f16594q == null) {
            io.sentry.util.a aVarA = this.f16593p.a();
            try {
                if (this.f16594q != null) {
                    aVarA.close();
                    return;
                }
                b bVar = new b(this);
                if (O(this.f16588i, this.f16589l.getLogger(), this.f16590m, bVar)) {
                    this.f16594q = bVar;
                    this.f16589l.getLogger().q(w5.DEBUG, "Network callback registered successfully", new Object[0]);
                } else {
                    this.f16589l.getLogger().q(w5.WARNING, "Failed to register network callback", new Object[0]);
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

    public final o0.a o() {
        if (this.f16595r != null) {
            NetworkCapabilities networkCapabilities = this.f16595r;
            if (networkCapabilities != null) {
                boolean zHasCapability = networkCapabilities.hasCapability(12);
                this.f16590m.getClass();
                if (zHasCapability && networkCapabilities.hasCapability(16)) {
                    for (int i10 : z) {
                        if (networkCapabilities.hasTransport(i10)) {
                            return o0.a.CONNECTED;
                        }
                    }
                }
            }
            return o0.a.DISCONNECTED;
        }
        ConnectivityManager connectivityManagerG = G(this.f16588i, this.f16589l.getLogger());
        if (connectivityManagerG == null) {
            return o0.a.UNKNOWN;
        }
        Context context = this.f16588i;
        ILogger logger = this.f16589l.getLogger();
        if (!xc.d.o0(context)) {
            logger.q(w5.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
            return o0.a.NO_PERMISSION;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManagerG.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected() ? o0.a.CONNECTED : o0.a.DISCONNECTED;
            }
            logger.q(w5.INFO, "NetworkInfo is null, there's no active network.", new Object[0]);
            return o0.a.DISCONNECTED;
        } catch (Throwable th) {
            logger.l(w5.WARNING, "Could not retrieve Connection Status", th);
            return o0.a.UNKNOWN;
        }
    }

    @Override // io.sentry.o0
    public final String s() {
        this.f16591n.getClass();
        if (SystemClock.uptimeMillis() - this.f16597t >= 120000) {
            V(null);
        }
        return y();
    }

    public final String y() {
        NetworkCapabilities networkCapabilities = this.f16595r;
        if (networkCapabilities != null) {
            return x(networkCapabilities);
        }
        Context context = this.f16588i;
        ILogger logger = this.f16589l.getLogger();
        r0 r0Var = this.f16590m;
        ConnectivityManager connectivityManagerG = G(context, logger);
        if (connectivityManagerG != null) {
            if (!xc.d.o0(context)) {
                logger.q(w5.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
                return null;
            }
            try {
                r0Var.getClass();
                Network activeNetwork = connectivityManagerG.getActiveNetwork();
                if (activeNetwork == null) {
                    logger.q(w5.INFO, "Network is null and cannot check network status", new Object[0]);
                    return null;
                }
                NetworkCapabilities networkCapabilities2 = connectivityManagerG.getNetworkCapabilities(activeNetwork);
                if (networkCapabilities2 == null) {
                    logger.q(w5.INFO, "NetworkCapabilities is null and cannot check network type", new Object[0]);
                    return null;
                }
                boolean zHasTransport = networkCapabilities2.hasTransport(3);
                boolean zHasTransport2 = networkCapabilities2.hasTransport(1);
                boolean zHasTransport3 = networkCapabilities2.hasTransport(0);
                if (zHasTransport) {
                    return "ethernet";
                }
                if (zHasTransport2) {
                    return "wifi";
                }
                if (zHasTransport3) {
                    return "cellular";
                }
            } catch (Throwable th) {
                logger.l(w5.ERROR, "Failed to retrieve network info", th);
                return null;
            }
        }
        return null;
    }

    @Override // io.sentry.o0
    public final boolean y0(o0.b bVar) {
        io.sentry.util.a aVarA = this.f16593p.a();
        try {
            this.f16592o.add(bVar);
            aVarA.close();
            l();
            return this.f16594q != null;
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
