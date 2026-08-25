package androidx.compose.ui.text.caches;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u001c\u0010\u0006\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00038\u0000X\u0081\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\n"}, d2 = {"EMPTY_INTS", "", "EMPTY_OBJECTS", "", "", "[Ljava/lang/Object;", "binarySearchInternal", "", ContentDisposition.Parameters.Size, "value", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContainerHelpersKt {
    public static final int[] EMPTY_INTS = new int[0];
    public static final Object[] EMPTY_OBJECTS = new Object[0];

    public static final int binarySearchInternal(int[] iArr, int i10, int i11) {
        int i12 = i10 - 1;
        int i13 = 0;
        while (i13 <= i12) {
            int i14 = (i13 + i12) >>> 1;
            int i15 = iArr[i14];
            if (i15 < i11) {
                i13 = i14 + 1;
            } else {
                if (i15 <= i11) {
                    return i14;
                }
                i12 = i14 - 1;
            }
        }
        return ~i13;
    }
}
