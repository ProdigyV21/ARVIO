package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbu implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        GoogleSignInOptions googleSignInOptions = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 2) {
                strV = d.V(i10, parcel);
            } else if (c10 != 5) {
                d.J0(i10, parcel);
            } else {
                googleSignInOptions = (GoogleSignInOptions) d.T(parcel, i10, GoogleSignInOptions.CREATOR);
            }
        }
        d.e0(iL0, parcel);
        return new SignInConfiguration(strV, googleSignInOptions);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new SignInConfiguration[i10];
    }
}
