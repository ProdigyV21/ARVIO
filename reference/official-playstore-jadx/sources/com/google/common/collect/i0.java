package com.google.common.collect;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final class i0 extends g3 implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.media3.exoplayer.trackselection.a f14024i;

    public i0(androidx.media3.exoplayer.trackselection.a aVar) {
        this.f14024i = aVar;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.f14024i.compare(obj, obj2);
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof i0) {
            return this.f14024i.equals(((i0) obj).f14024i);
        }
        return false;
    }

    public final int hashCode() {
        return this.f14024i.hashCode();
    }

    public final String toString() {
        return this.f14024i.toString();
    }
}
