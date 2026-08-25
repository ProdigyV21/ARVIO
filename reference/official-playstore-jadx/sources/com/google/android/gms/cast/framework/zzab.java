package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.e0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzab extends e0 implements zzac {
    public zzab() {
        super("com.google.android.gms.cast.framework.IAppVisibilityListener");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 == 1) {
            b bVarZzb = zzb();
            parcel2.writeNoException();
            a1.d(parcel2, bVarZzb);
        } else if (i10 == 2) {
            zzc();
            parcel2.writeNoException();
        } else if (i10 == 3) {
            zzd();
            parcel2.writeNoException();
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
