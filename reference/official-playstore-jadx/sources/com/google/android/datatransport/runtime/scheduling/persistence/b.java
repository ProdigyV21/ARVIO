package com.google.android.datatransport.runtime.scheduling.persistence;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f12615a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.google.android.datatransport.runtime.i f12616b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final com.google.android.datatransport.runtime.h f12617c;

    public b(long j10, com.google.android.datatransport.runtime.i iVar, com.google.android.datatransport.runtime.h hVar) {
        this.f12615a = j10;
        this.f12616b = iVar;
        if (hVar == null) {
            throw new NullPointerException("Null event");
        }
        this.f12617c = hVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            if (this.f12615a == bVar.f12615a && this.f12616b.equals(bVar.f12616b) && this.f12617c.equals(bVar.f12617c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j10 = this.f12615a;
        return ((((((int) (j10 ^ (j10 >>> 32))) ^ 1000003) * 1000003) ^ this.f12616b.hashCode()) * 1000003) ^ this.f12617c.hashCode();
    }

    public final String toString() {
        return "PersistedEvent{id=" + this.f12615a + ", transportContext=" + this.f12616b + ", event=" + this.f12617c + "}";
    }
}
