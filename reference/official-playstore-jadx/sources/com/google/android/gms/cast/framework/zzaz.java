package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.e0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzaz extends e0 implements zzba {
    public zzaz() {
        super("com.google.android.gms.cast.framework.ISessionProvider");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 == 1) {
            String string = parcel.readString();
            a1.e(parcel);
            b bVarZzb = zzb(string);
            parcel2.writeNoException();
            a1.d(parcel2, bVarZzb);
        } else if (i10 == 2) {
            boolean zZzc = zzc();
            parcel2.writeNoException();
            int i12 = a1.f13157a;
            parcel2.writeInt(zZzc ? 1 : 0);
        } else if (i10 == 3) {
            String strZzd = zzd();
            parcel2.writeNoException();
            parcel2.writeString(strZzd);
        } else {
            if (i10 != 4) {
                return false;
            }
            parcel2.writeNoException();
            parcel2.writeInt(12451000);
        }
        return true;
    }
}
