package com.caverock.androidsvg;

import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class n0 implements o0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f12380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Serializable f12381c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f12379a = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f12382d = new r();

    public n0(String str) {
        this.f12380b = 0;
        String strTrim = str.trim();
        this.f12381c = strTrim;
        this.f12380b = strTrim.length();
    }

    public static boolean l(int i10) {
        return i10 == 32 || i10 == 10 || i10 == 13 || i10 == 9;
    }

    @Override // com.caverock.androidsvg.o0
    public void a(float f10, float f11, float f12, float f13) {
        c((byte) 3);
        i(4);
        float[] fArr = (float[]) this.f12382d;
        int i10 = this.f12380b;
        int i11 = i10 + 1;
        this.f12380b = i11;
        fArr[i10] = f10;
        int i12 = i10 + 2;
        this.f12380b = i12;
        fArr[i11] = f11;
        int i13 = i10 + 3;
        this.f12380b = i13;
        fArr[i12] = f12;
        this.f12380b = i10 + 4;
        fArr[i13] = f13;
    }

    @Override // com.caverock.androidsvg.o0
    public void b(float f10, float f11, float f12, boolean z, boolean z5, float f13, float f14) {
        c((byte) ((z ? 2 : 0) | 4 | (z5 ? 1 : 0)));
        i(5);
        float[] fArr = (float[]) this.f12382d;
        int i10 = this.f12380b;
        int i11 = i10 + 1;
        this.f12380b = i11;
        fArr[i10] = f10;
        int i12 = i10 + 2;
        this.f12380b = i12;
        fArr[i11] = f11;
        int i13 = i10 + 3;
        this.f12380b = i13;
        fArr[i12] = f12;
        int i14 = i10 + 4;
        this.f12380b = i14;
        fArr[i13] = f13;
        this.f12380b = i10 + 5;
        fArr[i14] = f14;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [byte[], java.io.Serializable, java.lang.Object] */
    public void c(byte b10) {
        int i10 = this.f12379a;
        byte[] bArr = (byte[]) this.f12381c;
        if (i10 == bArr.length) {
            ?? r02 = new byte[bArr.length * 2];
            System.arraycopy(bArr, 0, r02, 0, bArr.length);
            this.f12381c = r02;
        }
        byte[] bArr2 = (byte[]) this.f12381c;
        int i11 = this.f12379a;
        this.f12379a = i11 + 1;
        bArr2[i11] = b10;
    }

    @Override // com.caverock.androidsvg.o0
    public void close() {
        c((byte) 8);
    }

    @Override // com.caverock.androidsvg.o0
    public void cubicTo(float f10, float f11, float f12, float f13, float f14, float f15) {
        c((byte) 2);
        i(6);
        float[] fArr = (float[]) this.f12382d;
        int i10 = this.f12380b;
        int i11 = i10 + 1;
        this.f12380b = i11;
        fArr[i10] = f10;
        int i12 = i10 + 2;
        this.f12380b = i12;
        fArr[i11] = f11;
        int i13 = i10 + 3;
        this.f12380b = i13;
        fArr[i12] = f12;
        int i14 = i10 + 4;
        this.f12380b = i14;
        fArr[i13] = f13;
        int i15 = i10 + 5;
        this.f12380b = i15;
        fArr[i14] = f14;
        this.f12380b = i10 + 6;
        fArr[i15] = f15;
    }

    public int d() {
        int i10 = this.f12379a;
        int i11 = this.f12380b;
        if (i10 == i11) {
            return -1;
        }
        int i12 = i10 + 1;
        this.f12379a = i12;
        if (i12 < i11) {
            return ((String) this.f12381c).charAt(i12);
        }
        return -1;
    }

    public Boolean e(Object obj) {
        if (obj == null) {
            return null;
        }
        u();
        int i10 = this.f12379a;
        if (i10 == this.f12380b) {
            return null;
        }
        char cCharAt = ((String) this.f12381c).charAt(i10);
        if (cCharAt != '0' && cCharAt != '1') {
            return null;
        }
        this.f12379a++;
        return Boolean.valueOf(cCharAt == '1');
    }

    public float f(float f10) {
        if (Float.isNaN(f10)) {
            return Float.NaN;
        }
        u();
        return n();
    }

    public boolean g(char c10) {
        int i10 = this.f12379a;
        boolean z = i10 < this.f12380b && ((String) this.f12381c).charAt(i10) == c10;
        if (z) {
            this.f12379a++;
        }
        return z;
    }

    public boolean h(String str) {
        int length = str.length();
        int i10 = this.f12379a;
        boolean z = i10 <= this.f12380b - length && ((String) this.f12381c).substring(i10, i10 + length).equals(str);
        if (z) {
            this.f12379a += length;
        }
        return z;
    }

    public void i(int i10) {
        float[] fArr = (float[]) this.f12382d;
        if (fArr.length < this.f12380b + i10) {
            float[] fArr2 = new float[fArr.length * 2];
            System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
            this.f12382d = fArr2;
        }
    }

    public boolean j() {
        return this.f12379a == this.f12380b;
    }

    public void k(o0 o0Var) {
        int i10 = 0;
        for (int i11 = 0; i11 < this.f12379a; i11++) {
            byte b10 = ((byte[]) this.f12381c)[i11];
            if (b10 == 0) {
                float[] fArr = (float[]) this.f12382d;
                int i12 = i10 + 1;
                float f10 = fArr[i10];
                i10 += 2;
                o0Var.moveTo(f10, fArr[i12]);
            } else if (b10 == 1) {
                float[] fArr2 = (float[]) this.f12382d;
                int i13 = i10 + 1;
                float f11 = fArr2[i10];
                i10 += 2;
                o0Var.lineTo(f11, fArr2[i13]);
            } else if (b10 == 2) {
                float[] fArr3 = (float[]) this.f12382d;
                o0Var.cubicTo(fArr3[i10], fArr3[i10 + 1], fArr3[i10 + 2], fArr3[i10 + 3], fArr3[i10 + 4], fArr3[i10 + 5]);
                i10 += 6;
            } else if (b10 == 3) {
                float[] fArr4 = (float[]) this.f12382d;
                float f12 = fArr4[i10];
                float f13 = fArr4[i10 + 1];
                int i14 = i10 + 3;
                float f14 = fArr4[i10 + 2];
                i10 += 4;
                o0Var.a(f12, f13, f14, fArr4[i14]);
            } else if (b10 != 8) {
                boolean z = (b10 & 2) != 0;
                boolean z5 = (b10 & 1) != 0;
                float[] fArr5 = (float[]) this.f12382d;
                o0Var.b(fArr5[i10], fArr5[i10 + 1], fArr5[i10 + 2], z, z5, fArr5[i10 + 3], fArr5[i10 + 4]);
                i10 += 5;
            } else {
                o0Var.close();
            }
        }
    }

    @Override // com.caverock.androidsvg.o0
    public void lineTo(float f10, float f11) {
        c((byte) 1);
        i(2);
        float[] fArr = (float[]) this.f12382d;
        int i10 = this.f12380b;
        int i11 = i10 + 1;
        this.f12380b = i11;
        fArr[i10] = f10;
        this.f12380b = i10 + 2;
        fArr[i11] = f11;
    }

    public Integer m() {
        int i10 = this.f12379a;
        if (i10 == this.f12380b) {
            return null;
        }
        String str = (String) this.f12381c;
        this.f12379a = i10 + 1;
        return Integer.valueOf(str.charAt(i10));
    }

    @Override // com.caverock.androidsvg.o0
    public void moveTo(float f10, float f11) {
        c((byte) 0);
        i(2);
        float[] fArr = (float[]) this.f12382d;
        int i10 = this.f12380b;
        int i11 = i10 + 1;
        this.f12380b = i11;
        fArr[i10] = f10;
        this.f12380b = i10 + 2;
        fArr[i11] = f11;
    }

    public float n() {
        r rVar = (r) this.f12382d;
        float fA = rVar.a(this.f12379a, this.f12380b, (String) this.f12381c);
        if (!Float.isNaN(fA)) {
            this.f12379a = rVar.f12419a;
        }
        return fA;
    }

    public g0 o() {
        float fN = n();
        if (Float.isNaN(fN)) {
            return null;
        }
        int iS = s();
        return iS == 0 ? new g0(fN, 1) : new g0(fN, iS);
    }

    public String p() {
        String str = (String) this.f12381c;
        if (j()) {
            return null;
        }
        int i10 = this.f12379a;
        char cCharAt = str.charAt(i10);
        if (cCharAt != '\'' && cCharAt != '\"') {
            return null;
        }
        int iD = d();
        while (iD != -1 && iD != cCharAt) {
            iD = d();
        }
        if (iD == -1) {
            this.f12379a = i10;
            return null;
        }
        int i11 = this.f12379a;
        this.f12379a = i11 + 1;
        return str.substring(i10 + 1, i11);
    }

    public String q() {
        return r(' ', false);
    }

    public String r(char c10, boolean z) {
        String str = (String) this.f12381c;
        if (j()) {
            return null;
        }
        char cCharAt = str.charAt(this.f12379a);
        if ((!z && l(cCharAt)) || cCharAt == c10) {
            return null;
        }
        int i10 = this.f12379a;
        int iD = d();
        while (iD != -1 && iD != c10 && (z || !l(iD))) {
            iD = d();
        }
        return str.substring(i10, this.f12379a);
    }

    public int s() {
        String str = (String) this.f12381c;
        if (j()) {
            return 0;
        }
        if (str.charAt(this.f12379a) == '%') {
            this.f12379a++;
            return 9;
        }
        int i10 = this.f12379a;
        if (i10 > this.f12380b - 2) {
            return 0;
        }
        try {
            int iZ = com.arflix.tv.data.repository.g.z(str.substring(i10, i10 + 2).toLowerCase(Locale.US));
            this.f12379a += 2;
            return iZ;
        } catch (IllegalArgumentException unused) {
            return 0;
        }
    }

    public float t() {
        u();
        r rVar = (r) this.f12382d;
        float fA = rVar.a(this.f12379a, this.f12380b, (String) this.f12381c);
        if (!Float.isNaN(fA)) {
            this.f12379a = rVar.f12419a;
        }
        return fA;
    }

    public boolean u() {
        v();
        int i10 = this.f12379a;
        if (i10 == this.f12380b || ((String) this.f12381c).charAt(i10) != ',') {
            return false;
        }
        this.f12379a++;
        v();
        return true;
    }

    public void v() {
        while (true) {
            int i10 = this.f12379a;
            if (i10 >= this.f12380b || !l(((String) this.f12381c).charAt(i10))) {
                return;
            } else {
                this.f12379a++;
            }
        }
    }
}
