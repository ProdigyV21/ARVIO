package com.google.android.gms.cast.framework;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.cast.a;
import com.google.android.gms.internal.cast.a1;

/* JADX INFO: loaded from: classes4.dex */
public final class zzap extends a implements zzar {
    public zzap(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.IReconnectionService");
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zze() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final int zzf(Intent intent, int i10, int i11) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, intent);
        parcelZza.writeInt(i10);
        parcelZza.writeInt(i11);
        Parcel parcelZzb = zzb(2, parcelZza);
        int i12 = parcelZzb.readInt();
        parcelZzb.recycle();
        return i12;
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final IBinder zzg(Intent intent) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, intent);
        Parcel parcelZzb = zzb(3, parcelZza);
        IBinder strongBinder = parcelZzb.readStrongBinder();
        parcelZzb.recycle();
        return strongBinder;
    }

    @Override // com.google.android.gms.cast.framework.zzar
    public final void zzh() throws RemoteException {
        zzc(4, zza());
    }
}
