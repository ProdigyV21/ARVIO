package android.support.v4.media;

import android.accounts.Account;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.v;
import androidx.activity.result.n;
import androidx.leanback.widget.a0;
import androidx.leanback.widget.z;
import androidx.recyclerview.widget.c0;
import androidx.recyclerview.widget.v1;
import androidx.recyclerview.widget.w1;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.i;
import com.google.android.gms.common.internal.i0;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.j0;
import com.google.android.gms.common.internal.r;
import com.google.android.gms.common.internal.t0;
import com.google.android.gms.fido.fido2.api.common.Attachment$UnsupportedAttachmentException;
import i4.h0;
import i4.q;
import i4.u0;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f843a;

    public /* synthetic */ d(int i10) {
        this.f843a = i10;
    }

    public static void a(j jVar, Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = jVar.f12864i;
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        int i12 = jVar.f12865l;
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(i12);
        int i13 = jVar.f12866m;
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(i13);
        a.a.g0(parcel, 4, jVar.f12867n, false);
        a.a.b0(parcel, 5, jVar.f12868o);
        a.a.j0(parcel, 6, jVar.f12869p, i10);
        a.a.Z(parcel, 7, jVar.f12870q, false);
        a.a.f0(parcel, 8, jVar.f12871r, i10, false);
        a.a.j0(parcel, 10, jVar.f12872s, i10);
        a.a.j0(parcel, 11, jVar.f12873t, i10);
        boolean z = jVar.f12874u;
        a.a.l0(parcel, 12, 4);
        parcel.writeInt(z ? 1 : 0);
        int i14 = jVar.f12875v;
        a.a.l0(parcel, 13, 4);
        parcel.writeInt(i14);
        boolean z5 = jVar.f12876w;
        a.a.l0(parcel, 14, 4);
        parcel.writeInt(z5 ? 1 : 0);
        a.a.g0(parcel, 15, jVar.x, false);
        a.a.n0(iM0, parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        Bundle bundle;
        switch (this.f843a) {
            case 0:
                return new MediaBrowserCompat$MediaItem(parcel);
            case 1:
                Object objCreateFromParcel = MediaDescription.CREATOR.createFromParcel(parcel);
                if (objCreateFromParcel == null) {
                    return null;
                }
                MediaDescription mediaDescription = (MediaDescription) objCreateFromParcel;
                String strG = e.g(mediaDescription);
                CharSequence charSequenceI = e.i(mediaDescription);
                CharSequence charSequenceH = e.h(mediaDescription);
                CharSequence charSequenceC = e.c(mediaDescription);
                Bitmap bitmapE = e.e(mediaDescription);
                Uri uriF = e.f(mediaDescription);
                Bundle bundleD = e.d(mediaDescription);
                if (bundleD != null) {
                    bundleD = v.e(bundleD);
                }
                Uri uriA = bundleD != null ? (Uri) bundleD.getParcelable(androidx.media3.session.legacy.MediaDescriptionCompat.DESCRIPTION_KEY_MEDIA_URI) : null;
                if (uriA == null) {
                    bundle = bundleD;
                } else if (bundleD.containsKey(androidx.media3.session.legacy.MediaDescriptionCompat.DESCRIPTION_KEY_NULL_BUNDLE_FLAG) && bundleD.size() == 2) {
                    bundle = null;
                } else {
                    bundleD.remove(androidx.media3.session.legacy.MediaDescriptionCompat.DESCRIPTION_KEY_MEDIA_URI);
                    bundleD.remove(androidx.media3.session.legacy.MediaDescriptionCompat.DESCRIPTION_KEY_NULL_BUNDLE_FLAG);
                    bundle = bundleD;
                }
                if (uriA == null) {
                    uriA = f.a(mediaDescription);
                }
                MediaDescriptionCompat mediaDescriptionCompat = new MediaDescriptionCompat(strG, charSequenceI, charSequenceH, charSequenceC, bitmapE, uriF, bundle, uriA);
                mediaDescriptionCompat.f837s = mediaDescription;
                return mediaDescriptionCompat;
            case 2:
                return new MediaMetadataCompat(parcel);
            case 3:
                return new RatingCompat(parcel.readInt(), parcel.readFloat());
            case 4:
                return new android.support.v4.os.f(parcel);
            case 5:
                return new androidx.activity.result.a(parcel.readInt() == 0 ? null : (Intent) Intent.CREATOR.createFromParcel(parcel), parcel.readInt());
            case 6:
                return new n((IntentSender) parcel.readParcelable(IntentSender.class.getClassLoader()), (Intent) parcel.readParcelable(Intent.class.getClassLoader()), parcel.readInt(), parcel.readInt());
            case 7:
                z zVar = new z();
                zVar.f3183l = Bundle.EMPTY;
                zVar.f3182i = parcel.readInt();
                zVar.f3183l = parcel.readBundle(a0.class.getClassLoader());
                return zVar;
            case 8:
                return new androidx.navigation.j(parcel);
            case 9:
                c0 c0Var = new c0();
                c0Var.f4900i = parcel.readInt();
                c0Var.f4901l = parcel.readInt();
                c0Var.f4902m = parcel.readInt() == 1;
                return c0Var;
            case 10:
                v1 v1Var = new v1();
                v1Var.f5073i = parcel.readInt();
                v1Var.f5074l = parcel.readInt();
                v1Var.f5076n = parcel.readInt() == 1;
                int i10 = parcel.readInt();
                if (i10 > 0) {
                    int[] iArr = new int[i10];
                    v1Var.f5075m = iArr;
                    parcel.readIntArray(iArr);
                }
                return v1Var;
            case 11:
                w1 w1Var = new w1();
                w1Var.f5079i = parcel.readInt();
                w1Var.f5080l = parcel.readInt();
                int i11 = parcel.readInt();
                w1Var.f5081m = i11;
                if (i11 > 0) {
                    int[] iArr2 = new int[i11];
                    w1Var.f5082n = iArr2;
                    parcel.readIntArray(iArr2);
                }
                int i12 = parcel.readInt();
                w1Var.f5083o = i12;
                if (i12 > 0) {
                    int[] iArr3 = new int[i12];
                    w1Var.f5084p = iArr3;
                    parcel.readIntArray(iArr3);
                }
                w1Var.f5086r = parcel.readInt() == 1;
                w1Var.f5087s = parcel.readInt() == 1;
                w1Var.f5088t = parcel.readInt() == 1;
                w1Var.f5085q = parcel.readArrayList(v1.class.getClassLoader());
                return w1Var;
            case 12:
                int iL0 = xc.d.L0(parcel);
                ArrayList arrayListB0 = null;
                int iZ0 = 0;
                while (parcel.dataPosition() < iL0) {
                    int i13 = parcel.readInt();
                    char c10 = (char) i13;
                    if (c10 == 1) {
                        iZ0 = xc.d.z0(i13, parcel);
                    } else if (c10 != 2) {
                        xc.d.J0(i13, parcel);
                    } else {
                        arrayListB0 = xc.d.b0(parcel, i13, r.CREATOR);
                    }
                }
                xc.d.e0(iL0, parcel);
                return new com.google.android.gms.common.internal.a0(iZ0, arrayListB0);
            case 13:
                int iL02 = xc.d.L0(parcel);
                int iZ02 = -1;
                int iZ03 = 0;
                int iZ04 = 0;
                int iZ05 = 0;
                int iZ06 = 0;
                String strV = null;
                String strV2 = null;
                long jB0 = 0;
                long jB02 = 0;
                while (parcel.dataPosition() < iL02) {
                    int i14 = parcel.readInt();
                    switch ((char) i14) {
                        case 1:
                            iZ03 = xc.d.z0(i14, parcel);
                            break;
                        case 2:
                            iZ04 = xc.d.z0(i14, parcel);
                            break;
                        case 3:
                            iZ05 = xc.d.z0(i14, parcel);
                            break;
                        case 4:
                            jB0 = xc.d.B0(i14, parcel);
                            break;
                        case 5:
                            jB02 = xc.d.B0(i14, parcel);
                            break;
                        case 6:
                            strV = xc.d.V(i14, parcel);
                            break;
                        case 7:
                            strV2 = xc.d.V(i14, parcel);
                            break;
                        case '\b':
                            iZ06 = xc.d.z0(i14, parcel);
                            break;
                        case '\t':
                            iZ02 = xc.d.z0(i14, parcel);
                            break;
                        default:
                            xc.d.J0(i14, parcel);
                            break;
                    }
                }
                xc.d.e0(iL02, parcel);
                return new r(iZ03, iZ04, iZ05, jB0, jB02, strV, strV2, iZ06, iZ02);
            case 14:
                int iL03 = xc.d.L0(parcel);
                Account account = null;
                int iZ07 = 0;
                int iZ08 = 0;
                GoogleSignInAccount googleSignInAccount = null;
                while (parcel.dataPosition() < iL03) {
                    int i15 = parcel.readInt();
                    char c11 = (char) i15;
                    if (c11 == 1) {
                        iZ07 = xc.d.z0(i15, parcel);
                    } else if (c11 == 2) {
                        account = (Account) xc.d.T(parcel, i15, Account.CREATOR);
                    } else if (c11 == 3) {
                        iZ08 = xc.d.z0(i15, parcel);
                    } else if (c11 != 4) {
                        xc.d.J0(i15, parcel);
                    } else {
                        googleSignInAccount = (GoogleSignInAccount) xc.d.T(parcel, i15, GoogleSignInAccount.CREATOR);
                    }
                }
                xc.d.e0(iL03, parcel);
                return new i0(iZ07, account, iZ08, googleSignInAccount);
            case 15:
                int iL04 = xc.d.L0(parcel);
                int iZ09 = 0;
                boolean zU0 = false;
                boolean zU02 = false;
                IBinder iBinderY0 = null;
                o3.b bVar = null;
                while (parcel.dataPosition() < iL04) {
                    int i16 = parcel.readInt();
                    char c12 = (char) i16;
                    if (c12 == 1) {
                        iZ09 = xc.d.z0(i16, parcel);
                    } else if (c12 == 2) {
                        iBinderY0 = xc.d.y0(i16, parcel);
                    } else if (c12 == 3) {
                        bVar = (o3.b) xc.d.T(parcel, i16, o3.b.CREATOR);
                    } else if (c12 == 4) {
                        zU0 = xc.d.u0(i16, parcel);
                    } else if (c12 != 5) {
                        xc.d.J0(i16, parcel);
                    } else {
                        zU02 = xc.d.u0(i16, parcel);
                    }
                }
                xc.d.e0(iL04, parcel);
                return new j0(iZ09, iBinderY0, bVar, zU0, zU02);
            case 16:
                int iL05 = xc.d.L0(parcel);
                boolean zU03 = false;
                int iZ010 = 0;
                boolean zU04 = false;
                int iZ011 = 0;
                int iZ012 = 0;
                while (parcel.dataPosition() < iL05) {
                    int i17 = parcel.readInt();
                    char c13 = (char) i17;
                    if (c13 == 1) {
                        iZ010 = xc.d.z0(i17, parcel);
                    } else if (c13 == 2) {
                        zU03 = xc.d.u0(i17, parcel);
                    } else if (c13 == 3) {
                        zU04 = xc.d.u0(i17, parcel);
                    } else if (c13 == 4) {
                        iZ011 = xc.d.z0(i17, parcel);
                    } else if (c13 != 5) {
                        xc.d.J0(i17, parcel);
                    } else {
                        iZ012 = xc.d.z0(i17, parcel);
                    }
                }
                xc.d.e0(iL05, parcel);
                return new com.google.android.gms.common.internal.v(zU03, iZ010, zU04, iZ011, iZ012);
            case 17:
                return new BinderWrapper(parcel);
            case 18:
                int iL06 = xc.d.L0(parcel);
                Bundle bundleP = null;
                i iVar = null;
                int iZ013 = 0;
                o3.d[] dVarArr = null;
                while (parcel.dataPosition() < iL06) {
                    int i18 = parcel.readInt();
                    char c14 = (char) i18;
                    if (c14 == 1) {
                        bundleP = xc.d.P(i18, parcel);
                    } else if (c14 == 2) {
                        dVarArr = (o3.d[]) xc.d.a0(parcel, i18, o3.d.CREATOR);
                    } else if (c14 == 3) {
                        iZ013 = xc.d.z0(i18, parcel);
                    } else if (c14 != 4) {
                        xc.d.J0(i18, parcel);
                    } else {
                        iVar = (i) xc.d.T(parcel, i18, i.CREATOR);
                    }
                }
                xc.d.e0(iL06, parcel);
                t0 t0Var = new t0();
                t0Var.f12913i = bundleP;
                t0Var.f12914l = dVarArr;
                t0Var.f12915m = iZ013;
                t0Var.f12916n = iVar;
                return t0Var;
            case 19:
                int iL07 = xc.d.L0(parcel);
                com.google.android.gms.common.internal.v vVar = null;
                int[] iArrR = null;
                int[] iArrR2 = null;
                boolean zU05 = false;
                boolean zU06 = false;
                int iZ014 = 0;
                while (parcel.dataPosition() < iL07) {
                    int i19 = parcel.readInt();
                    switch ((char) i19) {
                        case 1:
                            vVar = (com.google.android.gms.common.internal.v) xc.d.T(parcel, i19, com.google.android.gms.common.internal.v.CREATOR);
                            break;
                        case 2:
                            zU05 = xc.d.u0(i19, parcel);
                            break;
                        case 3:
                            zU06 = xc.d.u0(i19, parcel);
                            break;
                        case 4:
                            iArrR = xc.d.R(i19, parcel);
                            break;
                        case 5:
                            iZ014 = xc.d.z0(i19, parcel);
                            break;
                        case 6:
                            iArrR2 = xc.d.R(i19, parcel);
                            break;
                        default:
                            xc.d.J0(i19, parcel);
                            break;
                    }
                }
                xc.d.e0(iL07, parcel);
                return new i(vVar, zU05, zU06, iArrR, iZ014, iArrR2);
            case 20:
                int iL08 = xc.d.L0(parcel);
                Bundle bundle2 = new Bundle();
                Scope[] scopeArr = j.f12863y;
                String strV3 = null;
                IBinder iBinderY02 = null;
                Account account2 = null;
                String strV4 = null;
                int iZ015 = 0;
                int iZ016 = 0;
                int iZ017 = 0;
                boolean zU07 = false;
                int iZ018 = 0;
                boolean zU08 = false;
                o3.d[] dVarArr2 = j.z;
                o3.d[] dVarArr3 = dVarArr2;
                while (parcel.dataPosition() < iL08) {
                    int i20 = parcel.readInt();
                    switch ((char) i20) {
                        case 1:
                            iZ015 = xc.d.z0(i20, parcel);
                            break;
                        case 2:
                            iZ016 = xc.d.z0(i20, parcel);
                            break;
                        case 3:
                            iZ017 = xc.d.z0(i20, parcel);
                            break;
                        case 4:
                            strV3 = xc.d.V(i20, parcel);
                            break;
                        case 5:
                            iBinderY02 = xc.d.y0(i20, parcel);
                            break;
                        case 6:
                            scopeArr = (Scope[]) xc.d.a0(parcel, i20, Scope.CREATOR);
                            break;
                        case 7:
                            bundle2 = xc.d.P(i20, parcel);
                            break;
                        case '\b':
                            account2 = (Account) xc.d.T(parcel, i20, Account.CREATOR);
                            break;
                        case '\t':
                        default:
                            xc.d.J0(i20, parcel);
                            break;
                        case '\n':
                            dVarArr2 = (o3.d[]) xc.d.a0(parcel, i20, o3.d.CREATOR);
                            break;
                        case 11:
                            dVarArr3 = (o3.d[]) xc.d.a0(parcel, i20, o3.d.CREATOR);
                            break;
                        case '\f':
                            zU07 = xc.d.u0(i20, parcel);
                            break;
                        case '\r':
                            iZ018 = xc.d.z0(i20, parcel);
                            break;
                        case 14:
                            zU08 = xc.d.u0(i20, parcel);
                            break;
                        case 15:
                            strV4 = xc.d.V(i20, parcel);
                            break;
                    }
                }
                xc.d.e0(iL08, parcel);
                return new j(iZ015, iZ016, iZ017, strV3, iBinderY02, scopeArr, bundle2, account2, dVarArr2, dVarArr3, zU07, iZ018, zU08, strV4);
            case 21:
                int iL09 = xc.d.L0(parcel);
                String strV5 = null;
                int iZ019 = 0;
                com.google.android.gms.common.server.response.a aVar = null;
                while (parcel.dataPosition() < iL09) {
                    int i21 = parcel.readInt();
                    char c15 = (char) i21;
                    if (c15 == 1) {
                        iZ019 = xc.d.z0(i21, parcel);
                    } else if (c15 == 2) {
                        strV5 = xc.d.V(i21, parcel);
                    } else if (c15 != 3) {
                        xc.d.J0(i21, parcel);
                    } else {
                        aVar = (com.google.android.gms.common.server.response.a) xc.d.T(parcel, i21, com.google.android.gms.common.server.response.a.CREATOR);
                    }
                }
                xc.d.e0(iL09, parcel);
                return new com.google.android.gms.common.server.response.i(aVar, strV5, iZ019);
            case 22:
                int iL010 = xc.d.L0(parcel);
                ArrayList arrayListB02 = null;
                int iZ020 = 0;
                String strV6 = null;
                while (parcel.dataPosition() < iL010) {
                    int i22 = parcel.readInt();
                    char c16 = (char) i22;
                    if (c16 == 1) {
                        iZ020 = xc.d.z0(i22, parcel);
                    } else if (c16 == 2) {
                        arrayListB02 = xc.d.b0(parcel, i22, com.google.android.gms.common.server.response.h.CREATOR);
                    } else if (c16 != 3) {
                        xc.d.J0(i22, parcel);
                    } else {
                        strV6 = xc.d.V(i22, parcel);
                    }
                }
                xc.d.e0(iL010, parcel);
                return new com.google.android.gms.common.server.response.j(iZ020, strV6, arrayListB02);
            case 23:
                int iL011 = xc.d.L0(parcel);
                String strV7 = null;
                int iZ021 = 0;
                ArrayList arrayListB03 = null;
                while (parcel.dataPosition() < iL011) {
                    int i23 = parcel.readInt();
                    char c17 = (char) i23;
                    if (c17 == 1) {
                        iZ021 = xc.d.z0(i23, parcel);
                    } else if (c17 == 2) {
                        strV7 = xc.d.V(i23, parcel);
                    } else if (c17 != 3) {
                        xc.d.J0(i23, parcel);
                    } else {
                        arrayListB03 = xc.d.b0(parcel, i23, com.google.android.gms.common.server.response.i.CREATOR);
                    }
                }
                xc.d.e0(iL011, parcel);
                return new com.google.android.gms.common.server.response.h(iZ021, strV7, arrayListB03);
            case 24:
                int iL012 = xc.d.L0(parcel);
                int iZ022 = 0;
                Parcel parcel2 = null;
                com.google.android.gms.common.server.response.j jVar = null;
                while (parcel.dataPosition() < iL012) {
                    int i24 = parcel.readInt();
                    char c18 = (char) i24;
                    if (c18 == 1) {
                        iZ022 = xc.d.z0(i24, parcel);
                    } else if (c18 == 2) {
                        int iC0 = xc.d.C0(i24, parcel);
                        int iDataPosition = parcel.dataPosition();
                        if (iC0 == 0) {
                            parcel2 = null;
                        } else {
                            Parcel parcelObtain = Parcel.obtain();
                            parcelObtain.appendFrom(parcel, iDataPosition, iC0);
                            parcel.setDataPosition(iDataPosition + iC0);
                            parcel2 = parcelObtain;
                        }
                    } else if (c18 != 3) {
                        xc.d.J0(i24, parcel);
                    } else {
                        jVar = (com.google.android.gms.common.server.response.j) xc.d.T(parcel, i24, com.google.android.gms.common.server.response.j.CREATOR);
                    }
                }
                xc.d.e0(iL012, parcel);
                return new com.google.android.gms.common.server.response.e(iZ022, parcel2, jVar);
            case 25:
                try {
                    return i4.b.a(parcel.readString());
                } catch (Attachment$UnsupportedAttachmentException e5) {
                    throw new RuntimeException(e5);
                }
            case 26:
                int iL013 = xc.d.L0(parcel);
                boolean zU09 = false;
                while (parcel.dataPosition() < iL013) {
                    int i25 = parcel.readInt();
                    if (((char) i25) != 1) {
                        xc.d.J0(i25, parcel);
                    } else {
                        zU09 = xc.d.u0(i25, parcel);
                    }
                }
                xc.d.e0(iL013, parcel);
                return new u0(zU09);
            case 27:
                int iL014 = xc.d.L0(parcel);
                long jB03 = 0;
                while (parcel.dataPosition() < iL014) {
                    int i26 = parcel.readInt();
                    if (((char) i26) != 1) {
                        xc.d.J0(i26, parcel);
                    } else {
                        jB03 = xc.d.B0(i26, parcel);
                    }
                }
                xc.d.e0(iL014, parcel);
                return new h0(jB03);
            case 28:
                int iL015 = xc.d.L0(parcel);
                boolean zU010 = false;
                while (parcel.dataPosition() < iL015) {
                    int i27 = parcel.readInt();
                    if (((char) i27) != 1) {
                        xc.d.J0(i27, parcel);
                    } else {
                        zU010 = xc.d.u0(i27, parcel);
                    }
                }
                xc.d.e0(iL015, parcel);
                return new i4.i0(zU010);
            default:
                int iL016 = xc.d.L0(parcel);
                boolean zU011 = false;
                while (parcel.dataPosition() < iL016) {
                    int i28 = parcel.readInt();
                    if (((char) i28) != 1) {
                        xc.d.J0(i28, parcel);
                    } else {
                        zU011 = xc.d.u0(i28, parcel);
                    }
                }
                xc.d.e0(iL016, parcel);
                return new q(zU011);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i10) {
        switch (this.f843a) {
            case 0:
                return new MediaBrowserCompat$MediaItem[i10];
            case 1:
                return new MediaDescriptionCompat[i10];
            case 2:
                return new MediaMetadataCompat[i10];
            case 3:
                return new RatingCompat[i10];
            case 4:
                return new android.support.v4.os.f[i10];
            case 5:
                return new androidx.activity.result.a[i10];
            case 6:
                return new n[i10];
            case 7:
                return new z[i10];
            case 8:
                return new androidx.navigation.j[i10];
            case 9:
                return new c0[i10];
            case 10:
                return new v1[i10];
            case 11:
                return new w1[i10];
            case 12:
                return new com.google.android.gms.common.internal.a0[i10];
            case 13:
                return new r[i10];
            case 14:
                return new i0[i10];
            case 15:
                return new j0[i10];
            case 16:
                return new com.google.android.gms.common.internal.v[i10];
            case 17:
                return new BinderWrapper[i10];
            case 18:
                return new t0[i10];
            case 19:
                return new i[i10];
            case 20:
                return new j[i10];
            case 21:
                return new com.google.android.gms.common.server.response.i[i10];
            case 22:
                return new com.google.android.gms.common.server.response.j[i10];
            case 23:
                return new com.google.android.gms.common.server.response.h[i10];
            case 24:
                return new com.google.android.gms.common.server.response.e[i10];
            case 25:
                return new i4.b[i10];
            case 26:
                return new u0[i10];
            case 27:
                return new h0[i10];
            case 28:
                return new i4.i0[i10];
            default:
                return new q[i10];
        }
    }
}
