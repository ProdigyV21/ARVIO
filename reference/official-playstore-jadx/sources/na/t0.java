package na;

/* JADX INFO: loaded from: classes5.dex */
public final class t0 implements j, d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f7.j f20652i;

    /* JADX WARN: Multi-variable type inference failed */
    public t0(r7.p pVar) {
        this.f20652i = (f7.j) pVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r6v3, types: [f7.j, r7.p] */
    @Override // na.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collect(na.k r6, d7.d r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof na.a
            if (r0 == 0) goto L13
            r0 = r7
            na.a r0 = (na.a) r0
            int r1 = r0.f20519n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20519n = r1
            goto L18
        L13:
            na.a r0 = new na.a
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.f20517l
            int r1 = r0.f20519n
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 != r3) goto L2b
            oa.t r6 = r0.f20516i
            k2.c.G(r7)     // Catch: java.lang.Throwable -> L29
            goto L53
        L29:
            r7 = move-exception
            goto L5d
        L2b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L33:
            k2.c.G(r7)
            oa.t r7 = new oa.t
            d7.j r1 = r0.getContext()
            r7.<init>(r6, r1)
            r0.f20516i = r7     // Catch: java.lang.Throwable -> L5b
            r0.f20519n = r3     // Catch: java.lang.Throwable -> L5b
            f7.j r6 = r5.f20652i     // Catch: java.lang.Throwable -> L5b
            java.lang.Object r6 = r6.invoke(r7, r0)     // Catch: java.lang.Throwable -> L5b
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L4e
            goto L4f
        L4e:
            r6 = r2
        L4f:
            if (r6 != r0) goto L52
            return r0
        L52:
            r6 = r7
        L53:
            r6.releaseIntercepted()
            return r2
        L57:
            r4 = r7
            r7 = r6
            r6 = r4
            goto L5d
        L5b:
            r6 = move-exception
            goto L57
        L5d:
            r6.releaseIntercepted()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: na.t0.collect(na.k, d7.d):java.lang.Object");
    }
}
