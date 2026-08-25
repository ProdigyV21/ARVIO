package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class a1 extends n {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f22187l;

    public a1(String str) {
        this.f22187l = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void U0(int r9) {
        /*
            r0 = 4
            r1 = 1
            if (r9 == r1) goto L9
            if (r9 == r0) goto L9
            java.lang.String r2 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            goto Lb
        L9:
            java.lang.String r2 = "@NotNull method %s.%s must not return null"
        Lb:
            r3 = 3
            r4 = 2
            if (r9 == r1) goto L13
            if (r9 == r0) goto L13
            r5 = r3
            goto L14
        L13:
            r5 = r4
        L14:
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/types/TypeUtils$SpecialType"
            r7 = 0
            if (r9 == r1) goto L30
            if (r9 == r4) goto L2b
            if (r9 == r3) goto L26
            if (r9 == r0) goto L30
            java.lang.String r8 = "newAttributes"
            r5[r7] = r8
            goto L32
        L26:
            java.lang.String r8 = "kotlinTypeRefiner"
            r5[r7] = r8
            goto L32
        L2b:
            java.lang.String r8 = "delegate"
            r5[r7] = r8
            goto L32
        L30:
            r5[r7] = r6
        L32:
            java.lang.String r7 = "refine"
            if (r9 == r1) goto L3e
            if (r9 == r0) goto L3b
            r5[r1] = r6
            goto L42
        L3b:
            r5[r1] = r7
            goto L42
        L3e:
            java.lang.String r6 = "toString"
            r5[r1] = r6
        L42:
            if (r9 == r1) goto L56
            if (r9 == r4) goto L52
            if (r9 == r3) goto L4f
            if (r9 == r0) goto L56
            java.lang.String r3 = "replaceAttributes"
            r5[r4] = r3
            goto L56
        L4f:
            r5[r4] = r7
            goto L56
        L52:
            java.lang.String r3 = "replaceDelegate"
            r5[r4] = r3
        L56:
            java.lang.String r2 = java.lang.String.format(r2, r5)
            if (r9 == r1) goto L64
            if (r9 == r0) goto L64
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r2)
            goto L69
        L64:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r2)
        L69:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: v9.a1.U0(int):void");
    }

    @Override // v9.n, v9.w
    /* JADX INFO: renamed from: K0 */
    public final w N0(w9.e eVar) {
        if (eVar != null) {
            return this;
        }
        U0(3);
        throw null;
    }

    @Override // v9.b0, v9.d1
    public final /* bridge */ /* synthetic */ d1 M0(boolean z) {
        M0(z);
        throw null;
    }

    @Override // v9.n, v9.d1
    public final d1 N0(w9.e eVar) {
        if (eVar != null) {
            return this;
        }
        U0(3);
        throw null;
    }

    @Override // v9.b0, v9.d1
    public final /* bridge */ /* synthetic */ d1 O0(k0 k0Var) {
        O0(k0Var);
        throw null;
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: P0 */
    public final b0 M0(boolean z) {
        throw new IllegalStateException(this.f22187l);
    }

    @Override // v9.b0
    /* JADX INFO: renamed from: Q0 */
    public final b0 O0(k0 k0Var) {
        if (k0Var != null) {
            throw new IllegalStateException(this.f22187l);
        }
        U0(0);
        throw null;
    }

    @Override // v9.n
    public final b0 R0() {
        throw new IllegalStateException(this.f22187l);
    }

    @Override // v9.n
    /* JADX INFO: renamed from: S0 */
    public final b0 K0(w9.e eVar) {
        if (eVar != null) {
            return this;
        }
        U0(3);
        throw null;
    }

    @Override // v9.n
    public final n T0(b0 b0Var) {
        throw new IllegalStateException(this.f22187l);
    }

    @Override // v9.b0
    public final String toString() {
        String str = this.f22187l;
        if (str != null) {
            return str;
        }
        U0(1);
        throw null;
    }
}
