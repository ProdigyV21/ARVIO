package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        ArrayList arrayListX = null;
        GoogleSignInAccount googleSignInAccount = null;
        PendingIntent pendingIntent = null;
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
                    arrayListX = d.X(i10, parcel);
                    break;
                case 5:
                    googleSignInAccount = (GoogleSignInAccount) d.T(parcel, i10, GoogleSignInAccount.CREATOR);
                    break;
                case 6:
                    pendingIntent = (PendingIntent) d.T(parcel, i10, PendingIntent.CREATOR);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new AuthorizationResult(strV, strV2, strV3, arrayListX, googleSignInAccount, pendingIntent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new AuthorizationResult[i10];
    }
}
