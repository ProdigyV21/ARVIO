package com.google.android.gms.cast.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.j;
import com.google.android.gms.internal.cast.a;
import com.google.android.gms.internal.cast.a1;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzak extends a implements IInterface {
    public zzak(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastService");
    }

    public final void zze(j jVar, String[] strArr, String str, List list, com.google.android.gms.common.api.j jVar2) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, jVar);
        parcelZza.writeStringArray(strArr);
        parcelZza.writeString(str);
        parcelZza.writeTypedList(null);
        a1.c(parcelZza, jVar2);
        zzd(2, parcelZza);
    }

    public final void zzf(zzag zzagVar, String[] strArr, com.google.android.gms.common.api.j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, zzagVar);
        parcelZza.writeStringArray(strArr);
        a1.c(parcelZza, jVar);
        zzd(5, parcelZza);
    }

    public final void zzg(zzag zzagVar, String[] strArr, com.google.android.gms.common.api.j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, zzagVar);
        parcelZza.writeStringArray(strArr);
        a1.c(parcelZza, jVar);
        zzd(6, parcelZza);
    }

    public final void zzh(zzag zzagVar, String[] strArr, com.google.android.gms.common.api.j jVar) throws RemoteException {
        Parcel parcelZza = zza();
        a1.d(parcelZza, zzagVar);
        parcelZza.writeStringArray(strArr);
        a1.c(parcelZza, jVar);
        zzd(7, parcelZza);
    }
}
