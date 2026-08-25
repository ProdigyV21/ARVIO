package com.google.android.gms.internal.fido;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class f0 extends s {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Object f13777n;

    public f0(Object obj) {
        this.f13777n = obj;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final int a(Object[] objArr) {
        objArr[0] = this.f13777n;
        return 1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f13777n.equals(obj);
    }

    @Override // com.google.android.gms.internal.fido.l
    public final h0 d() {
        return new x(this.f13777n);
    }

    @Override // com.google.android.gms.internal.fido.s
    public final q h() {
        Object[] objArr = {this.f13777n};
        for (int i10 = 0; i10 < 1; i10++) {
            m mVar = q.f13808l;
            if (objArr[i10] == null) {
                throw new NullPointerException(a0.c.i(i10, "at index "));
            }
        }
        return q.h(1, objArr);
    }

    @Override // com.google.android.gms.internal.fido.s, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.f13777n.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return new x(this.f13777n);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return a0.c.l("[", this.f13777n.toString(), "]");
    }
}
