package kotlin.collections;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends ab.j implements ListIterator {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f f19687n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(f fVar, int i10) {
        super(fVar, 1);
        this.f19687n = fVar;
        c cVar = f.Companion;
        int size = fVar.size();
        cVar.getClass();
        c.c(i10, size);
        this.f812l = i10;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f812l > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f812l;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f812l - 1;
        this.f812l = i10;
        return this.f19687n.get(i10);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f812l - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
