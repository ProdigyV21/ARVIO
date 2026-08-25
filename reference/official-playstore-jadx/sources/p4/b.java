package p4;

import com.google.common.util.concurrent.r0;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements ListIterator, Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f21042i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f21043l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e f21044m;

    public b(e eVar, int i10) {
        int size = eVar.size();
        if (i10 < 0 || i10 > size) {
            throw new IndexOutOfBoundsException(r0.I(i10, size, "index"));
        }
        this.f21042i = size;
        this.f21043l = i10;
        this.f21044m = eVar;
    }

    public final Object a(int i10) {
        return this.f21044m.get(i10);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f21043l < this.f21042i;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f21043l > 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f21043l;
        this.f21043l = i10 + 1;
        return a(i10);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f21043l;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f21043l - 1;
        this.f21043l = i10;
        return a(i10);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f21043l - 1;
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
