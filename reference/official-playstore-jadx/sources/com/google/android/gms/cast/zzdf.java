package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzdf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        String strV2 = null;
        int iZ0 = 0;
        int iZ02 = 0;
        int iZ03 = 0;
        int iZ04 = 0;
        int iZ05 = 0;
        int iZ06 = 0;
        int iZ07 = 0;
        int iZ08 = 0;
        int iZ09 = 0;
        float fX0 = 0.0f;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    fX0 = d.x0(i10, parcel);
                    break;
                case 3:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 4:
                    iZ02 = d.z0(i10, parcel);
                    break;
                case 5:
                    iZ03 = d.z0(i10, parcel);
                    break;
                case 6:
                    iZ04 = d.z0(i10, parcel);
                    break;
                case 7:
                    iZ05 = d.z0(i10, parcel);
                    break;
                case '\b':
                    iZ06 = d.z0(i10, parcel);
                    break;
                case '\t':
                    iZ07 = d.z0(i10, parcel);
                    break;
                case '\n':
                    strV = d.V(i10, parcel);
                    break;
                case 11:
                    iZ08 = d.z0(i10, parcel);
                    break;
                case '\f':
                    iZ09 = d.z0(i10, parcel);
                    break;
                case '\r':
                    strV2 = d.V(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new TextTrackStyle(fX0, iZ0, iZ02, iZ03, iZ04, iZ05, iZ06, iZ07, strV, iZ08, iZ09, strV2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new TextTrackStyle[i10];
    }
}
