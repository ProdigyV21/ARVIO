package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public abstract class i extends o4.b implements j {
    public i() {
        super("com.google.android.gms.common.api.internal.IStatusCallback");
    }

    public static j asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
        return iInterfaceQueryLocalInterface instanceof j ? (j) iInterfaceQueryLocalInterface : new k0(iBinder, "com.google.android.gms.common.api.internal.IStatusCallback", 0);
    }

    @Override // o4.b
    public final boolean zaa(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 != 1) {
            return false;
        }
        Status status = (Status) o4.c.a(parcel, Status.CREATOR);
        o4.c.b(parcel);
        onResult(status);
        return true;
    }
}
