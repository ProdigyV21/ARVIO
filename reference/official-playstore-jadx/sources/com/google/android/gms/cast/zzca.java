package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzca implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        String strV4 = null;
        String strV5 = null;
        ArrayList arrayListX = null;
        int iZ0 = 0;
        int iZ02 = 0;
        long jB0 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    jB0 = d.B0(i10, parcel);
                    break;
                case 3:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 4:
                    strV2 = d.V(i10, parcel);
                    break;
                case 5:
                    strV3 = d.V(i10, parcel);
                    break;
                case 6:
                    strV4 = d.V(i10, parcel);
                    break;
                case 7:
                    strV5 = d.V(i10, parcel);
                    break;
                case '\b':
                    iZ02 = d.z0(i10, parcel);
                    break;
                case '\t':
                    arrayListX = d.X(i10, parcel);
                    break;
                case '\n':
                    strV = d.V(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new MediaTrack(jB0, iZ0, strV2, strV3, strV4, strV5, iZ02, arrayListX, CastUtils.jsonStringToJsonObject(strV));
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new MediaTrack[i10];
    }
}
