package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbc implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = "";
        GoogleSignInAccount googleSignInAccount = null;
        String strV2 = "";
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 4) {
                strV = d.V(i10, parcel);
            } else if (c10 == 7) {
                googleSignInAccount = (GoogleSignInAccount) d.T(parcel, i10, GoogleSignInAccount.CREATOR);
            } else if (c10 != '\b') {
                d.J0(i10, parcel);
            } else {
                strV2 = d.V(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new SignInAccount(strV, googleSignInAccount, strV2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new SignInAccount[i10];
    }
}
