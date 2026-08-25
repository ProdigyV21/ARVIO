package com.google.common.collect;

import androidx.media3.common.TrackGroup;

/* JADX INFO: loaded from: classes4.dex */
public final class y0 extends k1 {
    @Override // com.google.common.collect.k1
    public final l1 b() {
        throw new UnsupportedOperationException("Not supported for bimaps");
    }

    @Override // com.google.common.collect.k1
    public final k1 c(Object obj, Object obj2) {
        super.c(obj, obj2);
        return this;
    }

    @Override // com.google.common.collect.k1
    public final k1 e(Iterable iterable) {
        super.e(iterable);
        return this;
    }

    public final void f(TrackGroup trackGroup, String str) {
        super.c(trackGroup, str);
    }
}
