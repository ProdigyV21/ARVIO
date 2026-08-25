package j$.util.concurrent;

/* JADX INFO: loaded from: classes2.dex */
public final class r extends l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public r f18509e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public r f18510f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public r f18511g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public r f18512h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f18513i;

    public r(int i10, Object obj, Object obj2, l lVar, r rVar) {
        super(i10, obj, obj2, lVar);
        this.f18509e = rVar;
    }

    @Override // j$.util.concurrent.l
    public final l a(Object obj, int i10) {
        return b(i10, obj, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0047 A[PHI: r8
      0x0047: PHI (r8v5 java.lang.Class) = (r8v4 java.lang.Class), (r8v6 java.lang.Class) binds: [B:29:0x0040, B:21:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final j$.util.concurrent.r b(int r6, java.lang.Object r7, java.lang.Class r8) {
        /*
            r5 = this;
            if (r7 == 0) goto L52
            r0 = r5
        L3:
            j$.util.concurrent.r r1 = r0.f18510f
            j$.util.concurrent.r r2 = r0.f18511g
            int r3 = r0.f18488a
            if (r3 <= r6) goto Lc
            goto L22
        Lc:
            if (r3 >= r6) goto Lf
            goto L1e
        Lf:
            java.lang.Object r3 = r0.f18489b
            if (r3 == r7) goto L51
            if (r3 == 0) goto L1c
            boolean r4 = r7.equals(r3)
            if (r4 == 0) goto L1c
            goto L51
        L1c:
            if (r1 != 0) goto L20
        L1e:
            r0 = r2
            goto L4e
        L20:
            if (r2 != 0) goto L24
        L22:
            r0 = r1
            goto L4e
        L24:
            if (r8 != 0) goto L2c
            java.lang.Class r8 = j$.util.concurrent.ConcurrentHashMap.c(r7)
            if (r8 == 0) goto L47
        L2c:
            int r0 = j$.util.concurrent.ConcurrentHashMap.f18456g
            if (r3 == 0) goto L3f
            java.lang.Class r0 = r3.getClass()
            if (r0 == r8) goto L37
            goto L3f
        L37:
            r0 = r7
            java.lang.Comparable r0 = (java.lang.Comparable) r0
            int r0 = r0.compareTo(r3)
            goto L40
        L3f:
            r0 = 0
        L40:
            if (r0 == 0) goto L47
            if (r0 >= 0) goto L45
            goto L22
        L45:
            r1 = r2
            goto L22
        L47:
            j$.util.concurrent.r r0 = r2.b(r6, r7, r8)
            if (r0 == 0) goto L22
            return r0
        L4e:
            if (r0 != 0) goto L3
            goto L52
        L51:
            return r0
        L52:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.r.b(int, java.lang.Object, java.lang.Class):j$.util.concurrent.r");
    }
}
