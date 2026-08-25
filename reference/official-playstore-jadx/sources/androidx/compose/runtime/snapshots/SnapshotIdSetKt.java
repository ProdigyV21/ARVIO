package androidx.compose.runtime.snapshots;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000¨\u0006\u0004"}, d2 = {"binarySearch", "", "", "value", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnapshotIdSetKt {
    public static final int binarySearch(int[] iArr, int i10) {
        int length = iArr.length - 1;
        int i11 = 0;
        while (i11 <= length) {
            int i12 = (i11 + length) >>> 1;
            int i13 = iArr[i12];
            if (i10 > i13) {
                i11 = i12 + 1;
            } else {
                if (i10 >= i13) {
                    return i12;
                }
                length = i12 - 1;
            }
        }
        return -(i11 + 1);
    }
}
