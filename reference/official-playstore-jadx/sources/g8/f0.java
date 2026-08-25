package g8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 extends j8.l {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f15502q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ArrayList f15503r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final v9.j f15504s;

    public f0(u9.p pVar, g gVar, f9.f fVar, boolean z, int i10) {
        super(pVar, gVar, fVar, v0.f15554b);
        this.f15502q = z;
        x7.i iVarX = qb.d.X(0, i10);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iVarX, 10));
        x7.h it = iVarX.iterator();
        while (it.f22624m) {
            int iNextInt = it.nextInt();
            arrayList.add(j8.s0.J0(this, 1, f9.f.j("T" + iNextInt), iNextInt, pVar));
        }
        this.f15503r = arrayList;
        List listC = y.c(this);
        int i11 = m9.d.f20302a;
        this.f15504s = new v9.j(this, listC, Collections.singleton(i9.f.d(this).j().e()), pVar);
    }

    @Override // g8.f
    public final e C() {
        return null;
    }

    @Override // g8.f
    public final boolean E0() {
        return false;
    }

    @Override // g8.f
    public final c1 S() {
        return null;
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
    public final v9.o0 g() {
        return this.f15504s;
    }

    @Override // j8.b0
    public final /* bridge */ /* synthetic */ p9.n g0(w9.e eVar) {
        return p9.m.f21235b;
    }

    @Override // h8.a
    public final h8.h getAnnotations() {
        return h8.g.f15863a;
    }

    @Override // g8.f, g8.o, g8.a0
    public final p getVisibility() {
        return q.f15528e;
    }

    @Override // g8.f
    public final Collection h() {
        return kotlin.collections.b0.f19686i;
    }

    @Override // g8.i
    public final boolean i() {
        return this.f15502q;
    }

    @Override // g8.a0
    public final boolean i0() {
        return false;
    }

    @Override // j8.l, g8.a0
    public final boolean isExternal() {
        return false;
    }

    @Override // g8.f
    public final boolean isInline() {
        return false;
    }

    @Override // g8.f
    public final /* bridge */ /* synthetic */ p9.n j0() {
        return p9.m.f21235b;
    }

    @Override // g8.f, g8.i
    public final List n() {
        return this.f15503r;
    }

    @Override // g8.f, g8.a0
    public final int o() {
        return 1;
    }

    @Override // g8.f
    public final int p0() {
        return 1;
    }

    @Override // g8.f
    public final boolean q() {
        return false;
    }

    public final String toString() {
        return "class " + getName() + " (not found)";
    }

    @Override // g8.f
    public final Collection w() {
        return kotlin.collections.z.f19728i;
    }
}
