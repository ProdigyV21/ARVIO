package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.cast.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zzan extends a implements zzao {
    public zzan(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.IDiscoveryManager");
    }

    @Override // com.google.android.gms.cast.framework.zzao
    public final b zze() throws RemoteException {
        Parcel parcelZzb = zzb(5, zza());
        b bVarF0 = d.f0(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return bVarF0;
    }
}
