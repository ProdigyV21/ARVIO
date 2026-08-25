package com.google.android.gms.cast.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbn;
import com.google.android.gms.common.api.j;
import com.google.android.gms.internal.cast.a;
import com.google.android.gms.internal.cast.a1;

/* JADX INFO: loaded from: classes4.dex */
public final class zzah extends a implements IInterface {
    public zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastDeviceController");
    }

    public final void zze(j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, jVar);
        zzd(1, parcelZza);
    }

    public final void zzf(j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, jVar);
        zzd(4, parcelZza);
    }

    public final void zzg(String str, j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        a1.c(parcelZza, jVar);
        zzd(5, parcelZza);
    }

    public final void zzh(j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, jVar);
        zzd(6, parcelZza);
    }

    public final void zzi(double d4, double d10, boolean z, j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeDouble(d4);
        parcelZza.writeDouble(d10);
        int i10 = a1.f13157a;
        parcelZza.writeInt(z ? 1 : 0);
        a1.c(parcelZza, jVar);
        zzd(7, parcelZza);
    }

    public final void zzj(boolean z, double d4, boolean z5, j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        int i10 = a1.f13157a;
        parcelZza.writeInt(z ? 1 : 0);
        parcelZza.writeDouble(d4);
        parcelZza.writeInt(z5 ? 1 : 0);
        a1.c(parcelZza, jVar);
        zzd(8, parcelZza);
    }

    public final void zzk(String str, String str2, long j10, j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        parcelZza.writeLong(j10);
        a1.c(parcelZza, jVar);
        zzd(9, parcelZza);
    }

    public final void zzl(String str, j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        a1.c(parcelZza, jVar);
        zzd(11, parcelZza);
    }

    public final void zzm(String str, j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        a1.c(parcelZza, jVar);
        zzd(12, parcelZza);
    }

    public final void zzn(String str, LaunchOptions launchOptions, j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        a1.c(parcelZza, launchOptions);
        a1.c(parcelZza, jVar);
        zzd(13, parcelZza);
    }

    public final void zzo(String str, String str2, zzbn zzbnVar, j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        a1.c(parcelZza, zzbnVar);
        a1.c(parcelZza, jVar);
        zzd(14, parcelZza);
    }

    public final void zzp(j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, jVar);
        zzd(17, parcelZza);
    }

    public final void zzq(zzaj zzajVar, j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, zzajVar);
        a1.c(parcelZza, jVar);
        zzd(18, parcelZza);
    }

    public final void zzr(j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, jVar);
        zzd(19, parcelZza);
    }
}
