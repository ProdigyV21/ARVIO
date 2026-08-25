package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbw implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        String strV2 = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 1) {
                strV = d.V(i10, parcel);
            } else if (c10 != 2) {
                d.J0(i10, parcel);
            } else {
                strV2 = d.V(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new SignInPassword(strV, strV2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new SignInPassword[i10];
    }
}
