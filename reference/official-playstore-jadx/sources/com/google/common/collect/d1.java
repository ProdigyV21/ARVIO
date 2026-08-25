package com.google.common.collect;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final class d1 extends r implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13963i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f13964l;

    public d1(Object obj, Object obj2) {
        this.f13963i = obj;
        this.f13964l = obj2;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f13963i;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f13964l;
    }

    @Override // com.google.common.collect.r, java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
