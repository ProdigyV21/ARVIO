package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public final class f implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f12303a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f12304b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f12305c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f12306d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f12307e;

    public f(int i10, int i11, String str, boolean z, boolean z5) {
        this.f12303a = i10;
        this.f12304b = i11;
        this.f12305c = z;
        this.f12306d = z5;
        this.f12307e = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0064 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0065 A[RETURN] */
    @Override // com.caverock.androidsvg.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(com.caverock.androidsvg.b1 r8) {
        /*
            r7 = this;
            boolean r0 = r7.f12306d
            java.lang.String r1 = r7.f12307e
            if (r0 == 0) goto Lc
            if (r1 != 0) goto Lc
            java.lang.String r1 = r8.n()
        Lc:
            com.caverock.androidsvg.z0 r0 = r8.f12293b
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L3c
            java.util.List r0 = r0.getChildren()
            java.util.Iterator r0 = r0.iterator()
            r4 = r2
            r5 = r4
        L1c:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L3e
            java.lang.Object r6 = r0.next()
            com.caverock.androidsvg.d1 r6 = (com.caverock.androidsvg.d1) r6
            com.caverock.androidsvg.b1 r6 = (com.caverock.androidsvg.b1) r6
            if (r6 != r8) goto L2d
            r4 = r5
        L2d:
            if (r1 == 0) goto L39
            java.lang.String r6 = r6.n()
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L1c
        L39:
            int r5 = r5 + 1
            goto L1c
        L3c:
            r4 = r2
            r5 = r3
        L3e:
            boolean r8 = r7.f12305c
            if (r8 == 0) goto L44
            int r4 = r4 + r3
            goto L46
        L44:
            int r4 = r5 - r4
        L46:
            int r8 = r7.f12303a
            int r0 = r7.f12304b
            if (r8 != 0) goto L4f
            if (r4 != r0) goto L65
            goto L64
        L4f:
            int r4 = r4 - r0
            int r0 = r4 % r8
            if (r0 != 0) goto L65
            int r0 = java.lang.Integer.signum(r4)
            if (r0 == 0) goto L64
            int r0 = java.lang.Integer.signum(r4)
            int r8 = java.lang.Integer.signum(r8)
            if (r0 != r8) goto L65
        L64:
            return r3
        L65:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.f.a(com.caverock.androidsvg.b1):boolean");
    }

    public final String toString() {
        String str = this.f12305c ? "" : "last-";
        boolean z = this.f12306d;
        int i10 = this.f12304b;
        int i11 = this.f12303a;
        return z ? String.format("nth-%schild(%dn%+d of type <%s>)", str, Integer.valueOf(i11), Integer.valueOf(i10), this.f12307e) : String.format("nth-%schild(%dn%+d)", str, Integer.valueOf(i11), Integer.valueOf(i10));
    }
}
