package com.typesafe.config.impl;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public final class i0 extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g1 f14551a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f14552b;

    public i0(g1 g1Var, ArrayList arrayList) {
        this.f14551a = g1Var;
        this.f14552b = new ArrayList(arrayList);
    }

    @Override // com.typesafe.config.impl.a
    public final Collection b() {
        return this.f14552b;
    }
}
