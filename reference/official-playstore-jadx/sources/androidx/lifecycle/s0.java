package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public final class s0 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public kotlin.jvm.internal.k0 f3288i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public kotlin.jvm.internal.k0 f3289l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ka.k0 f3290m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3291n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r f3292o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ q f3293p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ ka.k0 f3294q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ androidx.work.impl.constraints.l f3295r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(r rVar, q qVar, ka.k0 k0Var, androidx.work.impl.constraints.l lVar, d7.d dVar) {
        super(2, dVar);
        this.f3292o = rVar;
        this.f3293p = qVar;
        this.f3294q = k0Var;
        this.f3295r = lVar;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        return new s0(this.f3292o, this.f3293p, this.f3294q, this.f3295r, dVar);
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((s0) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[SYNTHETIC] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
        /*
            r13 = this;
            int r0 = r13.f3291n
            r1 = 0
            x6.t0 r2 = x6.t0.f22605a
            androidx.lifecycle.r r3 = r13.f3292o
            r4 = 1
            if (r0 == 0) goto L21
            if (r0 != r4) goto L19
            kotlin.jvm.internal.k0 r4 = r13.f3289l
            kotlin.jvm.internal.k0 r5 = r13.f3288i
            k2.c.G(r14)     // Catch: java.lang.Throwable -> L15
            goto L8d
        L15:
            r0 = move-exception
            r14 = r0
            goto La4
        L19:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L21:
            k2.c.G(r14)
            androidx.lifecycle.q r14 = r3.getCurrentState()
            androidx.lifecycle.q r0 = androidx.lifecycle.q.f3266i
            if (r14 != r0) goto L2e
            goto L9f
        L2e:
            kotlin.jvm.internal.k0 r7 = new kotlin.jvm.internal.k0
            r7.<init>()
            kotlin.jvm.internal.k0 r14 = new kotlin.jvm.internal.k0
            r14.<init>()
            androidx.lifecycle.q r0 = r13.f3293p     // Catch: java.lang.Throwable -> La0
            ka.k0 r8 = r13.f3294q     // Catch: java.lang.Throwable -> La0
            androidx.work.impl.constraints.l r12 = r13.f3295r     // Catch: java.lang.Throwable -> La0
            r13.f3288i = r7     // Catch: java.lang.Throwable -> La0
            r13.f3289l = r14     // Catch: java.lang.Throwable -> La0
            r13.f3290m = r8     // Catch: java.lang.Throwable -> La0
            r13.f3291n = r4     // Catch: java.lang.Throwable -> La0
            ka.l r10 = new ka.l     // Catch: java.lang.Throwable -> La0
            d7.d r5 = t7.a.A(r13)     // Catch: java.lang.Throwable -> La0
            r10.<init>(r4, r5)     // Catch: java.lang.Throwable -> La0
            r10.s()     // Catch: java.lang.Throwable -> La0
            androidx.lifecycle.n r4 = androidx.lifecycle.p.Companion     // Catch: java.lang.Throwable -> La0
            r4.getClass()     // Catch: java.lang.Throwable -> La0
            int r4 = r0.ordinal()     // Catch: java.lang.Throwable -> La0
            r5 = 2
            if (r4 == r5) goto L6d
            r5 = 3
            if (r4 == r5) goto L6a
            r5 = 4
            if (r4 == r5) goto L66
            r6 = r1
            goto L70
        L66:
            androidx.lifecycle.p r4 = androidx.lifecycle.p.ON_RESUME     // Catch: java.lang.Throwable -> La0
        L68:
            r6 = r4
            goto L70
        L6a:
            androidx.lifecycle.p r4 = androidx.lifecycle.p.ON_START     // Catch: java.lang.Throwable -> La0
            goto L68
        L6d:
            androidx.lifecycle.p r4 = androidx.lifecycle.p.ON_CREATE     // Catch: java.lang.Throwable -> La0
            goto L68
        L70:
            androidx.lifecycle.p r9 = androidx.lifecycle.n.a(r0)     // Catch: java.lang.Throwable -> La0
            ua.d r11 = ua.e.a()     // Catch: java.lang.Throwable -> La0
            androidx.lifecycle.r0 r5 = new androidx.lifecycle.r0     // Catch: java.lang.Throwable -> La0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> La0
            r14.f19746i = r5     // Catch: java.lang.Throwable -> La0
            r3.addObserver(r5)     // Catch: java.lang.Throwable -> La0
            java.lang.Object r0 = r10.r()     // Catch: java.lang.Throwable -> La0
            e7.a r4 = e7.a.f15033i
            if (r0 != r4) goto L8b
            return r4
        L8b:
            r4 = r14
            r5 = r7
        L8d:
            java.lang.Object r14 = r5.f19746i
            ka.v1 r14 = (ka.v1) r14
            if (r14 == 0) goto L96
            r14.cancel(r1)
        L96:
            java.lang.Object r14 = r4.f19746i
            androidx.lifecycle.w r14 = (androidx.lifecycle.w) r14
            if (r14 == 0) goto L9f
            r3.removeObserver(r14)
        L9f:
            return r2
        La0:
            r0 = move-exception
            r4 = r14
            r14 = r0
            r5 = r7
        La4:
            java.lang.Object r0 = r5.f19746i
            ka.v1 r0 = (ka.v1) r0
            if (r0 == 0) goto Lad
            r0.cancel(r1)
        Lad:
            java.lang.Object r0 = r4.f19746i
            androidx.lifecycle.w r0 = (androidx.lifecycle.w) r0
            if (r0 == 0) goto Lb6
            r3.removeObserver(r0)
        Lb6:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.s0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
