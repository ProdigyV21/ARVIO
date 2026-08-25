package com.google.common.collect;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class r2 implements com.google.common.base.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14098a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ u2 f14099b;

    public /* synthetic */ r2(u2 u2Var, int i10) {
        this.f14098a = i10;
        this.f14099b = u2Var;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        switch (this.f14098a) {
            case 0:
                Map.Entry entry = (Map.Entry) obj;
                a8.e eVar = (a8.e) this.f14099b;
                entry.getKey();
                return ((com.google.common.base.m) eVar.f183l).apply(entry.getValue());
            default:
                Map.Entry entry2 = (Map.Entry) obj;
                u2 u2Var = this.f14099b;
                u2Var.getClass();
                entry2.getClass();
                return new s2(entry2, u2Var);
        }
    }
}
