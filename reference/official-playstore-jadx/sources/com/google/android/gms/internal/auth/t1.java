package com.google.android.gms.internal.auth;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class t1 implements w1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f13122i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f13123l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ x1 f13124m;

    public t1(x1 x1Var) {
        this.f13124m = x1Var;
        this.f13123l = x1Var.c();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13122i < this.f13123l;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i10 = this.f13122i;
        if (i10 >= this.f13123l) {
            throw new NoSuchElementException();
        }
        this.f13122i = i10 + 1;
        return Byte.valueOf(this.f13124m.b(i10));
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
