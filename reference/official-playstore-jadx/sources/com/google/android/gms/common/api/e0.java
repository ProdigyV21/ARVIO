package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class e0 implements Parcelable.Creator {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e0 f12684b = new e0(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12685a;

    public /* synthetic */ e0(int i10) {
        this.f12685a = i10;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f12685a) {
            case 0:
                int iDataPosition = parcel.dataPosition();
                if (parcel.readInt() != -204102970) {
                    parcel.setDataPosition(iDataPosition - 4);
                    return j.f12816n;
                }
                int iL0 = xc.d.L0(parcel);
                boolean zU0 = false;
                l lVar = null;
                while (parcel.dataPosition() < iL0) {
                    int i10 = parcel.readInt();
                    char c10 = (char) i10;
                    if (c10 == 1) {
                        lVar = (l) xc.d.T(parcel, i10, l.CREATOR);
                    } else if (c10 != 2) {
                        xc.d.J0(i10, parcel);
                    } else {
                        zU0 = xc.d.u0(i10, parcel);
                    }
                }
                xc.d.e0(iL0, parcel);
                return new j(lVar, zU0);
            case 1:
                int iL02 = xc.d.L0(parcel);
                int iZ0 = 0;
                boolean zU02 = true;
                int iZ02 = 0;
                int iZ03 = 0;
                while (parcel.dataPosition() < iL02) {
                    int i11 = parcel.readInt();
                    char c11 = (char) i11;
                    if (c11 == 1) {
                        iZ0 = xc.d.z0(i11, parcel);
                    } else if (c11 == 2) {
                        iZ02 = xc.d.z0(i11, parcel);
                    } else if (c11 == 3) {
                        iZ03 = xc.d.z0(i11, parcel);
                    } else if (c11 != 4) {
                        xc.d.J0(i11, parcel);
                    } else {
                        zU02 = xc.d.u0(i11, parcel);
                    }
                }
                xc.d.e0(iL02, parcel);
                return new l(iZ0, iZ02, iZ03, zU02);
            case 2:
                int iL03 = xc.d.L0(parcel);
                String strV = null;
                int iZ04 = 0;
                while (parcel.dataPosition() < iL03) {
                    int i12 = parcel.readInt();
                    char c12 = (char) i12;
                    if (c12 == 1) {
                        iZ04 = xc.d.z0(i12, parcel);
                    } else if (c12 != 2) {
                        xc.d.J0(i12, parcel);
                    } else {
                        strV = xc.d.V(i12, parcel);
                    }
                }
                xc.d.e0(iL03, parcel);
                return new Scope(iZ04, strV);
            default:
                int iL04 = xc.d.L0(parcel);
                String strV2 = null;
                o3.b bVar = null;
                int iZ05 = 0;
                PendingIntent pendingIntent = null;
                while (parcel.dataPosition() < iL04) {
                    int i13 = parcel.readInt();
                    char c13 = (char) i13;
                    if (c13 == 1) {
                        iZ05 = xc.d.z0(i13, parcel);
                    } else if (c13 == 2) {
                        strV2 = xc.d.V(i13, parcel);
                    } else if (c13 == 3) {
                        pendingIntent = (PendingIntent) xc.d.T(parcel, i13, PendingIntent.CREATOR);
                    } else if (c13 != 4) {
                        xc.d.J0(i13, parcel);
                    } else {
                        bVar = (o3.b) xc.d.T(parcel, i13, o3.b.CREATOR);
                    }
                }
                xc.d.e0(iL04, parcel);
                return new Status(iZ05, strV2, pendingIntent, bVar);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        switch (this.f12685a) {
            case 0:
                return new j[i10];
            case 1:
                return new l[i10];
            case 2:
                return new Scope[i10];
            default:
                return new Status[i10];
        }
    }
}
