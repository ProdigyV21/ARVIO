package com.google.android.gms.auth;

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
        int iZ0 = 0;
        int iZ02 = 0;
        int iZ03 = 0;
        long jB0 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 2:
                    jB0 = d.B0(i10, parcel);
                    break;
                case 3:
                    strV = d.V(i10, parcel);
                    break;
                case 4:
                    iZ02 = d.z0(i10, parcel);
                    break;
                case 5:
                    iZ03 = d.z0(i10, parcel);
                    break;
                case 6:
                    strV2 = d.V(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new AccountChangeEvent(iZ0, jB0, strV, iZ02, iZ03, strV2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new AccountChangeEvent[i10];
    }
}
