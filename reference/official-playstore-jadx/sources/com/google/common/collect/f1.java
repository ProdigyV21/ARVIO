package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class f1 extends a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final h1 f13997m;

    public f1(int i10, h1 h1Var) {
        super(h1Var.size(), i10);
        this.f13997m = h1Var;
    }

    @Override // com.google.common.collect.a
    public final Object a(int i10) {
        return this.f13997m.get(i10);
    }
}
