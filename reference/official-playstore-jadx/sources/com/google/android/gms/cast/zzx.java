package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.j;
import com.google.android.gms.tasks.i;
import j$.util.Objects;
import qb.d;

/* JADX INFO: loaded from: classes4.dex */
final class zzx extends zzy {
    final /* synthetic */ i zza;
    final /* synthetic */ CastRemoteDisplayClient zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzx(CastRemoteDisplayClient castRemoteDisplayClient, i iVar) {
        super(null);
        this.zza = iVar;
        Objects.requireNonNull(castRemoteDisplayClient);
        this.zzb = castRemoteDisplayClient;
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.c3
    public final void zzd(int i10, j jVar) throws RemoteException {
        Object[] objArr = {Integer.valueOf(i10)};
        CastRemoteDisplayClient castRemoteDisplayClient = this.zzb;
        castRemoteDisplayClient.zzb().d("onError: %d", objArr);
        castRemoteDisplayClient.zza();
        d.S(Status.f12672q, null, this.zza);
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.c3
    public final void zzf(j jVar) throws RemoteException {
        CastRemoteDisplayClient castRemoteDisplayClient = this.zzb;
        castRemoteDisplayClient.zzb().d("onDisconnected", new Object[0]);
        castRemoteDisplayClient.zza();
        d.S(Status.f12670o, null, this.zza);
    }
}
