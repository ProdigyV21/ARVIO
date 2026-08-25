package androidx.media3.common.util;

import android.os.Looper;
import android.text.TextUtils;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: loaded from: classes3.dex */
public final class Assertions {
    private Assertions() {
    }

    @Deprecated
    @Pure
    public static void checkArgument(boolean z) {
        ac.b.j(z);
    }

    @Deprecated
    @Pure
    public static int checkIndex(int i10, int i11, int i12) {
        if (i10 < i11 || i10 >= i12) {
            throw new IndexOutOfBoundsException();
        }
        return i10;
    }

    @Deprecated
    @Pure
    public static void checkMainThread() {
        ac.b.t(Looper.myLooper() == Looper.getMainLooper(), "Not in application's main thread");
    }

    @EnsuresNonNull({"#1"})
    @Deprecated
    @Pure
    public static String checkNotEmpty(String str) {
        ac.b.j(!TextUtils.isEmpty(str));
        return str;
    }

    @EnsuresNonNull({"#1"})
    @Deprecated
    @Pure
    public static <T> T checkNotNull(T t2, Object obj) {
        ac.b.p(t2, obj);
        return t2;
    }

    @Deprecated
    @Pure
    public static void checkState(boolean z) {
        ac.b.s(z);
    }

    @EnsuresNonNull({"#1"})
    @Deprecated
    @Pure
    public static <T> T checkStateNotNull(T t2, Object obj) {
        ac.b.p(t2, obj);
        return t2;
    }

    @Deprecated
    @Pure
    public static void checkArgument(boolean z, Object obj) {
        ac.b.k(z, obj);
    }

    @EnsuresNonNull({"#1"})
    @Deprecated
    @Pure
    public static String checkNotEmpty(String str, Object obj) {
        ac.b.k(!TextUtils.isEmpty(str), obj);
        return str;
    }

    @EnsuresNonNull({"#1"})
    @Deprecated
    @Pure
    public static <T> T checkNotNull(T t2) {
        t2.getClass();
        return t2;
    }

    @Deprecated
    @Pure
    public static void checkState(boolean z, Object obj) {
        ac.b.t(z, obj);
    }

    @EnsuresNonNull({"#1"})
    @Deprecated
    @Pure
    public static <T> T checkStateNotNull(T t2) {
        t2.getClass();
        return t2;
    }
}
