package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.e0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzax extends e0 implements zzay {
    public zzax() {
        super("com.google.android.gms.cast.framework.ISessionManagerListener");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        switch (i10) {
            case 1:
                b bVarZzb = zzb();
                parcel2.writeNoException();
                a1.d(parcel2, bVarZzb);
                return true;
            case 2:
                b bVarF0 = d.f0(parcel.readStrongBinder());
                a1.e(parcel);
                zzc(bVarF0);
                parcel2.writeNoException();
                return true;
            case 3:
                b bVarF02 = d.f0(parcel.readStrongBinder());
                String string = parcel.readString();
                a1.e(parcel);
                zzd(bVarF02, string);
                parcel2.writeNoException();
                return true;
            case 4:
                b bVarF03 = d.f0(parcel.readStrongBinder());
                int i12 = parcel.readInt();
                a1.e(parcel);
                zze(bVarF03, i12);
                parcel2.writeNoException();
                return true;
            case 5:
                b bVarF04 = d.f0(parcel.readStrongBinder());
                a1.e(parcel);
                zzf(bVarF04);
                parcel2.writeNoException();
                return true;
            case 6:
                b bVarF05 = d.f0(parcel.readStrongBinder());
                int i13 = parcel.readInt();
                a1.e(parcel);
                zzg(bVarF05, i13);
                parcel2.writeNoException();
                return true;
            case 7:
                b bVarF06 = d.f0(parcel.readStrongBinder());
                String string2 = parcel.readString();
                a1.e(parcel);
                zzh(bVarF06, string2);
                parcel2.writeNoException();
                return true;
            case 8:
                b bVarF07 = d.f0(parcel.readStrongBinder());
                int i14 = a1.f13157a;
                boolean z = parcel.readInt() != 0;
                a1.e(parcel);
                zzi(bVarF07, z);
                parcel2.writeNoException();
                return true;
            case 9:
                b bVarF08 = d.f0(parcel.readStrongBinder());
                int i15 = parcel.readInt();
                a1.e(parcel);
                zzj(bVarF08, i15);
                parcel2.writeNoException();
                return true;
            case 10:
                b bVarF09 = d.f0(parcel.readStrongBinder());
                int i16 = parcel.readInt();
                a1.e(parcel);
                zzk(bVarF09, i16);
                parcel2.writeNoException();
                return true;
            case 11:
                parcel2.writeNoException();
                parcel2.writeInt(12451000);
                return true;
            default:
                return false;
        }
    }
}
