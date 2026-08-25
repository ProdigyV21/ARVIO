package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class x0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13839a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final char[] f13840b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f13841c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f13842d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f13843e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f13844f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final byte[] f13845g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f13846h;

    /* JADX WARN: Illegal instructions before constructor call */
    public x0(String str, char[] cArr) {
        byte[] bArr = new byte[128];
        Arrays.fill(bArr, (byte) -1);
        for (int i10 = 0; i10 < cArr.length; i10++) {
            char c10 = cArr[i10];
            if (!(c10 < 128)) {
                throw new IllegalArgumentException(b.d("Non-ASCII character: %s", Character.valueOf(c10)));
            }
            if (!(bArr[c10] == -1)) {
                throw new IllegalArgumentException(b.d("Duplicate character: %s", Character.valueOf(c10)));
            }
            bArr[c10] = (byte) i10;
        }
        this(str, cArr, bArr, false);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof x0)) {
            return false;
        }
        x0 x0Var = (x0) obj;
        return this.f13846h == x0Var.f13846h && Arrays.equals(this.f13840b, x0Var.f13840b);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f13840b) + (true != this.f13846h ? 1237 : 1231);
    }

    public final String toString() {
        return this.f13839a;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0061 A[LOOP:0: B:16:0x005d->B:18:0x0061, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public x0(java.lang.String r4, char[] r5, byte[] r6, boolean r7) {
        /*
            r3 = this;
            r3.<init>()
            r3.f13839a = r4
            r5.getClass()
            r3.f13840b = r5
            int r4 = r5.length     // Catch: java.lang.ArithmeticException -> L73
            java.math.RoundingMode r0 = java.math.RoundingMode.UNNECESSARY     // Catch: java.lang.ArithmeticException -> L73
            if (r4 <= 0) goto L7d
            int[] r1 = com.google.android.gms.internal.fido.b1.f13758a     // Catch: java.lang.ArithmeticException -> L73
            int r0 = r0.ordinal()     // Catch: java.lang.ArithmeticException -> L73
            r0 = r1[r0]     // Catch: java.lang.ArithmeticException -> L73
            switch(r0) {
                case 1: goto L38;
                case 2: goto L3d;
                case 3: goto L3d;
                case 4: goto L2f;
                case 5: goto L2f;
                case 6: goto L20;
                case 7: goto L20;
                case 8: goto L20;
                default: goto L1a;
            }     // Catch: java.lang.ArithmeticException -> L73
        L1a:
            java.lang.AssertionError r4 = new java.lang.AssertionError     // Catch: java.lang.ArithmeticException -> L73
            r4.<init>()     // Catch: java.lang.ArithmeticException -> L73
            throw r4     // Catch: java.lang.ArithmeticException -> L73
        L20:
            int r0 = java.lang.Integer.numberOfLeadingZeros(r4)     // Catch: java.lang.ArithmeticException -> L73
            r1 = -1257966797(0xffffffffb504f333, float:-4.9527733E-7)
            int r1 = r1 >>> r0
            int r0 = 31 - r0
            int r1 = r1 - r4
            int r1 = r1 >>> 31
            int r0 = r0 + r1
            goto L43
        L2f:
            int r0 = r4 + (-1)
            int r0 = java.lang.Integer.numberOfLeadingZeros(r0)     // Catch: java.lang.ArithmeticException -> L73
            int r0 = 32 - r0
            goto L43
        L38:
            int r0 = r4 + (-1)
            r0 = r0 & r4
            if (r0 != 0) goto L75
        L3d:
            int r0 = java.lang.Integer.numberOfLeadingZeros(r4)     // Catch: java.lang.ArithmeticException -> L73
            int r0 = 31 - r0
        L43:
            r3.f13842d = r0     // Catch: java.lang.ArithmeticException -> L73
            int r5 = java.lang.Integer.numberOfTrailingZeros(r0)
            int r1 = 3 - r5
            r2 = 1
            int r1 = r2 << r1
            r3.f13843e = r1
            int r5 = r0 >> r5
            r3.f13844f = r5
            int r4 = r4 + (-1)
            r3.f13841c = r4
            r3.f13845g = r6
            boolean[] r4 = new boolean[r1]
            r5 = 0
        L5d:
            int r6 = r3.f13844f
            if (r5 >= r6) goto L70
            int r6 = r5 * 8
            int r0 = r3.f13842d
            java.math.RoundingMode r1 = java.math.RoundingMode.CEILING
            int r6 = com.google.android.gms.internal.fido.b.a(r6, r0)
            r4[r6] = r2
            int r5 = r5 + 1
            goto L5d
        L70:
            r3.f13846h = r7
            return
        L73:
            r4 = move-exception
            goto L86
        L75:
            java.lang.ArithmeticException r4 = new java.lang.ArithmeticException     // Catch: java.lang.ArithmeticException -> L73
            java.lang.String r6 = "mode was UNNECESSARY, but rounding was necessary"
            r4.<init>(r6)     // Catch: java.lang.ArithmeticException -> L73
            throw r4     // Catch: java.lang.ArithmeticException -> L73
        L7d:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: java.lang.ArithmeticException -> L73
            java.lang.String r6 = "x (0) must be > 0"
            r4.<init>(r6)     // Catch: java.lang.ArithmeticException -> L73
            throw r4     // Catch: java.lang.ArithmeticException -> L73
        L86:
            int r5 = r5.length
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Illegal alphabet length "
            java.lang.String r5 = a0.c.i(r5, r7)
            r6.<init>(r5, r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.x0.<init>(java.lang.String, char[], byte[], boolean):void");
    }
}
