package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbg implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        boolean zU0 = false;
        int iZ0 = 0;
        boolean zU02 = false;
        BeginSignInRequest.PasswordRequestOptions passwordRequestOptions = null;
        BeginSignInRequest.GoogleIdTokenRequestOptions googleIdTokenRequestOptions = null;
        String strV = null;
        BeginSignInRequest.PasskeysRequestOptions passkeysRequestOptions = null;
        BeginSignInRequest.PasskeyJsonRequestOptions passkeyJsonRequestOptions = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    passwordRequestOptions = (BeginSignInRequest.PasswordRequestOptions) d.T(parcel, i10, BeginSignInRequest.PasswordRequestOptions.CREATOR);
                    break;
                case 2:
                    googleIdTokenRequestOptions = (BeginSignInRequest.GoogleIdTokenRequestOptions) d.T(parcel, i10, BeginSignInRequest.GoogleIdTokenRequestOptions.CREATOR);
                    break;
                case 3:
                    strV = d.V(i10, parcel);
                    break;
                case 4:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 5:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 6:
                    passkeysRequestOptions = (BeginSignInRequest.PasskeysRequestOptions) d.T(parcel, i10, BeginSignInRequest.PasskeysRequestOptions.CREATOR);
                    break;
                case 7:
                    passkeyJsonRequestOptions = (BeginSignInRequest.PasskeyJsonRequestOptions) d.T(parcel, i10, BeginSignInRequest.PasskeyJsonRequestOptions.CREATOR);
                    break;
                case '\b':
                    zU02 = d.u0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new BeginSignInRequest(passwordRequestOptions, googleIdTokenRequestOptions, strV, zU0, iZ0, passkeysRequestOptions, passkeyJsonRequestOptions, zU02);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new BeginSignInRequest[i10];
    }
}
