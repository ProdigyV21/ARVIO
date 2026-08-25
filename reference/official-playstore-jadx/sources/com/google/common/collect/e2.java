package com.google.common.collect;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class e2 extends o4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13977i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f13978l;

    public e2(Object obj) {
        this.f13977i = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.f13978l;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f13978l) {
            throw new NoSuchElementException();
        }
        this.f13978l = true;
        return this.f13977i;
    }
}
