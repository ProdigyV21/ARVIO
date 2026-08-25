package com.google.common.collect;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final class x3 extends g3 implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final g3 f14142i;

    public x3(g3 g3Var) {
        this.f14142i = g3Var;
    }

    @Override // com.google.common.collect.g3
    public final g3 a() {
        return this.f14142i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.f14142i.compare(obj2, obj);
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof x3) {
            return this.f14142i.equals(((x3) obj).f14142i);
        }
        return false;
    }

    public final int hashCode() {
        return -this.f14142i.hashCode();
    }

    public final String toString() {
        return this.f14142i + ".reverse()";
    }
}
