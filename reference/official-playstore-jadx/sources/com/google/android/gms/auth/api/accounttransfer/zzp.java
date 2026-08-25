package com.google.android.gms.auth.api.accounttransfer;

import a0.c;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzp implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        HashSet hashSet = new HashSet();
        int iZ0 = 0;
        ArrayList arrayListB0 = null;
        zzs zzsVar = null;
        int iZ02 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 1) {
                iZ0 = d.z0(i10, parcel);
                hashSet.add(1);
            } else if (c10 == 2) {
                arrayListB0 = d.b0(parcel, i10, zzu.CREATOR);
                hashSet.add(2);
            } else if (c10 == 3) {
                iZ02 = d.z0(i10, parcel);
                hashSet.add(3);
            } else if (c10 != 4) {
                d.J0(i10, parcel);
            } else {
                zzsVar = (zzs) d.T(parcel, i10, zzs.CREATOR);
                hashSet.add(4);
            }
        }
        if (parcel.dataPosition() == iL0) {
            return new zzo(hashSet, iZ0, arrayListB0, iZ02, zzsVar);
        }
        throw new SafeParcelReader$ParseException(c.i(iL0, "Overread allowed size end="), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new zzo[i10];
    }
}
