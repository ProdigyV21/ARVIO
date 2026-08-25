package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzab implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        int iZ0 = 0;
        boolean zU0 = false;
        boolean zU02 = false;
        boolean zU03 = false;
        boolean zU04 = false;
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        String strV4 = null;
        String strV5 = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 3:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 4:
                    zU02 = d.u0(i10, parcel);
                    break;
                case 5:
                    strV = d.V(i10, parcel);
                    break;
                case 6:
                    strV2 = d.V(i10, parcel);
                    break;
                case 7:
                    strV3 = d.V(i10, parcel);
                    break;
                case '\b':
                    strV4 = d.V(i10, parcel);
                    break;
                case '\t':
                    strV5 = d.V(i10, parcel);
                    break;
                case '\n':
                    zU03 = d.u0(i10, parcel);
                    break;
                case 11:
                    zU04 = d.u0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new zzaa(iZ0, zU0, zU02, strV, strV2, strV3, strV4, strV5, zU03, zU04);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new zzaa[i10];
    }
}
