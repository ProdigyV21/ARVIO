package androidx.fragment.app;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class a1 implements z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2663a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ b1 f2664b;

    public a1(b1 b1Var, int i10) {
        this.f2664b = b1Var;
        this.f2663a = i10;
    }

    @Override // androidx.fragment.app.z0
    public final boolean a(ArrayList arrayList, ArrayList arrayList2) {
        b1 b1Var = this.f2664b;
        c0 c0Var = b1Var.x;
        int i10 = this.f2663a;
        if (c0Var == null || i10 >= 0 || !c0Var.getChildFragmentManager().N(-1, 0)) {
            return b1Var.O(arrayList, arrayList2, i10, 1);
        }
        return false;
    }
}
