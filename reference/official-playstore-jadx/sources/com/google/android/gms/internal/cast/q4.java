package com.google.android.gms.internal.cast;

import j$.util.Objects;
import java.util.AbstractMap;

/* JADX INFO: loaded from: classes4.dex */
public final class q4 extends j4 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r4 f13554m;

    public q4(r4 r4Var) {
        this.f13554m = r4Var;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final boolean e() {
        return true;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i10) {
        r4 r4Var = this.f13554m;
        u0.r(i10, r4Var.f13590p);
        Object[] objArr = r4Var.f13589o;
        int i11 = i10 + i10;
        Object obj = objArr[i11];
        Objects.requireNonNull(obj);
        Object obj2 = objArr[i11 + 1];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13554m.f13590p;
    }
}
