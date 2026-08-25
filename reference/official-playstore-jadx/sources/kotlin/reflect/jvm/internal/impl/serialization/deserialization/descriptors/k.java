package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.k1;
import androidx.emoji2.text.e0;
import g8.c1;
import g8.p0;
import g8.t0;
import g8.v0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import v9.b0;
import v9.o0;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends j8.b implements g8.s {
    public final g8.k A;
    public final u9.t B;
    public final u9.s C;
    public final u9.t D;
    public final u9.s E;
    public final u9.t F;
    public final s9.y G;
    public final h8.h H;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final a9.n f19866o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final c9.a f19867p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final v0 f19868q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final f9.b f19869r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f19870s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final g8.p f19871t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f19872u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final s9.m f19873v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final p9.o f19874w;
    public final i x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final t0 f19875y;
    public final e0 z;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public k(s9.m r17, a9.n r18, c9.f r19, c9.a r20, g8.v0 r21) {
        /*
            Method dump skipped, instruction units count: 468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k.<init>(s9.m, a9.n, c9.f, c9.a, g8.v0):void");
    }

    @Override // g8.f
    public final g8.e C() {
        return (g8.e) this.B.invoke();
    }

    @Override // g8.f
    public final boolean E0() {
        return c9.e.f7439h.c(this.f19866o.f583n).booleanValue();
    }

    public final g G0() {
        this.f19873v.f21731a.f21721q.getClass();
        t0 t0Var = this.f19875y;
        j8.b bVar = t0Var.f15543a;
        int i10 = m9.d.f20302a;
        i9.f.d(bVar);
        u9.s sVar = t0Var.f15546d;
        kotlin.reflect.m mVar = t0.f15542f[0];
        return (g) ((p9.n) sVar.invoke());
    }

    public final b0 H0(f9.f fVar) {
        Iterator it = G0().g(fVar, o8.e.f20799q).iterator();
        boolean z = false;
        Object obj = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
            } else {
                Object next = it.next();
                if (((p0) next).M() == null) {
                    if (z) {
                        break;
                    }
                    z = true;
                    obj = next;
                }
            }
        }
        obj = null;
        p0 p0Var = (p0) obj;
        return (b0) (p0Var != null ? p0Var.getType() : null);
    }

    @Override // g8.f
    public final c1 S() {
        return (c1) this.F.invoke();
    }

    @Override // g8.a0
    public final boolean V() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Override // j8.b, g8.f
    public final List W() {
        s9.m mVar = this.f19873v;
        androidx.work.impl.constraints.j jVar = mVar.f21734d;
        a9.n nVar = this.f19866o;
        List list = nVar.f592w;
        boolean zIsEmpty = list.isEmpty();
        ?? arrayList = list;
        if (zIsEmpty) {
            arrayList = 0;
        }
        if (arrayList == 0) {
            List list2 = nVar.x;
            arrayList = new ArrayList(kotlin.collections.s.U(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(jVar.b(((Integer) it.next()).intValue()));
            }
        }
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(new j8.x(F0(), new q9.a(this, mVar.f21738h.g((k1) it2.next()), null), h8.g.f15863a));
        }
        return arrayList2;
    }

    @Override // g8.f
    public final boolean Y() {
        return c9.e.f7437f.c(this.f19866o.f583n) == a9.m.COMPANION_OBJECT;
    }

    @Override // g8.f
    public final boolean c0() {
        return c9.e.f7442l.c(this.f19866o.f583n).booleanValue();
    }

    @Override // g8.k
    public final g8.k d() {
        return this.A;
    }

    @Override // g8.n
    public final v0 f() {
        return this.f19868q;
    }

    @Override // g8.h
    public final o0 g() {
        return this.x;
    }

    @Override // j8.b0
    public final p9.n g0(w9.e eVar) {
        t0 t0Var = this.f19875y;
        j8.b bVar = t0Var.f15543a;
        int i10 = m9.d.f20302a;
        i9.f.d(bVar);
        u9.s sVar = t0Var.f15546d;
        kotlin.reflect.m mVar = t0.f15542f[0];
        return (p9.n) sVar.invoke();
    }

    @Override // h8.a
    public final h8.h getAnnotations() {
        return this.H;
    }

    @Override // g8.f, g8.o, g8.a0
    public final g8.p getVisibility() {
        return this.f19871t;
    }

    @Override // g8.f
    public final Collection h() {
        return (Collection) this.C.invoke();
    }

    @Override // g8.i
    public final boolean i() {
        return c9.e.f7438g.c(this.f19866o.f583n).booleanValue();
    }

    @Override // g8.a0
    public final boolean i0() {
        return c9.e.f7441j.c(this.f19866o.f583n).booleanValue();
    }

    @Override // g8.a0
    public final boolean isExternal() {
        return c9.e.f7440i.c(this.f19866o.f583n).booleanValue();
    }

    @Override // g8.f
    public final boolean isInline() {
        if (!c9.e.k.c(this.f19866o.f583n).booleanValue()) {
            return false;
        }
        c9.a aVar = this.f19867p;
        int i10 = aVar.f7425b;
        if (i10 >= 1) {
            if (i10 > 1) {
                return false;
            }
            int i11 = aVar.f7426c;
            if (i11 >= 4 && (i11 > 4 || aVar.f7427d > 1)) {
                return false;
            }
        }
        return true;
    }

    @Override // g8.f
    public final p9.n j0() {
        return this.f19874w;
    }

    @Override // g8.f, g8.i
    public final List n() {
        return this.f19873v.f21738h.b();
    }

    @Override // g8.f, g8.a0
    public final int o() {
        return this.f19870s;
    }

    @Override // g8.f
    public final int p0() {
        return this.f19872u;
    }

    @Override // g8.f
    public final boolean q() {
        return c9.e.k.c(this.f19866o.f583n).booleanValue() && this.f19867p.a(1, 4, 2);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("deserialized ");
        sb2.append(i0() ? "expect " : "");
        sb2.append("class ");
        sb2.append(getName());
        return sb2.toString();
    }

    @Override // g8.f
    public final Collection w() {
        return (Collection) this.E.invoke();
    }
}
