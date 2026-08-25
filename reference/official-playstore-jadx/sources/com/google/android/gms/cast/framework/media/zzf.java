package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.e0;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzf extends e0 implements zzg {
    public zzf() {
        super("com.google.android.gms.cast.framework.media.INotificationActionsProvider");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 == 1) {
            parcel2.writeNoException();
            parcel2.writeInt(12451000);
        } else if (i10 == 2) {
            b bVarZze = zze();
            parcel2.writeNoException();
            a1.d(parcel2, bVarZze);
        } else if (i10 == 3) {
            List listZzf = zzf();
            parcel2.writeNoException();
            parcel2.writeTypedList(listZzf);
        } else {
            if (i10 != 4) {
                return false;
            }
            int[] iArrZzg = zzg();
            parcel2.writeNoException();
            parcel2.writeIntArray(iArrZzg);
        }
        return true;
    }
}
