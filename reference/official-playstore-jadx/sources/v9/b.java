package v9;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b extends g {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(u9.v vVar) {
        super(vVar);
        if (vVar != null) {
        } else {
            m(0);
            throw null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void m(int r9) {
        /*
            r0 = 4
            r1 = 3
            r2 = 1
            if (r9 == r2) goto Lc
            if (r9 == r1) goto Lc
            if (r9 == r0) goto Lc
            java.lang.String r3 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            goto Le
        Lc:
            java.lang.String r3 = "@NotNull method %s.%s must not return null"
        Le:
            r4 = 2
            if (r9 == r2) goto L17
            if (r9 == r1) goto L17
            if (r9 == r0) goto L17
            r5 = r1
            goto L18
        L17:
            r5 = r4
        L18:
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/types/AbstractClassTypeConstructor"
            r7 = 0
            if (r9 == r2) goto L2f
            if (r9 == r4) goto L2a
            if (r9 == r1) goto L2f
            if (r9 == r0) goto L2f
            java.lang.String r8 = "storageManager"
            r5[r7] = r8
            goto L31
        L2a:
            java.lang.String r8 = "classifier"
            r5[r7] = r8
            goto L31
        L2f:
            r5[r7] = r6
        L31:
            if (r9 == r2) goto L3f
            if (r9 == r1) goto L3a
            if (r9 == r0) goto L3a
            r5[r2] = r6
            goto L43
        L3a:
            java.lang.String r6 = "getAdditionalNeighboursInSupertypeGraph"
            r5[r2] = r6
            goto L43
        L3f:
            java.lang.String r6 = "getBuiltIns"
            r5[r2] = r6
        L43:
            if (r9 == r2) goto L54
            if (r9 == r4) goto L50
            if (r9 == r1) goto L54
            if (r9 == r0) goto L54
            java.lang.String r6 = "<init>"
            r5[r4] = r6
            goto L54
        L50:
            java.lang.String r6 = "isSameClassifier"
            r5[r4] = r6
        L54:
            java.lang.String r3 = java.lang.String.format(r3, r5)
            if (r9 == r2) goto L64
            if (r9 == r1) goto L64
            if (r9 == r0) goto L64
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r3)
            goto L69
        L64:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r3)
        L69:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: v9.b.m(int):void");
    }

    @Override // v9.g
    public final w g() {
        g8.f fVarE = e();
        if (fVarE == null) {
            d8.k.a(108);
            throw null;
        }
        f9.f fVar = d8.k.f14705e;
        if (d8.k.b(fVarE, d8.p.f14732a) || d8.k.b(fVarE, d8.p.f14734b)) {
            return null;
        }
        return j().e();
    }

    @Override // v9.o0
    public final d8.k j() {
        d8.k kVarE = m9.d.e(e());
        if (kVarE != null) {
            return kVarE;
        }
        m(1);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006d A[RETURN] */
    @Override // v9.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean k(g8.h r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof g8.f
            r1 = 0
            if (r0 == 0) goto L6e
            g8.f r0 = r5.e()
            f9.f r2 = r0.getName()
            f9.f r3 = r6.getName()
            boolean r2 = kotlin.jvm.internal.p.a(r2, r3)
            r3 = 1
            if (r2 != 0) goto L1a
        L18:
            r6 = r1
            goto L6b
        L1a:
            g8.k r0 = r0.d()
            g8.k r6 = r6.d()
        L22:
            if (r0 == 0) goto L4c
            if (r6 == 0) goto L4c
            boolean r2 = r0 instanceof g8.b0
            if (r2 == 0) goto L2d
            boolean r6 = r6 instanceof g8.b0
            goto L6b
        L2d:
            boolean r2 = r6 instanceof g8.b0
            if (r2 == 0) goto L32
            goto L18
        L32:
            boolean r2 = r0 instanceof g8.h0
            if (r2 == 0) goto L4e
            boolean r2 = r6 instanceof g8.h0
            if (r2 == 0) goto L18
            g8.h0 r0 = (g8.h0) r0
            f9.c r0 = r0.c()
            g8.h0 r6 = (g8.h0) r6
            f9.c r6 = r6.c()
            boolean r6 = kotlin.jvm.internal.p.a(r0, r6)
            if (r6 == 0) goto L18
        L4c:
            r6 = r3
            goto L6b
        L4e:
            boolean r2 = r6 instanceof g8.h0
            if (r2 == 0) goto L53
            goto L18
        L53:
            f9.f r2 = r0.getName()
            f9.f r4 = r6.getName()
            boolean r2 = kotlin.jvm.internal.p.a(r2, r4)
            if (r2 != 0) goto L62
            goto L18
        L62:
            g8.k r0 = r0.d()
            g8.k r6 = r6.d()
            goto L22
        L6b:
            if (r6 == 0) goto L6e
            return r3
        L6e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: v9.b.k(g8.h):boolean");
    }

    @Override // v9.o0
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public abstract g8.f e();
}
