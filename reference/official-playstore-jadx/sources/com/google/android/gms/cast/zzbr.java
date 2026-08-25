package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbr implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        String strV2 = null;
        MediaMetadata mediaMetadata = null;
        ArrayList arrayListB0 = null;
        TextTrackStyle textTrackStyle = null;
        String strV3 = null;
        ArrayList arrayListB02 = null;
        ArrayList arrayListB03 = null;
        String strV4 = null;
        VastAdsRequest vastAdsRequest = null;
        String strV5 = null;
        String strV6 = null;
        String strV7 = null;
        String strV8 = null;
        long jB0 = 0;
        long jB02 = 0;
        int iZ0 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    strV = d.V(i10, parcel);
                    break;
                case 3:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 4:
                    strV2 = d.V(i10, parcel);
                    break;
                case 5:
                    mediaMetadata = (MediaMetadata) d.T(parcel, i10, MediaMetadata.CREATOR);
                    break;
                case 6:
                    jB0 = d.B0(i10, parcel);
                    break;
                case 7:
                    arrayListB0 = d.b0(parcel, i10, MediaTrack.CREATOR);
                    break;
                case '\b':
                    textTrackStyle = (TextTrackStyle) d.T(parcel, i10, TextTrackStyle.CREATOR);
                    break;
                case '\t':
                    strV3 = d.V(i10, parcel);
                    break;
                case '\n':
                    arrayListB02 = d.b0(parcel, i10, AdBreakInfo.CREATOR);
                    break;
                case 11:
                    arrayListB03 = d.b0(parcel, i10, AdBreakClipInfo.CREATOR);
                    break;
                case '\f':
                    strV4 = d.V(i10, parcel);
                    break;
                case '\r':
                    vastAdsRequest = (VastAdsRequest) d.T(parcel, i10, VastAdsRequest.CREATOR);
                    break;
                case 14:
                    jB02 = d.B0(i10, parcel);
                    break;
                case 15:
                    strV5 = d.V(i10, parcel);
                    break;
                case 16:
                    strV6 = d.V(i10, parcel);
                    break;
                case 17:
                    strV7 = d.V(i10, parcel);
                    break;
                case 18:
                    strV8 = d.V(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new MediaInfo(strV, iZ0, strV2, mediaMetadata, jB0, arrayListB0, textTrackStyle, strV3, arrayListB02, arrayListB03, strV4, vastAdsRequest, jB02, strV5, strV6, strV7, strV8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new MediaInfo[i10];
    }
}
