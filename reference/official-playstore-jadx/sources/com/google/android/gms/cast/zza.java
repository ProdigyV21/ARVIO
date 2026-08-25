package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zza implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        String strV4 = null;
        String strV5 = null;
        String strV6 = null;
        String strV7 = null;
        String strV8 = null;
        String strV9 = null;
        VastAdsRequest vastAdsRequest = null;
        long jB0 = 0;
        long jB02 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    strV = d.V(i10, parcel);
                    break;
                case 3:
                    strV2 = d.V(i10, parcel);
                    break;
                case 4:
                    jB0 = d.B0(i10, parcel);
                    break;
                case 5:
                    strV3 = d.V(i10, parcel);
                    break;
                case 6:
                    strV4 = d.V(i10, parcel);
                    break;
                case 7:
                    strV5 = d.V(i10, parcel);
                    break;
                case '\b':
                    strV6 = d.V(i10, parcel);
                    break;
                case '\t':
                    strV7 = d.V(i10, parcel);
                    break;
                case '\n':
                    strV8 = d.V(i10, parcel);
                    break;
                case 11:
                    jB02 = d.B0(i10, parcel);
                    break;
                case '\f':
                    strV9 = d.V(i10, parcel);
                    break;
                case '\r':
                    vastAdsRequest = (VastAdsRequest) d.T(parcel, i10, VastAdsRequest.CREATOR);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new AdBreakClipInfo(strV, strV2, jB0, strV3, strV4, strV5, strV6, strV7, strV8, jB02, strV9, vastAdsRequest);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new AdBreakClipInfo[i10];
    }
}
