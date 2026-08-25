package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.cast.a;
import com.google.android.gms.internal.cast.a1;

/* JADX INFO: loaded from: classes4.dex */
public final class zzav extends a implements zzaw {
    public zzav(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ISessionManager");
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final b zze() throws RemoteException {
        Parcel parcelZzb = zzb(1, zza());
        b bVarF0 = d.f0(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return bVarF0;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzf(zzay zzayVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, zzayVar);
        zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzg(zzay zzayVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, zzayVar);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzh(zzam zzamVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, zzamVar);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzi(zzam zzamVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, zzamVar);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzj(boolean z, boolean z5) throws RemoteException {
        Parcel parcelZza = zza();
        int i10 = a1.f13157a;
        parcelZza.writeInt(1);
        parcelZza.writeInt(z5 ? 1 : 0);
        zzc(6, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final b zzk() throws RemoteException {
        Parcel parcelZzb = zzb(7, zza());
        b bVarF0 = d.f0(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return bVarF0;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final int zzl() throws RemoteException {
        Parcel parcelZzb = zzb(8, zza());
        int i10 = parcelZzb.readInt();
        parcelZzb.recycle();
        return i10;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzm(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, bundle);
        zzc(9, parcelZza);
    }
}
