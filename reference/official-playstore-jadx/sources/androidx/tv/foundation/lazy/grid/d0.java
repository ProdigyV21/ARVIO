package androidx.tv.foundation.lazy.grid;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5193a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c0[] f5194b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g0 f5195c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f5196d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f5197e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5198f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f5199g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f5200h;

    public d0(int i10, c0[] c0VarArr, g0 g0Var, List list, boolean z, int i11) {
        this.f5193a = i10;
        this.f5194b = c0VarArr;
        this.f5195c = g0Var;
        this.f5196d = list;
        this.f5197e = z;
        this.f5198f = i11;
        int iMax = 0;
        for (c0 c0Var : c0VarArr) {
            iMax = Math.max(iMax, c0Var.f5184j);
        }
        this.f5199g = iMax;
        int i12 = iMax + this.f5198f;
        this.f5200h = i12 >= 0 ? i12 : 0;
    }

    public final c0[] a(int i10, int i11, int i12) {
        int i13;
        int i14;
        int i15;
        int i16;
        c0[] c0VarArr = this.f5194b;
        int length = c0VarArr.length;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        while (i17 < length) {
            c0 c0Var = c0VarArr[i17];
            int i20 = i18 + 1;
            int i21 = (int) ((o0) this.f5196d.get(i18)).f5283a;
            int i22 = this.f5195c.f5241b[i19];
            int i23 = this.f5193a;
            boolean z = this.f5197e;
            int i24 = z ? i23 : i19;
            if (z) {
                i13 = i19;
                i16 = i10;
                i14 = i11;
                i15 = i12;
            } else {
                i13 = i23;
                i14 = i11;
                i15 = i12;
                i16 = i10;
            }
            c0Var.b(i16, i22, i14, i15, i24, i13);
            i19 += i21;
            i17++;
            i18 = i20;
        }
        return c0VarArr;
    }
}
