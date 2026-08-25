package com.google.android.gms.internal.cast;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class o4 extends x4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13520i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f13521l;

    public o4(Object obj) {
        this.f13520i = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.f13521l;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f13521l) {
            throw new NoSuchElementException();
        }
        this.f13521l = true;
        return this.f13520i;
    }
}
