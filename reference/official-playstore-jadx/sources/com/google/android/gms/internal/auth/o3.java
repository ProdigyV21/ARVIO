package com.google.android.gms.internal.auth;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class o3 implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Iterator f13086i;

    public o3(p3 p3Var) {
        this.f13086i = p3Var.f13092i.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13086i.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.f13086i.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
