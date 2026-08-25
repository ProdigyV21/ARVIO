package com.google.common.collect;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public abstract class l4 implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Iterator f14050i;

    public l4(Iterator it) {
        it.getClass();
        this.f14050i = it;
    }

    public abstract Object a(Object obj);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f14050i.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return a(this.f14050i.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f14050i.remove();
    }
}
