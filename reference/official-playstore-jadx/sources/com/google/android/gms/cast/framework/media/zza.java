package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zza implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        boolean zU0 = false;
        boolean zU02 = false;
        String strV = null;
        String strV2 = null;
        IBinder iBinderY0 = null;
        NotificationOptions notificationOptions = null;
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
                    iBinderY0 = d.y0(i10, parcel);
                    break;
                case 5:
                    notificationOptions = (NotificationOptions) d.T(parcel, i10, NotificationOptions.CREATOR);
                    break;
                case 6:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 7:
                    zU02 = d.u0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new CastMediaOptions(strV, strV2, iBinderY0, notificationOptions, zU0, zU02);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new CastMediaOptions[i10];
    }
}
