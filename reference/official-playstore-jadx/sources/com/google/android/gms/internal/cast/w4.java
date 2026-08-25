package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class w4 extends m4 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Object f13668n;

    public w4(Object obj) {
        this.f13668n = obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f13668n.equals(obj);
    }

    @Override // com.google.android.gms.internal.cast.m4, com.google.android.gms.internal.cast.g4
    public final j4 d() {
        h4 h4Var = j4.f13340l;
        Object[] objArr = {this.f13668n};
        u0.s(0, objArr[0]);
        return j4.h(1, objArr);
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final int f(Object[] objArr) {
        objArr[0] = this.f13668n;
        return 1;
    }

    @Override // com.google.android.gms.internal.cast.m4, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.f13668n.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return new o4(this.f13668n);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        String string = this.f13668n.toString();
        return androidx.compose.material3.d.q(new StringBuilder(String.valueOf(string).length() + 2), "[", string, "]");
    }
}
