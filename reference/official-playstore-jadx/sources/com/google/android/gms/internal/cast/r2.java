package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public final class r2 extends v2 {
    @Override // com.google.android.gms.common.api.internal.d
    public final void doExecute(com.google.android.gms.common.api.b bVar) throws RemoteException {
        a3 a3Var = (a3) bVar;
        u2 u2Var = new u2(this);
        a3Var.getClass();
        a3.f13164d.d("stopRemoteDisplay", new Object[0]);
        d3 d3Var = (d3) a3Var.getService();
        a3Var.getContext();
        com.google.android.gms.common.api.j jVarI = u0.i();
        Parcel parcelZza = d3Var.zza();
        a1.d(parcelZza, u2Var);
        a1.c(parcelZza, jVarI);
        d3Var.zzd(6, parcelZza);
    }
}
