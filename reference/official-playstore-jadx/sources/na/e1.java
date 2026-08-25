package na;

/* JADX INFO: loaded from: classes5.dex */
public final class e1 extends f7.j implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f20554i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ k f20555l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ int f20556m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g1 f20557n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e1(g1 g1Var, d7.d dVar) {
        super(3, dVar);
        this.f20557n = g1Var;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj2).intValue();
        e1 e1Var = new e1(this.f20557n, (d7.d) obj3);
        e1Var.f20555l = (k) obj;
        e1Var.f20556m = iIntValue;
        return e1Var.invokeSuspend(x6.t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        if (r0.emit(na.a1.f20525i, r7) == r6) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007d, code lost:
    
        if (r0.emit(na.a1.f20527m, r7) != r6) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0062 A[PHI: r0
      0x0062: PHI (r0v3 na.k) = (r0v2 na.k), (r0v6 na.k) binds: [B:25:0x005f, B:13:0x0024] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0072 A[PHI: r0
      0x0072: PHI (r0v4 na.k) = (r0v3 na.k), (r0v7 na.k) binds: [B:28:0x006f, B:12:0x001e] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            int r0 = r7.f20554i
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            e7.a r6 = e7.a.f15033i
            if (r0 == 0) goto L34
            if (r0 == r5) goto L30
            if (r0 == r4) goto L2a
            if (r0 == r3) goto L24
            if (r0 == r2) goto L1e
            if (r0 != r1) goto L16
            goto L30
        L16:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1e:
            na.k r0 = r7.f20555l
            k2.c.G(r8)
            goto L72
        L24:
            na.k r0 = r7.f20555l
            k2.c.G(r8)
            goto L62
        L2a:
            na.k r0 = r7.f20555l
            k2.c.G(r8)
            goto L55
        L30:
            k2.c.G(r8)
            goto L80
        L34:
            k2.c.G(r8)
            na.k r0 = r7.f20555l
            int r8 = r7.f20556m
            if (r8 <= 0) goto L48
            r7.f20554i = r5
            na.a1 r8 = na.a1.f20525i
            java.lang.Object r8 = r0.emit(r8, r7)
            if (r8 != r6) goto L80
            goto L7f
        L48:
            r7.f20555l = r0
            r7.f20554i = r4
            r4 = 0
            java.lang.Object r8 = ka.s0.a(r4, r7)
            if (r8 != r6) goto L55
            goto L7f
        L55:
            r7.f20555l = r0
            r7.f20554i = r3
            na.a1 r8 = na.a1.f20526l
            java.lang.Object r8 = r0.emit(r8, r7)
            if (r8 != r6) goto L62
            goto L7f
        L62:
            r7.f20555l = r0
            r7.f20554i = r2
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Object r8 = ka.s0.a(r2, r7)
            if (r8 != r6) goto L72
            goto L7f
        L72:
            r8 = 0
            r7.f20555l = r8
            r7.f20554i = r1
            na.a1 r8 = na.a1.f20527m
            java.lang.Object r8 = r0.emit(r8, r7)
            if (r8 != r6) goto L80
        L7f:
            return r6
        L80:
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: na.e1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
