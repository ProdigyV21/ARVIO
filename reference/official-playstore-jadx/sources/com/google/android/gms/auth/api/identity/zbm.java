package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbm implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        boolean zU0 = false;
        boolean zU02 = false;
        boolean zU03 = false;
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        ArrayList arrayListX = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 2:
                    strV = d.V(i10, parcel);
                    break;
                case 3:
                    strV2 = d.V(i10, parcel);
                    break;
                case 4:
                    zU02 = d.u0(i10, parcel);
                    break;
                case 5:
                    strV3 = d.V(i10, parcel);
                    break;
                case 6:
                    arrayListX = d.X(i10, parcel);
                    break;
                case 7:
                    zU03 = d.u0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new BeginSignInRequest.GoogleIdTokenRequestOptions(zU0, strV, strV2, zU02, strV3, arrayListX, zU03);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new BeginSignInRequest.GoogleIdTokenRequestOptions[i10];
    }
}
