package kotlin.collections;

/* JADX INFO: loaded from: classes5.dex */
public final class c {
    public static void a(int i10, int i11, int i12) {
        if (i10 < 0 || i11 > i12) {
            StringBuilder sbV = androidx.compose.foundation.c.v("startIndex: ", i10, ", endIndex: ", i11, ", size: ");
            sbV.append(i12);
            throw new IndexOutOfBoundsException(sbV.toString());
        }
        if (i10 > i11) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i10, i11, "startIndex: ", " > endIndex: "));
        }
    }

    public static void b(int i10, int i11) {
        if (i10 < 0 || i10 >= i11) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.n(i10, i11, "index: ", ", size: "));
        }
    }

    public static void c(int i10, int i11) {
        if (i10 < 0 || i10 > i11) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.n(i10, i11, "index: ", ", size: "));
        }
    }

    public static void d(int i10, int i11, int i12) {
        if (i10 < 0 || i11 > i12) {
            StringBuilder sbV = androidx.compose.foundation.c.v("fromIndex: ", i10, ", toIndex: ", i11, ", size: ");
            sbV.append(i12);
            throw new IndexOutOfBoundsException(sbV.toString());
        }
        if (i10 > i11) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i10, i11, "fromIndex: ", " > toIndex: "));
        }
    }

    public static int e(int i10, int i11) {
        int i12 = i10 + (i10 >> 1);
        if (i12 - i11 < 0) {
            i12 = i11;
        }
        return i12 - 2147483639 > 0 ? i11 > 2147483639 ? Integer.MAX_VALUE : 2147483639 : i12;
    }
}
