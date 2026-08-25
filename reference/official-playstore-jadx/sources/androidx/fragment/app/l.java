package androidx.fragment.app;

import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z1 f2768a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final androidx.core.os.g f2769b;

    public l(z1 z1Var, androidx.core.os.g gVar) {
        this.f2768a = z1Var;
        this.f2769b = gVar;
    }

    public final void a() {
        z1 z1Var = this.f2768a;
        HashSet hashSet = z1Var.f2894e;
        if (hashSet.remove(this.f2769b) && hashSet.isEmpty()) {
            z1Var.b();
        }
    }

    public final boolean b() {
        z1 z1Var = this.f2768a;
        int iC = a2.c(z1Var.f2892c.mView);
        int i10 = z1Var.f2890a;
        if (iC != i10) {
            return (iC == 2 || i10 == 2) ? false : true;
        }
        return true;
    }
}
