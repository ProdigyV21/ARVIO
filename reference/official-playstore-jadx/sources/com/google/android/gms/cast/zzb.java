package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzb implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        boolean zU0 = false;
        boolean zU02 = false;
        boolean zU03 = false;
        String strV = null;
        String[] strArrW = null;
        long jB0 = 0;
        long jB02 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    jB0 = d.B0(i10, parcel);
                    break;
                case 3:
                    strV = d.V(i10, parcel);
                    break;
                case 4:
                    jB02 = d.B0(i10, parcel);
                    break;
                case 5:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 6:
                    strArrW = d.W(i10, parcel);
                    break;
                case 7:
                    zU02 = d.u0(i10, parcel);
                    break;
                case '\b':
                    zU03 = d.u0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new AdBreakInfo(jB0, strV, jB02, zU0, strArrW, zU02, zU03);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new AdBreakInfo[i10];
    }
}
