package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f1 implements com.google.android.gms.tasks.d, com.google.android.gms.tasks.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f13248i;

    public /* synthetic */ f1(com.google.android.gms.tasks.i iVar) {
        this.f13248i = iVar;
    }

    @Override // com.google.android.gms.tasks.d
    public /* synthetic */ void onFailure(Exception exc) {
        h1.f13273d.d(exc, "get checkbox consent failed", new Object[0]);
        this.f13248i.d(Boolean.FALSE);
    }

    @Override // com.google.android.gms.tasks.e
    public void onSuccess(Object obj) {
        m3 m3Var = (m3) obj;
        Logger logger = h1.f13273d;
        boolean z = false;
        if (m3Var != null) {
            r3 r3Var = m3Var.f13440a.f13623i;
            com.google.android.gms.common.internal.t.i(r3Var);
            if (r3Var.f13582i == 1) {
                z = true;
            }
        }
        this.f13248i.d(Boolean.valueOf(z));
    }
}
