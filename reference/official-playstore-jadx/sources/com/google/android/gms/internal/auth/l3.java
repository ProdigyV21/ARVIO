package com.google.android.gms.internal.auth;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class l3 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final l3 f13065e = new l3(0, new int[0], new Object[0], false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f13066a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f13067b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f13068c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f13069d;

    public l3(int i10, int[] iArr, Object[] objArr, boolean z) {
        this.f13066a = i10;
        this.f13067b = iArr;
        this.f13068c = objArr;
        this.f13069d = z;
    }

    public static l3 a() {
        return new l3(0, new int[8], new Object[8], true);
    }

    public final void b(int i10, Object obj) {
        if (!this.f13069d) {
            throw new UnsupportedOperationException();
        }
        c(this.f13066a + 1);
        int[] iArr = this.f13067b;
        int i11 = this.f13066a;
        iArr[i11] = i10;
        this.f13068c[i11] = obj;
        this.f13066a = i11 + 1;
    }

    public final void c(int i10) {
        int[] iArr = this.f13067b;
        if (i10 > iArr.length) {
            int i11 = this.f13066a;
            int i12 = (i11 / 2) + i11;
            if (i12 >= i10) {
                i10 = i12;
            }
            if (i10 < 8) {
                i10 = 8;
            }
            this.f13067b = Arrays.copyOf(iArr, i10);
            this.f13068c = Arrays.copyOf(this.f13068c, i10);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof l3)) {
            return false;
        }
        l3 l3Var = (l3) obj;
        int i10 = this.f13066a;
        if (i10 == l3Var.f13066a) {
            int[] iArr = this.f13067b;
            int[] iArr2 = l3Var.f13067b;
            int i11 = 0;
            while (true) {
                if (i11 >= i10) {
                    Object[] objArr = this.f13068c;
                    Object[] objArr2 = l3Var.f13068c;
                    int i12 = this.f13066a;
                    for (int i13 = 0; i13 < i12; i13++) {
                        if (objArr[i13].equals(objArr2[i13])) {
                        }
                    }
                    return true;
                }
                if (iArr[i11] != iArr2[i11]) {
                    break;
                }
                i11++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i10 = this.f13066a;
        int i11 = i10 + 527;
        int[] iArr = this.f13067b;
        int iHashCode = 17;
        int i12 = 17;
        for (int i13 = 0; i13 < i10; i13++) {
            i12 = (i12 * 31) + iArr[i13];
        }
        int i14 = (i11 * 31) + i12;
        Object[] objArr = this.f13068c;
        int i15 = this.f13066a;
        for (int i16 = 0; i16 < i15; i16++) {
            iHashCode = (iHashCode * 31) + objArr[i16].hashCode();
        }
        return (i14 * 31) + iHashCode;
    }
}
