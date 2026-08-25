package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzn implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        boolean zU0 = false;
        boolean zU02 = false;
        boolean zU03 = false;
        boolean zU04 = false;
        boolean zU05 = false;
        boolean zU06 = false;
        boolean zU07 = false;
        int iZ0 = 0;
        boolean zU08 = false;
        boolean zU09 = false;
        boolean zU010 = false;
        String strV = null;
        ArrayList arrayListX = null;
        LaunchOptions launchOptions = null;
        CastMediaOptions castMediaOptions = null;
        ArrayList arrayListX2 = null;
        zzj zzjVar = null;
        zzl zzlVar = null;
        double dW0 = 0.0d;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    strV = d.V(i10, parcel);
                    break;
                case 3:
                    arrayListX = d.X(i10, parcel);
                    break;
                case 4:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 5:
                    launchOptions = (LaunchOptions) d.T(parcel, i10, LaunchOptions.CREATOR);
                    break;
                case 6:
                    zU02 = d.u0(i10, parcel);
                    break;
                case 7:
                    castMediaOptions = (CastMediaOptions) d.T(parcel, i10, CastMediaOptions.CREATOR);
                    break;
                case '\b':
                    zU03 = d.u0(i10, parcel);
                    break;
                case '\t':
                    dW0 = d.w0(i10, parcel);
                    break;
                case '\n':
                    zU04 = d.u0(i10, parcel);
                    break;
                case 11:
                    zU05 = d.u0(i10, parcel);
                    break;
                case '\f':
                    zU06 = d.u0(i10, parcel);
                    break;
                case '\r':
                    arrayListX2 = d.X(i10, parcel);
                    break;
                case 14:
                    zU07 = d.u0(i10, parcel);
                    break;
                case 15:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 16:
                    zU08 = d.u0(i10, parcel);
                    break;
                case 17:
                    zzjVar = (zzj) d.T(parcel, i10, zzj.CREATOR);
                    break;
                case 18:
                    zzlVar = (zzl) d.T(parcel, i10, zzl.CREATOR);
                    break;
                case 19:
                    zU09 = d.u0(i10, parcel);
                    break;
                case 20:
                    zU010 = d.u0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new CastOptions(strV, arrayListX, zU0, launchOptions, zU02, castMediaOptions, zU03, dW0, zU04, zU05, zU06, arrayListX2, zU07, iZ0, zU08, zzjVar, zzlVar, zU09, zU010);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new CastOptions[i10];
    }
}
