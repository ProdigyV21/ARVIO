package na;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends oa.d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f7.j f20540n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final f7.j f20541o;

    /* JADX WARN: Multi-variable type inference failed */
    public c(r7.p pVar, d7.j jVar, int i10, int i11) {
        super(jVar, i10, i11);
        f7.j jVar2 = (f7.j) pVar;
        this.f20540n = jVar2;
        this.f20541o = jVar2;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r6v3, types: [f7.j, r7.p] */
    @Override // oa.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object c(ma.u r5, d7.d r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof na.b
            if (r0 == 0) goto L13
            r0 = r6
            na.b r0 = (na.b) r0
            int r1 = r0.f20532n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20532n = r1
            goto L1a
        L13:
            na.b r0 = new na.b
            f7.c r6 = (f7.c) r6
            r0.<init>(r4, r6)
        L1a:
            java.lang.Object r6 = r0.f20530l
            int r1 = r0.f20532n
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 != r3) goto L2b
            ma.u r5 = r0.f20529i
            k2.c.G(r6)
            goto L49
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            k2.c.G(r6)
            r0.f20529i = r5
            r0.f20532n = r3
            f7.j r6 = r4.f20540n
            java.lang.Object r6 = r6.invoke(r5, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L45
            goto L46
        L45:
            r6 = r2
        L46:
            if (r6 != r0) goto L49
            return r0
        L49:
            boolean r5 = r5.isClosedForSend()
            if (r5 == 0) goto L50
            return r2
        L50:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.\nOtherwise, a callback/listener may leak in case of external cancellation.\nSee callbackFlow API documentation for the details."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: na.c.c(ma.u, d7.d):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [f7.j, r7.p] */
    @Override // oa.d
    public final oa.d d(d7.j jVar, int i10, int i11) {
        return new c(this.f20541o, jVar, i10, i11);
    }

    @Override // oa.d
    public final String toString() {
        return "block[" + this.f20540n + "] -> " + super.toString();
    }
}
