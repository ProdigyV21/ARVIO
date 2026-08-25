package androidx.fragment.app;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class y1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2887i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ z1 f2888l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f2889m;

    public /* synthetic */ y1(n nVar, z1 z1Var, int i10) {
        this.f2887i = i10;
        this.f2889m = nVar;
        this.f2888l = z1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2887i) {
            case 0:
                ArrayList arrayList = this.f2889m.f2788b;
                z1 z1Var = this.f2888l;
                if (arrayList.contains(z1Var)) {
                    a2.a(z1Var.f2890a, z1Var.f2892c.mView);
                }
                break;
            default:
                n nVar = this.f2889m;
                ArrayList arrayList2 = nVar.f2788b;
                z1 z1Var2 = this.f2888l;
                arrayList2.remove(z1Var2);
                nVar.f2789c.remove(z1Var2);
                break;
        }
    }
}
