package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public abstract class y1 extends w1 {
    @Override // com.google.common.collect.c1
    public final int b(int i10, Object[] objArr) {
        return a().b(i10, objArr);
    }

    @Override // com.google.common.collect.c1
    /* JADX INFO: renamed from: g */
    public final o4 iterator() {
        return a().listIterator(0);
    }

    public abstract Object get(int i10);

    @Override // com.google.common.collect.w1
    public final h1 n() {
        return new x1(this);
    }
}
