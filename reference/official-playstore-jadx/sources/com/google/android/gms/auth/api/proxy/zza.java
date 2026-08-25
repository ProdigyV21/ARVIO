package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zza implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        byte[] bArrQ = null;
        Bundle bundleP = null;
        long jB0 = 0;
        int iZ0 = 0;
        int iZ02 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            char c10 = (char) i10;
            if (c10 == 1) {
                strV = d.V(i10, parcel);
            } else if (c10 == 2) {
                iZ02 = d.z0(i10, parcel);
            } else if (c10 == 3) {
                jB0 = d.B0(i10, parcel);
            } else if (c10 == 4) {
                bArrQ = d.Q(i10, parcel);
            } else if (c10 == 5) {
                bundleP = d.P(i10, parcel);
            } else if (c10 != 1000) {
                d.J0(i10, parcel);
            } else {
                iZ0 = d.z0(i10, parcel);
            }
        }
        d.e0(iL0, parcel);
        return new ProxyRequest(iZ0, strV, iZ02, jB0, bArrQ, bundleP);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new ProxyRequest[i10];
    }
}
