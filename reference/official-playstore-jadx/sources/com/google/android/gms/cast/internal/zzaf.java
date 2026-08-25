package com.google.android.gms.cast.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.j;
import com.google.android.gms.internal.cast.a1;
import com.google.android.gms.internal.cast.e0;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzaf extends e0 implements zzag {
    public zzaf() {
        super("com.google.android.gms.cast.internal.IBundleCallback");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 != 1) {
            return false;
        }
        Bundle bundle = (Bundle) a1.b(parcel, Bundle.CREATOR);
        j jVar = (j) a1.b(parcel, j.CREATOR);
        a1.e(parcel);
        zzb(bundle, jVar);
        return true;
    }
}
