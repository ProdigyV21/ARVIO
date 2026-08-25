package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class p1 extends q1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f13807i;

    public p1(String str) {
        this.f13807i = str;
    }

    @Override // com.google.android.gms.internal.fido.q1
    public final int a() {
        return q1.i((byte) 96);
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        q1 q1Var = (q1) obj;
        int iA = q1Var.a();
        int i10 = q1.i((byte) 96);
        if (i10 != iA) {
            return i10 - q1Var.a();
        }
        String str = ((p1) q1Var).f13807i;
        int length = str.length();
        String str2 = this.f13807i;
        if (str2.length() == length) {
            return str2.compareTo(str);
        }
        return str2.length() - str.length();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && p1.class == obj.getClass()) {
            return this.f13807i.equals(((p1) obj).f13807i);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(q1.i((byte) 96)), this.f13807i});
    }

    public final String toString() {
        return a0.c.p(new StringBuilder("\""), this.f13807i, "\"");
    }
}
