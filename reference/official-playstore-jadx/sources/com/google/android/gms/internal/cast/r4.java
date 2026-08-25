package com.google.android.gms.internal.cast;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class r4 extends m4 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient l4 f13588n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient Object[] f13589o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient int f13590p;

    public r4(l4 l4Var, Object[] objArr, int i10) {
        this.f13588n = l4Var;
        this.f13589o = objArr;
        this.f13590p = i10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.f13588n.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.cast.g4
    public final int f(Object[] objArr) {
        return d().f(objArr);
    }

    @Override // com.google.android.gms.internal.cast.m4
    public final j4 h() {
        return new q4(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return d().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13590p;
    }
}
