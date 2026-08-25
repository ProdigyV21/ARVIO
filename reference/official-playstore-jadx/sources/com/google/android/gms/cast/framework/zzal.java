package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.e0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzal extends e0 implements zzam {
    public zzal() {
        super("com.google.android.gms.cast.framework.ICastStateListener");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 == 1) {
            b bVarZzb = zzb();
            parcel2.writeNoException();
            a1.d(parcel2, bVarZzb);
        } else if (i10 == 2) {
            int i12 = parcel.readInt();
            a1.e(parcel);
            zzc(i12);
            parcel2.writeNoException();
        } else {
            if (i10 != 3) {
                return false;
            }
            parcel2.writeNoException();
            parcel2.writeInt(12451000);
        }
        return true;
    }
}
