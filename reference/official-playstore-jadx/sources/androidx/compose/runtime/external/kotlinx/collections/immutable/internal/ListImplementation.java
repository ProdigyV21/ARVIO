package androidx.compose.runtime.external.kotlinx.collections.immutable.internal;

import androidx.compose.foundation.c;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import q7.n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\n\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\f\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u000b\u0010\tJ'\u0010\u0011\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0016\u001a\u00020\u00042\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0001¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u001b\u001a\u00020\u00182\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00122\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0001¢\u0006\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/ListImplementation;", "", "<init>", "()V", "", "index", ContentDisposition.Parameters.Size, "Lx6/t0;", "checkElementIndex$runtime_release", "(II)V", "checkElementIndex", "checkPositionIndex$runtime_release", "checkPositionIndex", "fromIndex", "toIndex", "checkRangeIndexes$runtime_release", "(III)V", "checkRangeIndexes", "", "c", "orderedHashCode$runtime_release", "(Ljava/util/Collection;)I", "orderedHashCode", "other", "", "orderedEquals$runtime_release", "(Ljava/util/Collection;Ljava/util/Collection;)Z", "orderedEquals", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ListImplementation {
    public static final int $stable = 0;
    public static final ListImplementation INSTANCE = new ListImplementation();

    private ListImplementation() {
    }

    @n
    public static final void checkElementIndex$runtime_release(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(c.n(index, size, "index: ", ", size: "));
        }
    }

    @n
    public static final void checkPositionIndex$runtime_release(int index, int size) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(c.n(index, size, "index: ", ", size: "));
        }
    }

    @n
    public static final void checkRangeIndexes$runtime_release(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0 || toIndex > size) {
            StringBuilder sbV = c.v("fromIndex: ", fromIndex, ", toIndex: ", toIndex, ", size: ");
            sbV.append(size);
            throw new IndexOutOfBoundsException(sbV.toString());
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(c.n(fromIndex, toIndex, "fromIndex: ", " > toIndex: "));
        }
    }

    @n
    public static final boolean orderedEquals$runtime_release(Collection<?> c10, Collection<?> other) {
        if (c10.size() != other.size()) {
            return false;
        }
        Iterator<?> it = other.iterator();
        Iterator<?> it2 = c10.iterator();
        while (it2.hasNext()) {
            if (!p.a(it2.next(), it.next())) {
                return false;
            }
        }
        return true;
    }

    @n
    public static final int orderedHashCode$runtime_release(Collection<?> c10) {
        Iterator<?> it = c10.iterator();
        int iHashCode = 1;
        while (it.hasNext()) {
            Object next = it.next();
            iHashCode = (iHashCode * 31) + (next != null ? next.hashCode() : 0);
        }
        return iHashCode;
    }
}
