package com.squareup.moshi;

import java.io.EOFException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class d0 extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f14414a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f14415b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final l f14416c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f14417d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l f14418e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l f14419f;

    public d0(z zVar) {
        this.f14414a = zVar;
        Set set = j6.e.f19235a;
        this.f14415b = zVar.a(List.class, set, null);
        this.f14416c = zVar.a(Map.class, set, null);
        this.f14417d = zVar.a(String.class, set, null);
        this.f14418e = zVar.a(Double.class, set, null);
        this.f14419f = zVar.a(Boolean.class, set, null);
    }

    @Override // com.squareup.moshi.l
    public final Object a(p pVar) throws EOFException {
        int iC = h.f0.c(pVar.b0());
        if (iC == 0) {
            return this.f14415b.a(pVar);
        }
        if (iC == 2) {
            return this.f14416c.a(pVar);
        }
        if (iC == 5) {
            return this.f14417d.a(pVar);
        }
        if (iC == 6) {
            return this.f14418e.a(pVar);
        }
        if (iC == 7) {
            return this.f14419f.a(pVar);
        }
        if (iC == 8) {
            pVar.R();
            return null;
        }
        throw new IllegalStateException("Expected a value but was " + com.arflix.tv.data.repository.g.x(pVar.b0()) + " at path " + pVar.i());
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001e A[PHI: r1
      0x001e: PHI (r1v4 java.lang.Class<?>) = (r1v1 java.lang.Class<?>), (r1v2 java.lang.Class<?>) binds: [B:7:0x001c, B:10:0x0026] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.squareup.moshi.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e(com.squareup.moshi.q r5, java.lang.Object r6) {
        /*
            r4 = this;
            java.lang.Class r0 = r6.getClass()
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            if (r0 != r1) goto L16
            r5.o()
            r6 = 0
            r5.f14462o = r6
            r6 = 5
            r0 = 125(0x7d, float:1.75E-43)
            r1 = 3
            r5.x(r1, r6, r0)
            return
        L16:
            java.lang.Class<java.util.Map> r1 = java.util.Map.class
            boolean r2 = r1.isAssignableFrom(r0)
            if (r2 == 0) goto L20
        L1e:
            r0 = r1
            goto L29
        L20:
            java.lang.Class<java.util.Collection> r1 = java.util.Collection.class
            boolean r2 = r1.isAssignableFrom(r0)
            if (r2 == 0) goto L29
            goto L1e
        L29:
            java.util.Set r1 = j6.e.f19235a
            r2 = 0
            com.squareup.moshi.z r3 = r4.f14414a
            com.squareup.moshi.l r0 = r3.a(r0, r1, r2)
            r0.e(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.d0.e(com.squareup.moshi.q, java.lang.Object):void");
    }

    public final String toString() {
        return "JsonAdapter(Object)";
    }
}
