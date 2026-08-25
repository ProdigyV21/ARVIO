package i4;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.a2;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class d extends t3.a {
    public static final Parcelable.Creator<d> CREATOR = new k0(20);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p f16088i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r0 f16089l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final d0 f16090m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u0 f16091n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final h0 f16092o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final i0 f16093p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final t0 f16094q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final j0 f16095r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final q f16096s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final m0 f16097t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final n0 f16098u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final l0 f16099v;

    public d(p pVar, r0 r0Var, d0 d0Var, u0 u0Var, h0 h0Var, i0 i0Var, t0 t0Var, j0 j0Var, q qVar, m0 m0Var, n0 n0Var, l0 l0Var) {
        this.f16088i = pVar;
        this.f16090m = d0Var;
        this.f16089l = r0Var;
        this.f16091n = u0Var;
        this.f16092o = h0Var;
        this.f16093p = i0Var;
        this.f16094q = t0Var;
        this.f16095r = j0Var;
        this.f16096s = qVar;
        this.f16097t = m0Var;
        this.f16098u = n0Var;
        this.f16099v = l0Var;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return com.google.android.gms.common.internal.t.l(this.f16088i, dVar.f16088i) && com.google.android.gms.common.internal.t.l(this.f16089l, dVar.f16089l) && com.google.android.gms.common.internal.t.l(this.f16090m, dVar.f16090m) && com.google.android.gms.common.internal.t.l(this.f16091n, dVar.f16091n) && com.google.android.gms.common.internal.t.l(this.f16092o, dVar.f16092o) && com.google.android.gms.common.internal.t.l(this.f16093p, dVar.f16093p) && com.google.android.gms.common.internal.t.l(this.f16094q, dVar.f16094q) && com.google.android.gms.common.internal.t.l(this.f16095r, dVar.f16095r) && com.google.android.gms.common.internal.t.l(this.f16096s, dVar.f16096s) && com.google.android.gms.common.internal.t.l(this.f16097t, dVar.f16097t) && com.google.android.gms.common.internal.t.l(this.f16098u, dVar.f16098u) && com.google.android.gms.common.internal.t.l(this.f16099v, dVar.f16099v);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16088i, this.f16089l, this.f16090m, this.f16091n, this.f16092o, this.f16093p, this.f16094q, this.f16095r, this.f16096s, this.f16097t, this.f16098u, this.f16099v});
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.f16088i);
        String strValueOf2 = String.valueOf(this.f16089l);
        String strValueOf3 = String.valueOf(this.f16090m);
        String strValueOf4 = String.valueOf(this.f16091n);
        String strValueOf5 = String.valueOf(this.f16092o);
        String strValueOf6 = String.valueOf(this.f16093p);
        String strValueOf7 = String.valueOf(this.f16094q);
        String strValueOf8 = String.valueOf(this.f16095r);
        String strValueOf9 = String.valueOf(this.f16096s);
        String strValueOf10 = String.valueOf(this.f16097t);
        String strValueOf11 = String.valueOf(this.f16098u);
        StringBuilder sbR = a2.r("AuthenticationExtensions{\n fidoAppIdExtension=", strValueOf, ", \n cableAuthenticationExtension=", strValueOf2, ", \n userVerificationMethodExtension=");
        y.a.i(sbR, strValueOf3, ", \n googleMultiAssertionExtension=", strValueOf4, ", \n googleSessionIdExtension=");
        y.a.i(sbR, strValueOf5, ", \n googleSilentVerificationExtension=", strValueOf6, ", \n devicePublicKeyExtension=");
        y.a.i(sbR, strValueOf7, ", \n googleTunnelServerIdExtension=", strValueOf8, ", \n googleThirdPartyPaymentExtension=");
        y.a.i(sbR, strValueOf9, ", \n prfExtension=", strValueOf10, ", \n simpleTransactionAuthorizationExtension=");
        return a0.c.p(sbR, strValueOf11, "}");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 2, this.f16088i, i10, false);
        a.a.f0(parcel, 3, this.f16089l, i10, false);
        a.a.f0(parcel, 4, this.f16090m, i10, false);
        a.a.f0(parcel, 5, this.f16091n, i10, false);
        a.a.f0(parcel, 6, this.f16092o, i10, false);
        a.a.f0(parcel, 7, this.f16093p, i10, false);
        a.a.f0(parcel, 8, this.f16094q, i10, false);
        a.a.f0(parcel, 9, this.f16095r, i10, false);
        a.a.f0(parcel, 10, this.f16096s, i10, false);
        a.a.f0(parcel, 11, this.f16097t, i10, false);
        a.a.f0(parcel, 12, this.f16098u, i10, false);
        a.a.f0(parcel, 13, this.f16099v, i10, false);
        a.a.n0(iM0, parcel);
    }
}
