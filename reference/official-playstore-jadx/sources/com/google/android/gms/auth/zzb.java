package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzb implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        int iZ0 = 0;
        int iZ02 = 0;
        Account account = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 1) {
                iZ0 = d.z0(i10, parcel);
            } else if (c10 == 2) {
                iZ02 = d.z0(i10, parcel);
            } else if (c10 == 3) {
                strV = d.V(i10, parcel);
            } else if (c10 != 4) {
                d.J0(i10, parcel);
            } else {
                account = (Account) d.T(parcel, i10, Account.CREATOR);
            }
        }
        d.e0(iL0, parcel);
        return new AccountChangeEventsRequest(iZ0, iZ02, strV, account);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new AccountChangeEventsRequest[i10];
    }
}
