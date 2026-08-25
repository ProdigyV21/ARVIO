package com.google.common.collect;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a extends o4 implements ListIterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f13925i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f13926l;

    public a(int i10, int i11) {
        ac.b.q(i11, i10);
        this.f13925i = i10;
        this.f13926l = i11;
    }

    public abstract Object a(int i10);

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f13926l < this.f13925i;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f13926l > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f13926l;
        this.f13926l = i10 + 1;
        return a(i10);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f13926l;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f13926l - 1;
        this.f13926l = i10;
        return a(i10);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f13926l - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
