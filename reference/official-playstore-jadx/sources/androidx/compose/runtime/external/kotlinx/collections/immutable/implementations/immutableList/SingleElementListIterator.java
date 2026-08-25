package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\tJ\r\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\tR\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000b"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/SingleElementListIterator;", "E", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableList/AbstractListIterator;", "element", "index", "", "(Ljava/lang/Object;I)V", "Ljava/lang/Object;", LinkHeader.Rel.Next, "()Ljava/lang/Object;", "previous", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SingleElementListIterator<E> extends AbstractListIterator<E> {
    public static final int $stable = 0;
    private final E element;

    public SingleElementListIterator(E e5, int i10) {
        super(i10, 1);
        this.element = e5;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractListIterator, java.util.ListIterator, java.util.Iterator
    public E next() {
        checkHasNext$runtime_release();
        setIndex(getIndex() + 1);
        return this.element;
    }

    @Override // java.util.ListIterator
    public E previous() {
        checkHasPrevious$runtime_release();
        setIndex(getIndex() - 1);
        return this.element;
    }
}
