package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbk implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        int iZ0 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            if (((char) i10) != 1) {
                d.J0(i10, parcel);
            } else {
                iZ0 = d.z0(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new GetPhoneNumberHintIntentRequest(iZ0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new GetPhoneNumberHintIntentRequest[i10];
    }
}
