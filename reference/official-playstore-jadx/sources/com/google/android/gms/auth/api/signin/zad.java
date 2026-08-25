package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zad implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        ArrayList arrayListB0 = null;
        Account account = null;
        String strV = null;
        String strV2 = null;
        ArrayList arrayListB02 = null;
        String strV3 = null;
        int iZ0 = 0;
        boolean zU0 = false;
        boolean zU02 = false;
        boolean zU03 = false;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 2:
                    arrayListB0 = d.b0(parcel, i10, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) d.T(parcel, i10, Account.CREATOR);
                    break;
                case 4:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 5:
                    zU02 = d.u0(i10, parcel);
                    break;
                case 6:
                    zU03 = d.u0(i10, parcel);
                    break;
                case 7:
                    strV = d.V(i10, parcel);
                    break;
                case '\b':
                    strV2 = d.V(i10, parcel);
                    break;
                case '\t':
                    arrayListB02 = d.b0(parcel, i10, GoogleSignInOptionsExtensionParcelable.CREATOR);
                    break;
                case '\n':
                    strV3 = d.V(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new GoogleSignInOptions(iZ0, arrayListB0, account, zU0, zU02, zU03, strV, strV2, arrayListB02, strV3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new GoogleSignInOptions[i10];
    }
}
