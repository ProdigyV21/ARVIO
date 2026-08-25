package com.google.android.gms.internal.cast;

import android.os.Bundle;
import com.google.android.gms.cast.internal.Logger;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class l5 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Logger f13411d = new Logger("ApplicationAnalyticsUtils");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f13412e = "22.2.0";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13413a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f13414b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f13415c;

    public l5(Bundle bundle, String str) {
        this.f13413a = str;
        this.f13414b = u0.n(bundle, "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_ERROR");
        this.f13415c = u0.n(bundle, "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_CHANGE_REASON");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.cast.c6 a(com.google.android.gms.internal.cast.j5 r5, int r6) {
        /*
            r4 = this;
            com.google.android.gms.internal.cast.b6 r5 = r4.b(r5)
            com.google.android.gms.internal.cast.w5 r0 = r5.f()
            com.google.android.gms.internal.cast.v5 r0 = com.google.android.gms.internal.cast.w5.o(r0)
            java.util.Map r1 = r4.f13415c
            if (r1 == 0) goto L29
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)
            boolean r3 = r1.containsKey(r2)
            if (r3 != 0) goto L1b
            goto L29
        L1b:
            java.lang.Object r1 = r1.get(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            com.google.android.gms.common.internal.t.i(r1)
            int r1 = r1.intValue()
            goto L2b
        L29:
            int r1 = r6 + 10000
        L2b:
            r0.c()
            com.google.android.gms.internal.cast.pa r2 = r0.f13530l
            com.google.android.gms.internal.cast.w5 r2 = (com.google.android.gms.internal.cast.w5) r2
            r2.t(r1)
            java.util.Map r1 = r4.f13414b
            if (r1 == 0) goto L52
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)
            boolean r3 = r1.containsKey(r2)
            if (r3 != 0) goto L44
            goto L52
        L44:
            java.lang.Object r6 = r1.get(r2)
            java.lang.Integer r6 = (java.lang.Integer) r6
            com.google.android.gms.common.internal.t.i(r6)
            int r6 = r6.intValue()
            goto L54
        L52:
            int r6 = r6 + 10000
        L54:
            r0.c()
            com.google.android.gms.internal.cast.pa r1 = r0.f13530l
            com.google.android.gms.internal.cast.w5 r1 = (com.google.android.gms.internal.cast.w5) r1
            r1.u(r6)
            com.google.android.gms.internal.cast.pa r6 = r0.e()
            com.google.android.gms.internal.cast.w5 r6 = (com.google.android.gms.internal.cast.w5) r6
            r5.g(r6)
            com.google.android.gms.internal.cast.pa r5 = r5.e()
            com.google.android.gms.internal.cast.c6 r5 = (com.google.android.gms.internal.cast.c6) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.l5.a(com.google.android.gms.internal.cast.j5, int):com.google.android.gms.internal.cast.c6");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00cb A[PHI: r5
      0x00cb: PHI (r5v7 int) = (r5v6 int), (r5v8 int) binds: [B:26:0x00bd, B:30:0x00c3] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.cast.b6 b(com.google.android.gms.internal.cast.j5 r9) {
        /*
            Method dump skipped, instruction units count: 442
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.l5.b(com.google.android.gms.internal.cast.j5):com.google.android.gms.internal.cast.b6");
    }
}
