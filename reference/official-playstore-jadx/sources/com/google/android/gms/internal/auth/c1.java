package com.google.android.gms.internal.auth;

import android.content.Context;
import android.net.Uri;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class c1 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Object f13004g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static volatile s0 f13005h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicInteger f13006i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d1 f13007a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13008b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f13009c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile int f13010d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Object f13011e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f13012f;

    static {
        new AtomicReference();
        f13006i = new AtomicInteger();
    }

    public /* synthetic */ c1(d1 d1Var, String str, Object obj, int i10) {
        this.f13012f = i10;
        if (((Uri) d1Var.f13018c) == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.f13007a = d1Var;
        this.f13008b = str;
        this.f13009c = obj;
    }

    public static void c(Context context) {
        if (f13005h != null || context == null) {
            return;
        }
        Object obj = f13004g;
        synchronized (obj) {
            try {
                if (f13005h == null) {
                    synchronized (obj) {
                        s0 s0Var = f13005h;
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext != null) {
                            context = applicationContext;
                        }
                        if (s0Var == null || s0Var.f13109a != context) {
                            t0.c();
                            f1.b();
                            y0.d();
                            f13005h = new s0(context, s1.c(new p2(context, 3)));
                            f13006i.incrementAndGet();
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            } finally {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0037, code lost:
    
        android.util.Log.e("PhenotypeFlag", "Invalid double value for " + r2.f13008b + ": " + r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x009c, code lost:
    
        android.util.Log.e("PhenotypeFlag", "Invalid long value for " + r2.f13008b + ": " + r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
    
        android.util.Log.e("PhenotypeFlag", "Invalid byte[] value for " + r2.f13008b + ": " + r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r2.f13012f
            switch(r0) {
                case 0: goto L93;
                case 1: goto L56;
                case 2: goto L2e;
                default: goto L5;
            }
        L5:
            r0 = 3
            byte[] r0 = android.util.Base64.decode(r3, r0)     // Catch: java.lang.Throwable -> Lf
            com.google.android.gms.internal.auth.x3 r3 = com.google.android.gms.internal.auth.x3.j(r0)     // Catch: java.lang.Throwable -> Lf
            goto L2d
        Lf:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid byte[] value for "
            r0.<init>(r1)
            java.lang.String r1 = r2.f13008b
            r0.append(r1)
            java.lang.String r1 = ": "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "PhenotypeFlag"
            android.util.Log.e(r0, r3)
            r3 = 0
        L2d:
            return r3
        L2e:
            double r0 = java.lang.Double.parseDouble(r3)     // Catch: java.lang.NumberFormatException -> L37
            java.lang.Double r3 = java.lang.Double.valueOf(r0)     // Catch: java.lang.NumberFormatException -> L37
            goto L55
        L37:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid double value for "
            r0.<init>(r1)
            java.lang.String r1 = r2.f13008b
            r0.append(r1)
            java.lang.String r1 = ": "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "PhenotypeFlag"
            android.util.Log.e(r0, r3)
            r3 = 0
        L55:
            return r3
        L56:
            java.util.regex.Pattern r0 = com.google.android.gms.internal.auth.q0.f13094b
            java.util.regex.Matcher r0 = r0.matcher(r3)
            boolean r0 = r0.matches()
            if (r0 == 0) goto L65
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto L92
        L65:
            java.util.regex.Pattern r0 = com.google.android.gms.internal.auth.q0.f13095c
            java.util.regex.Matcher r0 = r0.matcher(r3)
            boolean r0 = r0.matches()
            if (r0 == 0) goto L74
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
            goto L92
        L74:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid boolean value for "
            r0.<init>(r1)
            java.lang.String r1 = r2.f13008b
            r0.append(r1)
            java.lang.String r1 = ": "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "PhenotypeFlag"
            android.util.Log.e(r0, r3)
            r3 = 0
        L92:
            return r3
        L93:
            long r0 = java.lang.Long.parseLong(r3)     // Catch: java.lang.NumberFormatException -> L9c
            java.lang.Long r3 = java.lang.Long.valueOf(r0)     // Catch: java.lang.NumberFormatException -> L9c
            goto Lba
        L9c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid long value for "
            r0.<init>(r1)
            java.lang.String r1 = r2.f13008b
            r0.append(r1)
            java.lang.String r1 = ": "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "PhenotypeFlag"
            android.util.Log.e(r0, r3)
            r3 = 0
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.c1.a(java.lang.String):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0058 A[PHI: r2
      0x0058: PHI (r2v1 com.google.android.gms.internal.auth.i1) = 
      (r2v0 com.google.android.gms.internal.auth.i1)
      (r2v5 com.google.android.gms.internal.auth.i1)
      (r2v5 com.google.android.gms.internal.auth.i1)
     binds: [B:8:0x0014, B:10:0x0022, B:16:0x0047] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005d A[Catch: all -> 0x0055, TryCatch #0 {all -> 0x0055, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0016, B:11:0x0024, B:13:0x0037, B:17:0x0048, B:23:0x005d, B:25:0x0065, B:27:0x006d, B:30:0x0081, B:32:0x0089, B:35:0x0093, B:37:0x0099, B:41:0x00a9, B:43:0x00af, B:40:0x00a7, B:45:0x00b5, B:47:0x00b9, B:50:0x00c1, B:51:0x00c4, B:52:0x00c8, B:53:0x00cd, B:54:0x00d0, B:55:0x00d1, B:56:0x00d6, B:57:0x00d7), top: B:63:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d1 A[Catch: all -> 0x0055, TryCatch #0 {all -> 0x0055, blocks: (B:5:0x000b, B:7:0x000f, B:9:0x0016, B:11:0x0024, B:13:0x0037, B:17:0x0048, B:23:0x005d, B:25:0x0065, B:27:0x006d, B:30:0x0081, B:32:0x0089, B:35:0x0093, B:37:0x0099, B:41:0x00a9, B:43:0x00af, B:40:0x00a7, B:45:0x00b5, B:47:0x00b9, B:50:0x00c1, B:51:0x00c4, B:52:0x00c8, B:53:0x00cd, B:54:0x00d0, B:55:0x00d1, B:56:0x00d6, B:57:0x00d7), top: B:63:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object b() {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.c1.b():java.lang.Object");
    }
}
