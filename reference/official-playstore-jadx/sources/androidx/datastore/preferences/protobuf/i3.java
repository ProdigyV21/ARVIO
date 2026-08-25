package androidx.datastore.preferences.protobuf;

import java.util.ListIterator;

/* JADX INFO: loaded from: classes3.dex */
public final class i3 implements ListIterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ListIterator f2449i;

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f2449i.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f2449i.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return (String) this.f2449i.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f2449i.nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return (String) this.f2449i.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f2449i.previousIndex();
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
