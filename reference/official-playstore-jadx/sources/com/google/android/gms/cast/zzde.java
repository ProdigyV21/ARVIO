package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzde implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        MediaLoadRequestData mediaLoadRequestData = null;
        String strV = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 2) {
                mediaLoadRequestData = (MediaLoadRequestData) d.T(parcel, i10, MediaLoadRequestData.CREATOR);
            } else if (c10 != 3) {
                d.J0(i10, parcel);
            } else {
                strV = d.V(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new SessionState(mediaLoadRequestData, CastUtils.jsonStringToJsonObject(strV));
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new SessionState[i10];
    }
}
