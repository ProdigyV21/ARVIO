package com.google.common.math;

import com.google.common.util.concurrent.r0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f14164a = 0;

    static {
        Math.log(2.0d);
    }

    public static boolean a(double d4) {
        if (r0.s(d4)) {
            return d4 == 0.0d || 52 - Long.numberOfTrailingZeros(r0.q(d4)) <= Math.getExponent(d4);
        }
        return false;
    }

    public static boolean b(double d4) {
        if (d4 > 0.0d && r0.s(d4)) {
            long jQ = r0.q(d4);
            if ((jQ & (jQ - 1)) == 0) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int c(double r6) {
        /*
            java.math.RoundingMode r0 = java.math.RoundingMode.CEILING
            r1 = 0
            int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            r2 = 0
            r3 = 1
            if (r1 <= 0) goto L12
            boolean r1 = com.google.common.util.concurrent.r0.s(r6)
            if (r1 == 0) goto L12
            r1 = r3
            goto L13
        L12:
            r1 = r2
        L13:
            java.lang.String r4 = "x must be positive and finite"
            ac.b.k(r1, r4)
            int r1 = java.lang.Math.getExponent(r6)
            int r4 = java.lang.Math.getExponent(r6)
            r5 = -1022(0xfffffffffffffc02, float:NaN)
            if (r4 < r5) goto L74
            int[] r4 = com.google.common.math.b.f14163a
            int r0 = r0.ordinal()
            r0 = r4[r0]
            switch(r0) {
                case 1: goto L69;
                case 2: goto L70;
                case 3: goto L62;
                case 4: goto L5a;
                case 5: goto L50;
                case 6: goto L36;
                case 7: goto L36;
                case 8: goto L36;
                default: goto L30;
            }
        L30:
            java.lang.AssertionError r6 = new java.lang.AssertionError
            r6.<init>()
            throw r6
        L36:
            long r6 = java.lang.Double.doubleToRawLongBits(r6)
            r4 = 4503599627370495(0xfffffffffffff, double:2.225073858507201E-308)
            long r6 = r6 & r4
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            long r6 = r6 | r4
            double r6 = java.lang.Double.longBitsToDouble(r6)
            double r6 = r6 * r6
            r4 = 4611686018427387904(0x4000000000000000, double:2.0)
            int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r6 <= 0) goto L70
            r2 = r3
            goto L70
        L50:
            if (r1 < 0) goto L53
            r2 = r3
        L53:
            boolean r6 = b(r6)
        L57:
            r6 = r6 ^ r3
            r2 = r2 & r6
            goto L70
        L5a:
            if (r1 >= 0) goto L5d
            r2 = r3
        L5d:
            boolean r6 = b(r6)
            goto L57
        L62:
            boolean r6 = b(r6)
            r2 = r6 ^ 1
            goto L70
        L69:
            boolean r6 = b(r6)
            kotlin.reflect.b0.h(r6)
        L70:
            if (r2 == 0) goto L73
            int r1 = r1 + r3
        L73:
            return r1
        L74:
            r0 = 4841369599423283200(0x4330000000000000, double:4.503599627370496E15)
            double r6 = r6 * r0
            int r6 = c(r6)
            int r6 = r6 + (-52)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.c.c(double):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long d(double r8, java.math.RoundingMode r10) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.c.d(double, java.math.RoundingMode):long");
    }
}
