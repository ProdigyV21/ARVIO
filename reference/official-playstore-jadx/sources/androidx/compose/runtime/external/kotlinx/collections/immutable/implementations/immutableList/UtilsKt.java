package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0005\u001a\u0018\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\nH\u0000\u001a\u001f\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0000¢\u0006\u0002\u0010\u000f\u001a\u0010\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"LOG_MAX_BUFFER_SIZE", "", "MAX_BUFFER_SIZE", "MAX_BUFFER_SIZE_MINUS_ONE", "MUTABLE_BUFFER_SIZE", "indexSegment", "index", "shift", "persistentVectorOf", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentList;", "E", "presizedBufferWith", "", "", "element", "(Ljava/lang/Object;)[Ljava/lang/Object;", "rootSize", "vectorSize", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UtilsKt {
    public static final int LOG_MAX_BUFFER_SIZE = 5;
    public static final int MAX_BUFFER_SIZE = 32;
    public static final int MAX_BUFFER_SIZE_MINUS_ONE = 31;
    public static final int MUTABLE_BUFFER_SIZE = 33;

    public static final int indexSegment(int i10, int i11) {
        return (i10 >> i11) & 31;
    }

    public static final <E> PersistentList<E> persistentVectorOf() {
        return SmallPersistentVector.INSTANCE.getEMPTY();
    }

    public static final Object[] presizedBufferWith(Object obj) {
        Object[] objArr = new Object[32];
        objArr[0] = obj;
        return objArr;
    }

    public static final int rootSize(int i10) {
        return (i10 - 1) & (-32);
    }
}
