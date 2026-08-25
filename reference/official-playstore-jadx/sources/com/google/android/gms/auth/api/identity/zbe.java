package com.google.android.gms.auth.api.identity;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbe implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        ArrayList arrayListB0 = null;
        String strV = null;
        Account account = null;
        String strV2 = null;
        String strV3 = null;
        Bundle bundleP = null;
        boolean zU0 = false;
        boolean zU02 = false;
        boolean zU03 = false;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    arrayListB0 = d.b0(parcel, i10, Scope.CREATOR);
                    break;
                case 2:
                    strV = d.V(i10, parcel);
                    break;
                case 3:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 4:
                    zU02 = d.u0(i10, parcel);
                    break;
                case 5:
                    account = (Account) d.T(parcel, i10, Account.CREATOR);
                    break;
                case 6:
                    strV2 = d.V(i10, parcel);
                    break;
                case 7:
                    strV3 = d.V(i10, parcel);
                    break;
                case '\b':
                    zU03 = d.u0(i10, parcel);
                    break;
                case '\t':
                    bundleP = d.P(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new AuthorizationRequest(arrayListB0, strV, zU0, zU02, account, strV2, strV3, zU03, bundleP);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new AuthorizationRequest[i10];
    }
}
