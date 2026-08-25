package androidx.compose.foundation.lazy.staggeredgrid;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¨\u0006\u0006"}, d2 = {"calculateCellsCrossAxisSizeImpl", "", "gridSize", "", "slotCount", "spacing", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridCellsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int[] calculateCellsCrossAxisSizeImpl(int i10, int i11, int i12) {
        int i13 = i10 - ((i11 - 1) * i12);
        int i14 = i13 / i11;
        int i15 = i13 % i11;
        int[] iArr = new int[i11];
        int i16 = 0;
        while (i16 < i11) {
            iArr[i16] = (i16 < i15 ? 1 : 0) + i14;
            i16++;
        }
        return iArr;
    }
}
