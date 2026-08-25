package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbz implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        MediaInfo mediaInfo = null;
        long[] jArrS = null;
        String strV = null;
        ArrayList arrayListB0 = null;
        AdBreakStatus adBreakStatus = null;
        VideoInfo videoInfo = null;
        MediaLiveSeekableRange mediaLiveSeekableRange = null;
        MediaQueueData mediaQueueData = null;
        int iZ0 = 0;
        int iZ02 = 0;
        int iZ03 = 0;
        boolean zU0 = false;
        int iZ04 = 0;
        int iZ05 = 0;
        int iZ06 = 0;
        boolean zU02 = false;
        double dW0 = 0.0d;
        double dW02 = 0.0d;
        long jB0 = 0;
        long jB02 = 0;
        long jB03 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    mediaInfo = (MediaInfo) d.T(parcel, i10, MediaInfo.CREATOR);
                    break;
                case 3:
                    jB0 = d.B0(i10, parcel);
                    break;
                case 4:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 5:
                    dW0 = d.w0(i10, parcel);
                    break;
                case 6:
                    iZ02 = d.z0(i10, parcel);
                    break;
                case 7:
                    iZ03 = d.z0(i10, parcel);
                    break;
                case '\b':
                    jB02 = d.B0(i10, parcel);
                    break;
                case '\t':
                    jB03 = d.B0(i10, parcel);
                    break;
                case '\n':
                    dW02 = d.w0(i10, parcel);
                    break;
                case 11:
                    zU0 = d.u0(i10, parcel);
                    break;
                case '\f':
                    jArrS = d.S(i10, parcel);
                    break;
                case '\r':
                    iZ04 = d.z0(i10, parcel);
                    break;
                case 14:
                    iZ05 = d.z0(i10, parcel);
                    break;
                case 15:
                    strV = d.V(i10, parcel);
                    break;
                case 16:
                    iZ06 = d.z0(i10, parcel);
                    break;
                case 17:
                    arrayListB0 = d.b0(parcel, i10, MediaQueueItem.CREATOR);
                    break;
                case 18:
                    zU02 = d.u0(i10, parcel);
                    break;
                case 19:
                    adBreakStatus = (AdBreakStatus) d.T(parcel, i10, AdBreakStatus.CREATOR);
                    break;
                case 20:
                    videoInfo = (VideoInfo) d.T(parcel, i10, VideoInfo.CREATOR);
                    break;
                case 21:
                    mediaLiveSeekableRange = (MediaLiveSeekableRange) d.T(parcel, i10, MediaLiveSeekableRange.CREATOR);
                    break;
                case 22:
                    mediaQueueData = (MediaQueueData) d.T(parcel, i10, MediaQueueData.CREATOR);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new MediaStatus(mediaInfo, jB0, iZ0, dW0, iZ02, iZ03, jB02, jB03, dW02, zU0, jArrS, iZ04, iZ05, strV, iZ06, arrayListB0, zU02, adBreakStatus, videoInfo, mediaLiveSeekableRange, mediaQueueData);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new MediaStatus[i10];
    }
}
