package androidx.media3.extractor.mp4;

import androidx.media3.common.util.Util;
import androidx.media3.extractor.SniffFailure;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class UnsupportedBrandsSniffFailure implements SniffFailure {
    public final com.google.common.primitives.b compatibleBrands;
    public final int majorBrand;

    public UnsupportedBrandsSniffFailure(int i10, int[] iArr) {
        com.google.common.primitives.b bVar;
        this.majorBrand = i10;
        if (iArr != null) {
            com.google.common.primitives.b bVar2 = com.google.common.primitives.b.f14177m;
            bVar = iArr.length == 0 ? com.google.common.primitives.b.f14177m : new com.google.common.primitives.b(Arrays.copyOf(iArr, iArr.length));
        } else {
            bVar = com.google.common.primitives.b.f14177m;
        }
        this.compatibleBrands = bVar;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList(this.compatibleBrands.f14179l);
        int i10 = 0;
        while (true) {
            com.google.common.primitives.b bVar = this.compatibleBrands;
            if (i10 >= bVar.f14179l) {
                return "UnsupportedBrands{major=" + Util.toFourccString(this.majorBrand) + ", compatible=" + arrayList + "}";
            }
            arrayList.add(Util.toFourccString(bVar.a(i10)));
            i10++;
        }
    }
}
