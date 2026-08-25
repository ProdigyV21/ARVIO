package j$.util;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class Spliterators {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y0 f18435a = new y0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w0 f18436b = new w0();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final x0 f18437c = new x0();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final v0 f18438d = new v0();

    public static void a(int i10, int i11, int i12) {
        if (i11 <= i12) {
            if (i11 < 0) {
                throw new ArrayIndexOutOfBoundsException(i11);
            }
            if (i12 > i10) {
                throw new ArrayIndexOutOfBoundsException(i12);
            }
            return;
        }
        throw new ArrayIndexOutOfBoundsException("origin(" + i11 + ") > fence(" + i12 + ")");
    }

    public static <T> Spliterator<T> spliterator(java.util.Collection<? extends T> collection, int i10) {
        return new a1((java.util.Collection) Objects.requireNonNull(collection), i10);
    }

    public static <T> Spliterator<T> spliteratorUnknownSize(Iterator<? extends T> it, int i10) {
        return new a1((Iterator) Objects.requireNonNull(it), i10);
    }
}
