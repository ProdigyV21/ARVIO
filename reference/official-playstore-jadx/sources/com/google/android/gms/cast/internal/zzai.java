package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.e0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzai extends e0 implements zzaj {
    public zzai() {
        super("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        boolean z;
        switch (i10) {
            case 1:
                int i12 = parcel.readInt();
                a1.e(parcel);
                zzb(i12);
                return true;
            case 2:
                ApplicationMetadata applicationMetadata = (ApplicationMetadata) a1.b(parcel, ApplicationMetadata.CREATOR);
                String string = parcel.readString();
                String string2 = parcel.readString();
                z = parcel.readInt() != 0;
                a1.e(parcel);
                zze(applicationMetadata, string, string2, z);
                return true;
            case 3:
                int i13 = parcel.readInt();
                a1.e(parcel);
                zzf(i13);
                return true;
            case 4:
                String string3 = parcel.readString();
                double d4 = parcel.readDouble();
                int i14 = a1.f13157a;
                z = parcel.readInt() != 0;
                a1.e(parcel);
                zzj(string3, d4, z);
                return true;
            case 5:
                String string4 = parcel.readString();
                String string5 = parcel.readString();
                a1.e(parcel);
                zzm(string4, string5);
                return true;
            case 6:
                String string6 = parcel.readString();
                byte[] bArrCreateByteArray = parcel.createByteArray();
                a1.e(parcel);
                zzn(string6, bArrCreateByteArray);
                return true;
            case 7:
                int i15 = parcel.readInt();
                a1.e(parcel);
                zzh(i15);
                return true;
            case 8:
                int i16 = parcel.readInt();
                a1.e(parcel);
                zzg(i16);
                return true;
            case 9:
                int i17 = parcel.readInt();
                a1.e(parcel);
                zzi(i17);
                return true;
            case 10:
                String string7 = parcel.readString();
                long j10 = parcel.readLong();
                int i18 = parcel.readInt();
                a1.e(parcel);
                zzo(string7, j10, i18);
                return true;
            case 11:
                String string8 = parcel.readString();
                long j11 = parcel.readLong();
                a1.e(parcel);
                zzp(string8, j11);
                return true;
            case 12:
                zza zzaVar = (zza) a1.b(parcel, zza.CREATOR);
                a1.e(parcel);
                zzl(zzaVar);
                return true;
            case 13:
                zzac zzacVar = (zzac) a1.b(parcel, zzac.CREATOR);
                a1.e(parcel);
                zzk(zzacVar);
                return true;
            case 14:
                int i19 = parcel.readInt();
                a1.e(parcel);
                zzc(i19);
                return true;
            case 15:
                int i20 = parcel.readInt();
                a1.e(parcel);
                zzd(i20);
                return true;
            default:
                return false;
        }
    }
}
