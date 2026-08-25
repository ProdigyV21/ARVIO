package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.a;
import com.google.android.gms.internal.auth.o0;

/* JADX INFO: loaded from: classes4.dex */
public final class zzc extends a implements zze {
    public zzc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.account.IWorkAccountService");
    }

    @Override // com.google.android.gms.auth.account.zze
    public final void zzd(zzb zzbVar, String str) throws RemoteException {
        Parcel parcelZza = zza();
        o0.d(parcelZza, zzbVar);
        parcelZza.writeString(str);
        zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.auth.account.zze
    public final void zze(zzb zzbVar, Account account) throws RemoteException {
        Parcel parcelZza = zza();
        o0.d(parcelZza, zzbVar);
        o0.c(parcelZza, account);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.auth.account.zze
    public final void zzf(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i10 = o0.f13083a;
        parcelZza.writeInt(z ? 1 : 0);
        zzc(1, parcelZza);
    }
}
