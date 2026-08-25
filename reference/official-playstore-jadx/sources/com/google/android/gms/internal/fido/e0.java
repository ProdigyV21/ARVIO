package com.google.android.gms.internal.fido;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final class e0 extends a0 implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a0 f13775i;

    public e0(a0 a0Var) {
        this.f13775i = a0Var;
    }

    @Override // com.google.android.gms.internal.fido.a0
    public final a0 a() {
        return this.f13775i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.f13775i.compare(obj2, obj);
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e0) {
            return this.f13775i.equals(((e0) obj).f13775i);
        }
        return false;
    }

    public final int hashCode() {
        return -this.f13775i.hashCode();
    }

    public final String toString() {
        return this.f13775i.toString().concat(".reverse()");
    }
}
