package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.l;

/* JADX INFO: loaded from: classes4.dex */
final class zzm extends l {
    final /* synthetic */ zzn zza;

    public zzm(zzn zznVar) {
        this.zza = zznVar;
    }

    @Override // com.google.android.gms.internal.auth.r
    public final void zzd(Status status) {
        this.zza.zzb.a(new AccountTransferException(status));
    }

    @Override // com.google.android.gms.internal.auth.l, com.google.android.gms.internal.auth.r
    public final void zze() {
        this.zza.zzb.b(null);
    }
}
