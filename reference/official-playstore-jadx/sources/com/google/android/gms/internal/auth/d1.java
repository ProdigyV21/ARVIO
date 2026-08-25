package com.google.android.gms.internal.auth;

import android.net.Uri;

/* JADX INFO: loaded from: classes4.dex */
public class d1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13016a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f13017b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f13018c;

    public d1(Uri uri, boolean z, boolean z5) {
        this.f13016a = 0;
        this.f13018c = uri;
        this.f13017b = z;
    }

    public com.typesafe.config.impl.c2 a(com.typesafe.config.impl.c2 c2Var, l6.k kVar, int i10) {
        com.typesafe.config.impl.c2 c2Var2 = com.typesafe.config.impl.m2.f14581a;
        if (!(c2Var instanceof com.typesafe.config.impl.j2) && !(c2Var instanceof com.typesafe.config.impl.k2) && !(c2Var instanceof com.typesafe.config.impl.l2)) {
            this.f13017b = false;
            return b(kVar, i10);
        }
        com.typesafe.config.impl.c2 c2VarB = b(kVar, i10);
        if (!this.f13017b) {
            this.f13017b = true;
        }
        return c2VarB;
    }

    public com.typesafe.config.impl.c2 b(l6.k kVar, int i10) {
        com.typesafe.config.impl.c2 g2Var;
        StringBuilder sb2 = (StringBuilder) this.f13018c;
        if (sb2.length() <= 0) {
            return null;
        }
        if (this.f13017b) {
            com.typesafe.config.impl.w1 w1VarB = ((com.typesafe.config.impl.w1) kVar).b(i10);
            String string = sb2.toString();
            com.typesafe.config.impl.c2 c2Var = com.typesafe.config.impl.m2.f14581a;
            g2Var = new com.typesafe.config.impl.k2(w1VarB, string);
        } else {
            com.typesafe.config.impl.w1 w1VarB2 = ((com.typesafe.config.impl.w1) kVar).b(i10);
            String string2 = sb2.toString();
            com.typesafe.config.impl.c2 c2Var2 = com.typesafe.config.impl.m2.f14581a;
            g2Var = new com.typesafe.config.impl.g2(w1VarB2, string2);
        }
        sb2.setLength(0);
        return g2Var;
    }

    public void c() {
        this.f13017b = false;
    }

    public void d(byte b10) {
        ((androidx.core.provider.j) this.f13018c).j(b10);
    }

    public void e(char c10) {
        androidx.core.provider.j jVar = (androidx.core.provider.j) this.f13018c;
        jVar.b(jVar.f2159b, 1);
        char[] cArr = (char[]) jVar.f2160c;
        int i10 = jVar.f2159b;
        jVar.f2159b = i10 + 1;
        cArr[i10] = c10;
    }

    public void f(int i10) {
        ((androidx.core.provider.j) this.f13018c).j(i10);
    }

    public void g(long j10) {
        ((androidx.core.provider.j) this.f13018c).j(j10);
    }

    public void h(String str) {
        ((androidx.core.provider.j) this.f13018c).i(str);
    }

    public void i(short s10) {
        ((androidx.core.provider.j) this.f13018c).j(s10);
    }

    public void j(String str) {
        byte b10;
        androidx.core.provider.j jVar = (androidx.core.provider.j) this.f13018c;
        jVar.b(jVar.f2159b, str.length() + 2);
        char[] cArr = (char[]) jVar.f2160c;
        int i10 = jVar.f2159b;
        int i11 = i10 + 1;
        cArr[i10] = '\"';
        int length = str.length();
        str.getChars(0, length, cArr, i11);
        int i12 = length + i11;
        int i13 = i11;
        while (i13 < i12) {
            char c10 = cArr[i13];
            byte[] bArr = eb.j0.f15158b;
            if (c10 < bArr.length && bArr[c10] != 0) {
                int length2 = str.length();
                for (int i14 = i13 - i11; i14 < length2; i14++) {
                    jVar.b(i13, 2);
                    char cCharAt = str.charAt(i14);
                    byte[] bArr2 = eb.j0.f15158b;
                    if (cCharAt >= bArr2.length || (b10 = bArr2[cCharAt]) == 0) {
                        int i15 = i13 + 1;
                        ((char[]) jVar.f2160c)[i13] = cCharAt;
                        i13 = i15;
                    } else {
                        if (b10 == 1) {
                            String str2 = eb.j0.f15157a[cCharAt];
                            jVar.b(i13, str2.length());
                            str2.getChars(0, str2.length(), (char[]) jVar.f2160c, i13);
                            int length3 = str2.length() + i13;
                            jVar.f2159b = length3;
                            i13 = length3;
                        } else {
                            char[] cArr2 = (char[]) jVar.f2160c;
                            cArr2[i13] = '\\';
                            cArr2[i13 + 1] = (char) b10;
                            i13 += 2;
                            jVar.f2159b = i13;
                        }
                    }
                }
                jVar.b(i13, 1);
                ((char[]) jVar.f2160c)[i13] = '\"';
                jVar.f2159b = i13 + 1;
                return;
            }
            i13++;
        }
        cArr[i12] = '\"';
        jVar.f2159b = i12 + 1;
    }

    public void m(long j10, String str) {
        new c1(this, str, Long.valueOf(j10), 0);
    }

    public c1 n(String str, boolean z) {
        return new c1(this, str, Boolean.valueOf(z), 1);
    }

    public String toString() {
        switch (this.f13016a) {
            case 4:
                return this.f13017b ? "FALL_THROUGH" : String.valueOf(this.f13018c);
            default:
                return super.toString();
        }
    }

    public d1(androidx.core.provider.j jVar) {
        this.f13016a = 2;
        this.f13018c = jVar;
        this.f13017b = true;
    }

    public d1() {
        this.f13016a = 1;
        this.f13018c = new StringBuilder();
        this.f13017b = false;
    }

    public d1(boolean z, String[] strArr) {
        this.f13016a = 3;
        this.f13017b = z;
        this.f13018c = strArr;
    }

    public d1(Object obj, boolean z) {
        this.f13016a = 4;
        this.f13018c = obj;
        this.f13017b = z;
    }

    public void k() {
    }

    public void l() {
    }
}
