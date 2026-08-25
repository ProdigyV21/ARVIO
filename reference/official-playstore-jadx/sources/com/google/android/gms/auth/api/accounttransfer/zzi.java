package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.o0;
import com.google.android.gms.internal.auth.q;
import com.google.android.gms.internal.auth.s;
import com.google.android.gms.internal.auth.t;

/* JADX INFO: loaded from: classes4.dex */
final class zzi extends zzn {
    final /* synthetic */ t zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzi(AccountTransferClient accountTransferClient, int i10, t tVar) {
        super(1610);
        this.zza = tVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.zzl
    public final void zza(s sVar) throws RemoteException {
        q qVar = this.zzc;
        t tVar = this.zza;
        Parcel parcelZza = sVar.zza();
        o0.d(parcelZza, qVar);
        o0.c(parcelZza, tVar);
        sVar.zzc(9, parcelZza);
    }
}
