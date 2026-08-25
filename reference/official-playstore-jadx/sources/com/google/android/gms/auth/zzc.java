package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzc implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        ArrayList arrayListB0 = null;
        int iZ0 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 1) {
                iZ0 = d.z0(i10, parcel);
            } else if (c10 != 2) {
                d.J0(i10, parcel);
            } else {
                arrayListB0 = d.b0(parcel, i10, AccountChangeEvent.CREATOR);
            }
        }
        d.e0(iL0, parcel);
        return new AccountChangeEventsResponse(iZ0, arrayListB0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new AccountChangeEventsResponse[i10];
    }
}
