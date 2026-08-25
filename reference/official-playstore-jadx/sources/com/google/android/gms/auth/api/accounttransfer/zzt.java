package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzt implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        ArrayList arrayListX = null;
        ArrayList arrayListX2 = null;
        ArrayList arrayListX3 = null;
        ArrayList arrayListX4 = null;
        ArrayList arrayListX5 = null;
        int iZ0 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 2:
                    arrayListX = d.X(i10, parcel);
                    break;
                case 3:
                    arrayListX2 = d.X(i10, parcel);
                    break;
                case 4:
                    arrayListX3 = d.X(i10, parcel);
                    break;
                case 5:
                    arrayListX4 = d.X(i10, parcel);
                    break;
                case 6:
                    arrayListX5 = d.X(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new zzs(iZ0, arrayListX, arrayListX2, arrayListX3, arrayListX4, arrayListX5);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new zzs[i10];
    }
}
