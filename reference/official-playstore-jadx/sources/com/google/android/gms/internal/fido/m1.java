package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class m1 extends q1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i1 f13797i;

    public m1(i1 i1Var) {
        this.f13797i = i1Var;
    }

    @Override // com.google.android.gms.internal.fido.q1
    public final int a() {
        return q1.i((byte) 64);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        q1 q1Var = (q1) obj;
        int iA = q1Var.a();
        int i10 = q1.i((byte) 64);
        if (i10 != iA) {
            return i10 - q1Var.a();
        }
        i1 i1Var = ((m1) q1Var).f13797i;
        i1 i1Var2 = this.f13797i;
        byte[] bArr = i1Var2.f13786l;
        int length = bArr.length;
        byte[] bArr2 = i1Var.f13786l;
        if (length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        return d1.f13771a.compare(i1Var2.k(), i1Var.k());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && m1.class == obj.getClass()) {
            return this.f13797i.equals(((m1) obj).f13797i);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(q1.i((byte) 64)), this.f13797i});
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        x0 x0Var;
        int i10;
        boolean z;
        y0 y0Var = a1.f13747d;
        a1 y0Var2 = y0Var.f13750c;
        if (y0Var2 == null) {
            x0 x0Var2 = y0Var.f13748a;
            char[] cArr = x0Var2.f13840b;
            int i11 = 0;
            while (true) {
                if (i11 >= cArr.length) {
                    x0Var = x0Var2;
                    break;
                }
                char c10 = cArr[i11];
                if (c10 < 'a' || c10 > 'z') {
                    i11++;
                } else {
                    int i12 = 0;
                    while (true) {
                        if (i12 >= cArr.length) {
                            z = false;
                            break;
                        }
                        char c11 = cArr[i12];
                        if (c11 >= 'A' && c11 <= 'Z') {
                            z = true;
                            break;
                        }
                        i12++;
                    }
                    if (z) {
                        throw new IllegalStateException("Cannot call upperCase() on a mixed-case alphabet");
                    }
                    char[] cArr2 = new char[cArr.length];
                    for (int i13 = 0; i13 < cArr.length; i13++) {
                        char c12 = cArr[i13];
                        if (c12 >= 97 && c12 <= 122) {
                            c12 ^= 32;
                        }
                        cArr2[i13] = (char) c12;
                    }
                    x0Var = new x0(x0Var2.f13839a.concat(".upperCase()"), cArr2);
                    byte[] bArr = x0Var.f13845g;
                    if (x0Var2.f13846h && !x0Var.f13846h) {
                        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                        for (i10 = 65; i10 <= 90; i10++) {
                            int i14 = i10 | 32;
                            byte b10 = bArr[i10];
                            byte b11 = bArr[i14];
                            if (b10 == -1) {
                                bArrCopyOf[i10] = b11;
                            } else {
                                char c13 = (char) i10;
                                char c14 = (char) i14;
                                if (b11 != -1) {
                                    throw new IllegalStateException(b.d("Can't ignoreCase() since '%s' and '%s' encode different values", Character.valueOf(c13), Character.valueOf(c14)));
                                }
                                bArrCopyOf[i14] = b10;
                            }
                        }
                        x0Var = new x0(x0Var.f13839a.concat(".ignoreCase()"), x0Var.f13840b, bArrCopyOf, true);
                    }
                }
            }
            y0Var2 = x0Var == x0Var2 ? y0Var : new y0(x0Var);
            y0Var.f13750c = y0Var2;
        }
        byte[] bArrK = this.f13797i.k();
        return a0.c.l("h'", y0Var2.c(bArrK.length, bArrK), "'");
    }
}
