package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.e0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzad extends e0 implements zzae {
    public zzad() {
        super("com.google.android.gms.cast.framework.ICastConnectionController");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 == 1) {
            String string = parcel.readString();
            String string2 = parcel.readString();
            a1.e(parcel);
            zzb(string, string2);
            parcel2.writeNoException();
        } else if (i10 == 2) {
            String string3 = parcel.readString();
            LaunchOptions launchOptions = (LaunchOptions) a1.b(parcel, LaunchOptions.CREATOR);
            a1.e(parcel);
            zzc(string3, launchOptions);
            parcel2.writeNoException();
        } else if (i10 == 3) {
            String string4 = parcel.readString();
            a1.e(parcel);
            zzd(string4);
            parcel2.writeNoException();
        } else if (i10 == 4) {
            int i12 = parcel.readInt();
            a1.e(parcel);
            zze(i12);
            parcel2.writeNoException();
        } else {
            if (i10 != 5) {
                return false;
            }
            parcel2.writeNoException();
            parcel2.writeInt(12451000);
        }
        return true;
    }
}
