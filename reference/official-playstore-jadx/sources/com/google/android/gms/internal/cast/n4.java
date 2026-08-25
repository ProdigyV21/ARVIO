package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n4 implements com.google.android.gms.tasks.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f13488i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f13489l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f13490m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f13491n;

    public /* synthetic */ n4(z4 z4Var, String str, int i10, SharedPreferences sharedPreferences) {
        this.f13489l = z4Var;
        this.f13490m = str;
        this.f13488i = i10;
        this.f13491n = sharedPreferences;
    }

    public Object a(com.typesafe.config.impl.w0 w0Var) {
        if (((com.typesafe.config.impl.w0) this.f13489l).equals(w0Var)) {
            return this.f13490m;
        }
        n4 n4Var = (n4) this.f13491n;
        if (n4Var != null) {
            return n4Var.a(w0Var);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0035  */
    @Override // com.google.android.gms.tasks.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void onSuccess(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = r11.f13489l
            r3 = r0
            com.google.android.gms.internal.cast.z4 r3 = (com.google.android.gms.internal.cast.z4) r3
            java.lang.Object r0 = r11.f13490m
            r6 = r0
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r0 = r11.f13491n
            r2 = r0
            android.content.SharedPreferences r2 = (android.content.SharedPreferences) r2
            r5 = r12
            android.os.Bundle r5 = (android.os.Bundle) r5
            com.google.android.gms.cast.framework.SessionManager r12 = r3.f13709a
            com.google.android.gms.common.internal.t.i(r12)
            com.google.android.gms.internal.cast.c1 r0 = r3.f13710b
            r1 = 3
            int r4 = r11.f13488i
            java.lang.Class<com.google.android.gms.cast.framework.CastSession> r7 = com.google.android.gms.cast.framework.CastSession.class
            r8 = 2
            if (r4 == r1) goto L24
            if (r4 != r8) goto L3e
            r4 = r8
        L24:
            com.google.android.gms.internal.cast.c0 r1 = r3.f13711c
            androidx.core.provider.e r9 = new androidx.core.provider.e
            r9.<init>(r3, r1, r6)
            com.google.android.gms.internal.cast.o8 r1 = new com.google.android.gms.internal.cast.o8
            r1.<init>(r9)
            r12.addSessionManagerListener(r1, r7)
            if (r0 == 0) goto L3e
            com.google.android.gms.internal.cast.g5 r1 = new com.google.android.gms.internal.cast.g5
            r10 = 1
            r1.<init>(r9, r10)
            r0.a(r1)
        L3e:
            r1 = 1
            if (r4 == r1) goto L43
            if (r4 != r8) goto L5e
        L43:
            com.google.android.gms.internal.cast.c0 r4 = r3.f13711c
            com.google.android.gms.internal.cast.i5 r1 = new com.google.android.gms.internal.cast.i5
            r1.<init>(r2, r3, r4, r5, r6)
            com.google.android.gms.internal.cast.ja r2 = new com.google.android.gms.internal.cast.ja
            r3 = 7
            r2.<init>(r1, r3)
            r12.addSessionManagerListener(r2, r7)
            if (r0 == 0) goto L5e
            com.google.android.gms.internal.cast.g5 r12 = new com.google.android.gms.internal.cast.g5
            r2 = 0
            r12.<init>(r1, r2)
            r0.a(r12)
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.n4.onSuccess(java.lang.Object):void");
    }

    public n4(int i10, com.typesafe.config.impl.w0 w0Var, Object obj, n4 n4Var) {
        this.f13488i = i10;
        this.f13489l = w0Var;
        this.f13490m = obj;
        this.f13491n = n4Var;
    }
}
