package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes4.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f12765a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f12766b;

    public l(Object obj, String str) {
        this.f12765a = obj;
        this.f12766b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return this.f12765a == lVar.f12765a && this.f12766b.equals(lVar.f12766b);
    }

    public final int hashCode() {
        return this.f12766b.hashCode() + (System.identityHashCode(this.f12765a) * 31);
    }
}
