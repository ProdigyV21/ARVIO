package com.google.android.gms.cast;

import android.net.Network;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import r3.b;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzr implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        String strV = null;
        String strV2 = null;
        String strV3 = null;
        String strV4 = null;
        String strV5 = null;
        ArrayList arrayListB0 = null;
        String strV6 = null;
        String strV7 = null;
        String strV8 = null;
        byte[] bArrQ = null;
        String strV9 = null;
        com.google.android.gms.cast.internal.zzaa zzaaVar = null;
        Integer numA0 = null;
        Boolean boolV0 = null;
        Network network = null;
        int iZ0 = 0;
        int iZ02 = 0;
        int iZ03 = 0;
        boolean zU0 = false;
        int iZ04 = -1;
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
                    strV5 = d.V(i10, parcel);
                    break;
                case 7:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case '\b':
                    arrayListB0 = d.b0(parcel, i10, b.CREATOR);
                    break;
                case '\t':
                    iZ02 = d.z0(i10, parcel);
                    break;
                case '\n':
                    iZ04 = d.z0(i10, parcel);
                    break;
                case 11:
                    strV6 = d.V(i10, parcel);
                    break;
                case '\f':
                    strV7 = d.V(i10, parcel);
                    break;
                case '\r':
                    iZ03 = d.z0(i10, parcel);
                    break;
                case 14:
                    strV8 = d.V(i10, parcel);
                    break;
                case 15:
                    bArrQ = d.Q(i10, parcel);
                    break;
                case 16:
                    strV9 = d.V(i10, parcel);
                    break;
                case 17:
                    zU0 = d.u0(i10, parcel);
                    break;
                case 18:
                    zzaaVar = (com.google.android.gms.cast.internal.zzaa) d.T(parcel, i10, com.google.android.gms.cast.internal.zzaa.CREATOR);
                    break;
                case 19:
                    numA0 = d.A0(i10, parcel);
                    break;
                case 20:
                    boolV0 = d.v0(i10, parcel);
                    break;
                case 21:
                    network = (Network) d.T(parcel, i10, Network.CREATOR);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new CastDevice(strV, strV2, strV3, strV4, strV5, iZ0, arrayListB0, iZ02, iZ04, strV6, strV7, iZ03, strV8, bArrQ, strV9, zU0, zzaaVar, numA0, boolV0, network);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new CastDevice[i10];
    }
}
