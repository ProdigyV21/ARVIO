package com.typesafe.config.impl;

import java.util.ListIterator;

/* JADX INFO: loaded from: classes4.dex */
public final class s1 implements ListIterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ListIterator f14618i;

    public s1(ListIterator listIterator) {
        this.f14618i = listIterator;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw t1.U("listIterator().add");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f14618i.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f14618i.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return (l6.o) this.f14618i.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f14618i.nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return (l6.o) this.f14618i.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f14618i.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw t1.U("listIterator().remove");
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw t1.U("listIterator().set");
    }
}
