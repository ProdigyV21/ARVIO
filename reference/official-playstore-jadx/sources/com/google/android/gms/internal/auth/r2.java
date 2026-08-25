package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class r2 {
    public static final q2 a(Object obj, Object obj2) {
        q2 q2Var = (q2) obj;
        q2 q2Var2 = (q2) obj2;
        if (!q2Var2.isEmpty()) {
            if (!q2Var.f13105i) {
                if (q2Var.isEmpty()) {
                    q2Var = new q2();
                } else {
                    q2 q2Var3 = new q2(q2Var);
                    q2Var3.f13105i = true;
                    q2Var = q2Var3;
                }
            }
            q2Var.c();
            if (!q2Var2.isEmpty()) {
                q2Var.putAll(q2Var2);
            }
        }
        return q2Var;
    }
}
