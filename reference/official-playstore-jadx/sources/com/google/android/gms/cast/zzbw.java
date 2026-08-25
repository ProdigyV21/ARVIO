package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import r3.b;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbw implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        double dW0 = 0.0d;
        String strV = null;
        ArrayList arrayListB0 = null;
        ArrayList arrayListB02 = null;
        int iZ0 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 2) {
                iZ0 = d.z0(i10, parcel);
            } else if (c10 == 3) {
                strV = d.V(i10, parcel);
            } else if (c10 == 4) {
                arrayListB0 = d.b0(parcel, i10, MediaMetadata.CREATOR);
            } else if (c10 == 5) {
                arrayListB02 = d.b0(parcel, i10, b.CREATOR);
            } else if (c10 != 6) {
                d.J0(i10, parcel);
            } else {
                dW0 = d.w0(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new MediaQueueContainerMetadata(iZ0, strV, arrayListB0, arrayListB02, dW0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new MediaQueueContainerMetadata[i10];
    }
}
