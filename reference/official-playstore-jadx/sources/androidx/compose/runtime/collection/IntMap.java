package androidx.compose.runtime.collection;

import android.util.SparseArray;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0017\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0013\b\u0016\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tJ\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0007H\u0086\u0002¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u000e\u001a\u0004\u0018\u00018\u00002\u0006\u0010\n\u001a\u00020\u0007H\u0086\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u000e\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00028\u0000¢\u0006\u0004\b\u000e\u0010\u0011J \u0010\u0014\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u0016\u0010\tJ\r\u0010\u0017\u001a\u00020\u0013¢\u0006\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/collection/IntMap;", "E", "", "Landroid/util/SparseArray;", "sparseArray", "<init>", "(Landroid/util/SparseArray;)V", "", "initialCapacity", "(I)V", "key", "", "contains", "(I)Z", "get", "(I)Ljava/lang/Object;", "valueIfAbsent", "(ILjava/lang/Object;)Ljava/lang/Object;", "value", "Lx6/t0;", "set", "(ILjava/lang/Object;)V", "remove", "clear", "()V", "Landroid/util/SparseArray;", "getSize", "()I", ContentDisposition.Parameters.Size, "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class IntMap<E> {
    public static final int $stable = 8;
    private final SparseArray<E> sparseArray;

    private IntMap(SparseArray<E> sparseArray) {
        this.sparseArray = sparseArray;
    }

    public final void clear() {
        this.sparseArray.clear();
    }

    public final boolean contains(int key) {
        return this.sparseArray.indexOfKey(key) >= 0;
    }

    public final E get(int key) {
        return this.sparseArray.get(key);
    }

    public final int getSize() {
        return this.sparseArray.size();
    }

    public final void remove(int key) {
        this.sparseArray.remove(key);
    }

    public final void set(int key, E value) {
        this.sparseArray.put(key, value);
    }

    public final E get(int key, E valueIfAbsent) {
        return this.sparseArray.get(key, valueIfAbsent);
    }

    public IntMap(int i10) {
        this(new SparseArray(i10));
    }

    public /* synthetic */ IntMap(int i10, int i11, h hVar) {
        this((i11 & 1) != 0 ? 10 : i10);
    }
}
