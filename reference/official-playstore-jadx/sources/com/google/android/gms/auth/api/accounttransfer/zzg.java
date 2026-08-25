package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.o;
import com.google.android.gms.internal.auth.o0;
import com.google.android.gms.internal.auth.s;

/* JADX INFO: loaded from: classes4.dex */
final class zzg extends zzl {
    final /* synthetic */ o zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzg(AccountTransferClient accountTransferClient, int i10, o oVar) {
        super(1608, null);
        this.zza = oVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.zzl
    public final void zza(s sVar) throws RemoteException {
        zzf zzfVar = new zzf(this, this);
        o oVar = this.zza;
        Parcel parcelZza = sVar.zza();
        o0.d(parcelZza, zzfVar);
        o0.c(parcelZza, oVar);
        sVar.zzc(7, parcelZza);
    }
}
