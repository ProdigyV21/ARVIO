package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.e0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzbb extends e0 implements zzbc {
    public zzbb() {
        super("com.google.android.gms.cast.framework.ISessionProxy");
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
                Bundle bundle = (Bundle) a1.b(parcel, Bundle.CREATOR);
                a1.e(parcel);
                zzd(bundle);
                parcel2.writeNoException();
                return true;
            case 3:
                Bundle bundle2 = (Bundle) a1.b(parcel, Bundle.CREATOR);
                a1.e(parcel);
                zzf(bundle2);
                parcel2.writeNoException();
                return true;
            case 4:
                boolean zA = a1.a(parcel);
                a1.e(parcel);
                zzg(zA);
                parcel2.writeNoException();
                return true;
            case 5:
                long jZzi = zzi();
                parcel2.writeNoException();
                parcel2.writeLong(jZzi);
                return true;
            case 6:
                parcel2.writeNoException();
                parcel2.writeInt(12451000);
                return true;
            case 7:
                Bundle bundle3 = (Bundle) a1.b(parcel, Bundle.CREATOR);
                a1.e(parcel);
                zzc(bundle3);
                parcel2.writeNoException();
                return true;
            case 8:
                Bundle bundle4 = (Bundle) a1.b(parcel, Bundle.CREATOR);
                a1.e(parcel);
                zze(bundle4);
                parcel2.writeNoException();
                return true;
            case 9:
                Bundle bundle5 = (Bundle) a1.b(parcel, Bundle.CREATOR);
                a1.e(parcel);
                zzh(bundle5);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
