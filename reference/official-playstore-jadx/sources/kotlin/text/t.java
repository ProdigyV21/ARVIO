package kotlin.text;

/* JADX INFO: loaded from: classes5.dex */
public abstract class t extends s {
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b3 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean E(java.lang.String r8) {
        /*
            int r0 = r8.length()
            r1 = 0
            if (r0 != 0) goto L9
            goto Lb4
        L9:
            char r0 = r8.charAt(r1)
            r2 = 43
            r3 = 45
            r4 = 1
            if (r0 == r3) goto L1d
            char r0 = r8.charAt(r1)
            if (r0 != r2) goto L1b
            goto L1d
        L1b:
            r0 = r1
            goto L1e
        L1d:
            r0 = r4
        L1e:
            r5 = r0
        L1f:
            int r6 = r8.length()
            if (r5 >= r6) goto L32
            char r6 = r8.charAt(r5)
            boolean r6 = java.lang.Character.isDigit(r6)
            if (r6 == 0) goto L32
            int r5 = r5 + 1
            goto L1f
        L32:
            int r6 = r8.length()
            if (r5 != r6) goto L3d
            int r5 = r5 - r0
            if (r5 <= 0) goto Lb4
            goto Lb3
        L3d:
            char r6 = r8.charAt(r5)
            r7 = 46
            if (r6 != r7) goto L64
            int r5 = r5 + 1
            int r6 = r8.length()
            if (r5 != r6) goto L51
            int r5 = r5 - r0
            if (r5 <= r4) goto Lb4
            goto Lb3
        L51:
            int r0 = r8.length()
            if (r5 >= r0) goto L64
            char r0 = r8.charAt(r5)
            boolean r0 = java.lang.Character.isDigit(r0)
            if (r0 == 0) goto L64
            int r5 = r5 + 1
            goto L51
        L64:
            int r0 = r8.length()
            if (r5 != r0) goto L6b
            goto Lb3
        L6b:
            char r0 = r8.charAt(r5)
            r6 = 101(0x65, float:1.42E-43)
            if (r0 == r6) goto L7c
            char r0 = r8.charAt(r5)
            r6 = 69
            if (r0 == r6) goto L7c
            goto Lb4
        L7c:
            int r0 = r5 + 1
            int r6 = r8.length()
            if (r0 != r6) goto L85
            goto Lb4
        L85:
            char r6 = r8.charAt(r0)
            if (r6 == r2) goto L91
            char r2 = r8.charAt(r0)
            if (r2 != r3) goto L93
        L91:
            int r0 = r5 + 2
        L93:
            int r2 = r8.length()
            if (r0 != r2) goto L9a
            goto Lb4
        L9a:
            int r2 = r8.length()
            if (r0 >= r2) goto Lad
            char r2 = r8.charAt(r0)
            boolean r2 = java.lang.Character.isDigit(r2)
            if (r2 == 0) goto Lad
            int r0 = r0 + 1
            goto L9a
        Lad:
            int r8 = r8.length()
            if (r0 != r8) goto Lb4
        Lb3:
            return r4
        Lb4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.t.E(java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean F(java.lang.String r19) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.t.F(java.lang.String):boolean");
    }

    public static Double G(String str) {
        try {
            if (F(str)) {
                return Double.valueOf(Double.parseDouble(str));
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }

    public static Float H(String str) {
        try {
            if (F(str)) {
                return Float.valueOf(Float.parseFloat(str));
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }
}
