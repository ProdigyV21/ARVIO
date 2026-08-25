package com.google.android.gms.internal.cast;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
public abstract class q9 extends u0 implements com.google.common.util.concurrent.d1 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Object f13555p = new Object();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final e4 f13556q = new e4(1);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final boolean f13557r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final u0 f13558s;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile Object f13559m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile g9 f13560n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile p9 f13561o;

    static {
        boolean z;
        u0 k9Var;
        Throwable th;
        Throwable th2;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        f13557r = z;
        String property = System.getProperty("java.runtime.name", "");
        Throwable th3 = null;
        if (property == null || property.contains("Android")) {
            try {
                k9Var = new o9(13);
            } catch (Error | Exception e5) {
                try {
                    k9Var = new j9(13);
                } catch (Error | Exception e6) {
                    th3 = e6;
                    k9Var = new k9(13);
                }
                th = th3;
                th2 = e5;
            }
        } else {
            try {
                k9Var = new j9(13);
            } catch (NoClassDefFoundError unused2) {
                k9Var = new k9(13);
            }
        }
        th = null;
        th2 = null;
        f13558s = k9Var;
        if (th != null) {
            e4 e4Var = f13556q;
            Logger loggerA = e4Var.a();
            Level level = Level.SEVERE;
            loggerA.logp(level, "com.google.common.util.concurrent.AbstractFutureState", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            e4Var.a().logp(level, "com.google.common.util.concurrent.AbstractFutureState", "<clinit>", "AtomicReferenceFieldUpdaterAtomicHelper is broken!", th);
        }
    }

    public final void C(p9 p9Var) {
        p9Var.f13544a = null;
        while (true) {
            p9 p9Var2 = this.f13561o;
            if (p9Var2 != p9.f13543c) {
                p9 p9Var3 = null;
                while (p9Var2 != null) {
                    p9 p9Var4 = p9Var2.f13545b;
                    if (p9Var2.f13544a != null) {
                        p9Var3 = p9Var2;
                    } else if (p9Var3 != null) {
                        p9Var3.f13545b = p9Var4;
                        if (p9Var3.f13544a == null) {
                            break;
                        }
                    } else if (!f13558s.v(this, p9Var2, p9Var4)) {
                        break;
                    }
                    p9Var2 = p9Var4;
                }
                return;
            }
            return;
        }
    }
}
