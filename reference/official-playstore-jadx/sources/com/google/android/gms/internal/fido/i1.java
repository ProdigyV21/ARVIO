package com.google.android.gms.internal.fido;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class i1 implements Iterable, Serializable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final i1 f13784m = new i1(j1.f13788a);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f13785i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final byte[] f13786l;

    static {
        int i10 = e1.f13776a;
    }

    public i1(byte[] bArr) {
        bArr.getClass();
        this.f13786l = bArr;
    }

    public static int g(int i10, int i11, int i12) {
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

    public static i1 h(int i10, byte[] bArr) {
        g(0, i10, bArr.length);
        byte[] bArr2 = new byte[i10];
        System.arraycopy(bArr, 0, bArr2, 0, i10);
        return new i1(bArr2);
    }

    public byte a(int i10) {
        return this.f13786l[i10];
    }

    public byte b(int i10) {
        return this.f13786l[i10];
    }

    public int c() {
        return 0;
    }

    public int d() {
        return this.f13786l.length;
    }

    public void e(int i10, byte[] bArr) {
        System.arraycopy(this.f13786l, 0, bArr, 0, i10);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof i1) || d() != ((i1) obj).d()) {
            return false;
        }
        if (d() == 0) {
            return true;
        }
        if (!(obj instanceof i1)) {
            return obj.equals(this);
        }
        i1 i1Var = (i1) obj;
        int i10 = this.f13785i;
        int i11 = i1Var.f13785i;
        if (i10 != 0 && i11 != 0 && i10 != i11) {
            return false;
        }
        int iD = d();
        if (iD > i1Var.d()) {
            throw new IllegalArgumentException("Length too large: " + iD + d());
        }
        if (iD > i1Var.d()) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(iD, i1Var.d(), "Ran off end of other: 0, ", ", "));
        }
        byte[] bArr = i1Var.f13786l;
        int iC = c() + iD;
        int iC2 = c();
        int iC3 = i1Var.c();
        while (iC2 < iC) {
            if (this.f13786l[iC2] != bArr[iC3]) {
                return false;
            }
            iC2++;
            iC3++;
        }
        return true;
    }

    public final ByteArrayInputStream f() {
        return new ByteArrayInputStream(this.f13786l, c(), d());
    }

    public final int hashCode() {
        int i10 = this.f13785i;
        if (i10 != 0) {
            return i10;
        }
        int iD = d();
        int iC = c();
        byte[] bArr = j1.f13788a;
        int i11 = iD;
        for (int i12 = iC; i12 < iC + iD; i12++) {
            i11 = (i11 * 31) + this.f13786l[i12];
        }
        if (i11 == 0) {
            i11 = 1;
        }
        this.f13785i = i11;
        return i11;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new f1(this);
    }

    public final byte[] k() {
        int iD = d();
        if (iD == 0) {
            return j1.f13788a;
        }
        byte[] bArr = new byte[iD];
        e(iD, bArr);
        return bArr;
    }

    public final String toString() {
        i1 g1Var;
        String strConcat;
        Locale locale = Locale.ROOT;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        int iD = d();
        if (d() <= 50) {
            strConcat = b.c(this);
        } else {
            int iG = g(0, 47, d());
            if (iG == 0) {
                g1Var = f13784m;
            } else {
                g1Var = new g1(this.f13786l, c(), iG);
            }
            strConcat = b.c(g1Var).concat("...");
        }
        return a0.c.p(androidx.fragment.app.a2.q("<ByteString@", hexString, " size=", iD, " contents=\""), strConcat, "\">");
    }
}
