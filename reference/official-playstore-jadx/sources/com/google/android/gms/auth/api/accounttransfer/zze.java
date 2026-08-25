package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.o0;
import com.google.android.gms.internal.auth.s;
import com.google.android.gms.internal.auth.u;

/* JADX INFO: loaded from: classes4.dex */
final class zze extends zzl {
    final /* synthetic */ u zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zze(AccountTransferClient accountTransferClient, int i10, u uVar) {
        super(1607, null);
        this.zza = uVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.zzl
    public final void zza(s sVar) throws RemoteException {
        zzd zzdVar = new zzd(this, this);
        u uVar = this.zza;
        Parcel parcelZza = sVar.zza();
        o0.d(parcelZza, zzdVar);
        o0.c(parcelZza, uVar);
        sVar.zzc(6, parcelZza);
    }
}
