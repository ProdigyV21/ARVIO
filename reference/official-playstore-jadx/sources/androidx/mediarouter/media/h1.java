package androidx.mediarouter.media;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public final class h1 extends s implements e1 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f4377f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final v f4378g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4379h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4381j;
    public c1 k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ j1 f4383m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4380i = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4382l = -1;

    public h1(j1 j1Var, String str, v vVar) {
        this.f4383m = j1Var;
        this.f4377f = str;
        this.f4378g = vVar;
    }

    @Override // androidx.mediarouter.media.e1
    public final int a() {
        return this.f4382l;
    }

    @Override // androidx.mediarouter.media.e1
    public final void b() {
        c1 c1Var = this.k;
        if (c1Var != null) {
            int i10 = this.f4382l;
            int i11 = c1Var.f4313d;
            c1Var.f4313d = i11 + 1;
            c1Var.b(4, i11, i10, null, null);
            this.k = null;
            this.f4382l = 0;
        }
    }

    @Override // androidx.mediarouter.media.e1
    public final void c(c1 c1Var) {
        g1 g1Var = new g1(this);
        this.k = c1Var;
        int i10 = c1Var.f4314e;
        c1Var.f4314e = i10 + 1;
        int i11 = c1Var.f4313d;
        c1Var.f4313d = i11 + 1;
        Bundle bundle = new Bundle();
        bundle.putString("memberRouteId", this.f4377f);
        bundle.putParcelable("routeControllerOptions", this.f4378g.f4489a);
        c1Var.b(11, i11, i10, null, bundle);
        c1Var.f4317h.put(i11, g1Var);
        this.f4382l = i10;
        if (this.f4379h) {
            c1Var.a(i10);
            int i12 = this.f4380i;
            if (i12 >= 0) {
                c1Var.c(this.f4382l, i12);
                this.f4380i = -1;
            }
            int i13 = this.f4381j;
            if (i13 != 0) {
                c1Var.d(this.f4382l, i13);
                this.f4381j = 0;
            }
        }
    }

    @Override // androidx.mediarouter.media.u
    public final void d() {
        j1 j1Var = this.f4383m;
        j1Var.f4420u.remove(this);
        b();
        j1Var.l();
    }

    @Override // androidx.mediarouter.media.u
    public final void e() {
        this.f4379h = true;
        c1 c1Var = this.k;
        if (c1Var != null) {
            c1Var.a(this.f4382l);
        }
    }

    @Override // androidx.mediarouter.media.u
    public final void f(int i10) {
        c1 c1Var = this.k;
        if (c1Var != null) {
            c1Var.c(this.f4382l, i10);
        } else {
            this.f4380i = i10;
            this.f4381j = 0;
        }
    }

    @Override // androidx.mediarouter.media.u
    public final void g() {
        h(0);
    }

    @Override // androidx.mediarouter.media.u
    public final void h(int i10) {
        this.f4379h = false;
        c1 c1Var = this.k;
        if (c1Var != null) {
            int i11 = this.f4382l;
            Bundle bundle = new Bundle();
            bundle.putInt("unselectReason", i10);
            int i12 = c1Var.f4313d;
            c1Var.f4313d = i12 + 1;
            c1Var.b(6, i12, i11, null, bundle);
        }
    }

    @Override // androidx.mediarouter.media.u
    public final void i(int i10) {
        c1 c1Var = this.k;
        if (c1Var != null) {
            c1Var.d(this.f4382l, i10);
        } else {
            this.f4381j += i10;
        }
    }
}
