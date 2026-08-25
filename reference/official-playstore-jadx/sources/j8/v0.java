package j8;

import g8.e1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public class v0 extends w0 implements e1 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f19371p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f19372q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f19373r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f19374s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final v9.w f19375t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final e1 f19376u;

    public v0(g8.a aVar, e1 e1Var, int i10, h8.h hVar, f9.f fVar, v9.w wVar, boolean z, boolean z5, boolean z10, v9.w wVar2, g8.v0 v0Var) {
        super(aVar, hVar, fVar, wVar, v0Var);
        this.f19371p = i10;
        this.f19372q = z;
        this.f19373r = z5;
        this.f19374s = z10;
        this.f19375t = wVar2;
        this.f19376u = e1Var == null ? this : e1Var;
    }

    @Override // g8.g1
    public final boolean K() {
        return false;
    }

    @Override // g8.k
    public final Object L(g8.m mVar, Object obj) {
        return mVar.j(this, obj);
    }

    @Override // g8.x0
    public final g8.l b(y0 y0Var) {
        if (y0Var.f22263a.e()) {
            return this;
        }
        throw new UnsupportedOperationException();
    }

    @Override // j8.p, g8.k
    public final g8.a d() {
        return (g8.a) super.d();
    }

    public e1 e0(e8.e eVar, f9.f fVar, int i10) {
        return new v0(eVar, null, i10, getAnnotations(), fVar, getType(), w0(), this.f19373r, this.f19374s, this.f19375t, g8.v0.f15554b);
    }

    @Override // g8.e1
    public final int getIndex() {
        return this.f19371p;
    }

    @Override // g8.o, g8.a0
    public final g8.p getVisibility() {
        return g8.q.f15529f;
    }

    @Override // g8.a
    public final Collection k() {
        Collection collectionK = ((g8.a) super.d()).k();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(collectionK, 10));
        Iterator it = collectionK.iterator();
        while (it.hasNext()) {
            arrayList.add((e1) ((g8.a) it.next()).e().get(this.f19371p));
        }
        return arrayList;
    }

    @Override // g8.g1
    public final /* bridge */ /* synthetic */ k9.g k0() {
        return null;
    }

    @Override // g8.e1
    public final boolean l0() {
        return this.f19374s;
    }

    @Override // g8.e1
    public final boolean m0() {
        return this.f19373r;
    }

    @Override // g8.e1
    public final v9.w q0() {
        return this.f19375t;
    }

    @Override // g8.e1
    public final boolean w0() {
        return this.f19372q && ((g8.c) ((g8.a) super.d())).getKind() != 2;
    }

    @Override // j8.p, g8.k
    public final g8.k d() {
        return (g8.a) super.d();
    }

    @Override // j8.p, j8.o, g8.k
    public final e1 a() {
        e1 e1Var = this.f19376u;
        return e1Var == this ? this : e1Var.a();
    }
}
