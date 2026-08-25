package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d0 extends com.google.android.gms.common.api.internal.d {
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ com.google.android.gms.common.api.x createFailedResult(Status status) {
        return new l0(status);
    }

    @Override // com.google.android.gms.common.api.internal.d
    public final void doExecute(com.google.android.gms.common.api.b bVar) throws RemoteException {
        z zVar = (z) bVar;
        zVar.getContext();
        b0 b0Var = (b0) zVar.getService();
        h0 h0Var = new h0((i0) this);
        Parcel parcelZza = b0Var.zza();
        o0.d(parcelZza, h0Var);
        b0Var.zzc(3, parcelZza);
    }
}
