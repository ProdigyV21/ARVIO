package com.google.android.gms.internal.auth;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes4.dex */
public final class j2 extends q1 implements RandomAccess, k2 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List f13051l;

    static {
        new j2();
    }

    public j2(int i10) {
        ArrayList arrayList = new ArrayList(i10);
        super(true);
        this.f13051l = arrayList;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i10, Object obj) {
        a();
        this.f13051l.add(i10, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.auth.q1, java.util.AbstractList, java.util.List
    public final boolean addAll(int i10, Collection collection) {
        a();
        if (collection instanceof k2) {
            collection = ((k2) collection).zzg();
        }
        boolean zAddAll = this.f13051l.addAll(i10, collection);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final String get(int i10) {
        List list = this.f13051l;
        Object obj = list.get(i10);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof x1) {
            x1 x1Var = (x1) obj;
            String str = x1Var.c() == 0 ? "" : new String(x1Var.f13144l, 0, x1Var.c(), i2.f13047a);
            byte[] bArr = x1Var.f13144l;
            int iC = x1Var.c();
            v3.f13137a.getClass();
            if (m1.a(0, iC, bArr)) {
                list.set(i10, str);
            }
            return str;
        }
        byte[] bArr2 = (byte[]) obj;
        String str2 = new String(bArr2, i2.f13047a);
        m1 m1Var = v3.f13137a;
        int length = bArr2.length;
        m1Var.getClass();
        if (m1.a(0, length, bArr2)) {
            list.set(i10, str2);
        }
        return str2;
    }

    @Override // com.google.android.gms.internal.auth.q1, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        a();
        this.f13051l.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.auth.q1, java.util.AbstractList, java.util.List
    public final Object remove(int i10) {
        a();
        Object objRemove = this.f13051l.remove(i10);
        ((AbstractList) this).modCount++;
        if (objRemove instanceof String) {
            return (String) objRemove;
        }
        if (!(objRemove instanceof x1)) {
            return new String((byte[]) objRemove, i2.f13047a);
        }
        x1 x1Var = (x1) objRemove;
        return x1Var.c() == 0 ? "" : new String(x1Var.f13144l, 0, x1Var.c(), i2.f13047a);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        a();
        Object obj2 = this.f13051l.set(i10, (String) obj);
        if (obj2 instanceof String) {
            return (String) obj2;
        }
        if (!(obj2 instanceof x1)) {
            return new String((byte[]) obj2, i2.f13047a);
        }
        x1 x1Var = (x1) obj2;
        return x1Var.c() == 0 ? "" : new String(x1Var.f13144l, 0, x1Var.c(), i2.f13047a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13051l.size();
    }

    @Override // com.google.android.gms.internal.auth.g2
    public final g2 zzd(int i10) {
        List list = this.f13051l;
        if (i10 < list.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i10);
        arrayList.addAll(list);
        return new j2(arrayList);
    }

    @Override // com.google.android.gms.internal.auth.k2
    public final k2 zze() {
        return this.f13103i ? new p3(this) : this;
    }

    @Override // com.google.android.gms.internal.auth.k2
    public final List zzg() {
        return Collections.unmodifiableList(this.f13051l);
    }

    public j2(ArrayList arrayList) {
        super(true);
        this.f13051l = arrayList;
    }

    public j2() {
        super(false);
        this.f13051l = Collections.EMPTY_LIST;
    }

    @Override // com.google.android.gms.internal.auth.q1, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(this.f13051l.size(), collection);
    }
}
