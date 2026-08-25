package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbq implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int i10;
        int iL0 = d.L0(parcel);
        String strV = null;
        long jB0 = 0;
        String strV2 = null;
        Integer numA0 = null;
        String strV3 = null;
        while (true) {
            long j10 = jB0;
            while (parcel.dataPosition() < iL0) {
                i10 = parcel.readInt();
                char c10 = (char) i10;
                if (c10 == 2) {
                    strV2 = d.V(i10, parcel);
                } else if (c10 != 3) {
                    if (c10 == 4) {
                        numA0 = d.A0(i10, parcel);
                    } else if (c10 == 5) {
                        strV3 = d.V(i10, parcel);
                    } else if (c10 != 6) {
                        d.J0(i10, parcel);
                    } else {
                        strV = d.V(i10, parcel);
                    }
                }
            }
            d.e0(iL0, parcel);
            return new MediaError(strV2, j10, numA0, strV3, CastUtils.jsonStringToJsonObject(strV));
            jB0 = d.B0(i10, parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new MediaError[i10];
    }
}
