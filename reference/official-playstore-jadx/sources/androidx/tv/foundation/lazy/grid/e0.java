package androidx.tv.foundation.lazy.grid;

import androidx.compose.ui.unit.Constraints;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f5213a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g0 f5214b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5215c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5216d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w f5217e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k0 f5218f;

    public e0(boolean z, g0 g0Var, int i10, int i11, w wVar, k0 k0Var) {
        this.f5213a = z;
        this.f5214b = g0Var;
        this.f5215c = i10;
        this.f5216d = i11;
        this.f5217e = wVar;
        this.f5218f = k0Var;
    }

    public final long a(int i10, int i11) {
        int i12;
        g0 g0Var = this.f5214b;
        if (i11 == 1) {
            i12 = g0Var.f5240a[i10];
        } else {
            int i13 = (i11 + i10) - 1;
            int[] iArr = g0Var.f5241b;
            i12 = (iArr[i13] + g0Var.f5240a[i13]) - iArr[i10];
        }
        if (i12 < 0) {
            i12 = 0;
        }
        return this.f5213a ? Constraints.INSTANCE.m5656fixedWidthOenEA2s(i12) : Constraints.INSTANCE.m5655fixedHeightOenEA2s(i12);
    }

    public final d0 b(int i10) {
        androidx.core.provider.j jVarB = this.f5218f.b(i10);
        int i11 = jVarB.f2159b;
        List list = (List) jVarB.f2160c;
        int size = list.size();
        int i12 = (size == 0 || i11 + size == this.f5215c) ? 0 : this.f5216d;
        c0[] c0VarArr = new c0[size];
        int i13 = 0;
        for (int i14 = 0; i14 < size; i14++) {
            int i15 = (int) ((o0) list.get(i14)).f5283a;
            long jA = a(i13, i15);
            i13 += i15;
            c0VarArr[i14] = this.f5217e.a(i11 + i14, i12, jA);
        }
        List list2 = (List) jVarB.f2160c;
        x xVar = (x) this;
        return new d0(i10, c0VarArr, xVar.f5328h, list2, xVar.f5327g, i12);
    }
}
