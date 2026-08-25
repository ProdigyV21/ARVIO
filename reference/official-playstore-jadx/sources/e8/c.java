package e8;

import d8.q;
import g8.c1;
import g8.h0;
import g8.v0;
import j8.s0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import p9.m;
import p9.n;
import u9.p;
import v9.o0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends j8.b {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final f9.b f15046v = new f9.b(q.f14766l, f9.f.j("Function"));

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final f9.b f15047w = new f9.b(q.f14764i, f9.f.j("KFunction"));

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final p f15048o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final h0 f15049p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final j f15050q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f15051r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final b f15052s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final d f15053t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final List f15054u;

    public c(p pVar, d8.d dVar, j jVar, int i10) {
        super(pVar, jVar.a(i10));
        this.f15048o = pVar;
        this.f15049p = dVar;
        this.f15050q = jVar;
        this.f15051r = i10;
        this.f15052s = new b(this);
        this.f15053t = new d(pVar, this);
        ArrayList arrayList = new ArrayList();
        x7.i iVar = new x7.i(1, i10, 1);
        ArrayList arrayList2 = new ArrayList(s.U(iVar, 10));
        x7.h it = iVar.iterator();
        while (it.f22624m) {
            arrayList.add(s0.J0(this, 2, f9.f.j("P" + it.nextInt()), arrayList.size(), this.f15048o));
            arrayList2.add(t0.f22605a);
        }
        arrayList.add(s0.J0(this, 3, f9.f.j("R"), arrayList.size(), this.f15048o));
        this.f15054u = x.c1(arrayList);
        j jVar2 = this.f15050q;
        if (jVar2.equals(f.f15055c) || jVar2.equals(i.f15058c) || jVar2.equals(g.f15056c)) {
            return;
        }
        jVar2.equals(h.f15057c);
    }

    @Override // g8.f
    public final /* bridge */ /* synthetic */ g8.e C() {
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

    @Override // g8.k
    public final g8.k d() {
        return this.f15049p;
    }

    @Override // g8.n
    public final v0 f() {
        return v0.f15554b;
    }

    @Override // g8.h
    public final o0 g() {
        return this.f15052s;
    }

    @Override // j8.b0
    public final n g0(w9.e eVar) {
        return this.f15053t;
    }

    @Override // h8.a
    public final h8.h getAnnotations() {
        return h8.g.f15863a;
    }

    @Override // g8.f, g8.o, g8.a0
    public final g8.p getVisibility() {
        return g8.q.f15528e;
    }

    @Override // g8.f
    public final /* bridge */ /* synthetic */ Collection h() {
        return z.f19728i;
    }

    @Override // g8.i
    public final boolean i() {
        return false;
    }

    @Override // g8.a0
    public final boolean i0() {
        return false;
    }

    @Override // g8.a0
    public final boolean isExternal() {
        return false;
    }

    @Override // g8.f
    public final boolean isInline() {
        return false;
    }

    @Override // g8.f
    public final /* bridge */ /* synthetic */ n j0() {
        return m.f21235b;
    }

    @Override // g8.f, g8.i
    public final List n() {
        return this.f15054u;
    }

    @Override // g8.f, g8.a0
    public final int o() {
        return 4;
    }

    @Override // g8.f
    public final int p0() {
        return 2;
    }

    @Override // g8.f
    public final boolean q() {
        return false;
    }

    public final String toString() {
        return getName().d();
    }

    @Override // g8.f
    public final /* bridge */ /* synthetic */ Collection w() {
        return z.f19728i;
    }
}
