package androidx.core.view;

/* JADX INFO: loaded from: classes3.dex */
public abstract class h3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q3 f2244a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public androidx.core.graphics.c[] f2245b;

    public h3() {
        this(new q3());
    }

    public final void a() {
        androidx.core.graphics.c[] cVarArr = this.f2245b;
        if (cVarArr != null) {
            androidx.core.graphics.c cVarF = cVarArr[0];
            androidx.core.graphics.c cVarF2 = cVarArr[1];
            q3 q3Var = this.f2244a;
            if (cVarF2 == null) {
                cVarF2 = q3Var.f2300a.f(2);
            }
            if (cVarF == null) {
                cVarF = q3Var.f2300a.f(1);
            }
            g(androidx.core.graphics.c.a(cVarF, cVarF2));
            androidx.core.graphics.c cVar = this.f2245b[f4.f.m(16)];
            if (cVar != null) {
                f(cVar);
            }
            androidx.core.graphics.c cVar2 = this.f2245b[f4.f.m(32)];
            if (cVar2 != null) {
                d(cVar2);
            }
            androidx.core.graphics.c cVar3 = this.f2245b[f4.f.m(64)];
            if (cVar3 != null) {
                h(cVar3);
            }
        }
    }

    public abstract q3 b();

    public void c(int i10, androidx.core.graphics.c cVar) {
        if (this.f2245b == null) {
            this.f2245b = new androidx.core.graphics.c[9];
        }
        for (int i11 = 1; i11 <= 256; i11 <<= 1) {
            if ((i10 & i11) != 0) {
                this.f2245b[f4.f.m(i11)] = cVar;
            }
        }
    }

    public void d(androidx.core.graphics.c cVar) {
    }

    public abstract void e(androidx.core.graphics.c cVar);

    public void f(androidx.core.graphics.c cVar) {
    }

    public abstract void g(androidx.core.graphics.c cVar);

    public void h(androidx.core.graphics.c cVar) {
    }

    public h3(q3 q3Var) {
        this.f2244a = q3Var;
    }
}
