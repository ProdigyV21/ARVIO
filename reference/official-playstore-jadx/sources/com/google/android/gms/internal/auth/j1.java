package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class j1 extends i1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13050i;

    public j1(Object obj) {
        this.f13050i = obj;
    }

    @Override // com.google.android.gms.internal.auth.i1
    public final Object a() {
        return this.f13050i;
    }

    @Override // com.google.android.gms.internal.auth.i1
    public final boolean b() {
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j1) {
            return this.f13050i.equals(((j1) obj).f13050i);
        }
        return false;
    }

    public final int hashCode() {
        return this.f13050i.hashCode() + 1502476572;
    }

    public final String toString() {
        return a0.c.l("Optional.of(", this.f13050i.toString(), ")");
    }
}
