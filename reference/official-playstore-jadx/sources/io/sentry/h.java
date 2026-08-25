package io.sentry;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/* JADX INFO: loaded from: classes4.dex */
public final class h extends AbstractCollection implements Queue, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final transient Object[] f17308i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient int f17309l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient int f17310m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient boolean f17311n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f17312o;

    public h(int i10) {
        if (i10 <= 0) {
            throw new IllegalArgumentException("The size must be greater than 0");
        }
        Object[] objArr = new Object[i10];
        this.f17308i = objArr;
        this.f17312o = objArr.length;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Queue
    public final boolean add(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Attempted to add null object to queue");
        }
        int size = size();
        int i10 = this.f17312o;
        if (size == i10) {
            remove();
        }
        int i11 = this.f17310m;
        int i12 = i11 + 1;
        this.f17310m = i12;
        this.f17308i[i11] = obj;
        if (i12 >= i10) {
            this.f17310m = 0;
        }
        if (this.f17310m == this.f17309l) {
            this.f17311n = true;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.f17311n = false;
        this.f17309l = 0;
        this.f17310m = 0;
        Arrays.fill(this.f17308i, (Object) null);
    }

    @Override // java.util.Queue
    public final Object element() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return peek();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new g(this);
    }

    @Override // java.util.Queue
    public final boolean offer(Object obj) {
        add(obj);
        return true;
    }

    @Override // java.util.Queue
    public final Object peek() {
        if (isEmpty()) {
            return null;
        }
        return this.f17308i[this.f17309l];
    }

    @Override // java.util.Queue
    public final Object poll() {
        if (isEmpty()) {
            return null;
        }
        return remove();
    }

    @Override // java.util.Queue
    public final Object remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        int i10 = this.f17309l;
        Object[] objArr = this.f17308i;
        Object obj = objArr[i10];
        if (obj != null) {
            int i11 = i10 + 1;
            this.f17309l = i11;
            objArr[i10] = null;
            if (i11 >= this.f17312o) {
                this.f17309l = 0;
            }
            this.f17311n = false;
        }
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        int i10 = this.f17310m;
        int i11 = this.f17309l;
        int i12 = this.f17312o;
        if (i10 < i11) {
            return (i12 - i11) + i10;
        }
        if (i10 != i11) {
            return i10 - i11;
        }
        if (this.f17311n) {
            return i12;
        }
        return 0;
    }
}
