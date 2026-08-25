package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzb implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            if (((char) i10) != 2) {
                d.J0(i10, parcel);
            } else {
                strV = d.V(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new zza(strV);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new zza[i10];
    }
}
