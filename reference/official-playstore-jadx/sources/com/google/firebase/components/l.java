package com.google.firebase.components;

/* JADX INFO: loaded from: classes4.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s f14279a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f14280b;

    public l(s sVar, boolean z) {
        this.f14279a = sVar;
        this.f14280b = z;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof l) {
            l lVar = (l) obj;
            if (lVar.f14279a.equals(this.f14279a) && lVar.f14280b == this.f14280b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.f14279a.hashCode() ^ 1000003) * 1000003) ^ Boolean.valueOf(this.f14280b).hashCode();
    }
}
