package ob;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final xb.m f20894a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f20895b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f20896c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f20897d;

    static {
        xb.m mVar = new xb.m("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n".getBytes(kotlin.text.a.f19924a));
        mVar.f22775m = "PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n";
        f20894a = mVar;
        f20895b = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        f20896c = new String[64];
        String[] strArr = new String[256];
        for (int i10 = 0; i10 < 256; i10++) {
            strArr[i10] = ib.c.i("%8s", Integer.toBinaryString(i10)).replace(' ', '0');
        }
        f20897d = strArr;
        String[] strArr2 = f20896c;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        int i11 = iArr[0];
        strArr2[i11 | 8] = a0.c.p(new StringBuilder(), strArr2[i11], "|PADDED");
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i12 = 0; i12 < 3; i12++) {
            int i13 = iArr2[i12];
            int i14 = iArr[0];
            String[] strArr3 = f20896c;
            int i15 = i14 | i13;
            strArr3[i15] = strArr3[i14] + '|' + strArr3[i13];
            StringBuilder sb2 = new StringBuilder();
            sb2.append(strArr3[i14]);
            sb2.append('|');
            strArr3[i15 | 8] = a0.c.p(sb2, strArr3[i13], "|PADDED");
        }
        int length = f20896c.length;
        for (int i16 = 0; i16 < length; i16++) {
            String[] strArr4 = f20896c;
            if (strArr4[i16] == null) {
                strArr4[i16] = f20897d[i16];
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(int r10, int r11, int r12, int r13, boolean r14) {
        /*
            java.lang.String[] r0 = ob.e.f20895b
            int r1 = r0.length
            r2 = 1
            r3 = 0
            if (r12 >= r1) goto La
            r0 = r0[r12]
            goto L18
        La:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r12)
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r1[r3] = r0
            java.lang.String r0 = "0x%02x"
            java.lang.String r0 = ib.c.i(r0, r1)
        L18:
            r1 = 5
            r4 = 3
            r5 = 2
            r6 = 4
            if (r13 != 0) goto L21
            java.lang.String r12 = ""
            goto L67
        L21:
            java.lang.String[] r7 = ob.e.f20897d
            if (r12 == r5) goto L65
            if (r12 == r4) goto L65
            if (r12 == r6) goto L5d
            r8 = 6
            if (r12 == r8) goto L5d
            r8 = 7
            if (r12 == r8) goto L65
            r8 = 8
            if (r12 == r8) goto L65
            java.lang.String[] r8 = ob.e.f20896c
            int r9 = r8.length
            if (r13 >= r9) goto L3b
            r7 = r8[r13]
            goto L3d
        L3b:
            r7 = r7[r13]
        L3d:
            if (r12 != r1) goto L4c
            r8 = r13 & 4
            if (r8 == 0) goto L4c
            java.lang.String r12 = "HEADERS"
            java.lang.String r13 = "PUSH_PROMISE"
            java.lang.String r12 = kotlin.text.u.O(r7, r12, r13, r3)
            goto L67
        L4c:
            if (r12 != 0) goto L5b
            r12 = r13 & 32
            if (r12 == 0) goto L5b
            java.lang.String r12 = "PRIORITY"
            java.lang.String r13 = "COMPRESSED"
            java.lang.String r12 = kotlin.text.u.O(r7, r12, r13, r3)
            goto L67
        L5b:
            r12 = r7
            goto L67
        L5d:
            if (r13 != r2) goto L62
            java.lang.String r12 = "ACK"
            goto L67
        L62:
            r12 = r7[r13]
            goto L67
        L65:
            r12 = r7[r13]
        L67:
            if (r14 == 0) goto L6c
            java.lang.String r13 = "<<"
            goto L6e
        L6c:
            java.lang.String r13 = ">>"
        L6e:
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.Object[] r14 = new java.lang.Object[r1]
            r14[r3] = r13
            r14[r2] = r10
            r14[r5] = r11
            r14[r4] = r0
            r14[r6] = r12
            java.lang.String r10 = "%s 0x%08x %5d %-13s %s"
            java.lang.String r10 = ib.c.i(r10, r14)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: ob.e.a(int, int, int, int, boolean):java.lang.String");
    }
}
