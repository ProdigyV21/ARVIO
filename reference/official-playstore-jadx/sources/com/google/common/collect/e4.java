package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class e4 extends w1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Object f13982n;

    public e4(Object obj) {
        obj.getClass();
        this.f13982n = obj;
    }

    @Override // com.google.common.collect.w1, com.google.common.collect.c1
    public final h1 a() {
        return h1.s(this.f13982n);
    }

    @Override // com.google.common.collect.c1
    public final int b(int i10, Object[] objArr) {
        objArr[i10] = this.f13982n;
        return i10 + 1;
    }

    @Override // com.google.common.collect.c1, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f13982n.equals(obj);
    }

    @Override // com.google.common.collect.c1
    public final boolean f() {
        return false;
    }

    @Override // com.google.common.collect.c1
    /* JADX INFO: renamed from: g */
    public final o4 iterator() {
        return new e2(this.f13982n);
    }

    @Override // com.google.common.collect.w1, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.f13982n.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return "[" + this.f13982n.toString() + ']';
    }
}
