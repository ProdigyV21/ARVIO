package com.google.common.collect;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public abstract class o0 implements Comparable, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f14076i;

    public o0(Comparable comparable) {
        this.f14076i = comparable;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Comparable, java.lang.Object] */
    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(o0 o0Var) {
        if (o0Var == n0.f14064n) {
            return 1;
        }
        if (o0Var == n0.f14063m) {
            return -1;
        }
        Object obj = o0Var.f14076i;
        j3 j3Var = j3.f14034m;
        int iCompareTo = this.f14076i.compareTo(obj);
        return iCompareTo != 0 ? iCompareTo : Boolean.compare(false, false);
    }

    public abstract void d(StringBuilder sb2);

    public final boolean equals(Object obj) {
        if (obj instanceof o0) {
            try {
                if (compareTo((o0) obj) == 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public abstract void f(StringBuilder sb2);

    public abstract int hashCode();

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Comparable, java.lang.Object] */
    public Comparable i() {
        return this.f14076i;
    }

    public abstract boolean j(Comparable comparable);
}
