package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.o0;
import com.google.android.gms.internal.auth.q;
import com.google.android.gms.internal.auth.s;
import com.google.android.gms.internal.auth.x;

/* JADX INFO: loaded from: classes4.dex */
final class zzh extends zzn {
    final /* synthetic */ x zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzh(AccountTransferClient accountTransferClient, int i10, x xVar) {
        super(1609);
        this.zza = xVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.zzl
    public final void zza(s sVar) throws RemoteException {
        q qVar = this.zzc;
        x xVar = this.zza;
        Parcel parcelZza = sVar.zza();
        o0.d(parcelZza, qVar);
        o0.c(parcelZza, xVar);
        sVar.zzc(8, parcelZza);
    }
}
