package androidx.tv.foundation.lazy.grid;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class m0 implements n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5273a;

    public m0(int i10) {
        this.f5273a = i10;
        if (i10 <= 0) {
            throw new IllegalArgumentException("grid with no rows/columns");
        }
    }

    @Override // androidx.tv.foundation.lazy.grid.n0
    public final ArrayList a(int i10, int i11) {
        int i12 = this.f5273a;
        int i13 = i10 - ((i12 - 1) * i11);
        int i14 = i13 / i12;
        int i15 = i13 % i12;
        ArrayList arrayList = new ArrayList(i12);
        int i16 = 0;
        while (i16 < i12) {
            arrayList.add(Integer.valueOf((i16 < i15 ? 1 : 0) + i14));
            i16++;
        }
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m0) {
            return this.f5273a == ((m0) obj).f5273a;
        }
        return false;
    }

    public final int hashCode() {
        return -this.f5273a;
    }
}
