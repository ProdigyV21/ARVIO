package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public abstract class c0 extends com.google.android.gms.common.api.internal.d {
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ com.google.android.gms.common.api.x createFailedResult(Status status) {
        return new k0(status);
    }

    @Override // com.google.android.gms.common.api.internal.d
    public final void doExecute(com.google.android.gms.common.api.b bVar) throws RemoteException {
        z zVar = (z) bVar;
        zVar.getContext();
        b0 b0Var = (b0) zVar.getService();
        g0 g0Var = (g0) this;
        e0 e0Var = new e0(g0Var, 2);
        Parcel parcelZza = b0Var.zza();
        o0.d(parcelZza, e0Var);
        o0.c(parcelZza, g0Var.f13033a);
        b0Var.zzc(1, parcelZza);
    }
}
