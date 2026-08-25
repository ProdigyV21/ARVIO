package com.google.android.gms.internal.auth;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class p implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13087a;

    public /* synthetic */ p(int i10) {
        this.f13087a = i10;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f13087a) {
            case 0:
                int iL0 = xc.d.L0(parcel);
                String strV = null;
                while (parcel.dataPosition() < iL0) {
                    int i10 = parcel.readInt();
                    char c10 = (char) i10;
                    if (c10 == 1) {
                        xc.d.z0(i10, parcel);
                    } else if (c10 != 2) {
                        xc.d.J0(i10, parcel);
                    } else {
                        strV = xc.d.V(i10, parcel);
                    }
                }
                xc.d.e0(iL0, parcel);
                return new o(strV);
            case 1:
                int iL02 = xc.d.L0(parcel);
                int iZ0 = 0;
                String strV2 = null;
                while (parcel.dataPosition() < iL02) {
                    int i11 = parcel.readInt();
                    char c11 = (char) i11;
                    if (c11 == 1) {
                        xc.d.z0(i11, parcel);
                    } else if (c11 == 2) {
                        strV2 = xc.d.V(i11, parcel);
                    } else if (c11 != 3) {
                        xc.d.J0(i11, parcel);
                    } else {
                        iZ0 = xc.d.z0(i11, parcel);
                    }
                }
                xc.d.e0(iL02, parcel);
                return new t(strV2, iZ0);
            case 2:
                int iL03 = xc.d.L0(parcel);
                String strV3 = null;
                while (parcel.dataPosition() < iL03) {
                    int i12 = parcel.readInt();
                    char c12 = (char) i12;
                    if (c12 == 1) {
                        xc.d.z0(i12, parcel);
                    } else if (c12 != 2) {
                        xc.d.J0(i12, parcel);
                    } else {
                        strV3 = xc.d.V(i12, parcel);
                    }
                }
                xc.d.e0(iL03, parcel);
                return new u(strV3);
            case 3:
                int iL04 = xc.d.L0(parcel);
                String strV4 = null;
                byte[] bArrQ = null;
                while (parcel.dataPosition() < iL04) {
                    int i13 = parcel.readInt();
                    char c13 = (char) i13;
                    if (c13 == 1) {
                        xc.d.z0(i13, parcel);
                    } else if (c13 == 2) {
                        strV4 = xc.d.V(i13, parcel);
                    } else if (c13 != 3) {
                        xc.d.J0(i13, parcel);
                    } else {
                        bArrQ = xc.d.Q(i13, parcel);
                    }
                }
                xc.d.e0(iL04, parcel);
                return new v(strV4, bArrQ);
            case 4:
                int iL05 = xc.d.L0(parcel);
                String strV5 = null;
                PendingIntent pendingIntent = null;
                while (parcel.dataPosition() < iL05) {
                    int i14 = parcel.readInt();
                    char c14 = (char) i14;
                    if (c14 == 1) {
                        xc.d.z0(i14, parcel);
                    } else if (c14 == 2) {
                        strV5 = xc.d.V(i14, parcel);
                    } else if (c14 != 3) {
                        xc.d.J0(i14, parcel);
                    } else {
                        pendingIntent = (PendingIntent) xc.d.T(parcel, i14, PendingIntent.CREATOR);
                    }
                }
                xc.d.e0(iL05, parcel);
                return new x(strV5, pendingIntent);
            default:
                int iL06 = xc.d.L0(parcel);
                String strV6 = null;
                int iZ02 = 0;
                while (parcel.dataPosition() < iL06) {
                    int i15 = parcel.readInt();
                    char c15 = (char) i15;
                    if (c15 == 1) {
                        iZ02 = xc.d.z0(i15, parcel);
                    } else if (c15 != 2) {
                        xc.d.J0(i15, parcel);
                    } else {
                        strV6 = xc.d.V(i15, parcel);
                    }
                }
                xc.d.e0(iL06, parcel);
                return new m0(iZ02, strV6);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        switch (this.f13087a) {
            case 0:
                return new o[i10];
            case 1:
                return new t[i10];
            case 2:
                return new u[i10];
            case 3:
                return new v[i10];
            case 4:
                return new x[i10];
            default:
                return new m0[i10];
        }
    }
}
