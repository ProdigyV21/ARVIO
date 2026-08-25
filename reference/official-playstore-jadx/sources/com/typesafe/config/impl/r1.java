package com.typesafe.config.impl;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class r1 implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Iterator f14615i;

    public r1(Iterator it) {
        this.f14615i = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f14615i.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return (l6.o) this.f14615i.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw t1.U("iterator().remove");
    }
}
