package com.google.common.collect;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class g0 implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f14007i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f14008l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14009m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h0 f14010n;

    public g0(h0 h0Var) {
        this.f14010n = h0Var;
        this.f14007i = h0Var.f14018n;
        this.f14008l = h0Var.isEmpty() ? -1 : 0;
        this.f14009m = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f14008l >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        h0 h0Var = this.f14010n;
        if (h0Var.f14018n != this.f14007i) {
            throw new ConcurrentModificationException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f14008l;
        this.f14009m = i10;
        Object obj = h0Var.d()[i10];
        int i11 = this.f14008l + 1;
        if (i11 >= h0Var.f14019o) {
            i11 = -1;
        }
        this.f14008l = i11;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        h0 h0Var = this.f14010n;
        if (h0Var.f14018n != this.f14007i) {
            throw new ConcurrentModificationException();
        }
        ac.b.t(this.f14009m >= 0, "no calls to next() since the last call to remove()");
        this.f14007i += 32;
        h0Var.remove(h0Var.d()[this.f14009m]);
        this.f14008l--;
        this.f14009m = -1;
    }
}
