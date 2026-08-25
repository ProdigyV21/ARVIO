package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzap implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        zzam zzamVar = null;
        zzam zzamVar2 = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 2) {
                zzamVar = (zzam) d.T(parcel, i10, zzam.CREATOR);
            } else if (c10 != 3) {
                d.J0(i10, parcel);
            } else {
                zzamVar2 = (zzam) d.T(parcel, i10, zzam.CREATOR);
            }
        }
        d.e0(iL0, parcel);
        return new zzao(zzamVar, zzamVar2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new zzao[i10];
    }
}
