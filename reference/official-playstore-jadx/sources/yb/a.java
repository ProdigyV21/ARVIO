package yb;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f23006a = "0123456789abcdef".getBytes(kotlin.text.a.f19924a);

    public static final String a(xb.j jVar, long j10) {
        if (j10 > 0) {
            long j11 = j10 - 1;
            if (jVar.x(j11) == 13) {
                String strQ0 = jVar.q0(j11, kotlin.text.a.f19924a);
                jVar.skip(2L);
                return strQ0;
            }
        }
        String strQ02 = jVar.q0(j10, kotlin.text.a.f19924a);
        jVar.skip(1L);
        return strQ02;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0051, code lost:
    
        if (r18 == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0053, code lost:
    
        return -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0073, code lost:
    
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int b(xb.j r16, xb.e0 r17, boolean r18) {
        /*
            r0 = r16
            xb.j0 r0 = r0.f22758i
            r1 = -1
            if (r0 != 0) goto Lb
            if (r18 == 0) goto La
            goto L53
        La:
            return r1
        Lb:
            byte[] r2 = r0.f22760a
            int r3 = r0.f22761b
            int r4 = r0.f22762c
            r5 = r17
            int[] r5 = r5.f22732l
            r6 = 0
            r8 = r0
            r9 = r1
            r7 = r6
        L19:
            int r10 = r7 + 1
            r11 = r5[r7]
            int r7 = r7 + 2
            r10 = r5[r10]
            if (r10 == r1) goto L24
            r9 = r10
        L24:
            if (r8 != 0) goto L27
            goto L51
        L27:
            r10 = 0
            if (r11 >= 0) goto L69
            int r11 = r11 * (-1)
            int r12 = r11 + r7
        L2e:
            int r11 = r3 + 1
            r3 = r2[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r13 = r7 + 1
            r7 = r5[r7]
            if (r3 == r7) goto L3b
            goto L73
        L3b:
            if (r13 != r12) goto L3f
            r3 = 1
            goto L40
        L3f:
            r3 = r6
        L40:
            if (r11 != r4) goto L59
            xb.j0 r2 = r8.f22765f
            int r4 = r2.f22761b
            byte[] r7 = r2.f22760a
            int r8 = r2.f22762c
            if (r2 != r0) goto L55
            if (r3 == 0) goto L51
            r2 = r7
            r7 = r10
            goto L5c
        L51:
            if (r18 == 0) goto L73
        L53:
            r0 = -2
            return r0
        L55:
            r15 = r7
            r7 = r2
            r2 = r15
            goto L5c
        L59:
            r7 = r8
            r8 = r4
            r4 = r11
        L5c:
            if (r3 == 0) goto L64
            r3 = r5[r13]
            r15 = r8
            r8 = r7
            r7 = r15
            goto L92
        L64:
            r3 = r4
            r4 = r8
            r8 = r7
            r7 = r13
            goto L2e
        L69:
            int r12 = r3 + 1
            r3 = r2[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r13 = r7 + r11
        L71:
            if (r7 != r13) goto L74
        L73:
            return r9
        L74:
            r14 = r5[r7]
            if (r3 != r14) goto L9c
            int r7 = r7 + r11
            r3 = r5[r7]
            if (r12 != r4) goto L90
            xb.j0 r8 = r8.f22765f
            int r2 = r8.f22761b
            byte[] r4 = r8.f22760a
            int r7 = r8.f22762c
            if (r8 != r0) goto L8c
            r8 = r4
            r4 = r2
            r2 = r8
            r8 = r10
            goto L92
        L8c:
            r15 = r4
            r4 = r2
            r2 = r15
            goto L92
        L90:
            r7 = r4
            r4 = r12
        L92:
            if (r3 < 0) goto L95
            return r3
        L95:
            int r3 = -r3
            r15 = r7
            r7 = r3
            r3 = r4
            r4 = r15
            goto L19
        L9c:
            int r7 = r7 + 1
            goto L71
        */
        throw new UnsupportedOperationException("Method not decompiled: yb.a.b(xb.j, xb.e0, boolean):int");
    }
}
