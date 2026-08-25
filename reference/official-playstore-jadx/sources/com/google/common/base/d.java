package com.google.common.base;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d extends i {
    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        return c(((Character) obj).charValue());
    }

    @Override // com.google.common.base.i
    public i d() {
        return new h(this);
    }
}
