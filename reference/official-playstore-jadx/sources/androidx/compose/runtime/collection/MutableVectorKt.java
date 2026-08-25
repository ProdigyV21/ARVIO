package androidx.compose.runtime.collection;

import androidx.compose.foundation.c;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u001a\u001f\u0010\u0004\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a'\u0010\b\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00002\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\b\u0010\t\u001a(\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f\"\u0006\b\u0000\u0010\n\u0018\u00012\b\b\u0002\u0010\u000b\u001a\u00020\u0001H\u0086\b¢\u0006\u0004\b\r\u0010\u000e\u001aG\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f\"\u0006\b\u0000\u0010\n\u0018\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0014\b\b\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00028\u00000\u0010H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0004\b\r\u0010\u0012\u001a\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\f\"\u0006\b\u0000\u0010\n\u0018\u0001H\u0086\b¢\u0006\u0004\b\u0013\u0010\u0014\u001a2\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\f\"\u0006\b\u0000\u0010\n\u0018\u00012\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0015\"\u00028\u0000H\u0086\b¢\u0006\u0004\b\u0013\u0010\u0017¨\u0006\u0018"}, d2 = {"", "", "index", "Lx6/t0;", "checkIndex", "(Ljava/util/List;I)V", "fromIndex", "toIndex", "checkSubIndex", "(Ljava/util/List;II)V", "T", "capacity", "Landroidx/compose/runtime/collection/MutableVector;", "MutableVector", "(I)Landroidx/compose/runtime/collection/MutableVector;", ContentDisposition.Parameters.Size, "Lkotlin/Function1;", "init", "(ILr7/l;)Landroidx/compose/runtime/collection/MutableVector;", "mutableVectorOf", "()Landroidx/compose/runtime/collection/MutableVector;", "", "elements", "([Ljava/lang/Object;)Landroidx/compose/runtime/collection/MutableVector;", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MutableVectorKt {
    public static final <T> MutableVector<T> MutableVector(int i10) {
        p.h();
        throw null;
    }

    public static MutableVector MutableVector$default(int i10, int i11, Object obj) {
        p.h();
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkIndex(List<?> list, int i10) {
        int size = list.size();
        if (i10 < 0 || i10 >= size) {
            throw new IndexOutOfBoundsException(c.s("Index ", i10, " is out of bounds. The list has ", size, " elements."));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkSubIndex(List<?> list, int i10, int i11) {
        int size = list.size();
        if (i10 > i11) {
            throw new IllegalArgumentException(c.s("Indices are out of order. fromIndex (", i10, ") is greater than toIndex (", i11, ")."));
        }
        if (i10 < 0) {
            throw new IndexOutOfBoundsException(c.o(i10, "fromIndex (", ") is less than 0."));
        }
        if (i11 > size) {
            throw new IndexOutOfBoundsException(c.r("toIndex (", i11, ") is more than than the list size (", size, ')'));
        }
    }

    public static final <T> MutableVector<T> mutableVectorOf() {
        p.h();
        throw null;
    }

    public static final <T> MutableVector<T> MutableVector(int i10, l<? super Integer, ? extends T> lVar) {
        p.h();
        throw null;
    }

    public static final /* synthetic */ <T> MutableVector<T> mutableVectorOf(T... tArr) {
        return new MutableVector<>(tArr, tArr.length);
    }
}
