package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.media.internal.zzf;
import com.google.android.gms.cast.framework.media.internal.zzg;
import com.google.android.gms.cast.framework.media.internal.zzi;
import com.google.android.gms.cast.framework.zzae;
import com.google.android.gms.cast.framework.zzag;
import com.google.android.gms.cast.framework.zzah;
import com.google.android.gms.cast.framework.zzaj;
import com.google.android.gms.cast.framework.zzak;
import com.google.android.gms.cast.framework.zzaq;
import com.google.android.gms.cast.framework.zzar;
import com.google.android.gms.cast.framework.zzat;
import com.google.android.gms.cast.framework.zzau;
import com.google.android.gms.cast.framework.zzbc;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class g0 extends a implements h0 {
    @Override // com.google.android.gms.internal.cast.h0
    public final zzak B(CastOptions castOptions, com.google.android.gms.dynamic.b bVar, zzae zzaeVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.c(parcelZza, castOptions);
        a1.d(parcelZza, bVar);
        a1.d(parcelZza, zzaeVar);
        Parcel parcelZzb = zzb(3, parcelZza);
        zzak zzakVarZzb = zzaj.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zzakVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.h0
    public final zzar S(com.google.android.gms.dynamic.d dVar, com.google.android.gms.dynamic.b bVar, com.google.android.gms.dynamic.b bVar2) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, dVar);
        a1.d(parcelZza, bVar);
        a1.d(parcelZza, bVar2);
        Parcel parcelZzb = zzb(5, parcelZza);
        zzar zzarVarZzb = zzaq.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zzarVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.h0
    public final zzau d0(String str, String str2, zzbc zzbcVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        a1.d(parcelZza, zzbcVar);
        Parcel parcelZzb = zzb(2, parcelZza);
        zzau zzauVarZzb = zzat.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zzauVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.h0
    public final zzah f(com.google.android.gms.dynamic.d dVar, CastOptions castOptions, x0 x0Var, Map map) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, dVar);
        a1.c(parcelZza, castOptions);
        a1.d(parcelZza, x0Var);
        parcelZza.writeMap(map);
        Parcel parcelZzb = zzb(1, parcelZza);
        zzah zzahVarZzb = zzag.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zzahVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.h0
    public final zzg k(com.google.android.gms.dynamic.d dVar, com.google.android.gms.dynamic.d dVar2, zzi zziVar, int i10, int i11) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, dVar);
        a1.d(parcelZza, dVar2);
        a1.d(parcelZza, zziVar);
        parcelZza.writeInt(i10);
        parcelZza.writeInt(i11);
        parcelZza.writeInt(0);
        parcelZza.writeLong(2097152L);
        parcelZza.writeInt(5);
        parcelZza.writeInt(333);
        parcelZza.writeInt(10000);
        Parcel parcelZzb = zzb(7, parcelZza);
        zzg zzgVarZzb = zzf.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zzgVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.h0
    public final zzg p(com.google.android.gms.dynamic.d dVar, zzi zziVar, int i10, int i11) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, dVar);
        a1.d(parcelZza, zziVar);
        parcelZza.writeInt(i10);
        parcelZza.writeInt(i11);
        parcelZza.writeInt(0);
        parcelZza.writeLong(2097152L);
        parcelZza.writeInt(5);
        parcelZza.writeInt(333);
        parcelZza.writeInt(10000);
        Parcel parcelZzb = zzb(6, parcelZza);
        zzg zzgVarZzb = zzf.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zzgVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.h0
    public final int zze() throws RemoteException {
        Parcel parcelZzb = zzb(8, zza());
        int i10 = parcelZzb.readInt();
        parcelZzb.recycle();
        return i10;
    }
}
