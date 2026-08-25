package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbq implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        int iZ0 = 0;
        PendingIntent pendingIntent = null;
        String strV = null;
        String strV2 = null;
        ArrayList arrayListX = null;
        String strV3 = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    pendingIntent = (PendingIntent) d.T(parcel, i10, PendingIntent.CREATOR);
                    break;
                case 2:
                    strV = d.V(i10, parcel);
                    break;
                case 3:
                    strV2 = d.V(i10, parcel);
                    break;
                case 4:
                    arrayListX = d.X(i10, parcel);
                    break;
                case 5:
                    strV3 = d.V(i10, parcel);
                    break;
                case 6:
                    iZ0 = d.z0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new SaveAccountLinkingTokenRequest(pendingIntent, strV, strV2, arrayListX, strV3, iZ0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new SaveAccountLinkingTokenRequest[i10];
    }
}
