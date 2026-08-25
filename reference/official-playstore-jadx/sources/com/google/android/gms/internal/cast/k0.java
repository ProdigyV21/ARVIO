package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public final class k0 extends a implements l0 {
    @Override // com.google.android.gms.internal.cast.l0
    public final void C(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        a1.c(parcelZza, bundle);
        zzc(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.l0
    public final void L(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        a1.c(parcelZza, bundle);
        zzc(8, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.l0
    public final void O(String str, String str2, Bundle bundle, int i10) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        a1.c(parcelZza, bundle);
        parcelZza.writeInt(i10);
        zzc(10, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.l0
    public final void U(int i10, String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        a1.c(parcelZza, bundle);
        parcelZza.writeInt(i10);
        zzc(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.l0
    public final void W(Bundle bundle, String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        a1.c(parcelZza, bundle);
        zzc(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.l0
    public final void X(Bundle bundle, String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        a1.c(parcelZza, bundle);
        zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.l0
    public final void b0(Bundle bundle, String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        a1.c(parcelZza, bundle);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.l0
    public final void e0(Bundle bundle, String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        a1.c(parcelZza, bundle);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.l0
    public final int zze() throws RemoteException {
        Parcel parcelZzb = zzb(7, zza());
        int i10 = parcelZzb.readInt();
        parcelZzb.recycle();
        return i10;
    }
}
