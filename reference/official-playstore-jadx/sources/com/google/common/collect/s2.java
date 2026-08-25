package com.google.common.collect;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class s2 extends r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Map.Entry f14105i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ u2 f14106l;

    public s2(Map.Entry entry, u2 u2Var) {
        this.f14105i = entry;
        this.f14106l = u2Var;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f14105i.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        Map.Entry entry = this.f14105i;
        return this.f14106l.d(entry.getKey(), entry.getValue());
    }
}
