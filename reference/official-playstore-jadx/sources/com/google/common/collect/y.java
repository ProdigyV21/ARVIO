package com.google.common.collect;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class y extends g3 implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final com.google.common.base.m f14143i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final g3 f14144l;

    public y(com.google.common.base.m mVar, g3 g3Var) {
        this.f14143i = mVar;
        this.f14144l = g3Var;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        com.google.common.base.m mVar = this.f14143i;
        return this.f14144l.compare(mVar.apply(obj), mVar.apply(obj2));
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof y) {
            y yVar = (y) obj;
            if (this.f14143i.equals(yVar.f14143i) && this.f14144l.equals(yVar.f14144l)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f14143i, this.f14144l});
    }

    public final String toString() {
        return this.f14144l + ".onResultOf(" + this.f14143i + ")";
    }
}
