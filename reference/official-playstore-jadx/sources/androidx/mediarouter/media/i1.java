package androidx.mediarouter.media;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public final class i1 extends u implements e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4407a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4408b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v f4409c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4410d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4411e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4412f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c1 f4413g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4414h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ j1 f4415i;

    public i1(j1 j1Var, String str, String str2, v vVar) {
        this.f4415i = j1Var;
        this.f4407a = str;
        this.f4408b = str2;
        this.f4409c = vVar;
    }

    @Override // androidx.mediarouter.media.e1
    public final int a() {
        return this.f4414h;
    }

    @Override // androidx.mediarouter.media.e1
    public final void b() {
        c1 c1Var = this.f4413g;
        if (c1Var != null) {
            int i10 = this.f4414h;
            int i11 = c1Var.f4313d;
            c1Var.f4313d = i11 + 1;
            c1Var.b(4, i11, i10, null, null);
            this.f4413g = null;
            this.f4414h = 0;
        }
    }

    @Override // androidx.mediarouter.media.e1
    public final void c(c1 c1Var) {
        this.f4413g = c1Var;
        int i10 = c1Var.f4314e;
        c1Var.f4314e = i10 + 1;
        Bundle bundle = new Bundle();
        bundle.putString("routeId", this.f4407a);
        bundle.putString("routeGroupId", this.f4408b);
        bundle.putParcelable("routeControllerOptions", this.f4409c.f4489a);
        int i11 = c1Var.f4313d;
        c1Var.f4313d = i11 + 1;
        c1Var.b(3, i11, i10, null, bundle);
        this.f4414h = i10;
        if (this.f4410d) {
            c1Var.a(i10);
            int i12 = this.f4411e;
            if (i12 >= 0) {
                c1Var.c(this.f4414h, i12);
                this.f4411e = -1;
            }
            int i13 = this.f4412f;
            if (i13 != 0) {
                c1Var.d(this.f4414h, i13);
                this.f4412f = 0;
            }
        }
    }

    @Override // androidx.mediarouter.media.u
    public final void d() {
        j1 j1Var = this.f4415i;
        j1Var.f4420u.remove(this);
        b();
        j1Var.l();
    }

    @Override // androidx.mediarouter.media.u
    public final void e() {
        this.f4410d = true;
        c1 c1Var = this.f4413g;
        if (c1Var != null) {
            c1Var.a(this.f4414h);
        }
    }

    @Override // androidx.mediarouter.media.u
    public final void f(int i10) {
        c1 c1Var = this.f4413g;
        if (c1Var != null) {
            c1Var.c(this.f4414h, i10);
        } else {
            this.f4411e = i10;
            this.f4412f = 0;
        }
    }

    @Override // androidx.mediarouter.media.u
    public final void g() {
        h(0);
    }

    @Override // androidx.mediarouter.media.u
    public final void h(int i10) {
        this.f4410d = false;
        c1 c1Var = this.f4413g;
        if (c1Var != null) {
            int i11 = this.f4414h;
            Bundle bundle = new Bundle();
            bundle.putInt("unselectReason", i10);
            int i12 = c1Var.f4313d;
            c1Var.f4313d = i12 + 1;
            c1Var.b(6, i12, i11, null, bundle);
        }
    }

    @Override // androidx.mediarouter.media.u
    public final void i(int i10) {
        c1 c1Var = this.f4413g;
        if (c1Var != null) {
            c1Var.d(this.f4414h, i10);
        } else {
            this.f4412f += i10;
        }
    }
}
