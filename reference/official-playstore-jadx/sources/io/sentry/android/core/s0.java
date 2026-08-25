package io.sentry.android.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import io.sentry.ILogger;
import io.sentry.a7;
import io.sentry.b6;
import io.sentry.v4;
import io.sentry.w5;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes5.dex */
public final class s0 implements io.sentry.d0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16738i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r0 f16739l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SentryAndroidOptions f16740m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Future f16741n;

    public s0(Context context, r0 r0Var, SentryAndroidOptions sentryAndroidOptions) {
        Future futureSubmit;
        new io.sentry.util.b();
        Context applicationContext = context.getApplicationContext();
        this.f16738i = applicationContext != null ? applicationContext : context;
        this.f16739l = r0Var;
        ac.b.V(sentryAndroidOptions, "The options object is required.");
        this.f16740m = sentryAndroidOptions;
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        try {
            futureSubmit = executorServiceNewSingleThreadExecutor.submit(new androidx.media3.datasource.c(this, sentryAndroidOptions, 3));
        } catch (RejectedExecutionException e5) {
            sentryAndroidOptions.getLogger().l(w5.WARNING, "Device info caching task rejected.", e5);
            futureSubmit = null;
        }
        this.f16741n = futureSubmit;
        executorServiceNewSingleThreadExecutor.shutdown();
    }

    public final void a(v4 v4Var, io.sentry.j0 j0Var) {
        Boolean bool;
        io.sentry.protocol.a aVarD = v4Var.f17985l.d();
        if (aVarD == null) {
            aVarD = new io.sentry.protocol.a();
        }
        aVarD.f17533o = (String) q0.f16729c.a(this.f16738i);
        io.sentry.android.core.performance.h hVarB = io.sentry.android.core.performance.g.c().b(this.f16740m);
        u0 u0Var = null;
        if (hVarB.d()) {
            aVarD.f17530l = (hVarB.d() ? new b6(hVarB.f16721l * 1000000) : null) == null ? null : k2.c.n(Double.valueOf(r2.f17105i / 1000000.0d).longValue());
        }
        if (!qb.l.u(j0Var) && aVarD.f17539u == null && (bool = h0.f16496o.f16500n) != null) {
            aVarD.f17539u = Boolean.valueOf(!bool.booleanValue());
        }
        Context context = this.f16738i;
        SentryAndroidOptions sentryAndroidOptions = this.f16740m;
        ILogger logger = sentryAndroidOptions.getLogger();
        r0 r0Var = this.f16739l;
        PackageInfo packageInfoF = q0.f(context, logger, r0Var);
        if (packageInfoF != null) {
            String strH = q0.h(packageInfoF, r0Var);
            if (v4Var.f17995v == null) {
                v4Var.f17995v = strH;
            }
            Future future = this.f16741n;
            if (future != null) {
                try {
                    u0Var = (u0) future.get();
                } catch (Throwable th) {
                    sentryAndroidOptions.getLogger().l(w5.ERROR, "Failed to retrieve device info", th);
                }
            } else {
                sentryAndroidOptions.getLogger().q(w5.ERROR, "Failed to retrieve device info", new Object[0]);
            }
            aVarD.f17529i = packageInfoF.packageName;
            aVarD.f17534p = packageInfoF.versionName;
            aVarD.f17535q = q0.h(packageInfoF, r0Var);
            HashMap map = new HashMap();
            String[] strArr = packageInfoF.requestedPermissions;
            int[] iArr = packageInfoF.requestedPermissionsFlags;
            if (strArr != null && strArr.length > 0 && iArr != null && iArr.length > 0) {
                for (int i10 = 0; i10 < strArr.length; i10++) {
                    String str = strArr[i10];
                    map.put(str.substring(str.lastIndexOf(46) + 1), (iArr[i10] & 2) == 2 ? "granted" : "not_granted");
                }
            }
            aVarD.f17536r = map;
            if (u0Var != null) {
                try {
                    com.google.android.gms.internal.auth.d1 d1Var = u0Var.f16777f;
                    if (d1Var != null) {
                        aVarD.f17540v = Boolean.valueOf(d1Var.f13017b);
                        String[] strArr2 = (String[]) d1Var.f13018c;
                        if (strArr2 != null) {
                            aVarD.f17541w = Arrays.asList(strArr2);
                        }
                    }
                } catch (Throwable unused) {
                }
            }
        }
        v4Var.f17985l.m(aVarD);
    }

    public final void b(v4 v4Var, boolean z, boolean z5) {
        io.sentry.protocol.i0 i0Var = v4Var.f17992s;
        if (i0Var == null) {
            i0Var = new io.sentry.protocol.i0();
            v4Var.f17992s = i0Var;
        }
        if (i0Var.f17598l == null) {
            i0Var.f17598l = z0.a(this.f16738i);
        }
        String str = i0Var.f17600n;
        SentryAndroidOptions sentryAndroidOptions = this.f16740m;
        if (str == null && sentryAndroidOptions.isSendDefaultPii()) {
            i0Var.f17600n = "{{auto}}";
        }
        io.sentry.protocol.c cVar = v4Var.f17985l;
        io.sentry.protocol.f fVarE = cVar.e();
        Future future = this.f16741n;
        if (fVarE == null) {
            if (future != null) {
                try {
                    cVar.o(((u0) future.get()).a(z, z5));
                } catch (Throwable th) {
                    sentryAndroidOptions.getLogger().l(w5.ERROR, "Failed to retrieve device info", th);
                }
            } else {
                sentryAndroidOptions.getLogger().q(w5.ERROR, "Failed to retrieve device info", new Object[0]);
            }
            io.sentry.protocol.o oVarG = cVar.g();
            if (future != null) {
                try {
                    cVar.r(((u0) future.get()).f16778g);
                } catch (Throwable th2) {
                    sentryAndroidOptions.getLogger().l(w5.ERROR, "Failed to retrieve os system", th2);
                }
            } else {
                sentryAndroidOptions.getLogger().q(w5.ERROR, "Failed to retrieve device info", new Object[0]);
            }
            if (oVarG != null) {
                String str2 = oVarG.f17653i;
                cVar.k((str2 == null || str2.isEmpty()) ? "os_1" : "os_" + str2.trim().toLowerCase(Locale.ROOT), oVarG);
            }
        }
        if (future == null) {
            sentryAndroidOptions.getLogger().q(w5.ERROR, "Failed to retrieve device info", new Object[0]);
            return;
        }
        try {
            p0 p0Var = ((u0) future.get()).f16776e;
            if (p0Var != null) {
                HashMap map = new HashMap();
                map.put("isSideLoaded", String.valueOf(p0Var.f16686a));
                String str3 = p0Var.f16687b;
                if (str3 != null) {
                    map.put("installerStore", str3);
                }
                for (Map.Entry entry : map.entrySet()) {
                    v4Var.b((String) entry.getKey(), (String) entry.getValue());
                }
            }
        } catch (Throwable th3) {
            sentryAndroidOptions.getLogger().l(w5.ERROR, "Error getting side loaded info.", th3);
        }
    }

    public final boolean c(v4 v4Var, io.sentry.j0 j0Var) {
        if (qb.l.B(j0Var)) {
            return true;
        }
        this.f16740m.getLogger().q(w5.DEBUG, "Event was cached so not applying data relevant to the current app execution/version: %s", v4Var.f17984i);
        return false;
    }

    @Override // io.sentry.d0
    public final a7 i(a7 a7Var, io.sentry.j0 j0Var) {
        boolean zC = c(a7Var, j0Var);
        if (zC) {
            a(a7Var, j0Var);
        }
        b(a7Var, false, zC);
        return a7Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0047  */
    @Override // io.sentry.d0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final io.sentry.j5 j(io.sentry.j5 r9, io.sentry.j0 r10) {
        /*
            r8 = this;
            boolean r0 = r8.c(r9, r10)
            r1 = 1
            if (r0 == 0) goto L5f
            r8.a(r9, r10)
            java.util.ArrayList r2 = r9.e()
            if (r2 == 0) goto L5f
            boolean r10 = qb.l.u(r10)
            java.util.ArrayList r2 = r9.e()
            java.util.Iterator r2 = r2.iterator()
        L1c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L5f
            java.lang.Object r3 = r2.next()
            io.sentry.protocol.d0 r3 = (io.sentry.protocol.d0) r3
            io.sentry.android.core.internal.util.e r4 = io.sentry.android.core.internal.util.e.f16600a
            r4.getClass()
            java.lang.Long r4 = r3.f17556i
            if (r4 == 0) goto L47
            long r4 = r4.longValue()
            android.os.Looper r6 = android.os.Looper.getMainLooper()
            java.lang.Thread r6 = r6.getThread()
            long r6 = io.sentry.android.core.internal.util.e.d(r6)
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 != 0) goto L47
            r4 = r1
            goto L48
        L47:
            r4 = 0
        L48:
            java.lang.Boolean r5 = r3.f17561p
            if (r5 != 0) goto L52
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
            r3.f17561p = r5
        L52:
            if (r10 != 0) goto L1c
            java.lang.Boolean r5 = r3.f17563r
            if (r5 != 0) goto L1c
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r3.f17563r = r4
            goto L1c
        L5f:
            r8.b(r9, r1, r0)
            java.util.ArrayList r10 = r9.d()
            if (r10 == 0) goto La3
            int r0 = r10.size()
            if (r0 <= r1) goto La3
            java.lang.Object r0 = a0.c.h(r1, r10)
            io.sentry.protocol.u r0 = (io.sentry.protocol.u) r0
            java.lang.String r1 = "java.lang"
            java.lang.String r2 = r0.f17702m
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto La3
            io.sentry.protocol.b0 r0 = r0.f17704o
            if (r0 == 0) goto La3
            java.util.List r0 = r0.f17545i
            if (r0 == 0) goto La3
            java.util.Iterator r0 = r0.iterator()
        L8a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto La3
            java.lang.Object r1 = r0.next()
            io.sentry.protocol.z r1 = (io.sentry.protocol.z) r1
            java.lang.String r2 = "com.android.internal.os.RuntimeInit$MethodAndArgsCaller"
            java.lang.String r1 = r1.f17734p
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L8a
            java.util.Collections.reverse(r10)
        La3:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.s0.j(io.sentry.j5, io.sentry.j0):io.sentry.j5");
    }

    @Override // io.sentry.d0
    public final io.sentry.protocol.e0 k(io.sentry.protocol.e0 e0Var, io.sentry.j0 j0Var) {
        boolean zC = c(e0Var, j0Var);
        if (zC) {
            a(e0Var, j0Var);
        }
        b(e0Var, false, zC);
        return e0Var;
    }
}
