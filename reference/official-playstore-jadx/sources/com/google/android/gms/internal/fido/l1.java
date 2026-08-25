package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class l1 extends q1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f13793i;

    public l1(boolean z) {
        this.f13793i = z;
    }

    @Override // com.google.android.gms.internal.fido.q1
    public final int a() {
        return q1.i((byte) -32);
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        q1 q1Var = (q1) obj;
        int iA = q1Var.a();
        int i10 = q1.i((byte) -32);
        if (i10 != iA) {
            return i10 - q1Var.a();
        }
        return (true != this.f13793i ? 20 : 21) - (true != ((l1) q1Var).f13793i ? 20 : 21);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && l1.class == obj.getClass() && this.f13793i == ((l1) obj).f13793i;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(q1.i((byte) -32)), Boolean.valueOf(this.f13793i)});
    }

    public final String toString() {
        return Boolean.toString(this.f13793i);
    }
}
