package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzc implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        long jB0 = 0;
        long jB02 = 0;
        long jB03 = 0;
        String strV = null;
        String strV2 = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 2) {
                jB0 = d.B0(i10, parcel);
            } else if (c10 == 3) {
                jB02 = d.B0(i10, parcel);
            } else if (c10 == 4) {
                strV = d.V(i10, parcel);
            } else if (c10 == 5) {
                strV2 = d.V(i10, parcel);
            } else if (c10 != 6) {
                d.J0(i10, parcel);
            } else {
                jB03 = d.B0(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new AdBreakStatus(jB0, jB02, strV, strV2, jB03);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new AdBreakStatus[i10];
    }
}
