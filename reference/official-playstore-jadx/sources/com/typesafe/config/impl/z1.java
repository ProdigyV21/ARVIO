package com.typesafe.config.impl;

import com.google.android.gms.internal.cast.n4;

/* JADX INFO: loaded from: classes4.dex */
public final class z1 implements x1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f14652a;

    public /* synthetic */ z1(Object obj) {
        this.f14652a = obj;
    }

    @Override // com.typesafe.config.impl.x1
    public l6.m a(String str, l6.l lVar) {
        l6.m mVarM = ((l6.b) this.f14652a).m(str);
        if (mVarM != null) {
            return mVarM;
        }
        String strL = a0.c.l("include was not found: '", str, "'");
        com.google.android.gms.common.api.internal.f1 f1Var = f1.f14531d;
        return new z0(str, strL, lVar);
    }

    public g b(w0 w0Var) {
        n4 n4Var;
        n4[] n4VarArr = ((h) this.f14652a).f14547b;
        Object objA = null;
        if (n4VarArr.length != 0 && (n4Var = n4VarArr[Math.abs(w0Var.hashCode()) % n4VarArr.length]) != null) {
            objA = n4Var.a(w0Var);
        }
        return (g) objA;
    }
}
