package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzby implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        MediaInfo mediaInfo = null;
        long[] jArrS = null;
        String strV = null;
        double dW0 = 0.0d;
        double dW02 = 0.0d;
        double dW03 = 0.0d;
        int iZ0 = 0;
        boolean zU0 = false;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    mediaInfo = (MediaInfo) d.T(parcel, i10, MediaInfo.CREATOR);
                    break;
                case 3:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 4:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 5:
                    dW0 = d.w0(i10, parcel);
                    break;
                case 6:
                    dW02 = d.w0(i10, parcel);
                    break;
                case 7:
                    dW03 = d.w0(i10, parcel);
                    break;
                case '\b':
                    jArrS = d.S(i10, parcel);
                    break;
                case '\t':
                    strV = d.V(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new MediaQueueItem(mediaInfo, iZ0, zU0, dW0, dW02, dW03, jArrS, strV);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new MediaQueueItem[i10];
    }
}
