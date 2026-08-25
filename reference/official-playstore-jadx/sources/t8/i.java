package t8;

import androidx.appcompat.app.i1;
import g8.c1;
import g8.k1;
import g8.t0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import p8.k0;
import v9.o0;
import x6.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends j8.l implements r8.c {
    public static final Set G = kotlin.collections.r.p0(new String[]{"equals", "hashCode", "getClass", "wait", "notify", "notifyAll", "toString"});
    public final n A;
    public final t0 B;
    public final p9.i C;
    public final c0 D;
    public final s8.e E;
    public final u9.s F;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final androidx.core.provider.e f21929q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final w8.g f21930r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final g8.f f21931s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final androidx.core.provider.e f21932t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final i0 f21933u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f21934v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f21935w;
    public final k1 x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final boolean f21936y;
    public final kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.i z;

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public i(androidx.core.provider.e r10, g8.k r11, w8.g r12, g8.f r13) {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t8.i.<init>(androidx.core.provider.e, g8.k, w8.g, g8.f):void");
    }

    @Override // g8.f
    public final g8.e C() {
        return null;
    }

    @Override // g8.f
    public final boolean E0() {
        return false;
    }

    public final n G0() {
        return (n) super.U();
    }

    @Override // j8.b, g8.f
    public final p9.n R() {
        return this.C;
    }

    @Override // g8.f
    public final c1 S() {
        return null;
    }

    @Override // j8.b, g8.f
    public final p9.n U() {
        return (n) super.U();
    }

    @Override // g8.a0
    public final boolean V() {
        return false;
    }

    @Override // g8.f
    public final boolean Y() {
        return false;
    }

    @Override // g8.f
    public final boolean c0() {
        return false;
    }

    @Override // g8.h
    public final o0 g() {
        return this.z;
    }

    @Override // j8.b0
    public final p9.n g0(w9.e eVar) {
        t0 t0Var = this.B;
        j8.b bVar = t0Var.f15543a;
        int i10 = m9.d.f20302a;
        i9.f.d(bVar);
        u9.s sVar = t0Var.f15546d;
        kotlin.reflect.m mVar = t0.f15542f[0];
        return (n) ((p9.n) sVar.invoke());
    }

    @Override // h8.a
    public final h8.h getAnnotations() {
        return this.E;
    }

    @Override // g8.f, g8.o, g8.a0
    public final g8.p getVisibility() {
        g8.p pVar = g8.q.f15524a;
        k1 k1Var = this.x;
        return (kotlin.jvm.internal.p.a(k1Var, pVar) && this.f21930r.n() == null) ? p8.r.f21176a : k0.a(k1Var);
    }

    @Override // g8.f
    public final Collection h() {
        return (List) this.A.f21953q.invoke();
    }

    @Override // g8.i
    public final boolean i() {
        return this.f21936y;
    }

    @Override // g8.a0
    public final boolean i0() {
        return false;
    }

    @Override // g8.f
    public final boolean isInline() {
        return false;
    }

    @Override // g8.f
    public final p9.n j0() {
        return this.D;
    }

    @Override // g8.f, g8.i
    public final List n() {
        return (List) this.F.invoke();
    }

    @Override // g8.f, g8.a0
    public final int o() {
        return this.f21935w;
    }

    @Override // g8.f
    public final int p0() {
        return this.f21934v;
    }

    @Override // g8.f
    public final boolean q() {
        return false;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Lazy Java class ");
        int i10 = m9.d.f20302a;
        sb2.append(i9.f.g(this));
        return sb2.toString();
    }

    @Override // g8.f
    public final Collection w() {
        if (this.f21935w != 2) {
            return kotlin.collections.z.f19728i;
        }
        u8.a aVarC = qb.l.C(2, false, null, 7);
        Collection collectionW = this.f21930r.w();
        ArrayList arrayList = new ArrayList();
        Iterator it = collectionW.iterator();
        while (it.hasNext()) {
            g8.h hVarE = ((i1) this.f21932t.f2144p).d0((w8.j) it.next(), aVarC).I0().e();
            g8.f fVar = hVarE instanceof g8.f ? (g8.f) hVarE : null;
            if (fVar != null) {
                arrayList.add(fVar);
            }
        }
        return kotlin.collections.x.W0(arrayList, new h());
    }
}
