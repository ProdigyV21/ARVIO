package com.google.common.collect;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public final class v extends b {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final transient int f14123q;

    public v() {
        super(f0.c(12));
        a0.c(3, "expectedValuesPerKey");
        this.f14123q = 3;
    }

    @Override // com.google.common.collect.q
    public final Collection j() {
        return new ArrayList(this.f14123q);
    }
}
