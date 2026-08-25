package com.google.android.gms.auth.api.accounttransfer;

import a0.c;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import java.util.HashSet;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzx implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        HashSet hashSet = new HashSet();
        int iZ0 = 0;
        String strV = null;
        byte[] bArrQ = null;
        PendingIntent pendingIntent = null;
        DeviceMetaData deviceMetaData = null;
        int iZ02 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 1:
                    iZ0 = d.z0(i10, parcel);
                    hashSet.add(1);
                    break;
                case 2:
                    strV = d.V(i10, parcel);
                    hashSet.add(2);
                    break;
                case 3:
                    iZ02 = d.z0(i10, parcel);
                    hashSet.add(3);
                    break;
                case 4:
                    bArrQ = d.Q(i10, parcel);
                    hashSet.add(4);
                    break;
                case 5:
                    pendingIntent = (PendingIntent) d.T(parcel, i10, PendingIntent.CREATOR);
                    hashSet.add(5);
                    break;
                case 6:
                    deviceMetaData = (DeviceMetaData) d.T(parcel, i10, DeviceMetaData.CREATOR);
                    hashSet.add(6);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        if (parcel.dataPosition() == iL0) {
            return new zzw(hashSet, iZ0, strV, iZ02, bArrQ, pendingIntent, deviceMetaData);
        }
        throw new SafeParcelReader$ParseException(c.i(iL0, "Overread allowed size end="), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new zzw[i10];
    }
}
