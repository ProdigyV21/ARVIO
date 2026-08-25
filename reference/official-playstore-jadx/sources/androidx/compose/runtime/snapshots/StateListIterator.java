package androidx.compose.runtime.snapshots;

import io.ktor.http.LinkHeader;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import s7.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0013\u0010\u0010J\u0017\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\fH\u0096\u0002¢\u0006\u0004\b\u0017\u0010\u000eJ\u0010\u0010\u0018\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0012J\u000f\u0010\u0019\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0019\u0010\u000bJ\u0017\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001a\u0010\u0016R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010\u001fR\u0016\u0010!\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\u001f¨\u0006\""}, d2 = {"Landroidx/compose/runtime/snapshots/StateListIterator;", "T", "", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "list", "", "offset", "<init>", "(Landroidx/compose/runtime/snapshots/SnapshotStateList;I)V", "Lx6/t0;", "validateModification", "()V", "", "hasPrevious", "()Z", "nextIndex", "()I", "previous", "()Ljava/lang/Object;", "previousIndex", "element", "add", "(Ljava/lang/Object;)V", "hasNext", LinkHeader.Rel.Next, "remove", "set", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "getList", "()Landroidx/compose/runtime/snapshots/SnapshotStateList;", "index", "I", "lastRequested", "structure", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class StateListIterator<T> implements ListIterator<T>, f {
    private int index;
    private int lastRequested = -1;
    private final SnapshotStateList<T> list;
    private int structure;

    public StateListIterator(SnapshotStateList<T> snapshotStateList, int i10) {
        this.list = snapshotStateList;
        this.index = i10 - 1;
        this.structure = snapshotStateList.getStructure$runtime_release();
    }

    private final void validateModification() {
        if (this.list.getStructure$runtime_release() != this.structure) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator
    public void add(T element) {
        validateModification();
        this.list.add(this.index + 1, element);
        this.lastRequested = -1;
        this.index++;
        this.structure = this.list.getStructure$runtime_release();
    }

    public final SnapshotStateList<T> getList() {
        return this.list;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return this.index < this.list.size() - 1;
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        return this.index >= 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public T next() {
        validateModification();
        int i10 = this.index + 1;
        this.lastRequested = i10;
        SnapshotStateListKt.validateRange(i10, this.list.size());
        T t2 = this.list.get(i10);
        this.index = i10;
        return t2;
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.index + 1;
    }

    @Override // java.util.ListIterator
    public T previous() {
        validateModification();
        SnapshotStateListKt.validateRange(this.index, this.list.size());
        int i10 = this.index;
        this.lastRequested = i10;
        this.index--;
        return this.list.get(i10);
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.index;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        validateModification();
        this.list.remove(this.index);
        this.index--;
        this.lastRequested = -1;
        this.structure = this.list.getStructure$runtime_release();
    }

    @Override // java.util.ListIterator
    public void set(T element) {
        validateModification();
        int i10 = this.lastRequested;
        if (i10 < 0) {
            SnapshotStateListKt.invalidIteratorSet();
            throw new KotlinNothingValueException();
        }
        this.list.set(i10, element);
        this.structure = this.list.getStructure$runtime_release();
    }
}
