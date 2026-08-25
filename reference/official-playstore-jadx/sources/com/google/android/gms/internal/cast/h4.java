package com.google.android.gms.internal.cast;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class h4 extends x4 implements ListIterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f13280i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f13281l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final j4 f13282m;

    public h4(j4 j4Var, int i10) {
        int size = j4Var.size();
        u0.u(i10, size);
        this.f13280i = size;
        this.f13281l = i10;
        this.f13282m = j4Var;
    }

    public final Object a(int i10) {
        return this.f13282m.get(i10);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f13281l < this.f13280i;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f13281l > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f13281l;
        this.f13281l = i10 + 1;
        return a(i10);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f13281l;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f13281l - 1;
        this.f13281l = i10;
        return a(i10);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f13281l - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
