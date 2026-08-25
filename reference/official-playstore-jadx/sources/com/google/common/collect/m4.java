package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class m4 extends r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final j3 f14060i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f14061l;

    public m4(j3 j3Var, Object obj) {
        this.f14060i = j3Var;
        this.f14061l = obj;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f14060i;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f14061l;
    }
}
