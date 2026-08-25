package com.google.android.gms.internal.auth;

import java.util.ListIterator;

/* JADX INFO: loaded from: classes4.dex */
public final class n3 implements ListIterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ListIterator f13081i;

    public n3(p3 p3Var, int i10) {
        this.f13081i = p3Var.f13092i.listIterator(i10);
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f13081i.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f13081i.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.f13081i.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f13081i.nextIndex();
    }

    @Override // java.util.ListIterator
    public final /* bridge */ /* synthetic */ Object previous() {
        return (String) this.f13081i.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f13081i.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
