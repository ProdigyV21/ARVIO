package i4;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException;
import com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType$UnsupportedPublicKeyCredTypeException;
import com.google.android.gms.fido.fido2.api.common.ResidentKeyRequirement$UnsupportedResidentKeyRequirementException;
import com.google.android.gms.fido.fido2.api.common.TokenBinding$UnsupportedTokenBindingStatusException;
import com.google.android.gms.fido.fido2.api.common.zzbc;
import com.google.android.gms.internal.fido.i1;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class k0 implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16132a;

    public /* synthetic */ k0(int i10) {
        this.f16132a = i10;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f16132a) {
            case 0:
                int iL0 = xc.d.L0(parcel);
                String strV = null;
                while (parcel.dataPosition() < iL0) {
                    int i10 = parcel.readInt();
                    if (((char) i10) != 1) {
                        xc.d.J0(i10, parcel);
                    } else {
                        strV = xc.d.V(i10, parcel);
                    }
                }
                xc.d.e0(iL0, parcel);
                return new j0(strV);
            case 1:
                int iL02 = xc.d.L0(parcel);
                int iZ0 = 0;
                byte[] bArrQ = null;
                byte[] bArrQ2 = null;
                byte[] bArrQ3 = null;
                while (parcel.dataPosition() < iL02) {
                    int i11 = parcel.readInt();
                    char c10 = (char) i11;
                    if (c10 == 1) {
                        bArrQ = xc.d.Q(i11, parcel);
                    } else if (c10 == 2) {
                        bArrQ2 = xc.d.Q(i11, parcel);
                    } else if (c10 == 3) {
                        bArrQ3 = xc.d.Q(i11, parcel);
                    } else if (c10 != 4) {
                        xc.d.J0(i11, parcel);
                    } else {
                        iZ0 = xc.d.z0(i11, parcel);
                    }
                }
                xc.d.e0(iL02, parcel);
                return new l0(bArrQ == null ? null : i1.h(bArrQ.length, bArrQ), bArrQ2 == null ? null : i1.h(bArrQ2.length, bArrQ2), bArrQ3 != null ? i1.h(bArrQ3.length, bArrQ3) : null, iZ0);
            case 2:
                int iL03 = xc.d.L0(parcel);
                while (true) {
                    byte[][] bArr = null;
                    while (parcel.dataPosition() < iL03) {
                        int i12 = parcel.readInt();
                        if (((char) i12) != 1) {
                            xc.d.J0(i12, parcel);
                        } else {
                            int iC0 = xc.d.C0(i12, parcel);
                            int iDataPosition = parcel.dataPosition();
                            if (iC0 == 0) {
                            }
                            int i13 = parcel.readInt();
                            byte[][] bArr2 = new byte[i13][];
                            for (int i14 = 0; i14 < i13; i14++) {
                                bArr2[i14] = parcel.createByteArray();
                            }
                            parcel.setDataPosition(iDataPosition + iC0);
                            bArr = bArr2;
                        }
                        break;
                    }
                    xc.d.e0(iL03, parcel);
                    return new m0(bArr);
                }
                break;
            case 3:
                int iL04 = xc.d.L0(parcel);
                w wVar = null;
                y yVar = null;
                byte[] bArrQ4 = null;
                ArrayList arrayListB0 = null;
                Double dValueOf = null;
                ArrayList arrayListB02 = null;
                k kVar = null;
                Integer numA0 = null;
                c0 c0Var = null;
                String strV2 = null;
                d dVar = null;
                String strV3 = null;
                ResultReceiver resultReceiver = null;
                while (parcel.dataPosition() < iL04) {
                    int i15 = parcel.readInt();
                    switch ((char) i15) {
                        case 2:
                            wVar = (w) xc.d.T(parcel, i15, w.CREATOR);
                            break;
                        case 3:
                            yVar = (y) xc.d.T(parcel, i15, y.CREATOR);
                            break;
                        case 4:
                            bArrQ4 = xc.d.Q(i15, parcel);
                            break;
                        case 5:
                            arrayListB0 = xc.d.b0(parcel, i15, v.CREATOR);
                            break;
                        case 6:
                            int iC02 = xc.d.C0(i15, parcel);
                            if (iC02 == 0) {
                                dValueOf = null;
                            } else {
                                xc.d.P0(parcel, iC02, 8);
                                dValueOf = Double.valueOf(parcel.readDouble());
                            }
                            break;
                        case 7:
                            arrayListB02 = xc.d.b0(parcel, i15, u.CREATOR);
                            break;
                        case '\b':
                            kVar = (k) xc.d.T(parcel, i15, k.CREATOR);
                            break;
                        case '\t':
                            numA0 = xc.d.A0(i15, parcel);
                            break;
                        case '\n':
                            c0Var = (c0) xc.d.T(parcel, i15, c0.CREATOR);
                            break;
                        case 11:
                            strV2 = xc.d.V(i15, parcel);
                            break;
                        case '\f':
                            dVar = (d) xc.d.T(parcel, i15, d.CREATOR);
                            break;
                        case '\r':
                            strV3 = xc.d.V(i15, parcel);
                            break;
                        case 14:
                            resultReceiver = (ResultReceiver) xc.d.T(parcel, i15, ResultReceiver.CREATOR);
                            break;
                        default:
                            xc.d.J0(i15, parcel);
                            break;
                    }
                }
                xc.d.e0(iL04, parcel);
                return new t(wVar, yVar, bArrQ4, arrayListB0, dValueOf, arrayListB02, kVar, numA0, c0Var, strV2, dVar, strV3, resultReceiver);
            case 4:
                int iL05 = xc.d.L0(parcel);
                String strV4 = null;
                String strV5 = null;
                byte[] bArrQ5 = null;
                h hVar = null;
                g gVar = null;
                i iVar = null;
                e eVar = null;
                String strV6 = null;
                while (parcel.dataPosition() < iL05) {
                    int i16 = parcel.readInt();
                    switch ((char) i16) {
                        case 1:
                            strV4 = xc.d.V(i16, parcel);
                            break;
                        case 2:
                            strV5 = xc.d.V(i16, parcel);
                            break;
                        case 3:
                            bArrQ5 = xc.d.Q(i16, parcel);
                            break;
                        case 4:
                            hVar = (h) xc.d.T(parcel, i16, h.CREATOR);
                            break;
                        case 5:
                            gVar = (g) xc.d.T(parcel, i16, g.CREATOR);
                            break;
                        case 6:
                            iVar = (i) xc.d.T(parcel, i16, i.CREATOR);
                            break;
                        case 7:
                            eVar = (e) xc.d.T(parcel, i16, e.CREATOR);
                            break;
                        case '\b':
                            strV6 = xc.d.V(i16, parcel);
                            break;
                        case '\t':
                            xc.d.V(i16, parcel);
                            break;
                        default:
                            xc.d.J0(i16, parcel);
                            break;
                    }
                }
                xc.d.e0(iL05, parcel);
                return new r(strV4, strV5, bArrQ5, hVar, gVar, iVar, eVar, strV6);
            case 5:
                int iL06 = xc.d.L0(parcel);
                String strV7 = null;
                byte[] bArrQ6 = null;
                ArrayList arrayListB03 = null;
                while (parcel.dataPosition() < iL06) {
                    int i17 = parcel.readInt();
                    char c11 = (char) i17;
                    if (c11 == 2) {
                        strV7 = xc.d.V(i17, parcel);
                    } else if (c11 == 3) {
                        bArrQ6 = xc.d.Q(i17, parcel);
                    } else if (c11 != 4) {
                        xc.d.J0(i17, parcel);
                    } else {
                        arrayListB03 = xc.d.b0(parcel, i17, Transport.CREATOR);
                    }
                }
                xc.d.e0(iL06, parcel);
                return new u(strV7, bArrQ6, arrayListB03);
            case 6:
                int iL07 = xc.d.L0(parcel);
                String strV8 = null;
                Integer numA02 = null;
                while (parcel.dataPosition() < iL07) {
                    int i18 = parcel.readInt();
                    char c12 = (char) i18;
                    if (c12 == 2) {
                        strV8 = xc.d.V(i18, parcel);
                    } else if (c12 != 3) {
                        xc.d.J0(i18, parcel);
                    } else {
                        numA02 = xc.d.A0(i18, parcel);
                    }
                }
                xc.d.e0(iL07, parcel);
                return new v(strV8, numA02.intValue());
            case 7:
                int iL08 = xc.d.L0(parcel);
                String strV9 = null;
                String strV10 = null;
                String strV11 = null;
                while (parcel.dataPosition() < iL08) {
                    int i19 = parcel.readInt();
                    char c13 = (char) i19;
                    if (c13 == 2) {
                        strV9 = xc.d.V(i19, parcel);
                    } else if (c13 == 3) {
                        strV10 = xc.d.V(i19, parcel);
                    } else if (c13 != 4) {
                        xc.d.J0(i19, parcel);
                    } else {
                        strV11 = xc.d.V(i19, parcel);
                    }
                }
                xc.d.e0(iL08, parcel);
                return new w(strV9, strV10, strV11);
            case 8:
                try {
                    return x.a(parcel.readString());
                } catch (PublicKeyCredentialType$UnsupportedPublicKeyCredTypeException e5) {
                    throw new RuntimeException(e5);
                }
            case 9:
                int iL09 = xc.d.L0(parcel);
                byte[] bArrQ7 = null;
                String strV12 = null;
                String strV13 = null;
                String strV14 = null;
                while (parcel.dataPosition() < iL09) {
                    int i20 = parcel.readInt();
                    char c14 = (char) i20;
                    if (c14 == 2) {
                        bArrQ7 = xc.d.Q(i20, parcel);
                    } else if (c14 == 3) {
                        strV12 = xc.d.V(i20, parcel);
                    } else if (c14 == 4) {
                        strV13 = xc.d.V(i20, parcel);
                    } else if (c14 != 5) {
                        xc.d.J0(i20, parcel);
                    } else {
                        strV14 = xc.d.V(i20, parcel);
                    }
                }
                xc.d.e0(iL09, parcel);
                return new y(strV12, strV13, strV14, bArrQ7);
            case 10:
                String string = parcel.readString();
                if (string == null) {
                    string = "";
                }
                try {
                    return a0.a(string);
                } catch (ResidentKeyRequirement$UnsupportedResidentKeyRequirementException e6) {
                    throw new RuntimeException(e6);
                }
            case 11:
                int iL010 = xc.d.L0(parcel);
                String strV15 = null;
                while (parcel.dataPosition() < iL010) {
                    int i21 = parcel.readInt();
                    if (((char) i21) != 1) {
                        xc.d.J0(i21, parcel);
                    } else {
                        strV15 = xc.d.V(i21, parcel);
                    }
                }
                xc.d.e0(iL010, parcel);
                return new n0(strV15);
            case 12:
                try {
                    return b0.a(parcel.readString());
                } catch (TokenBinding$UnsupportedTokenBindingStatusException e10) {
                    throw new RuntimeException(e10);
                }
            case 13:
                int iL011 = xc.d.L0(parcel);
                String strV16 = null;
                String strV17 = null;
                while (parcel.dataPosition() < iL011) {
                    int i22 = parcel.readInt();
                    char c15 = (char) i22;
                    if (c15 == 2) {
                        strV16 = xc.d.V(i22, parcel);
                    } else if (c15 != 3) {
                        xc.d.J0(i22, parcel);
                    } else {
                        strV17 = xc.d.V(i22, parcel);
                    }
                }
                xc.d.e0(iL011, parcel);
                return new c0(strV16, strV17);
            case 14:
                try {
                    return c.a(parcel.readString());
                } catch (AttestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException e11) {
                    throw new RuntimeException(e11);
                }
            case 15:
                int iL012 = xc.d.L0(parcel);
                boolean zU0 = false;
                while (parcel.dataPosition() < iL012) {
                    int i23 = parcel.readInt();
                    if (((char) i23) != 1) {
                        xc.d.J0(i23, parcel);
                    } else {
                        zU0 = xc.d.u0(i23, parcel);
                    }
                }
                xc.d.e0(iL012, parcel);
                return new d0(zU0);
            case 16:
                try {
                    return e0.a(parcel.readString());
                } catch (zzbc e12) {
                    throw new RuntimeException(e12);
                }
            case 17:
                int iL013 = xc.d.L0(parcel);
                ArrayList arrayListB04 = null;
                while (parcel.dataPosition() < iL013) {
                    int i24 = parcel.readInt();
                    if (((char) i24) != 1) {
                        xc.d.J0(i24, parcel);
                    } else {
                        arrayListB04 = xc.d.b0(parcel, i24, g0.CREATOR);
                    }
                }
                xc.d.e0(iL013, parcel);
                return new f0(arrayListB04);
            case 18:
                int iL014 = xc.d.L0(parcel);
                int iZ02 = 0;
                short s10 = 0;
                short s11 = 0;
                while (parcel.dataPosition() < iL014) {
                    int i25 = parcel.readInt();
                    char c16 = (char) i25;
                    if (c16 == 1) {
                        iZ02 = xc.d.z0(i25, parcel);
                    } else if (c16 == 2) {
                        xc.d.O0(parcel, i25, 4);
                        s10 = (short) parcel.readInt();
                    } else if (c16 != 3) {
                        xc.d.J0(i25, parcel);
                    } else {
                        xc.d.O0(parcel, i25, 4);
                        s11 = (short) parcel.readInt();
                    }
                }
                xc.d.e0(iL014, parcel);
                return new g0(iZ02, s10, s11);
            case 19:
                int iL015 = xc.d.L0(parcel);
                f0 f0Var = null;
                o0 o0Var = null;
                f fVar = null;
                p0 p0Var = null;
                String strV18 = null;
                while (parcel.dataPosition() < iL015) {
                    int i26 = parcel.readInt();
                    char c17 = (char) i26;
                    if (c17 == 1) {
                        f0Var = (f0) xc.d.T(parcel, i26, f0.CREATOR);
                    } else if (c17 == 2) {
                        o0Var = (o0) xc.d.T(parcel, i26, o0.CREATOR);
                    } else if (c17 == 3) {
                        fVar = (f) xc.d.T(parcel, i26, f.CREATOR);
                    } else if (c17 == 4) {
                        p0Var = (p0) xc.d.T(parcel, i26, p0.CREATOR);
                    } else if (c17 != 5) {
                        xc.d.J0(i26, parcel);
                    } else {
                        strV18 = xc.d.V(i26, parcel);
                    }
                }
                xc.d.e0(iL015, parcel);
                return new e(f0Var, o0Var, fVar, p0Var, strV18);
            case 20:
                int iL016 = xc.d.L0(parcel);
                p pVar = null;
                r0 r0Var = null;
                d0 d0Var = null;
                u0 u0Var = null;
                h0 h0Var = null;
                i0 i0Var = null;
                t0 t0Var = null;
                j0 j0Var = null;
                q qVar = null;
                m0 m0Var = null;
                n0 n0Var = null;
                l0 l0Var = null;
                while (parcel.dataPosition() < iL016) {
                    int i27 = parcel.readInt();
                    switch ((char) i27) {
                        case 2:
                            pVar = (p) xc.d.T(parcel, i27, p.CREATOR);
                            break;
                        case 3:
                            r0Var = (r0) xc.d.T(parcel, i27, r0.CREATOR);
                            break;
                        case 4:
                            d0Var = (d0) xc.d.T(parcel, i27, d0.CREATOR);
                            break;
                        case 5:
                            u0Var = (u0) xc.d.T(parcel, i27, u0.CREATOR);
                            break;
                        case 6:
                            h0Var = (h0) xc.d.T(parcel, i27, h0.CREATOR);
                            break;
                        case 7:
                            i0Var = (i0) xc.d.T(parcel, i27, i0.CREATOR);
                            break;
                        case '\b':
                            t0Var = (t0) xc.d.T(parcel, i27, t0.CREATOR);
                            break;
                        case '\t':
                            j0Var = (j0) xc.d.T(parcel, i27, j0.CREATOR);
                            break;
                        case '\n':
                            qVar = (q) xc.d.T(parcel, i27, q.CREATOR);
                            break;
                        case 11:
                            m0Var = (m0) xc.d.T(parcel, i27, m0.CREATOR);
                            break;
                        case '\f':
                            n0Var = (n0) xc.d.T(parcel, i27, n0.CREATOR);
                            break;
                        case '\r':
                            l0Var = (l0) xc.d.T(parcel, i27, l0.CREATOR);
                            break;
                        default:
                            xc.d.J0(i27, parcel);
                            break;
                    }
                }
                xc.d.e0(iL016, parcel);
                return new d(pVar, r0Var, d0Var, u0Var, h0Var, i0Var, t0Var, j0Var, qVar, m0Var, n0Var, l0Var);
            case 21:
                int iL017 = xc.d.L0(parcel);
                boolean zU02 = false;
                while (parcel.dataPosition() < iL017) {
                    int i28 = parcel.readInt();
                    if (((char) i28) != 1) {
                        xc.d.J0(i28, parcel);
                    } else {
                        zU02 = xc.d.u0(i28, parcel);
                    }
                }
                xc.d.e0(iL017, parcel);
                return new f(zU02);
            case 22:
                int iL018 = xc.d.L0(parcel);
                byte[] bArrQ8 = null;
                byte[] bArrQ9 = null;
                while (parcel.dataPosition() < iL018) {
                    int i29 = parcel.readInt();
                    char c18 = (char) i29;
                    if (c18 == 1) {
                        bArrQ8 = xc.d.Q(i29, parcel);
                    } else if (c18 != 2) {
                        xc.d.J0(i29, parcel);
                    } else {
                        bArrQ9 = xc.d.Q(i29, parcel);
                    }
                }
                xc.d.e0(iL018, parcel);
                return new o0(bArrQ8 == null ? null : i1.h(bArrQ8.length, bArrQ8), bArrQ9 != null ? i1.h(bArrQ9.length, bArrQ9) : null);
            case 23:
                int iL019 = xc.d.L0(parcel);
                boolean zU03 = false;
                byte[] bArrQ10 = null;
                while (parcel.dataPosition() < iL019) {
                    int i30 = parcel.readInt();
                    char c19 = (char) i30;
                    if (c19 == 1) {
                        zU03 = xc.d.u0(i30, parcel);
                    } else if (c19 != 2) {
                        xc.d.J0(i30, parcel);
                    } else {
                        bArrQ10 = xc.d.Q(i30, parcel);
                    }
                }
                xc.d.e0(iL019, parcel);
                return new p0(zU03, bArrQ10 != null ? i1.h(bArrQ10.length, bArrQ10) : null);
            case 24:
                int iL020 = xc.d.L0(parcel);
                byte[] bArrQ11 = null;
                byte[] bArrQ12 = null;
                byte[] bArrQ13 = null;
                byte[] bArrQ14 = null;
                byte[] bArrQ15 = null;
                while (parcel.dataPosition() < iL020) {
                    int i31 = parcel.readInt();
                    char c20 = (char) i31;
                    if (c20 == 2) {
                        bArrQ11 = xc.d.Q(i31, parcel);
                    } else if (c20 == 3) {
                        bArrQ12 = xc.d.Q(i31, parcel);
                    } else if (c20 == 4) {
                        bArrQ13 = xc.d.Q(i31, parcel);
                    } else if (c20 == 5) {
                        bArrQ14 = xc.d.Q(i31, parcel);
                    } else if (c20 != 6) {
                        xc.d.J0(i31, parcel);
                    } else {
                        bArrQ15 = xc.d.Q(i31, parcel);
                    }
                }
                xc.d.e0(iL020, parcel);
                return new g(bArrQ11, bArrQ12, bArrQ13, bArrQ14, bArrQ15);
            case 25:
                int iL021 = xc.d.L0(parcel);
                byte[] bArrQ16 = null;
                byte[] bArrQ17 = null;
                byte[] bArrQ18 = null;
                String[] strArrW = null;
                while (parcel.dataPosition() < iL021) {
                    int i32 = parcel.readInt();
                    char c21 = (char) i32;
                    if (c21 == 2) {
                        bArrQ16 = xc.d.Q(i32, parcel);
                    } else if (c21 == 3) {
                        bArrQ17 = xc.d.Q(i32, parcel);
                    } else if (c21 == 4) {
                        bArrQ18 = xc.d.Q(i32, parcel);
                    } else if (c21 != 5) {
                        xc.d.J0(i32, parcel);
                    } else {
                        strArrW = xc.d.W(i32, parcel);
                    }
                }
                xc.d.e0(iL021, parcel);
                return new h(bArrQ16, bArrQ17, bArrQ18, strArrW);
            case 26:
                int iL022 = xc.d.L0(parcel);
                int iZ03 = 0;
                String strV19 = null;
                int iZ04 = 0;
                while (parcel.dataPosition() < iL022) {
                    int i33 = parcel.readInt();
                    char c22 = (char) i33;
                    if (c22 == 2) {
                        iZ03 = xc.d.z0(i33, parcel);
                    } else if (c22 == 3) {
                        strV19 = xc.d.V(i33, parcel);
                    } else if (c22 != 4) {
                        xc.d.J0(i33, parcel);
                    } else {
                        iZ04 = xc.d.z0(i33, parcel);
                    }
                }
                xc.d.e0(iL022, parcel);
                return new i(iZ03, strV19, iZ04);
            case 27:
                int iL023 = xc.d.L0(parcel);
                String strV20 = null;
                Boolean boolV0 = null;
                String strV21 = null;
                String strV22 = null;
                while (parcel.dataPosition() < iL023) {
                    int i34 = parcel.readInt();
                    char c23 = (char) i34;
                    if (c23 == 2) {
                        strV20 = xc.d.V(i34, parcel);
                    } else if (c23 == 3) {
                        boolV0 = xc.d.v0(i34, parcel);
                    } else if (c23 == 4) {
                        strV21 = xc.d.V(i34, parcel);
                    } else if (c23 != 5) {
                        xc.d.J0(i34, parcel);
                    } else {
                        strV22 = xc.d.V(i34, parcel);
                    }
                }
                xc.d.e0(iL023, parcel);
                return new k(strV20, boolV0, strV21, strV22);
            case 28:
                try {
                    return l.a(parcel.readInt());
                } catch (COSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException e13) {
                    throw new RuntimeException(e13);
                }
            default:
                int iL024 = xc.d.L0(parcel);
                byte[] bArrQ19 = null;
                byte[] bArrQ20 = null;
                byte[] bArrQ21 = null;
                long jB0 = 0;
                while (parcel.dataPosition() < iL024) {
                    int i35 = parcel.readInt();
                    char c24 = (char) i35;
                    if (c24 == 1) {
                        jB0 = xc.d.B0(i35, parcel);
                    } else if (c24 == 2) {
                        bArrQ19 = xc.d.Q(i35, parcel);
                    } else if (c24 == 3) {
                        bArrQ20 = xc.d.Q(i35, parcel);
                    } else if (c24 != 4) {
                        xc.d.J0(i35, parcel);
                    } else {
                        bArrQ21 = xc.d.Q(i35, parcel);
                    }
                }
                xc.d.e0(iL024, parcel);
                return new q0(jB0, bArrQ19, bArrQ20, bArrQ21);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i10) {
        switch (this.f16132a) {
            case 0:
                return new j0[i10];
            case 1:
                return new l0[i10];
            case 2:
                return new m0[i10];
            case 3:
                return new t[i10];
            case 4:
                return new r[i10];
            case 5:
                return new u[i10];
            case 6:
                return new v[i10];
            case 7:
                return new w[i10];
            case 8:
                return new x[i10];
            case 9:
                return new y[i10];
            case 10:
                return new a0[i10];
            case 11:
                return new n0[i10];
            case 12:
                return new b0[i10];
            case 13:
                return new c0[i10];
            case 14:
                return new c[i10];
            case 15:
                return new d0[i10];
            case 16:
                return new e0[i10];
            case 17:
                return new f0[i10];
            case 18:
                return new g0[i10];
            case 19:
                return new e[i10];
            case 20:
                return new d[i10];
            case 21:
                return new f[i10];
            case 22:
                return new o0[i10];
            case 23:
                return new p0[i10];
            case 24:
                return new g[i10];
            case 25:
                return new h[i10];
            case 26:
                return new i[i10];
            case 27:
                return new k[i10];
            case 28:
                return new l[i10];
            default:
                return new q0[i10];
        }
    }
}
