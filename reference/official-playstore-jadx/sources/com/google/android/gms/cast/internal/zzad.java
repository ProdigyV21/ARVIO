package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzad implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        double dW0 = 0.0d;
        double dW02 = 0.0d;
        ApplicationMetadata applicationMetadata = null;
        com.google.android.gms.cast.zzao zzaoVar = null;
        boolean zU0 = false;
        int iZ0 = 0;
        int iZ02 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    dW0 = d.w0(i10, parcel);
                    break;
                case 3:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 4:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 5:
                    applicationMetadata = (ApplicationMetadata) d.T(parcel, i10, ApplicationMetadata.CREATOR);
                    break;
                case 6:
                    iZ02 = d.z0(i10, parcel);
                    break;
                case 7:
                    zzaoVar = (com.google.android.gms.cast.zzao) d.T(parcel, i10, com.google.android.gms.cast.zzao.CREATOR);
                    break;
                case '\b':
                    dW02 = d.w0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new zzac(dW0, zU0, iZ0, applicationMetadata, iZ02, zzaoVar, dW02);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new zzac[i10];
    }
}
