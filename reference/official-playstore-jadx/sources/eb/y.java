package eb;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends w {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final db.b0 f15193j;
    public final List k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f15194l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f15195m;

    public y(db.b bVar, db.b0 b0Var) {
        super(bVar, b0Var, null, null);
        this.f15193j = b0Var;
        List listC1 = kotlin.collections.x.c1(b0Var.f14881i.keySet());
        this.k = listC1;
        this.f15194l = listC1.size() * 2;
        this.f15195m = -1;
    }

    @Override // eb.w, eb.b
    public final db.m G(String str) {
        return this.f15195m % 2 == 0 ? db.n.c(str) : (db.m) kotlin.collections.h0.s0(this.f15193j, str);
    }

    @Override // eb.w, eb.b
    public final String P(ab.g gVar, int i10) {
        return (String) this.k.get(i10 / 2);
    }

    @Override // eb.w, eb.b
    public final db.m S() {
        return this.f15193j;
    }

    @Override // eb.w
    /* JADX INFO: renamed from: V */
    public final db.b0 S() {
        return this.f15193j;
    }

    @Override // eb.w, bb.b
    public final int w(ab.g gVar) {
        int i10 = this.f15195m;
        if (i10 >= this.f15194l - 1) {
            return -1;
        }
        int i11 = i10 + 1;
        this.f15195m = i11;
        return i11;
    }

    @Override // eb.w, eb.b, bb.b
    public final void u(ab.g gVar) {
    }
}
