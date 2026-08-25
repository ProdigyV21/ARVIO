package androidx.emoji2.text;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes3.dex */
public final class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseArray f2597a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i0 f2598b;

    public d0(int i10) {
        this.f2597a = new SparseArray(i10);
    }

    public final void a(i0 i0Var, int i10, int i11) {
        int iA = i0Var.a(i10);
        SparseArray sparseArray = this.f2597a;
        d0 d0Var = sparseArray == null ? null : (d0) sparseArray.get(iA);
        if (d0Var == null) {
            d0Var = new d0(1);
            sparseArray.put(i0Var.a(i10), d0Var);
        }
        if (i11 > i10) {
            d0Var.a(i0Var, i10 + 1, i11);
        } else {
            d0Var.f2598b = i0Var;
        }
    }
}
