package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import i4.r;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zbu implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        String strV4 = null;
        Uri uri = null;
        String strV5 = null;
        String strV6 = null;
        String strV7 = null;
        r rVar = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    strV = d.V(i10, parcel);
                    break;
                case 2:
                    strV2 = d.V(i10, parcel);
                    break;
                case 3:
                    strV3 = d.V(i10, parcel);
                    break;
                case 4:
                    strV4 = d.V(i10, parcel);
                    break;
                case 5:
                    uri = (Uri) d.T(parcel, i10, Uri.CREATOR);
                    break;
                case 6:
                    strV5 = d.V(i10, parcel);
                    break;
                case 7:
                    strV6 = d.V(i10, parcel);
                    break;
                case '\b':
                    strV7 = d.V(i10, parcel);
                    break;
                case '\t':
                    rVar = (r) d.T(parcel, i10, r.CREATOR);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new SignInCredential(strV, strV2, strV3, strV4, uri, strV5, strV6, strV7, rVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new SignInCredential[i10];
    }
}
