package com.google.common.collect;

import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public final class j0 extends l0 {
    public static l0 g(int i10) {
        return i10 < 0 ? l0.f14045b : i10 > 0 ? l0.f14046c : l0.f14044a;
    }

    @Override // com.google.common.collect.l0
    public final l0 a(int i10, int i11) {
        return g(Integer.compare(i10, i11));
    }

    @Override // com.google.common.collect.l0
    public final l0 b(long j10, long j11) {
        return g(Long.compare(j10, j11));
    }

    @Override // com.google.common.collect.l0
    public final l0 c(Object obj, Object obj2, Comparator comparator) {
        return g(comparator.compare(obj, obj2));
    }

    @Override // com.google.common.collect.l0
    public final l0 d(boolean z, boolean z5) {
        return g(Boolean.compare(z, z5));
    }

    @Override // com.google.common.collect.l0
    public final l0 e(boolean z, boolean z5) {
        return g(Boolean.compare(z5, z));
    }

    @Override // com.google.common.collect.l0
    public final int f() {
        return 0;
    }
}
