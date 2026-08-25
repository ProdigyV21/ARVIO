package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzo;
import com.google.android.gms.auth.api.accounttransfer.zzw;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public abstract class q extends w implements r {
    @Override // com.google.android.gms.internal.auth.w
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        switch (i10) {
            case 1:
                Status status = (Status) o0.a(parcel, Status.CREATOR);
                o0.b(parcel);
                zzh(status);
                return true;
            case 2:
                Status status2 = (Status) o0.a(parcel, Status.CREATOR);
                zzw zzwVar = (zzw) o0.a(parcel, zzw.CREATOR);
                o0.b(parcel);
                zzf(status2, zzwVar);
                return true;
            case 3:
                Status status3 = (Status) o0.a(parcel, Status.CREATOR);
                zzo zzoVar = (zzo) o0.a(parcel, zzo.CREATOR);
                o0.b(parcel);
                zzg(status3, zzoVar);
                return true;
            case 4:
                zze();
                return true;
            case 5:
                Status status4 = (Status) o0.a(parcel, Status.CREATOR);
                o0.b(parcel);
                zzd(status4);
                return true;
            case 6:
                byte[] bArrCreateByteArray = parcel.createByteArray();
                o0.b(parcel);
                zzb(bArrCreateByteArray);
                return true;
            case 7:
                DeviceMetaData deviceMetaData = (DeviceMetaData) o0.a(parcel, DeviceMetaData.CREATOR);
                o0.b(parcel);
                zzc(deviceMetaData);
                return true;
            default:
                return false;
        }
    }
}
