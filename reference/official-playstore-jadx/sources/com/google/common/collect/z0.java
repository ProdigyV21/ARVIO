package com.google.common.collect;

import j$.util.Map;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public abstract class z0 extends l1 implements x, Map {
    @Override // com.google.common.collect.l1
    public final c1 g() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.l1
    /* JADX INFO: renamed from: i */
    public final c1 values() {
        return ((n3) this).f14073r.keySet();
    }

    @Override // com.google.common.collect.l1, java.util.Map
    public final Collection values() {
        return ((n3) this).f14073r.keySet();
    }
}
