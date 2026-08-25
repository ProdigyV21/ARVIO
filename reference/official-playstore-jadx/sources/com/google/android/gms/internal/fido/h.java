package com.google.android.gms.internal.fido;

/* JADX INFO: loaded from: classes4.dex */
public final class h extends g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i4.v f13783i;

    public h(i4.v vVar) {
        this.f13783i = vVar;
    }

    @Override // com.google.android.gms.internal.fido.g
    public final Object a() {
        return this.f13783i;
    }

    @Override // com.google.android.gms.internal.fido.g
    public final boolean b() {
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof h) {
            return this.f13783i.equals(((h) obj).f13783i);
        }
        return false;
    }

    public final int hashCode() {
        return this.f13783i.hashCode() + 1502476572;
    }

    public final String toString() {
        return a0.c.l("Optional.of(", this.f13783i.toString(), ")");
    }
}
