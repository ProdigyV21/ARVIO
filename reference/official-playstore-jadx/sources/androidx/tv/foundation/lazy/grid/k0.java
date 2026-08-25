package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.IntervalList;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f5258a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f5259b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5260c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5261d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5262e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5263f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f5264g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f5265h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5266i;

    public k0(l lVar) {
        this.f5258a = lVar;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new h0(0, 0));
        this.f5259b = arrayList;
        this.f5263f = -1;
        this.f5264g = new ArrayList();
        this.f5265h = kotlin.collections.z.f19728i;
    }

    public final int a() {
        return ((int) Math.sqrt((((double) d()) * 1.0d) / ((double) this.f5266i))) + 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x009e  */
    /* JADX WARN: Type inference failed for: r4v15, types: [java.lang.Object, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.core.provider.j b(int r13) {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.foundation.lazy.grid.k0.b(int):androidx.core.provider.j");
    }

    public final int c(int i10) {
        if (d() <= 0) {
            return 0;
        }
        if (i10 >= d()) {
            throw new IllegalArgumentException("ItemIndex > total count");
        }
        if (!this.f5258a.f5269c) {
            return i10 / this.f5266i;
        }
        j0 j0Var = new j0(i10);
        ArrayList arrayList = this.f5259b;
        int iB = t7.a.b(arrayList, arrayList.size(), j0Var);
        if (iB < 0) {
            iB = (-iB) - 2;
        }
        int iA = a() * iB;
        int i11 = ((h0) arrayList.get(iB)).f5245a;
        if (i11 > i10) {
            throw new IllegalArgumentException("currentItemIndex > itemIndex");
        }
        int i12 = 0;
        while (true) {
            if (i11 >= i10) {
                break;
            }
            int i13 = i11 + 1;
            int iE = e(i11);
            i12 += iE;
            int i14 = this.f5266i;
            if (i12 >= i14) {
                if (i12 == i14) {
                    iA++;
                    i12 = 0;
                } else {
                    iA++;
                    i12 = iE;
                }
            }
            if (iA % a() == 0 && iA / a() >= arrayList.size()) {
                arrayList.add(new h0(i13 - (i12 <= 0 ? 0 : 1), 0));
            }
            i11 = i13;
        }
        return e(i10) + i12 > this.f5266i ? iA + 1 : iA;
    }

    public final int d() {
        return this.f5258a.f5268b.getSize();
    }

    public final int e(int i10) {
        i0.f5252b = this.f5266i;
        IntervalList.Interval interval = this.f5258a.f5268b.get(i10);
        int startIndex = i10 - interval.getStartIndex();
        return (int) ((o0) ((i) interval.getValue()).f5248b.invoke(i0.f5251a, Integer.valueOf(startIndex))).f5283a;
    }
}
