package j$.util.concurrent;

/* JADX INFO: loaded from: classes2.dex */
public final class g extends l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l[] f18482e;

    public g(l[] lVarArr) {
        super(-1, null, null);
        this.f18482e = lVarArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001f, code lost:
    
        return r0;
     */
    @Override // j$.util.concurrent.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final j$.util.concurrent.l a(java.lang.Object r4, int r5) {
        /*
            r3 = this;
            j$.util.concurrent.l[] r0 = r3.f18482e
        L2:
            int r1 = r0.length
            if (r1 == 0) goto L34
            int r1 = r1 + (-1)
            r1 = r1 & r5
            j$.util.concurrent.l r0 = j$.util.concurrent.ConcurrentHashMap.k(r0, r1)
            if (r0 != 0) goto Lf
            goto L34
        Lf:
            int r1 = r0.f18488a
            if (r1 != r5) goto L20
            java.lang.Object r2 = r0.f18489b
            if (r2 == r4) goto L1f
            if (r2 == 0) goto L20
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L20
        L1f:
            return r0
        L20:
            if (r1 >= 0) goto L30
            boolean r1 = r0 instanceof j$.util.concurrent.g
            if (r1 == 0) goto L2b
            j$.util.concurrent.g r0 = (j$.util.concurrent.g) r0
            j$.util.concurrent.l[] r0 = r0.f18482e
            goto L2
        L2b:
            j$.util.concurrent.l r4 = r0.a(r4, r5)
            return r4
        L30:
            j$.util.concurrent.l r0 = r0.f18491d
            if (r0 != 0) goto Lf
        L34:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.g.a(java.lang.Object, int):j$.util.concurrent.l");
    }
}
