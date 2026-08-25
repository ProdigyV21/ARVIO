package com.squareup.moshi;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class s implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public u f14464i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public u f14465l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14466m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f14467n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f14468o;

    public s(v vVar, int i10) {
        this.f14468o = i10;
        this.f14467n = vVar;
        this.f14464i = vVar.f14483m.f14474n;
        this.f14466m = vVar.f14485o;
    }

    public final Object a() {
        return b();
    }

    public final u b() {
        u uVar = this.f14464i;
        v vVar = this.f14467n;
        if (uVar == vVar.f14483m) {
            throw new NoSuchElementException();
        }
        if (vVar.f14485o != this.f14466m) {
            throw new ConcurrentModificationException();
        }
        this.f14464i = uVar.f14474n;
        this.f14465l = uVar;
        return uVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f14464i != this.f14467n.f14483m;
    }

    @Override // java.util.Iterator
    public Object next() {
        switch (this.f14468o) {
            case 1:
                return b().f14476p;
            default:
                return a();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        u uVar = this.f14465l;
        if (uVar == null) {
            throw new IllegalStateException();
        }
        v vVar = this.f14467n;
        vVar.e(uVar, true);
        this.f14465l = null;
        this.f14466m = vVar.f14485o;
    }
}
