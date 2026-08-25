package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzm implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        Long lValueOf = null;
        ArrayList arrayListX = null;
        String strV2 = null;
        int iZ0 = 0;
        boolean zU0 = false;
        boolean zU02 = false;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 2:
                    strV = d.V(i10, parcel);
                    break;
                case 3:
                    int iC0 = d.C0(i10, parcel);
                    if (iC0 != 0) {
                        d.P0(parcel, iC0, 8);
                        lValueOf = Long.valueOf(parcel.readLong());
                    } else {
                        lValueOf = null;
                    }
                    break;
                case 4:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 5:
                    zU02 = d.u0(i10, parcel);
                    break;
                case 6:
                    arrayListX = d.X(i10, parcel);
                    break;
                case 7:
                    strV2 = d.V(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new TokenData(iZ0, strV, lValueOf, zU0, zU02, arrayListX, strV2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new TokenData[i10];
    }
}
