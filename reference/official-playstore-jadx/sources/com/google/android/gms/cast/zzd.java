package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import r3.b;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzd implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        int iZ0 = 0;
        String strV = null;
        String strV2 = null;
        ArrayList arrayListB0 = null;
        ArrayList arrayListX = null;
        String strV3 = null;
        Uri uri = null;
        String strV4 = null;
        String strV5 = null;
        Boolean boolV0 = null;
        Boolean boolV02 = null;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    strV = d.V(i10, parcel);
                    break;
                case 3:
                    strV2 = d.V(i10, parcel);
                    break;
                case 4:
                    arrayListB0 = d.b0(parcel, i10, b.CREATOR);
                    break;
                case 5:
                    arrayListX = d.X(i10, parcel);
                    break;
                case 6:
                    strV3 = d.V(i10, parcel);
                    break;
                case 7:
                    uri = (Uri) d.T(parcel, i10, Uri.CREATOR);
                    break;
                case '\b':
                    strV4 = d.V(i10, parcel);
                    break;
                case '\t':
                    strV5 = d.V(i10, parcel);
                    break;
                case '\n':
                    boolV0 = d.v0(i10, parcel);
                    break;
                case 11:
                    boolV02 = d.v0(i10, parcel);
                    break;
                case '\f':
                    iZ0 = d.z0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new ApplicationMetadata(strV, strV2, arrayListB0, arrayListX, strV3, uri, strV4, strV5, boolV0, boolV02, iZ0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new ApplicationMetadata[i10];
    }
}
