package com.google.common.collect;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public final class m0 extends g3 implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Comparator[] f14056i;

    public m0(y yVar, y yVar2) {
        this.f14056i = new Comparator[]{yVar, yVar2};
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int i10 = 0;
        while (true) {
            Comparator[] comparatorArr = this.f14056i;
            if (i10 >= comparatorArr.length) {
                return 0;
            }
            int iCompare = comparatorArr[i10].compare(obj, obj2);
            if (iCompare != 0) {
                return iCompare;
            }
            i10++;
        }
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof m0) {
            return Arrays.equals(this.f14056i, ((m0) obj).f14056i);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f14056i);
    }

    public final String toString() {
        return a0.c.p(new StringBuilder("Ordering.compound("), Arrays.toString(this.f14056i), ")");
    }
}
