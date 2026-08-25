package com.google.android.gms.internal.fido;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public final class k extends a0 implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Comparator f13789i;

    public k(Comparator comparator) {
        comparator.getClass();
        this.f13789i = comparator;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.f13789i.compare(obj, obj2);
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof k) {
            return this.f13789i.equals(((k) obj).f13789i);
        }
        return false;
    }

    public final int hashCode() {
        return this.f13789i.hashCode();
    }

    public final String toString() {
        return this.f13789i.toString();
    }
}
