package com.google.android.gms.auth.api.accounttransfer;

import a0.c;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import java.util.HashSet;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzv implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        HashSet hashSet = new HashSet();
        int iZ0 = 0;
        zzw zzwVar = null;
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 1) {
                iZ0 = d.z0(i10, parcel);
                hashSet.add(1);
            } else if (c10 == 2) {
                zzwVar = (zzw) d.T(parcel, i10, zzw.CREATOR);
                hashSet.add(2);
            } else if (c10 == 3) {
                strV = d.V(i10, parcel);
                hashSet.add(3);
            } else if (c10 == 4) {
                strV2 = d.V(i10, parcel);
                hashSet.add(4);
            } else if (c10 != 5) {
                d.J0(i10, parcel);
            } else {
                strV3 = d.V(i10, parcel);
                hashSet.add(5);
            }
        }
        if (parcel.dataPosition() == iL0) {
            return new zzu(hashSet, iZ0, zzwVar, strV, strV2, strV3);
        }
        throw new SafeParcelReader$ParseException(c.i(iL0, "Overread allowed size end="), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new zzu[i10];
    }
}
