package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.l;
import com.google.android.gms.tasks.i;

/* JADX INFO: loaded from: classes4.dex */
class zzj extends l {
    final zzl zzb;

    public zzj(zzl zzlVar) {
        this.zzb = zzlVar;
    }

    @Override // com.google.android.gms.internal.auth.r
    public final void zzd(Status status) {
        i iVar = this.zzb.zzb;
        int i10 = AccountTransferClient.zza;
        iVar.a(new AccountTransferException(status));
    }
}
