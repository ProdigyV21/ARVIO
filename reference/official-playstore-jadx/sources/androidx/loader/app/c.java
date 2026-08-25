package androidx.loader.app;

import android.os.Looper;
import androidx.lifecycle.j0;
import androidx.lifecycle.k0;
import p0.g;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends j0 implements androidx.loader.content.d {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final androidx.loader.content.e f3319l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f3320m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public d f3321n;

    public c(androidx.loader.content.e eVar) {
        this.f3319l = eVar;
        eVar.registerListener(0, this);
    }

    @Override // androidx.loader.content.d
    public final void a(Object obj) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.i(obj);
        } else {
            j(obj);
        }
    }

    @Override // androidx.lifecycle.h0
    public final void f() {
        this.f3319l.startLoading();
    }

    @Override // androidx.lifecycle.h0
    public final void g() {
        this.f3319l.stopLoading();
    }

    @Override // androidx.lifecycle.h0
    public final void h(k0 k0Var) {
        super.h(k0Var);
        this.f3320m = null;
        this.f3321n = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.lifecycle.y, java.lang.Object] */
    public final void k() {
        ?? r02 = this.f3320m;
        d dVar = this.f3321n;
        if (r02 == 0 || dVar == null) {
            return;
        }
        super.h(dVar);
        e(r02, dVar);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(64);
        sb2.append("LoaderInfo{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append(" #0 : ");
        g.a(sb2, this.f3319l);
        sb2.append("}}");
        return sb2.toString();
    }
}
