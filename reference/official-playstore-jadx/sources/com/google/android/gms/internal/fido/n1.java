package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class n1 extends q1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f13799i;

    public n1(long j10) {
        this.f13799i = j10;
    }

    @Override // com.google.android.gms.internal.fido.q1
    public final int a() {
        return q1.i(this.f13799i >= 0 ? (byte) 0 : (byte) 32);
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        q1 q1Var = (q1) obj;
        if (a() != q1Var.a()) {
            return a() - q1Var.a();
        }
        long jAbs = Math.abs(this.f13799i);
        long jAbs2 = Math.abs(((n1) q1Var).f13799i);
        if (jAbs < jAbs2) {
            return -1;
        }
        return jAbs > jAbs2 ? 1 : 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && n1.class == obj.getClass() && this.f13799i == ((n1) obj).f13799i;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(a()), Long.valueOf(this.f13799i)});
    }

    public final String toString() {
        return Long.toString(this.f13799i);
    }
}
