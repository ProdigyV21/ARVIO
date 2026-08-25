package oa;

/* JADX INFO: loaded from: classes5.dex */
public final class k implements na.k {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ma.f f20832i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f20833l;

    public k(ma.f fVar, int i10) {
        this.f20832i = fVar;
        this.f20833l = i10;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0051, code lost:
    
        if (ka.m0.B(r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // na.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r6, d7.d r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof oa.j
            if (r0 == 0) goto L13
            r0 = r7
            oa.j r0 = (oa.j) r0
            int r1 = r0.f20831m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20831m = r1
            goto L18
        L13:
            oa.j r0 = new oa.j
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.f20829i
            int r1 = r0.f20831m
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L36
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r7)
            goto L54
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            k2.c.G(r7)
            goto L4b
        L36:
            k2.c.G(r7)
            kotlin.collections.d0 r7 = new kotlin.collections.d0
            int r1 = r5.f20833l
            r7.<init>(r1, r6)
            r0.f20831m = r3
            ma.f r6 = r5.f20832i
            java.lang.Object r6 = r6.send(r7, r0)
            if (r6 != r4) goto L4b
            goto L53
        L4b:
            r0.f20831m = r2
            java.lang.Object r6 = ka.m0.B(r0)
            if (r6 != r4) goto L54
        L53:
            return r4
        L54:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: oa.k.emit(java.lang.Object, d7.d):java.lang.Object");
    }
}
