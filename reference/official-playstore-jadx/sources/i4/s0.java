package i4;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.versionedparcelable.ParcelImpl;
import com.google.android.gms.fido.fido2.api.common.ErrorCode$UnsupportedErrorCodeException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class s0 implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16184a;

    public /* synthetic */ s0(int i10) {
        this.f16184a = i10;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f16184a) {
            case 0:
                int iL0 = xc.d.L0(parcel);
                ArrayList arrayListB0 = null;
                while (parcel.dataPosition() < iL0) {
                    int i10 = parcel.readInt();
                    if (((char) i10) != 1) {
                        xc.d.J0(i10, parcel);
                    } else {
                        arrayListB0 = xc.d.b0(parcel, i10, q0.CREATOR);
                    }
                }
                xc.d.e0(iL0, parcel);
                return new r0(arrayListB0);
            case 1:
                int iL02 = xc.d.L0(parcel);
                while (parcel.dataPosition() < iL02) {
                    int i11 = parcel.readInt();
                    if (((char) i11) != 1) {
                        xc.d.J0(i11, parcel);
                    } else {
                        xc.d.u0(i11, parcel);
                    }
                }
                xc.d.e0(iL02, parcel);
                return new t0();
            case 2:
                try {
                    return o.a(parcel.readInt());
                } catch (ErrorCode$UnsupportedErrorCodeException e5) {
                    throw new IllegalArgumentException(e5);
                }
            case 3:
                int iL03 = xc.d.L0(parcel);
                String strV = null;
                while (parcel.dataPosition() < iL03) {
                    int i12 = parcel.readInt();
                    if (((char) i12) != 2) {
                        xc.d.J0(i12, parcel);
                    } else {
                        strV = xc.d.V(i12, parcel);
                    }
                }
                xc.d.e0(iL03, parcel);
                return new p(strV);
            case 4:
                return new ParcelImpl(parcel);
            case 5:
                int iL04 = xc.d.L0(parcel);
                PendingIntent pendingIntent = null;
                String strV2 = null;
                Integer numA0 = null;
                int iZ0 = 0;
                int iZ02 = 0;
                while (parcel.dataPosition() < iL04) {
                    int i13 = parcel.readInt();
                    char c10 = (char) i13;
                    if (c10 == 1) {
                        iZ0 = xc.d.z0(i13, parcel);
                    } else if (c10 == 2) {
                        iZ02 = xc.d.z0(i13, parcel);
                    } else if (c10 == 3) {
                        pendingIntent = (PendingIntent) xc.d.T(parcel, i13, PendingIntent.CREATOR);
                    } else if (c10 == 4) {
                        strV2 = xc.d.V(i13, parcel);
                    } else if (c10 != 5) {
                        xc.d.J0(i13, parcel);
                    } else {
                        numA0 = xc.d.A0(i13, parcel);
                    }
                }
                xc.d.e0(iL04, parcel);
                return new o3.b(iZ0, iZ02, pendingIntent, strV2, numA0);
            case 6:
                int iL05 = xc.d.L0(parcel);
                int iZ03 = 0;
                boolean zU0 = false;
                long jB0 = -1;
                String strV3 = null;
                while (parcel.dataPosition() < iL05) {
                    int i14 = parcel.readInt();
                    char c11 = (char) i14;
                    if (c11 == 1) {
                        strV3 = xc.d.V(i14, parcel);
                    } else if (c11 == 2) {
                        iZ03 = xc.d.z0(i14, parcel);
                    } else if (c11 == 3) {
                        jB0 = xc.d.B0(i14, parcel);
                    } else if (c11 != 4) {
                        xc.d.J0(i14, parcel);
                    } else {
                        zU0 = xc.d.u0(i14, parcel);
                    }
                }
                xc.d.e0(iL05, parcel);
                return new o3.d(strV3, iZ03, jB0, zU0);
            case 7:
                int iL06 = xc.d.L0(parcel);
                int iZ04 = 0;
                int iZ05 = 0;
                Uri uri = null;
                int iZ06 = 0;
                while (parcel.dataPosition() < iL06) {
                    int i15 = parcel.readInt();
                    char c12 = (char) i15;
                    if (c12 == 1) {
                        iZ04 = xc.d.z0(i15, parcel);
                    } else if (c12 == 2) {
                        uri = (Uri) xc.d.T(parcel, i15, Uri.CREATOR);
                    } else if (c12 == 3) {
                        iZ06 = xc.d.z0(i15, parcel);
                    } else if (c12 != 4) {
                        xc.d.J0(i15, parcel);
                    } else {
                        iZ05 = xc.d.z0(i15, parcel);
                    }
                }
                xc.d.e0(iL06, parcel);
                return new r3.b(iZ04, uri, iZ06, iZ05);
            case 8:
                u0.k kVar = new u0.k(parcel);
                kVar.f22035i = parcel.readInt();
                return kVar;
            case 9:
                int iL07 = xc.d.L0(parcel);
                Intent intent = null;
                int iZ07 = 0;
                int iZ08 = 0;
                while (parcel.dataPosition() < iL07) {
                    int i16 = parcel.readInt();
                    char c13 = (char) i16;
                    if (c13 == 1) {
                        iZ07 = xc.d.z0(i16, parcel);
                    } else if (c13 == 2) {
                        iZ08 = xc.d.z0(i16, parcel);
                    } else if (c13 != 3) {
                        xc.d.J0(i16, parcel);
                    } else {
                        intent = (Intent) xc.d.T(parcel, i16, Intent.CREATOR);
                    }
                }
                xc.d.e0(iL07, parcel);
                return new u4.b(iZ07, iZ08, intent);
            case 10:
                int iL08 = xc.d.L0(parcel);
                ArrayList arrayListX = null;
                String strV4 = null;
                while (parcel.dataPosition() < iL08) {
                    int i17 = parcel.readInt();
                    char c14 = (char) i17;
                    if (c14 == 1) {
                        arrayListX = xc.d.X(i17, parcel);
                    } else if (c14 != 2) {
                        xc.d.J0(i17, parcel);
                    } else {
                        strV4 = xc.d.V(i17, parcel);
                    }
                }
                xc.d.e0(iL08, parcel);
                return new u4.f(strV4, arrayListX);
            case 11:
                int iL09 = xc.d.L0(parcel);
                o3.b bVar = null;
                int iZ09 = 0;
                com.google.android.gms.common.internal.j0 j0Var = null;
                while (parcel.dataPosition() < iL09) {
                    int i18 = parcel.readInt();
                    char c15 = (char) i18;
                    if (c15 == 1) {
                        iZ09 = xc.d.z0(i18, parcel);
                    } else if (c15 == 2) {
                        bVar = (o3.b) xc.d.T(parcel, i18, o3.b.CREATOR);
                    } else if (c15 != 3) {
                        xc.d.J0(i18, parcel);
                    } else {
                        j0Var = (com.google.android.gms.common.internal.j0) xc.d.T(parcel, i18, com.google.android.gms.common.internal.j0.CREATOR);
                    }
                }
                xc.d.e0(iL09, parcel);
                return new u4.g(iZ09, bVar, j0Var);
            case 12:
                int iL010 = xc.d.L0(parcel);
                z3.a aVar = null;
                int iZ010 = 0;
                while (parcel.dataPosition() < iL010) {
                    int i19 = parcel.readInt();
                    char c16 = (char) i19;
                    if (c16 == 1) {
                        iZ010 = xc.d.z0(i19, parcel);
                    } else if (c16 != 2) {
                        xc.d.J0(i19, parcel);
                    } else {
                        aVar = (z3.a) xc.d.T(parcel, i19, z3.a.CREATOR);
                    }
                }
                xc.d.e0(iL010, parcel);
                return new z3.b(iZ010, aVar);
            case 13:
                int iL011 = xc.d.L0(parcel);
                ArrayList arrayListB02 = null;
                int iZ011 = 0;
                while (parcel.dataPosition() < iL011) {
                    int i20 = parcel.readInt();
                    char c17 = (char) i20;
                    if (c17 == 1) {
                        iZ011 = xc.d.z0(i20, parcel);
                    } else if (c17 != 2) {
                        xc.d.J0(i20, parcel);
                    } else {
                        arrayListB02 = xc.d.b0(parcel, i20, z3.c.CREATOR);
                    }
                }
                xc.d.e0(iL011, parcel);
                return new z3.a(iZ011, arrayListB02);
            default:
                int iL012 = xc.d.L0(parcel);
                int iZ012 = 0;
                String strV5 = null;
                int iZ013 = 0;
                while (parcel.dataPosition() < iL012) {
                    int i21 = parcel.readInt();
                    char c18 = (char) i21;
                    if (c18 == 1) {
                        iZ012 = xc.d.z0(i21, parcel);
                    } else if (c18 == 2) {
                        strV5 = xc.d.V(i21, parcel);
                    } else if (c18 != 3) {
                        xc.d.J0(i21, parcel);
                    } else {
                        iZ013 = xc.d.z0(i21, parcel);
                    }
                }
                xc.d.e0(iL012, parcel);
                return new z3.c(iZ012, strV5, iZ013);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i10) {
        switch (this.f16184a) {
            case 0:
                return new r0[i10];
            case 1:
                return new t0[i10];
            case 2:
                return new o[i10];
            case 3:
                return new p[i10];
            case 4:
                return new ParcelImpl[i10];
            case 5:
                return new o3.b[i10];
            case 6:
                return new o3.d[i10];
            case 7:
                return new r3.b[i10];
            case 8:
                return new u0.k[i10];
            case 9:
                return new u4.b[i10];
            case 10:
                return new u4.f[i10];
            case 11:
                return new u4.g[i10];
            case 12:
                return new z3.b[i10];
            case 13:
                return new z3.a[i10];
            default:
                return new z3.c[i10];
        }
    }
}
