package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class c2 extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c2 f13957n = new c2(new Object[0]);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object[] f13958m;

    public c2(Object[] objArr) {
        super(objArr.length, 0);
        this.f13958m = objArr;
    }

    @Override // com.google.common.collect.a
    public final Object a(int i10) {
        return this.f13958m[i10];
    }
}
