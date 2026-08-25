package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet;

import io.ktor.http.LinkHeader;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import s7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B%\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010\u0012R&\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\"\u0010\u0017\u001a\u00020\u00168\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/persistentOrderedSet/PersistentOrderedSetIterator;", "E", "", "", "nextElement", "", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/persistentOrderedSet/Links;", "map", "<init>", "(Ljava/lang/Object;Ljava/util/Map;)V", "Lx6/t0;", "checkHasNext", "()V", "", "hasNext", "()Z", LinkHeader.Rel.Next, "()Ljava/lang/Object;", "Ljava/lang/Object;", "Ljava/util/Map;", "getMap$runtime_release", "()Ljava/util/Map;", "", "index", "I", "getIndex$runtime_release", "()I", "setIndex$runtime_release", "(I)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class PersistentOrderedSetIterator<E> implements Iterator<E>, a {
    public static final int $stable = 8;
    private int index;
    private final Map<E, Links> map;
    private Object nextElement;

    public PersistentOrderedSetIterator(Object obj, Map<E, Links> map) {
        this.nextElement = obj;
        this.map = map;
    }

    private final void checkHasNext() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
    }

    /* JADX INFO: renamed from: getIndex$runtime_release, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    public final Map<E, Links> getMap$runtime_release() {
        return this.map;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.map.size();
    }

    @Override // java.util.Iterator
    public E next() {
        checkHasNext();
        E e5 = (E) this.nextElement;
        this.index++;
        Links links = this.map.get(e5);
        if (links != null) {
            this.nextElement = links.getNext();
            return e5;
        }
        throw new ConcurrentModificationException("Hash code of an element (" + e5 + ") has changed after it was added to the persistent set.");
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setIndex$runtime_release(int i10) {
        this.index = i10;
    }
}
