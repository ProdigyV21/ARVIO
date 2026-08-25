package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import r3.b;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbv implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        int iZ0 = 0;
        ArrayList arrayListB0 = null;
        Bundle bundleP = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 2) {
                arrayListB0 = d.b0(parcel, i10, b.CREATOR);
            } else if (c10 == 3) {
                bundleP = d.P(i10, parcel);
            } else if (c10 != 4) {
                d.J0(i10, parcel);
            } else {
                iZ0 = d.z0(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new MediaMetadata(arrayListB0, bundleP, iZ0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new MediaMetadata[i10];
    }
}
