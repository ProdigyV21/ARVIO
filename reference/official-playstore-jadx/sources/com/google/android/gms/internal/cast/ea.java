package com.google.android.gms.internal.cast;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class ea implements ga {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f13242i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f13243l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ha f13244m;

    public ea(ha haVar) {
        this.f13244m = haVar;
        this.f13243l = haVar.c();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13242i < this.f13243l;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i10 = this.f13242i;
        if (i10 >= this.f13243l) {
            throw new NoSuchElementException();
        }
        this.f13242i = i10 + 1;
        return Byte.valueOf(this.f13244m.b(i10));
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
