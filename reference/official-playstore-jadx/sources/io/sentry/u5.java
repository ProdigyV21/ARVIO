package io.sentry;

import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes4.dex */
public final class u5 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile u5 f17952c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final io.sentry.util.b f17953d = new io.sentry.util.b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile Boolean f17954e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final io.sentry.util.b f17955f = new io.sentry.util.b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CopyOnWriteArraySet f17956a = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArraySet f17957b = new CopyOnWriteArraySet();

    public static u5 d() {
        if (f17952c == null) {
            io.sentry.util.a aVarA = f17953d.a();
            try {
                if (f17952c == null) {
                    f17952c = new u5();
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
        return f17952c;
    }

    public final void a(String str) {
        ac.b.V(str, "integration is required.");
        this.f17956a.add(str);
    }

    public final void b(String str, String str2) {
        this.f17957b.add(new io.sentry.protocol.w(str, str2));
        io.sentry.util.a aVarA = f17955f.a();
        try {
            f17954e = null;
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

    public final boolean c(ILogger iLogger) {
        Boolean bool = f17954e;
        if (bool != null) {
            return bool.booleanValue();
        }
        io.sentry.util.a aVarA = f17955f.a();
        try {
            boolean z = false;
            for (io.sentry.protocol.w wVar : this.f17957b) {
                if (wVar.f17709i.startsWith("maven:io.sentry:") && !"8.40.0".equalsIgnoreCase(wVar.f17710l)) {
                    iLogger.q(w5.ERROR, "The Sentry SDK has been configured with mixed versions. Expected %s to match core SDK version %s but was %s", wVar.f17709i, "8.40.0", wVar.f17710l);
                    z = true;
                }
            }
            if (z) {
                w5 w5Var = w5.ERROR;
                iLogger.q(w5Var, "^^^^^^^^^^^^^^^^^^^^^^^^^^^^", new Object[0]);
                iLogger.q(w5Var, "^^^^^^^^^^^^^^^^^^^^^^^^^^^^", new Object[0]);
                iLogger.q(w5Var, "^^^^^^^^^^^^^^^^^^^^^^^^^^^^", new Object[0]);
                iLogger.q(w5Var, "^^^^^^^^^^^^^^^^^^^^^^^^^^^^", new Object[0]);
            }
            f17954e = Boolean.valueOf(z);
            aVarA.close();
            return z;
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
