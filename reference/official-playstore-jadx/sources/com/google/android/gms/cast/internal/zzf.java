package com.google.android.gms.cast.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.i;
import j$.util.Objects;
import qb.d;

/* JADX INFO: loaded from: classes4.dex */
final class zzf extends i {
    final /* synthetic */ com.google.android.gms.tasks.i zza;

    public zzf(zzn zznVar, com.google.android.gms.tasks.i iVar) {
        this.zza = iVar;
        Objects.requireNonNull(zznVar);
    }

    @Override // com.google.android.gms.common.api.internal.j
    public final void onResult(Status status) throws RemoteException {
        d.S(status, null, this.zza);
    }
}
