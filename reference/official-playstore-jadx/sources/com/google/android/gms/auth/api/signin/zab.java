package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zab implements Parcelable.Creator {
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
        ArrayList arrayListB0 = null;
        String strV7 = null;
        String strV8 = null;
        long jB0 = 0;
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
                    strV3 = d.V(i10, parcel);
                    break;
                case 5:
                    strV4 = d.V(i10, parcel);
                    break;
                case 6:
                    uri = (Uri) d.T(parcel, i10, Uri.CREATOR);
                    break;
                case 7:
                    strV5 = d.V(i10, parcel);
                    break;
                case '\b':
                    jB0 = d.B0(i10, parcel);
                    break;
                case '\t':
                    strV6 = d.V(i10, parcel);
                    break;
                case '\n':
                    arrayListB0 = d.b0(parcel, i10, Scope.CREATOR);
                    break;
                case 11:
                    strV7 = d.V(i10, parcel);
                    break;
                case '\f':
                    strV8 = d.V(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new GoogleSignInAccount(strV, strV2, strV3, strV4, uri, strV5, jB0, strV6, arrayListB0, strV7, strV8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new GoogleSignInAccount[i10];
    }
}
