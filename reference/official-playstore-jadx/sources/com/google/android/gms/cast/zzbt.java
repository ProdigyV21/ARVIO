package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbt implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        long jB0 = 0;
        long jB02 = 0;
        MediaInfo mediaInfo = null;
        MediaQueueData mediaQueueData = null;
        Boolean boolV0 = null;
        long[] jArrS = null;
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        String strV4 = null;
        String strV5 = null;
        double dW0 = 0.0d;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    mediaInfo = (MediaInfo) d.T(parcel, i10, MediaInfo.CREATOR);
                    break;
                case 3:
                    mediaQueueData = (MediaQueueData) d.T(parcel, i10, MediaQueueData.CREATOR);
                    break;
                case 4:
                    boolV0 = d.v0(i10, parcel);
                    break;
                case 5:
                    jB0 = d.B0(i10, parcel);
                    break;
                case 6:
                    dW0 = d.w0(i10, parcel);
                    break;
                case 7:
                    jArrS = d.S(i10, parcel);
                    break;
                case '\b':
                    strV = d.V(i10, parcel);
                    break;
                case '\t':
                    strV2 = d.V(i10, parcel);
                    break;
                case '\n':
                    strV3 = d.V(i10, parcel);
                    break;
                case 11:
                    strV4 = d.V(i10, parcel);
                    break;
                case '\f':
                    strV5 = d.V(i10, parcel);
                    break;
                case '\r':
                    jB02 = d.B0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new MediaLoadRequestData(mediaInfo, mediaQueueData, boolV0, jB0, dW0, jArrS, strV, strV2, strV3, strV4, strV5, jB02);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new MediaLoadRequestData[i10];
    }
}
