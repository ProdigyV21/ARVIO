package androidx.core.view;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d0 implements androidx.lifecycle.w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2207i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f2208l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Serializable f2209m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f2210n;

    public /* synthetic */ d0(Object obj, Serializable serializable, Object obj2, int i10) {
        this.f2207i = i10;
        this.f2208l = obj;
        this.f2209m = serializable;
        this.f2210n = obj2;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        n1.h hVar;
        n1.k kVar;
        switch (this.f2207i) {
            case 0:
                f0 f0Var = (f0) this.f2208l;
                androidx.lifecycle.q qVar = (androidx.lifecycle.q) this.f2209m;
                i0 i0Var = (i0) this.f2210n;
                f0Var.getClass();
                Runnable runnable = f0Var.f2229a;
                CopyOnWriteArrayList copyOnWriteArrayList = f0Var.f2230b;
                androidx.lifecycle.p.Companion.getClass();
                int iOrdinal = qVar.ordinal();
                if (pVar == (iOrdinal != 2 ? iOrdinal != 3 ? iOrdinal != 4 ? null : androidx.lifecycle.p.ON_RESUME : androidx.lifecycle.p.ON_START : androidx.lifecycle.p.ON_CREATE)) {
                    copyOnWriteArrayList.add(i0Var);
                    runnable.run();
                } else if (pVar == androidx.lifecycle.p.ON_DESTROY) {
                    f0Var.a(i0Var);
                } else if (pVar == androidx.lifecycle.n.a(qVar)) {
                    copyOnWriteArrayList.remove(i0Var);
                    runnable.run();
                }
                break;
            case 1:
                n1.i iVar = (n1.i) this.f2208l;
                kotlin.jvm.internal.k0 k0Var = (kotlin.jvm.internal.k0) this.f2209m;
                r7.l lVar = (r7.l) this.f2210n;
                int i10 = n1.f.f20457a[pVar.ordinal()];
                if (i10 == 1) {
                    k0Var.f19746i = lVar.invoke(iVar);
                } else if (i10 == 2 && (hVar = (n1.h) k0Var.f19746i) != null) {
                    hVar.a();
                }
                break;
            default:
                n1.j jVar = (n1.j) this.f2208l;
                kotlin.jvm.internal.k0 k0Var2 = (kotlin.jvm.internal.k0) this.f2209m;
                r7.l lVar2 = (r7.l) this.f2210n;
                int i11 = n1.g.f20458a[pVar.ordinal()];
                if (i11 == 1) {
                    k0Var2.f19746i = lVar2.invoke(jVar);
                } else if (i11 == 2 && (kVar = (n1.k) k0Var2.f19746i) != null) {
                    kVar.a();
                }
                break;
        }
    }
}
