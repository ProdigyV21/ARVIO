package oa;

import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e extends d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final na.j f20812n;

    public e(na.j jVar, d7.j jVar2, int i10, int i11) {
        super(jVar2, i10, i11);
        this.f20812n = jVar;
    }

    @Override // oa.d
    public final Object c(ma.u uVar, d7.d dVar) {
        Object objH = h(new x(uVar), dVar);
        return objH == e7.a.f15033i ? objH : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0077  */
    @Override // oa.d, na.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collect(na.k r8, d7.d r9) {
        /*
            r7 = this;
            int r0 = r7.f20810l
            r1 = -3
            e7.a r2 = e7.a.f15033i
            x6.t0 r3 = x6.t0.f22605a
            if (r0 != r1) goto L77
            d7.j r0 = r9.getContext()
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            ka.b0 r4 = ka.b0.f19553i
            d7.j r5 = r7.f20809i
            java.lang.Object r1 = r5.fold(r1, r4)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            r4 = 0
            if (r1 != 0) goto L25
            d7.j r1 = r0.plus(r5)
            goto L29
        L25:
            d7.j r1 = ka.c0.a(r0, r5, r4)
        L29:
            boolean r5 = kotlin.jvm.internal.p.a(r1, r0)
            if (r5 == 0) goto L36
            java.lang.Object r8 = r7.h(r8, r9)
            if (r8 != r2) goto L7e
            return r8
        L36:
            d7.f r5 = d7.f.f14687i
            d7.j$a r6 = r1.get(r5)
            d7.j$a r0 = r0.get(r5)
            boolean r0 = kotlin.jvm.internal.p.a(r6, r0)
            if (r0 == 0) goto L77
            d7.j r0 = r9.getContext()
            boolean r5 = r8 instanceof oa.x
            if (r5 == 0) goto L50
            r5 = 1
            goto L52
        L50:
            boolean r5 = r8 instanceof oa.r
        L52:
            if (r5 == 0) goto L55
            goto L5b
        L55:
            na.g r5 = new na.g
            r5.<init>(r8, r0)
            r8 = r5
        L5b:
            androidx.work.impl.constraints.controllers.c r0 = new androidx.work.impl.constraints.controllers.c
            r5 = 0
            r6 = 3
            r0.<init>(r7, r5, r6)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            pa.z r5 = pa.z.f21301i
            java.lang.Object r4 = r1.fold(r4, r5)
            java.lang.Object r8 = oa.b.b(r1, r8, r4, r0, r9)
            if (r8 != r2) goto L73
            goto L74
        L73:
            r8 = r3
        L74:
            if (r8 != r2) goto L7e
            return r8
        L77:
            java.lang.Object r8 = super.collect(r8, r9)
            if (r8 != r2) goto L7e
            return r8
        L7e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: oa.e.collect(na.k, d7.d):java.lang.Object");
    }

    public abstract Object h(na.k kVar, d7.d dVar);

    @Override // oa.d
    public final String toString() {
        return this.f20812n + " -> " + super.toString();
    }
}
