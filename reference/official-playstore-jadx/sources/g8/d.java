package g8;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements b1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b1 f15497i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i f15498l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f15499m;

    public d(b1 b1Var, i iVar, int i10) {
        this.f15497i = b1Var;
        this.f15498l = iVar;
        this.f15499m = i10;
    }

    @Override // g8.b1
    public final u9.v J() {
        return this.f15497i.J();
    }

    @Override // g8.k
    public final Object L(m mVar, Object obj) {
        return this.f15497i.L(mVar, obj);
    }

    @Override // g8.b1
    public final boolean O() {
        return true;
    }

    @Override // g8.h, g8.k
    public final h a() {
        return this.f15497i.a();
    }

    @Override // g8.k
    public final k d() {
        return this.f15498l;
    }

    @Override // g8.n
    public final v0 f() {
        return this.f15497i.f();
    }

    @Override // g8.b1, g8.h
    public final v9.o0 g() {
        return this.f15497i.g();
    }

    @Override // h8.a
    public final h8.h getAnnotations() {
        return this.f15497i.getAnnotations();
    }

    @Override // g8.b1
    public final int getIndex() {
        return this.f15497i.getIndex() + this.f15499m;
    }

    @Override // g8.d0
    public final f9.f getName() {
        return this.f15497i.getName();
    }

    @Override // g8.b1
    public final List getUpperBounds() {
        return this.f15497i.getUpperBounds();
    }

    @Override // g8.h
    public final v9.b0 m() {
        return this.f15497i.m();
    }

    public final String toString() {
        return this.f15497i + "[inner-copy]";
    }

    @Override // g8.b1
    public final boolean u() {
        return this.f15497i.u();
    }

    @Override // g8.b1
    public final int y() {
        return this.f15497i.y();
    }

    @Override // g8.k
    public final k a() {
        return this.f15497i.a();
    }

    @Override // g8.b1, g8.h, g8.k
    public final b1 a() {
        return this.f15497i.a();
    }
}
