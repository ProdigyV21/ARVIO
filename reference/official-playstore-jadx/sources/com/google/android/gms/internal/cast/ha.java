package com.google.android.gms.internal.cast;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class ha implements Iterable, Serializable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final ha f13297m = new ha(ya.f13699b);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f13298i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final byte[] f13299l;

    static {
        int i10 = da.f13235a;
    }

    public ha(byte[] bArr) {
        bArr.getClass();
        this.f13299l = bArr;
    }

    public static void d(int i10) {
        if (((i10 - 47) | 47) >= 0) {
            return;
        }
        throw new IndexOutOfBoundsException(androidx.compose.foundation.c.p(i10, "End index: 47 >= ", new StringBuilder(String.valueOf(47).length() + 15 + String.valueOf(i10).length())));
    }

    public byte a(int i10) {
        return this.f13299l[i10];
    }

    public byte b(int i10) {
        return this.f13299l[i10];
    }

    public int c() {
        return this.f13299l.length;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ha) && c() == ((ha) obj).c()) {
            if (c() == 0) {
                return true;
            }
            if (!(obj instanceof ha)) {
                return obj.equals(this);
            }
            ha haVar = (ha) obj;
            int i10 = this.f13298i;
            int i11 = haVar.f13298i;
            if (i10 == 0 || i11 == 0 || i10 == i11) {
                int iC = c();
                if (iC > haVar.c()) {
                    int iC2 = c();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(iC).length() + 18 + String.valueOf(iC2).length());
                    sb2.append("Length too large: ");
                    sb2.append(iC);
                    sb2.append(iC2);
                    throw new IllegalArgumentException(sb2.toString());
                }
                if (iC <= haVar.c()) {
                    byte[] bArr = haVar.f13299l;
                    int i12 = 0;
                    int i13 = 0;
                    while (i12 < iC) {
                        if (this.f13299l[i12] == bArr[i13]) {
                            i12++;
                            i13++;
                        }
                    }
                    return true;
                }
                int iC3 = haVar.c();
                StringBuilder sb3 = new StringBuilder(String.valueOf(iC).length() + 27 + String.valueOf(iC3).length());
                sb3.append("Ran off end of other: 0, ");
                sb3.append(iC);
                sb3.append(", ");
                sb3.append(iC3);
                throw new IllegalArgumentException(sb3.toString());
            }
        }
        return false;
    }

    public final int hashCode() {
        int i10 = this.f13298i;
        if (i10 != 0) {
            return i10;
        }
        int iC = c();
        Charset charset = ya.f13698a;
        int i11 = iC;
        for (int i12 = 0; i12 < iC; i12++) {
            i11 = (i11 * 31) + this.f13299l[i12];
        }
        if (i11 == 0) {
            i11 = 1;
        }
        this.f13298i = i11;
        return i11;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new ea(this);
    }

    public final String toString() {
        String strConcat;
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        int iC = c();
        if (c() <= 50) {
            strConcat = u0.k(this);
        } else {
            d(c());
            strConcat = u0.k(new fa(this.f13299l)).concat("...");
        }
        return a0.c.p(androidx.fragment.app.a2.q("<ByteString@", hexString, " size=", iC, " contents=\""), strConcat, "\">");
    }
}
