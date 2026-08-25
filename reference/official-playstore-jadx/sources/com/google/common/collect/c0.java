package com.google.common.collect;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class c0 implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f13950i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f13951l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13952m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ f0 f13953n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f13954o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ f0 f13955p;

    public c0(f0 f0Var, int i10) {
        this.f13954o = i10;
        this.f13955p = f0Var;
        this.f13953n = f0Var;
        this.f13950i = f0Var.f13992o;
        this.f13951l = f0Var.isEmpty() ? -1 : 0;
        this.f13952m = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13951l >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object e0Var;
        f0 f0Var = this.f13953n;
        if (f0Var.f13992o != this.f13950i) {
            throw new ConcurrentModificationException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f13951l;
        this.f13952m = i10;
        switch (this.f13954o) {
            case 0:
                e0Var = this.f13955p.m()[i10];
                break;
            case 1:
                e0Var = new e0(this.f13955p, i10);
                break;
            default:
                e0Var = this.f13955p.o()[i10];
                break;
        }
        int i11 = this.f13951l + 1;
        if (i11 >= f0Var.f13993p) {
            i11 = -1;
        }
        this.f13951l = i11;
        return e0Var;
    }

    @Override // java.util.Iterator
    public final void remove() {
        f0 f0Var = this.f13953n;
        if (f0Var.f13992o != this.f13950i) {
            throw new ConcurrentModificationException();
        }
        ac.b.t(this.f13952m >= 0, "no calls to next() since the last call to remove()");
        this.f13950i += 32;
        f0Var.remove(f0Var.m()[this.f13952m]);
        this.f13951l--;
        this.f13952m = -1;
    }
}
