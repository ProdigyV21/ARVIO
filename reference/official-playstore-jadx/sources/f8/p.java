package f8;

import g8.l0;
import j8.d0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.b0;
import u9.r;

/* JADX INFO: loaded from: classes5.dex */
public final class p implements l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u9.p f15344a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d0 f15345b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public s9.j f15346c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r f15347d;

    public p(u9.p pVar, l8.c cVar, d0 d0Var) {
        this.f15344a = pVar;
        this.f15345b = d0Var;
        this.f15347d = pVar.e(new s8.d(this, 2));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    @Override // g8.l0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(f9.c r5) throws java.io.IOException {
        /*
            r4 = this;
            u9.r r0 = r4.f15347d
            boolean r1 = r0.m(r5)
            r2 = 0
            if (r1 == 0) goto L10
            java.lang.Object r5 = r0.invoke(r5)
            g8.h0 r5 = (g8.h0) r5
            goto L4f
        L10:
            f9.f r0 = d8.q.k
            boolean r0 = r5.h(r0)
            r1 = 0
            if (r0 != 0) goto L1b
        L19:
            r0 = r1
            goto L43
        L1b:
            t9.a r0 = t9.a.f21999m
            r0.getClass()
            java.lang.String r0 = t9.a.a(r5)
            java.lang.Class<t9.d> r3 = t9.d.class
            java.lang.ClassLoader r3 = r3.getClassLoader()
            if (r3 != 0) goto L31
            java.io.InputStream r0 = java.lang.ClassLoader.getSystemResourceAsStream(r0)
            goto L43
        L31:
            java.net.URL r0 = r3.getResource(r0)
            if (r0 != 0) goto L38
            goto L19
        L38:
            java.net.URLConnection r0 = r0.openConnection()
            r0.setUseCaches(r2)
            java.io.InputStream r0 = r0.getInputStream()
        L43:
            if (r0 == 0) goto L4e
            u9.p r1 = r4.f15344a
            j8.d0 r3 = r4.f15345b
            t9.c r5 = f4.f.g(r5, r1, r3, r0)
            goto L4f
        L4e:
            r5 = r1
        L4f:
            if (r5 != 0) goto L53
            r5 = 1
            return r5
        L53:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: f8.p.a(f9.c):boolean");
    }

    @Override // g8.l0
    public final void b(f9.c cVar, ArrayList arrayList) {
        ea.o.d(arrayList, this.f15347d.invoke(cVar));
    }

    @Override // g8.i0
    public final List c(f9.c cVar) {
        return t7.a.F(this.f15347d.invoke(cVar));
    }

    @Override // g8.i0
    public final Collection p(f9.c cVar, r7.l lVar) {
        return b0.f19686i;
    }
}
