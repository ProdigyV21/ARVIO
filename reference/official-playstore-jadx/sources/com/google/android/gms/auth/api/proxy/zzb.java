package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzb implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        PendingIntent pendingIntent = null;
        Bundle bundleP = null;
        byte[] bArrQ = null;
        int iZ0 = 0;
        int iZ02 = 0;
        int iZ03 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 1) {
                iZ02 = d.z0(i10, parcel);
            } else if (c10 == 2) {
                pendingIntent = (PendingIntent) d.T(parcel, i10, PendingIntent.CREATOR);
            } else if (c10 == 3) {
                iZ03 = d.z0(i10, parcel);
            } else if (c10 == 4) {
                bundleP = d.P(i10, parcel);
            } else if (c10 == 5) {
                bArrQ = d.Q(i10, parcel);
            } else if (c10 != 1000) {
                d.J0(i10, parcel);
            } else {
                iZ0 = d.z0(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new ProxyResponse(iZ0, iZ02, pendingIntent, iZ03, bundleP, bArrQ);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new ProxyResponse[i10];
    }
}
