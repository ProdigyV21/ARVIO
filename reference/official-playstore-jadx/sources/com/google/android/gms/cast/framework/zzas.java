package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.cast.a;
import com.google.android.gms.internal.cast.a1;

/* JADX INFO: loaded from: classes4.dex */
public final class zzas extends a implements zzau {
    public zzas(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ISession");
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final int zze() throws RemoteException {
        Parcel parcelZzb = zzb(17, zza());
        int i10 = parcelZzb.readInt();
        parcelZzb.recycle();
        return i10;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final b zzf() throws RemoteException {
        Parcel parcelZzb = zzb(1, zza());
        b bVarF0 = d.f0(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return bVarF0;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final String zzg() throws RemoteException {
        Parcel parcelZzb = zzb(2, zza());
        String string = parcelZzb.readString();
        parcelZzb.recycle();
        return string;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final String zzh() throws RemoteException {
        Parcel parcelZzb = zzb(3, zza());
        String string = parcelZzb.readString();
        parcelZzb.recycle();
        return string;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final boolean zzi() throws RemoteException {
        Parcel parcelZzb = zzb(5, zza());
        boolean zA = a1.a(parcelZzb);
        parcelZzb.recycle();
        return zA;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final boolean zzj() throws RemoteException {
        Parcel parcelZzb = zzb(6, zza());
        boolean zA = a1.a(parcelZzb);
        parcelZzb.recycle();
        return zA;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final boolean zzk() throws RemoteException {
        Parcel parcelZzb = zzb(7, zza());
        boolean zA = a1.a(parcelZzb);
        parcelZzb.recycle();
        return zA;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final boolean zzl() throws RemoteException {
        Parcel parcelZzb = zzb(8, zza());
        boolean zA = a1.a(parcelZzb);
        parcelZzb.recycle();
        return zA;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final boolean zzm() throws RemoteException {
        Parcel parcelZzb = zzb(9, zza());
        boolean zA = a1.a(parcelZzb);
        parcelZzb.recycle();
        return zA;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final boolean zzn() throws RemoteException {
        Parcel parcelZzb = zzb(10, zza());
        boolean zA = a1.a(parcelZzb);
        parcelZzb.recycle();
        return zA;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final int zzo() throws RemoteException {
        Parcel parcelZzb = zzb(18, zza());
        int i10 = parcelZzb.readInt();
        parcelZzb.recycle();
        return i10;
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final void zzp(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc(11, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final void zzq(int i10) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i10);
        zzc(12, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final void zzr(int i10) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i10);
        zzc(13, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final void zzs(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i10 = a1.f13157a;
        parcelZza.writeInt(z ? 1 : 0);
        zzc(14, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final void zzt(int i10) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i10);
        zzc(15, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzau
    public final void zzu(int i10) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i10);
        zzc(16, parcelZza);
    }
}
