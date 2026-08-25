package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.internal.cast.a;
import com.google.android.gms.internal.cast.a1;
import o3.b;

/* JADX INFO: loaded from: classes4.dex */
public final class zzai extends a implements zzak {
    public zzai(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ICastSession");
    }

    @Override // com.google.android.gms.cast.framework.zzak
    public final void zze(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        int i10 = a1.f13157a;
        parcelZza.writeInt(0);
        zzc(1, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzak
    public final void zzf(int i10) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i10);
        zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzak
    public final void zzg(b bVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, bVar);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzak
    public final void zzh(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, applicationMetadata);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        parcelZza.writeInt(z ? 1 : 0);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzak
    public final void zzi(int i10) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i10);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzak
    public final void zzj(boolean z, int i10) throws RemoteException {
        Parcel parcelZza = zza();
        int i11 = a1.f13157a;
        parcelZza.writeInt(z ? 1 : 0);
        parcelZza.writeInt(0);
        zzc(6, parcelZza);
    }
}
