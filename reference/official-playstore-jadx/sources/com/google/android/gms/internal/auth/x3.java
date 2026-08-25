package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class x3 extends d2 implements x2 {
    private static final x3 zzb;
    private g2 zzd = g3.f13035n;

    static {
        x3 x3Var = new x3();
        zzb = x3Var;
        d2.e(x3Var);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0044, code lost:
    
        if (r7 != false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.internal.auth.x3 j(byte[] r7) throws com.google.android.gms.internal.auth.zzfb {
        /*
            com.google.android.gms.internal.auth.x3 r0 = com.google.android.gms.internal.auth.x3.zzb
            int r5 = r7.length
            com.google.android.gms.internal.auth.y1 r1 = com.google.android.gms.internal.auth.y1.f13148b
            com.google.android.gms.internal.auth.d2 r2 = r0.b()
            com.google.android.gms.internal.auth.f3 r0 = com.google.android.gms.internal.auth.f3.f13029c     // Catch: java.lang.IndexOutOfBoundsException -> L5a java.io.IOException -> L5f com.google.android.gms.internal.auth.zzgy -> L7a com.google.android.gms.internal.auth.zzfb -> L86
            java.lang.Class r3 = r2.getClass()     // Catch: java.lang.IndexOutOfBoundsException -> L5a java.io.IOException -> L5f com.google.android.gms.internal.auth.zzgy -> L7a com.google.android.gms.internal.auth.zzfb -> L86
            com.google.android.gms.internal.auth.i3 r3 = r0.a(r3)     // Catch: java.lang.IndexOutOfBoundsException -> L5a java.io.IOException -> L5f com.google.android.gms.internal.auth.zzgy -> L7a com.google.android.gms.internal.auth.zzfb -> L86
            a3.d r6 = new a3.d     // Catch: java.lang.IndexOutOfBoundsException -> L5a java.io.IOException -> L5f com.google.android.gms.internal.auth.zzgy -> L7a com.google.android.gms.internal.auth.zzfb -> L86
            r6.<init>()     // Catch: java.lang.IndexOutOfBoundsException -> L5a java.io.IOException -> L5f com.google.android.gms.internal.auth.zzgy -> L7a com.google.android.gms.internal.auth.zzfb -> L86
            r1.getClass()     // Catch: java.lang.IndexOutOfBoundsException -> L5a java.io.IOException -> L5f com.google.android.gms.internal.auth.zzgy -> L7a com.google.android.gms.internal.auth.zzfb -> L86
            r4 = 0
            r1 = r3
            r3 = r7
            r1.f(r2, r3, r4, r5, r6)     // Catch: java.lang.IndexOutOfBoundsException -> L5a java.io.IOException -> L5f com.google.android.gms.internal.auth.zzgy -> L7a com.google.android.gms.internal.auth.zzfb -> L86
            r1.a(r2)     // Catch: java.lang.IndexOutOfBoundsException -> L5a java.io.IOException -> L5f com.google.android.gms.internal.auth.zzgy -> L7a com.google.android.gms.internal.auth.zzfb -> L86
            r7 = 1
            java.lang.Object r1 = r2.h(r7)
            java.lang.Byte r1 = (java.lang.Byte) r1
            byte r1 = r1.byteValue()
            if (r1 != r7) goto L32
            goto L46
        L32:
            if (r1 == 0) goto L49
            java.lang.Class r7 = r2.getClass()
            com.google.android.gms.internal.auth.i3 r7 = r0.a(r7)
            boolean r7 = r7.e(r2)
            r0 = 2
            r2.h(r0)
            if (r7 == 0) goto L49
        L46:
            com.google.android.gms.internal.auth.x3 r2 = (com.google.android.gms.internal.auth.x3) r2
            return r2
        L49:
            com.google.android.gms.internal.auth.zzgy r7 = new com.google.android.gms.internal.auth.zzgy
            java.lang.String r0 = "Message was missing required fields.  (Lite runtime could not determine which fields were missing)."
            r7.<init>(r0)
            com.google.android.gms.internal.auth.zzfb r0 = new com.google.android.gms.internal.auth.zzfb
            java.lang.String r7 = r7.getMessage()
            r0.<init>(r7)
            throw r0
        L5a:
            com.google.android.gms.internal.auth.zzfb r7 = com.google.android.gms.internal.auth.zzfb.c()
            throw r7
        L5f:
            r0 = move-exception
            r7 = r0
            java.lang.Throwable r0 = r7.getCause()
            boolean r0 = r0 instanceof com.google.android.gms.internal.auth.zzfb
            if (r0 == 0) goto L70
            java.lang.Throwable r7 = r7.getCause()
            com.google.android.gms.internal.auth.zzfb r7 = (com.google.android.gms.internal.auth.zzfb) r7
            throw r7
        L70:
            com.google.android.gms.internal.auth.zzfb r0 = new com.google.android.gms.internal.auth.zzfb
            java.lang.String r1 = r7.getMessage()
            r0.<init>(r1, r7)
            throw r0
        L7a:
            r0 = move-exception
            r7 = r0
            com.google.android.gms.internal.auth.zzfb r0 = new com.google.android.gms.internal.auth.zzfb
            java.lang.String r7 = r7.getMessage()
            r0.<init>(r7)
            throw r0
        L86:
            r0 = move-exception
            r7 = r0
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.x3.j(byte[]):com.google.android.gms.internal.auth.x3");
    }

    @Override // com.google.android.gms.internal.auth.d2
    public final Object h(int i10) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new h3(zzb, new Object[]{"zzd"});
        }
        if (i11 == 3) {
            return new x3();
        }
        if (i11 == 4) {
            return new w3();
        }
        if (i11 != 5) {
            return null;
        }
        return zzb;
    }

    public final g2 k() {
        return this.zzd;
    }
}
