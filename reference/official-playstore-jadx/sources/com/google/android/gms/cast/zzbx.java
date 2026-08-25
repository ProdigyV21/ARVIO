package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbx implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        int iZ0 = 0;
        int iZ02 = 0;
        int iZ03 = 0;
        boolean zU0 = false;
        long jB0 = 0;
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        MediaQueueContainerMetadata mediaQueueContainerMetadata = null;
        ArrayList arrayListB0 = null;
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
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 5:
                    strV3 = d.V(i10, parcel);
                    break;
                case 6:
                    mediaQueueContainerMetadata = (MediaQueueContainerMetadata) d.T(parcel, i10, MediaQueueContainerMetadata.CREATOR);
                    break;
                case 7:
                    iZ02 = d.z0(i10, parcel);
                    break;
                case '\b':
                    arrayListB0 = d.b0(parcel, i10, MediaQueueItem.CREATOR);
                    break;
                case '\t':
                    iZ03 = d.z0(i10, parcel);
                    break;
                case '\n':
                    jB0 = d.B0(i10, parcel);
                    break;
                case 11:
                    zU0 = d.u0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new MediaQueueData(strV, strV2, iZ0, strV3, mediaQueueContainerMetadata, iZ02, arrayListB0, iZ03, jB0, zU0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new MediaQueueData[i10];
    }
}
