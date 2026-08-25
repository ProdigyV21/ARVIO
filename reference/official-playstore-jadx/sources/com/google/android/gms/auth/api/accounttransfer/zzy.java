package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzy implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        int iZ0 = 0;
        boolean zU0 = false;
        boolean zU02 = false;
        long jB0 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 1) {
                iZ0 = d.z0(i10, parcel);
            } else if (c10 == 2) {
                zU0 = d.u0(i10, parcel);
            } else if (c10 == 3) {
                jB0 = d.B0(i10, parcel);
            } else if (c10 != 4) {
                d.J0(i10, parcel);
            } else {
                zU02 = d.u0(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new DeviceMetaData(iZ0, zU0, jB0, zU02);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new DeviceMetaData[i10];
    }
}
