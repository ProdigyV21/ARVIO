package ma;

/* JADX INFO: loaded from: classes5.dex */
public abstract class s {
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(ma.u r4, r7.a r5, f7.c r6) {
        /*
            boolean r0 = r6 instanceof ma.r
            if (r0 == 0) goto L13
            r0 = r6
            ma.r r0 = (ma.r) r0
            int r1 = r0.f20354n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f20354n = r1
            goto L18
        L13:
            ma.r r0 = new ma.r
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f20353m
            int r1 = r0.f20354n
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            r7.a r5 = r0.f20352l
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L27
            goto L65
        L27:
            r4 = move-exception
            goto L6b
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            k2.c.G(r6)
            d7.j r6 = r0.getContext()
            ka.u1 r1 = ka.u1.f19642i
            d7.j$a r6 = r6.get(r1)
            if (r6 != r4) goto L6f
            r0.f20351i = r4     // Catch: java.lang.Throwable -> L27
            r0.f20352l = r5     // Catch: java.lang.Throwable -> L27
            r0.f20354n = r2     // Catch: java.lang.Throwable -> L27
            ka.l r6 = new ka.l     // Catch: java.lang.Throwable -> L27
            d7.d r0 = t7.a.A(r0)     // Catch: java.lang.Throwable -> L27
            r6.<init>(r2, r0)     // Catch: java.lang.Throwable -> L27
            r6.s()     // Catch: java.lang.Throwable -> L27
            ab.h r0 = new ab.h     // Catch: java.lang.Throwable -> L27
            r1 = 27
            r0.<init>(r6, r1)     // Catch: java.lang.Throwable -> L27
            r4.invokeOnClose(r0)     // Catch: java.lang.Throwable -> L27
            java.lang.Object r4 = r6.r()     // Catch: java.lang.Throwable -> L27
            e7.a r6 = e7.a.f15033i
            if (r4 != r6) goto L65
            return r6
        L65:
            r5.invoke()
            x6.t0 r4 = x6.t0.f22605a
            return r4
        L6b:
            r5.invoke()
            throw r4
        L6f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "awaitClose() can only be invoked from the producer context"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: ma.s.a(ma.u, r7.a, f7.c):java.lang.Object");
    }
}
