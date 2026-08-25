package com.google.common.primitives;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements Serializable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final b f14177m = new b(new int[0]);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f14178i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f14179l;

    public b(int[] iArr) {
        int length = iArr.length;
        this.f14178i = iArr;
        this.f14179l = length;
    }

    public final int a(int i10) {
        ac.b.n(i10, this.f14179l);
        return this.f14178i[i10];
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            int i10 = bVar.f14179l;
            int i11 = this.f14179l;
            if (i11 == i10) {
                for (int i12 = 0; i12 < i11; i12++) {
                    if (a(i12) == bVar.a(i12)) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i10 = 1;
        for (int i11 = 0; i11 < this.f14179l; i11++) {
            i10 = (i10 * 31) + this.f14178i[i11];
        }
        return i10;
    }

    public final String toString() {
        int i10 = this.f14179l;
        if (i10 == 0) {
            return "[]";
        }
        StringBuilder sb2 = new StringBuilder(i10 * 5);
        sb2.append('[');
        int[] iArr = this.f14178i;
        sb2.append(iArr[0]);
        for (int i11 = 1; i11 < i10; i11++) {
            sb2.append(", ");
            sb2.append(iArr[i11]);
        }
        sb2.append(']');
        return sb2.toString();
    }
}
