package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.o0;
import com.google.android.gms.internal.auth.q;
import com.google.android.gms.internal.auth.s;
import com.google.android.gms.internal.auth.v;

/* JADX INFO: loaded from: classes4.dex */
final class zzc extends zzn {
    final /* synthetic */ v zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzc(AccountTransferClient accountTransferClient, int i10, v vVar) {
        super(1606);
        this.zza = vVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.zzl
    public final void zza(s sVar) throws RemoteException {
        q qVar = this.zzc;
        v vVar = this.zza;
        Parcel parcelZza = sVar.zza();
        o0.d(parcelZza, qVar);
        o0.c(parcelZza, vVar);
        sVar.zzc(5, parcelZza);
    }
}
