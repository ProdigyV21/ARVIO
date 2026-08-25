package com.google.android.gms.internal.fido;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class x extends h0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Object f13837l = new Object();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f13838i;

    public x(Object obj) {
        this.f13838i = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13838i != f13837l;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object obj = this.f13838i;
        Object obj2 = f13837l;
        if (obj == obj2) {
            throw new NoSuchElementException();
        }
        this.f13838i = obj2;
        return obj;
    }
}
