package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class f0 implements b0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final double f22425f = Math.random();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ int f22426g = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z4.g f22427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.google.firebase.installations.c f22428b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y5.g f22429c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final kb.d f22430d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d7.j f22431e;

    public f0(z4.g gVar, com.google.firebase.installations.c cVar, y5.g gVar2, kb.d dVar, d7.j jVar) {
        this.f22427a = gVar;
        this.f22428b = cVar;
        this.f22429c = gVar2;
        this.f22430d = dVar;
        this.f22431e = jVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object b(w5.f0 r4, f7.c r5) {
        /*
            boolean r0 = r5 instanceof w5.c0
            if (r0 == 0) goto L13
            r0 = r5
            w5.c0 r0 = (w5.c0) r0
            int r1 = r0.f22393m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f22393m = r1
            goto L18
        L13:
            w5.c0 r0 = new w5.c0
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.f22391i
            int r1 = r0.f22393m
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)     // Catch: java.lang.Exception -> L44
            goto L41
        L25:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            k2.c.G(r5)
            com.google.firebase.installations.c r4 = r4.f22428b     // Catch: java.lang.Exception -> L44
            com.google.android.gms.tasks.t r4 = r4.getId()     // Catch: java.lang.Exception -> L44
            r0.f22393m = r2     // Catch: java.lang.Exception -> L44
            java.lang.Object r5 = a.a.G(r4, r0)     // Catch: java.lang.Exception -> L44
            e7.a r4 = e7.a.f15033i
            if (r5 != r4) goto L41
            return r4
        L41:
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> L44
            return r5
        L44:
            r4 = move-exception
            java.lang.String r5 = "SessionFirelogPublisher"
            java.lang.String r0 = "Error getting Firebase Installation ID. Using an empty ID"
            android.util.Log.e(r5, r0, r4)
            java.lang.String r4 = ""
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: w5.f0.b(w5.f0, f7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object c(w5.f0 r4, f7.c r5) {
        /*
            boolean r0 = r5 instanceof w5.e0
            if (r0 == 0) goto L13
            r0 = r5
            w5.e0 r0 = (w5.e0) r0
            int r1 = r0.f22419n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f22419n = r1
            goto L18
        L13:
            w5.e0 r0 = new w5.e0
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.f22417l
            int r1 = r0.f22419n
            r2 = 1
            java.lang.String r3 = "SessionFirelogPublisher"
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            w5.f0 r4 = r0.f22416i
            k2.c.G(r5)
            goto L48
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            k2.c.G(r5)
            java.lang.String r5 = "Data Collection is enabled for at least one Subscriber"
            android.util.Log.d(r3, r5)
            y5.g r5 = r4.f22429c
            r0.f22416i = r4
            r0.f22419n = r2
            java.lang.Object r5 = r5.b(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L48
            return r0
        L48:
            y5.g r5 = r4.f22429c
            kb.d r0 = r5.f22897a
            java.lang.Boolean r0 = r0.k()
            if (r0 == 0) goto L57
            boolean r2 = r0.booleanValue()
            goto L63
        L57:
            y5.c r5 = r5.f22898b
            java.lang.Boolean r5 = r5.a()
            if (r5 == 0) goto L63
            boolean r2 = r5.booleanValue()
        L63:
            if (r2 != 0) goto L6d
            java.lang.String r4 = "Sessions SDK disabled. Events will not be sent."
            android.util.Log.d(r3, r4)
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            return r4
        L6d:
            y5.g r4 = r4.f22429c
            double r4 = r4.a()
            double r0 = w5.f0.f22425f
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 > 0) goto L7c
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            return r4
        L7c:
            java.lang.String r4 = "Sessions SDK has dropped this session due to sampling."
            android.util.Log.d(r3, r4)
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: w5.f0.c(w5.f0, f7.c):java.lang.Object");
    }

    @Override // w5.b0
    public final void a(y yVar) {
        ka.m0.p(ka.l0.a(this.f22431e), null, 0, new d0(this, yVar, null), 3);
    }
}
