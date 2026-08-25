package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzdh implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        int iZ0 = 0;
        int iZ02 = 0;
        int iZ03 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 2) {
                iZ0 = d.z0(i10, parcel);
            } else if (c10 == 3) {
                iZ02 = d.z0(i10, parcel);
            } else if (c10 != 4) {
                d.J0(i10, parcel);
            } else {
                iZ03 = d.z0(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new VideoInfo(iZ0, iZ02, iZ03);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new VideoInfo[i10];
    }
}
