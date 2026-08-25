package i4;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.fragment.app.a2;
import com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class t extends j {
    public static final Parcelable.Creator<t> CREATOR = new k0(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final w f16185i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final y f16186l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final byte[] f16187m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final List f16188n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Double f16189o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final List f16190p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final k f16191q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Integer f16192r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final c0 f16193s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final c f16194t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final d f16195u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final String f16196v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ResultReceiver f16197w;

    public t(w wVar, y yVar, byte[] bArr, ArrayList arrayList, Double d4, ArrayList arrayList2, k kVar, Integer num, c0 c0Var, String str, d dVar, String str2, ResultReceiver resultReceiver) {
        this.f16197w = resultReceiver;
        if (str2 != null) {
            try {
                t tVarD = d(new JSONObject(str2));
                this.f16185i = tVarD.f16185i;
                this.f16186l = tVarD.f16186l;
                this.f16187m = tVarD.f16187m;
                this.f16188n = tVarD.f16188n;
                this.f16189o = tVarD.f16189o;
                this.f16190p = tVarD.f16190p;
                this.f16191q = tVarD.f16191q;
                this.f16192r = tVarD.f16192r;
                this.f16193s = tVarD.f16193s;
                this.f16194t = tVarD.f16194t;
                this.f16195u = tVarD.f16195u;
                this.f16196v = str2;
                return;
            } catch (JSONException e5) {
                throw new IllegalArgumentException(e5);
            }
        }
        com.google.android.gms.common.internal.t.i(wVar);
        this.f16185i = wVar;
        com.google.android.gms.common.internal.t.i(yVar);
        this.f16186l = yVar;
        com.google.android.gms.common.internal.t.i(bArr);
        this.f16187m = bArr;
        com.google.android.gms.common.internal.t.i(arrayList);
        this.f16188n = arrayList;
        this.f16189o = d4;
        this.f16190p = arrayList2;
        this.f16191q = kVar;
        this.f16192r = num;
        this.f16193s = c0Var;
        if (str != null) {
            try {
                this.f16194t = c.a(str);
            } catch (AttestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException e6) {
                throw new IllegalArgumentException(e6);
            }
        } else {
            this.f16194t = null;
        }
        this.f16195u = dVar;
        this.f16196v = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0215  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static i4.t d(org.json.JSONObject r32) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 904
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i4.t.d(org.json.JSONObject):i4.t");
    }

    public final boolean equals(Object obj) {
        List list;
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        List list2 = tVar.f16188n;
        List list3 = tVar.f16190p;
        if (com.google.android.gms.common.internal.t.l(this.f16185i, tVar.f16185i) && com.google.android.gms.common.internal.t.l(this.f16186l, tVar.f16186l) && Arrays.equals(this.f16187m, tVar.f16187m) && com.google.android.gms.common.internal.t.l(this.f16189o, tVar.f16189o)) {
            List list4 = this.f16188n;
            if (list4.containsAll(list2) && list2.containsAll(list4) && ((((list = this.f16190p) == null && list3 == null) || (list != null && list3 != null && list.containsAll(list3) && list3.containsAll(list))) && com.google.android.gms.common.internal.t.l(this.f16191q, tVar.f16191q) && com.google.android.gms.common.internal.t.l(this.f16192r, tVar.f16192r) && com.google.android.gms.common.internal.t.l(this.f16193s, tVar.f16193s) && com.google.android.gms.common.internal.t.l(this.f16194t, tVar.f16194t) && com.google.android.gms.common.internal.t.l(this.f16195u, tVar.f16195u) && com.google.android.gms.common.internal.t.l(this.f16196v, tVar.f16196v))) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16185i, this.f16186l, Integer.valueOf(Arrays.hashCode(this.f16187m)), this.f16188n, this.f16189o, this.f16190p, this.f16191q, this.f16192r, this.f16193s, this.f16194t, this.f16195u, this.f16196v});
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.f16185i);
        String strValueOf2 = String.valueOf(this.f16186l);
        String strB = c4.d.b(this.f16187m);
        String strValueOf3 = String.valueOf(this.f16188n);
        String strValueOf4 = String.valueOf(this.f16190p);
        String strValueOf5 = String.valueOf(this.f16191q);
        String strValueOf6 = String.valueOf(this.f16193s);
        String strValueOf7 = String.valueOf(this.f16194t);
        String strValueOf8 = String.valueOf(this.f16195u);
        StringBuilder sbR = a2.r("PublicKeyCredentialCreationOptions{\n rp=", strValueOf, ", \n user=", strValueOf2, ", \n challenge=");
        y.a.i(sbR, strB, ", \n parameters=", strValueOf3, ", \n timeoutSeconds=");
        sbR.append(this.f16189o);
        sbR.append(", \n excludeList=");
        sbR.append(strValueOf4);
        sbR.append(", \n authenticatorSelection=");
        sbR.append(strValueOf5);
        sbR.append(", \n requestId=");
        sbR.append(this.f16192r);
        sbR.append(", \n tokenBinding=");
        sbR.append(strValueOf6);
        sbR.append(", \n attestationConveyancePreference=");
        return a2.n(sbR, strValueOf7, ", \n authenticationExtensions=", strValueOf8, "}");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 2, this.f16185i, i10, false);
        a.a.f0(parcel, 3, this.f16186l, i10, false);
        a.a.a0(parcel, 4, this.f16187m, false);
        a.a.k0(parcel, 5, this.f16188n, false);
        Double d4 = this.f16189o;
        if (d4 != null) {
            a.a.l0(parcel, 6, 8);
            parcel.writeDouble(d4.doubleValue());
        }
        a.a.k0(parcel, 7, this.f16190p, false);
        a.a.f0(parcel, 8, this.f16191q, i10, false);
        a.a.d0(parcel, 9, this.f16192r);
        a.a.f0(parcel, 10, this.f16193s, i10, false);
        c cVar = this.f16194t;
        a.a.g0(parcel, 11, cVar == null ? null : cVar.f16085i, false);
        a.a.f0(parcel, 12, this.f16195u, i10, false);
        a.a.g0(parcel, 13, this.f16196v, false);
        a.a.f0(parcel, 14, this.f16197w, i10, false);
        a.a.n0(iM0, parcel);
    }

    public t(String str) {
        try {
            t tVarD = d(new JSONObject(str));
            this.f16185i = tVarD.f16185i;
            this.f16186l = tVarD.f16186l;
            this.f16187m = tVarD.f16187m;
            this.f16188n = tVarD.f16188n;
            this.f16189o = tVarD.f16189o;
            this.f16190p = tVarD.f16190p;
            this.f16191q = tVarD.f16191q;
            this.f16192r = tVarD.f16192r;
            this.f16193s = tVarD.f16193s;
            this.f16194t = tVarD.f16194t;
            this.f16195u = tVarD.f16195u;
            this.f16196v = str;
        } catch (JSONException e5) {
            throw new IllegalArgumentException(e5);
        }
    }
}
