package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.ListIterator;

/* JADX INFO: loaded from: classes5.dex */
public final class n0 implements ListIterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ListIterator f19814i;

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f19814i.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f19814i.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return (String) this.f19814i.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f19814i.nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return (String) this.f19814i.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f19814i.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
