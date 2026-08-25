package com.google.android.gms.internal.fido;

/* JADX INFO: loaded from: classes4.dex */
public final class o extends q {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient q f13800m;

    public o(q qVar) {
        this.f13800m = qVar;
    }

    @Override // com.google.android.gms.internal.fido.q, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return this.f13800m.contains(obj);
    }

    @Override // com.google.android.gms.internal.fido.q
    public final q f() {
        return this.f13800m;
    }

    @Override // com.google.android.gms.internal.fido.q, java.util.List
    /* JADX INFO: renamed from: g */
    public final q subList(int i10, int i11) {
        q qVar = this.f13800m;
        b.m(i10, i11, qVar.size());
        return qVar.subList(qVar.size() - i11, qVar.size() - i10).f();
    }

    @Override // java.util.List
    public final Object get(int i10) {
        q qVar = this.f13800m;
        b.e(i10, qVar.size());
        return qVar.get((qVar.size() - 1) - i10);
    }

    @Override // com.google.android.gms.internal.fido.q, java.util.List
    public final int indexOf(Object obj) {
        int iLastIndexOf = this.f13800m.lastIndexOf(obj);
        if (iLastIndexOf >= 0) {
            return (r0.size() - 1) - iLastIndexOf;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.fido.q, java.util.List
    public final int lastIndexOf(Object obj) {
        int iIndexOf = this.f13800m.indexOf(obj);
        if (iIndexOf >= 0) {
            return (r0.size() - 1) - iIndexOf;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13800m.size();
    }
}
