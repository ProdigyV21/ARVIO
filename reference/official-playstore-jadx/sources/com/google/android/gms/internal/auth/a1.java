package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile i1 f12979a = g1.f13034i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f12980b = new Object();

    /* JADX WARN: Removed duplicated region for block: B:29:0x0079 A[Catch: all -> 0x004b, TRY_LEAVE, TryCatch #1 {all -> 0x004b, blocks: (B:12:0x0035, B:14:0x003d, B:15:0x0049, B:19:0x004d, B:21:0x0059, B:25:0x0069, B:27:0x006f, B:34:0x008a, B:35:0x0095, B:29:0x0079, B:30:0x007d, B:31:0x0083), top: B:43:0x0035 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(android.content.Context r5, android.net.Uri r6) {
        /*
            java.lang.String r0 = "com.google.android.gms.phenotype"
            java.lang.String r6 = r6.getAuthority()
            boolean r0 = r0.equals(r6)
            r1 = 0
            if (r0 != 0) goto L1d
            java.lang.String r5 = java.lang.String.valueOf(r6)
            java.lang.String r6 = " is an unsupported authority. Only com.google.android.gms.phenotype authority is supported."
            java.lang.String r0 = "PhenotypeClientHelper"
            java.lang.String r5 = r5.concat(r6)
            android.util.Log.e(r0, r5)
            return r1
        L1d:
            com.google.android.gms.internal.auth.i1 r6 = com.google.android.gms.internal.auth.a1.f12979a
            boolean r6 = r6.b()
            if (r6 == 0) goto L32
            com.google.android.gms.internal.auth.i1 r5 = com.google.android.gms.internal.auth.a1.f12979a
            java.lang.Object r5 = r5.a()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            return r5
        L32:
            java.lang.Object r6 = com.google.android.gms.internal.auth.a1.f12980b
            monitor-enter(r6)
            com.google.android.gms.internal.auth.i1 r0 = com.google.android.gms.internal.auth.a1.f12979a     // Catch: java.lang.Throwable -> L4b
            boolean r0 = r0.b()     // Catch: java.lang.Throwable -> L4b
            if (r0 == 0) goto L4d
            com.google.android.gms.internal.auth.i1 r5 = com.google.android.gms.internal.auth.a1.f12979a     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r5 = r5.a()     // Catch: java.lang.Throwable -> L4b
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Throwable -> L4b
            boolean r5 = r5.booleanValue()     // Catch: java.lang.Throwable -> L4b
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L4b
            return r5
        L4b:
            r5 = move-exception
            goto La3
        L4d:
            java.lang.String r0 = "com.google.android.gms"
            java.lang.String r2 = r5.getPackageName()     // Catch: java.lang.Throwable -> L4b
            boolean r0 = r0.equals(r2)     // Catch: java.lang.Throwable -> L4b
            if (r0 != 0) goto L79
            android.content.pm.PackageManager r0 = r5.getPackageManager()     // Catch: java.lang.Throwable -> L4b
            java.lang.String r2 = "com.google.android.gms.phenotype"
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L4b
            r4 = 29
            if (r3 >= r4) goto L67
            r3 = r1
            goto L69
        L67:
            r3 = 268435456(0x10000000, float:2.524355E-29)
        L69:
            android.content.pm.ProviderInfo r0 = r0.resolveContentProvider(r2, r3)     // Catch: java.lang.Throwable -> L4b
            if (r0 == 0) goto L8a
            java.lang.String r2 = "com.google.android.gms"
            java.lang.String r0 = r0.packageName     // Catch: java.lang.Throwable -> L4b
            boolean r0 = r2.equals(r0)     // Catch: java.lang.Throwable -> L4b
            if (r0 == 0) goto L8a
        L79:
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch: java.lang.Throwable -> L4b
            java.lang.String r0 = "com.google.android.gms"
            android.content.pm.ApplicationInfo r5 = r5.getApplicationInfo(r0, r1)     // Catch: java.lang.Throwable -> L4b android.content.pm.PackageManager.NameNotFoundException -> L8a
            int r5 = r5.flags     // Catch: java.lang.Throwable -> L4b
            r5 = r5 & 129(0x81, float:1.81E-43)
            if (r5 == 0) goto L8a
            r1 = 1
        L8a:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Throwable -> L4b
            com.google.android.gms.internal.auth.j1 r0 = new com.google.android.gms.internal.auth.j1     // Catch: java.lang.Throwable -> L4b
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L4b
            com.google.android.gms.internal.auth.a1.f12979a = r0     // Catch: java.lang.Throwable -> L4b
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L4b
            com.google.android.gms.internal.auth.i1 r5 = com.google.android.gms.internal.auth.a1.f12979a
            java.lang.Object r5 = r5.a()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            return r5
        La3:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L4b
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.a1.a(android.content.Context, android.net.Uri):boolean");
    }
}
