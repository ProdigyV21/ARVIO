package com.google.android.gms.internal.auth;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class x1 implements Iterable, Serializable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final x1 f13142m = new x1(i2.f13048b);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f13143i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final byte[] f13144l;

    static {
        int i10 = r1.f13108a;
    }

    public x1(byte[] bArr) {
        bArr.getClass();
        this.f13144l = bArr;
    }

    public static int d(int i10, int i11, int i12) {
        int i13 = i11 - i10;
        if ((i10 | i11 | i13 | (i12 - i11)) >= 0) {
            return i13;
        }
        if (i10 < 0) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.o(i10, "Beginning index: ", " < 0"));
        }
        if (i11 < i10) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.n(i10, i11, "Beginning index larger than ending index: ", ", "));
        }
        throw new IndexOutOfBoundsException(androidx.compose.foundation.c.n(i11, i12, "End index: ", " >= "));
    }

    public static x1 e(int i10, int i11, byte[] bArr) {
        d(i10, i10 + i11, bArr.length);
        byte[] bArr2 = new byte[i11];
        System.arraycopy(bArr, i10, bArr2, 0, i11);
        return new x1(bArr2);
    }

    public byte a(int i10) {
        return this.f13144l[i10];
    }

    public byte b(int i10) {
        return this.f13144l[i10];
    }

    public int c() {
        return this.f13144l.length;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof x1) && c() == ((x1) obj).c()) {
            if (c() == 0) {
                return true;
            }
            if (!(obj instanceof x1)) {
                return obj.equals(this);
            }
            x1 x1Var = (x1) obj;
            int i10 = this.f13143i;
            int i11 = x1Var.f13143i;
            if (i10 == 0 || i11 == 0 || i10 == i11) {
                int iC = c();
                if (iC > x1Var.c()) {
                    throw new IllegalArgumentException("Length too large: " + iC + c());
                }
                if (iC > x1Var.c()) {
                    throw new IllegalArgumentException(androidx.compose.foundation.c.n(iC, x1Var.c(), "Ran off end of other: 0, ", ", "));
                }
                byte[] bArr = x1Var.f13144l;
                int i12 = 0;
                int i13 = 0;
                while (i12 < iC) {
                    if (this.f13144l[i12] == bArr[i13]) {
                        i12++;
                        i13++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i10 = this.f13143i;
        if (i10 != 0) {
            return i10;
        }
        int iC = c();
        int i11 = iC;
        for (int i12 = 0; i12 < iC; i12++) {
            i11 = (i11 * 31) + this.f13144l[i12];
        }
        if (i11 == 0) {
            i11 = 1;
        }
        this.f13143i = i11;
        return i11;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new t1(this);
    }

    public final String toString() {
        String strConcat;
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        int iC = c();
        if (c() <= 50) {
            strConcat = s1.d(this);
        } else {
            int iD = d(0, 47, c());
            strConcat = s1.d(iD == 0 ? f13142m : new u1(this.f13144l, iD)).concat("...");
        }
        return a0.c.p(androidx.fragment.app.a2.q("<ByteString@", hexString, " size=", iC, " contents=\""), strConcat, "\">");
    }
}
