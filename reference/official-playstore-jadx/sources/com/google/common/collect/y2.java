package com.google.common.collect;

import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class y2 implements com.google.common.base.c0, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f14145i;

    public y2() {
        a0.c(2, "expectedValuesPerKey");
        this.f14145i = 2;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        return new ArrayList(this.f14145i);
    }
}
