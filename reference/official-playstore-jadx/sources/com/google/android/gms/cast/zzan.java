package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzan implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        float fX0 = 0.0f;
        float fX02 = 0.0f;
        float fX03 = 0.0f;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 2) {
                fX0 = d.x0(i10, parcel);
            } else if (c10 == 3) {
                fX02 = d.x0(i10, parcel);
            } else if (c10 != 4) {
                d.J0(i10, parcel);
            } else {
                fX03 = d.x0(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new zzam(fX0, fX02, fX03);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new zzam[i10];
    }
}
