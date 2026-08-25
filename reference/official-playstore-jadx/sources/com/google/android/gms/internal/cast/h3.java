package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class h3 implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13279a;

    public /* synthetic */ h3(int i10) {
        this.f13279a = i10;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f13279a) {
            case 0:
                int iL0 = xc.d.L0(parcel);
                String strV = null;
                byte[] bArrQ = null;
                ArrayList arrayList = null;
                while (parcel.dataPosition() < iL0) {
                    int i10 = parcel.readInt();
                    char c10 = (char) i10;
                    if (c10 == 1) {
                        strV = xc.d.V(i10, parcel);
                    } else if (c10 == 2) {
                        bArrQ = xc.d.Q(i10, parcel);
                    } else if (c10 != 3) {
                        xc.d.J0(i10, parcel);
                    } else {
                        int iC0 = xc.d.C0(i10, parcel);
                        int iDataPosition = parcel.dataPosition();
                        if (iC0 == 0) {
                            arrayList = null;
                        } else {
                            ArrayList arrayList2 = new ArrayList();
                            int i11 = parcel.readInt();
                            for (int i12 = 0; i12 < i11; i12++) {
                                arrayList2.add(Integer.valueOf(parcel.readInt()));
                            }
                            parcel.setDataPosition(iDataPosition + iC0);
                            arrayList = arrayList2;
                        }
                    }
                }
                xc.d.e0(iL0, parcel);
                return new i3(strV, bArrQ, arrayList);
            case 1:
                int iL02 = xc.d.L0(parcel);
                boolean zU0 = false;
                ArrayList arrayListB0 = null;
                boolean zU02 = false;
                while (parcel.dataPosition() < iL02) {
                    int i13 = parcel.readInt();
                    char c11 = (char) i13;
                    if (c11 == 1) {
                        arrayListB0 = xc.d.b0(parcel, i13, i3.CREATOR);
                    } else if (c11 == 2) {
                        zU0 = xc.d.u0(i13, parcel);
                    } else if (c11 != 3) {
                        xc.d.J0(i13, parcel);
                    } else {
                        zU02 = xc.d.u0(i13, parcel);
                    }
                }
                xc.d.e0(iL02, parcel);
                return new j3(arrayListB0, zU0, zU02);
            case 2:
                int iL03 = xc.d.L0(parcel);
                boolean zU03 = false;
                int iZ0 = 0;
                while (parcel.dataPosition() < iL03) {
                    int i14 = parcel.readInt();
                    char c12 = (char) i14;
                    if (c12 == 2) {
                        zU03 = xc.d.u0(i14, parcel);
                    } else if (c12 != 3) {
                        xc.d.J0(i14, parcel);
                    } else {
                        iZ0 = xc.d.z0(i14, parcel);
                    }
                }
                xc.d.e0(iL03, parcel);
                return new k3(zU03, iZ0);
            case 3:
                int iL04 = xc.d.L0(parcel);
                boolean zU04 = false;
                boolean zU05 = false;
                int iZ02 = 0;
                while (parcel.dataPosition() < iL04) {
                    int i15 = parcel.readInt();
                    char c13 = (char) i15;
                    if (c13 == 2) {
                        zU04 = xc.d.u0(i15, parcel);
                    } else if (c13 == 3) {
                        zU05 = xc.d.u0(i15, parcel);
                    } else if (c13 != 4) {
                        xc.d.J0(i15, parcel);
                    } else {
                        iZ02 = xc.d.z0(i15, parcel);
                    }
                }
                xc.d.e0(iL04, parcel);
                return new n3(zU04, zU05, iZ02);
            default:
                int iL05 = xc.d.L0(parcel);
                int iZ03 = 0;
                boolean zU06 = false;
                int iZ04 = 0;
                boolean zU07 = false;
                ArrayList arrayListX = null;
                String strV2 = null;
                while (parcel.dataPosition() < iL05) {
                    int i16 = parcel.readInt();
                    switch ((char) i16) {
                        case 2:
                            iZ03 = xc.d.z0(i16, parcel);
                            break;
                        case 3:
                            zU06 = xc.d.u0(i16, parcel);
                            break;
                        case 4:
                            arrayListX = xc.d.X(i16, parcel);
                            break;
                        case 5:
                            iZ04 = xc.d.z0(i16, parcel);
                            break;
                        case 6:
                            strV2 = xc.d.V(i16, parcel);
                            break;
                        case 7:
                            zU07 = xc.d.u0(i16, parcel);
                            break;
                        default:
                            xc.d.J0(i16, parcel);
                            break;
                    }
                }
                xc.d.e0(iL05, parcel);
                return new r3(iZ03, zU06, arrayListX, iZ04, strV2, zU07);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        switch (this.f13279a) {
            case 0:
                return new i3[i10];
            case 1:
                return new j3[i10];
            case 2:
                return new k3[i10];
            case 3:
                return new n3[i10];
            default:
                return new r3[i10];
        }
    }
}
