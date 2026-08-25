package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbl implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        boolean zU0 = false;
        int iZ0 = 0;
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        String strV4 = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    strV = d.V(i10, parcel);
                    break;
                case 2:
                    strV2 = d.V(i10, parcel);
                    break;
                case 3:
                    strV3 = d.V(i10, parcel);
                    break;
                case 4:
                    strV4 = d.V(i10, parcel);
                    break;
                case 5:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 6:
                    iZ0 = d.z0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new GetSignInIntentRequest(strV, strV2, strV3, strV4, zU0, iZ0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new GetSignInIntentRequest[i10];
    }
}
