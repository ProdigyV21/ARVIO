package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class g implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = xc.d.L0(parcel);
        String strV = null;
        String strV2 = null;
        z3.b bVar = null;
        int iZ0 = 0;
        int iZ02 = 0;
        boolean zU0 = false;
        int iZ03 = 0;
        boolean zU02 = false;
        int iZ04 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    iZ0 = xc.d.z0(i10, parcel);
                    break;
                case 2:
                    iZ02 = xc.d.z0(i10, parcel);
                    break;
                case 3:
                    zU0 = xc.d.u0(i10, parcel);
                    break;
                case 4:
                    iZ03 = xc.d.z0(i10, parcel);
                    break;
                case 5:
                    zU02 = xc.d.u0(i10, parcel);
                    break;
                case 6:
                    strV = xc.d.V(i10, parcel);
                    break;
                case 7:
                    iZ04 = xc.d.z0(i10, parcel);
                    break;
                case '\b':
                    strV2 = xc.d.V(i10, parcel);
                    break;
                case '\t':
                    bVar = (z3.b) xc.d.T(parcel, i10, z3.b.CREATOR);
                    break;
                default:
                    xc.d.J0(i10, parcel);
                    break;
            }
        }
        xc.d.e0(iL0, parcel);
        return new a(iZ0, iZ02, zU0, iZ03, zU02, strV, iZ04, strV2, bVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new a[i10];
    }
}
