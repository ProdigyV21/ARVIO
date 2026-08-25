package com.google.android.gms.internal.fido;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class f1 implements h1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f13778i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f13779l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i1 f13780m;

    public f1(i1 i1Var) {
        this.f13780m = i1Var;
        this.f13779l = i1Var.d();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13778i < this.f13779l;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i10 = this.f13778i;
        if (i10 >= this.f13779l) {
            throw new NoSuchElementException();
        }
        this.f13778i = i10 + 1;
        return Byte.valueOf(this.f13780m.b(i10));
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
