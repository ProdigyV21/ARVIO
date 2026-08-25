package kotlin.collections;

import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 implements ListIterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ListIterator f19697i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n0 f19698l;

    public m0(n0 n0Var, int i10) {
        this.f19698l = n0Var;
        this.f19697i = ((List) n0Var.f19700l).listIterator(x.Z(i10, n0Var));
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f19697i.hasPrevious();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f19697i.hasNext();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return this.f19697i.previous();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return t7.a.w(this.f19698l) - this.f19697i.previousIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return this.f19697i.next();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return t7.a.w(this.f19698l) - this.f19697i.nextIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
