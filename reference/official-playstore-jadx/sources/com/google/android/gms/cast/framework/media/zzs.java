package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import xc.d;

/* JADX INFO: loaded from: classes4.dex */
public final class zzs implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int iL0 = d.L0(parcel);
        int iZ0 = 0;
        int iZ02 = 0;
        int iZ03 = 0;
        int iZ04 = 0;
        int iZ05 = 0;
        int iZ06 = 0;
        int iZ07 = 0;
        int iZ08 = 0;
        int iZ09 = 0;
        int iZ010 = 0;
        int iZ011 = 0;
        int iZ012 = 0;
        int iZ013 = 0;
        int iZ014 = 0;
        int iZ015 = 0;
        int iZ016 = 0;
        int iZ017 = 0;
        int iZ018 = 0;
        int iZ019 = 0;
        int iZ020 = 0;
        int iZ021 = 0;
        int iZ022 = 0;
        int iZ023 = 0;
        int iZ024 = 0;
        int iZ025 = 0;
        int iZ026 = 0;
        int iZ027 = 0;
        boolean zU0 = false;
        boolean zU02 = false;
        ArrayList arrayListX = null;
        int[] iArrR = null;
        String strV = null;
        IBinder iBinderY0 = null;
        long jB0 = 0;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            switch ((char) i10) {
                case 2:
                    arrayListX = d.X(i10, parcel);
                    break;
                case 3:
                    iArrR = d.R(i10, parcel);
                    break;
                case 4:
                    jB0 = d.B0(i10, parcel);
                    break;
                case 5:
                    strV = d.V(i10, parcel);
                    break;
                case 6:
                    iZ0 = d.z0(i10, parcel);
                    break;
                case 7:
                    iZ02 = d.z0(i10, parcel);
                    break;
                case '\b':
                    iZ03 = d.z0(i10, parcel);
                    break;
                case '\t':
                    iZ04 = d.z0(i10, parcel);
                    break;
                case '\n':
                    iZ05 = d.z0(i10, parcel);
                    break;
                case 11:
                    iZ06 = d.z0(i10, parcel);
                    break;
                case '\f':
                    iZ07 = d.z0(i10, parcel);
                    break;
                case '\r':
                    iZ08 = d.z0(i10, parcel);
                    break;
                case 14:
                    iZ09 = d.z0(i10, parcel);
                    break;
                case 15:
                    iZ010 = d.z0(i10, parcel);
                    break;
                case 16:
                    iZ011 = d.z0(i10, parcel);
                    break;
                case 17:
                    iZ012 = d.z0(i10, parcel);
                    break;
                case 18:
                    iZ013 = d.z0(i10, parcel);
                    break;
                case 19:
                    iZ014 = d.z0(i10, parcel);
                    break;
                case 20:
                    iZ015 = d.z0(i10, parcel);
                    break;
                case 21:
                    iZ016 = d.z0(i10, parcel);
                    break;
                case 22:
                    iZ017 = d.z0(i10, parcel);
                    break;
                case 23:
                    iZ018 = d.z0(i10, parcel);
                    break;
                case 24:
                    iZ019 = d.z0(i10, parcel);
                    break;
                case 25:
                    iZ020 = d.z0(i10, parcel);
                    break;
                case 26:
                    iZ021 = d.z0(i10, parcel);
                    break;
                case 27:
                    iZ022 = d.z0(i10, parcel);
                    break;
                case 28:
                    iZ023 = d.z0(i10, parcel);
                    break;
                case 29:
                    iZ024 = d.z0(i10, parcel);
                    break;
                case 30:
                    iZ025 = d.z0(i10, parcel);
                    break;
                case 31:
                    iZ026 = d.z0(i10, parcel);
                    break;
                case ' ':
                    iZ027 = d.z0(i10, parcel);
                    break;
                case '!':
                    iBinderY0 = d.y0(i10, parcel);
                    break;
                case '\"':
                    zU0 = d.u0(i10, parcel);
                    break;
                case '#':
                    zU02 = d.u0(i10, parcel);
                    break;
                default:
                    d.J0(i10, parcel);
                    break;
            }
        }
        d.e0(iL0, parcel);
        return new NotificationOptions(arrayListX, iArrR, jB0, strV, iZ0, iZ02, iZ03, iZ04, iZ05, iZ06, iZ07, iZ08, iZ09, iZ010, iZ011, iZ012, iZ013, iZ014, iZ015, iZ016, iZ017, iZ018, iZ019, iZ020, iZ021, iZ022, iZ023, iZ024, iZ025, iZ026, iZ027, iBinderY0, zU0, zU02);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        return new NotificationOptions[i10];
    }
}
