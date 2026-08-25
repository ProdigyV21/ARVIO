package com.google.android.gms.internal.fido;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class m extends h0 implements ListIterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f13794i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f13795l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final q f13796m;

    public m(q qVar, int i10) {
        int size = qVar.size();
        if (i10 < 0 || i10 > size) {
            throw new IndexOutOfBoundsException(b.n(i10, size, "index"));
        }
        this.f13794i = size;
        this.f13795l = i10;
        this.f13796m = qVar;
    }

    public final Object a(int i10) {
        return this.f13796m.get(i10);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f13795l < this.f13794i;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f13795l > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f13795l;
        this.f13795l = i10 + 1;
        return a(i10);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f13795l;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f13795l - 1;
        this.f13795l = i10;
        return a(i10);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f13795l - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
