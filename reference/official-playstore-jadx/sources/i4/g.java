package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.fido.a1;
import com.google.android.gms.internal.fido.i1;
import com.google.android.gms.internal.fido.y0;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class g extends j {
    public static final Parcelable.Creator<g> CREATOR = new k0(24);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i1 f16110i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i1 f16111l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i1 f16112m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final i1 f16113n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final i1 f16114o;

    public g(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        com.google.android.gms.common.internal.t.i(bArr);
        i1 i1VarH = i1.h(bArr.length, bArr);
        com.google.android.gms.common.internal.t.i(bArr2);
        i1 i1VarH2 = i1.h(bArr2.length, bArr2);
        com.google.android.gms.common.internal.t.i(bArr3);
        i1 i1VarH3 = i1.h(bArr3.length, bArr3);
        com.google.android.gms.common.internal.t.i(bArr4);
        i1 i1VarH4 = i1.h(bArr4.length, bArr4);
        i1 i1VarH5 = bArr5 == null ? null : i1.h(bArr5.length, bArr5);
        this.f16110i = i1VarH;
        this.f16111l = i1VarH2;
        this.f16112m = i1VarH3;
        this.f16113n = i1VarH4;
        this.f16114o = i1VarH5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return com.google.android.gms.common.internal.t.l(this.f16110i, gVar.f16110i) && com.google.android.gms.common.internal.t.l(this.f16111l, gVar.f16111l) && com.google.android.gms.common.internal.t.l(this.f16112m, gVar.f16112m) && com.google.android.gms.common.internal.t.l(this.f16113n, gVar.f16113n) && com.google.android.gms.common.internal.t.l(this.f16114o, gVar.f16114o);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(Arrays.hashCode(new Object[]{this.f16110i})), Integer.valueOf(Arrays.hashCode(new Object[]{this.f16111l})), Integer.valueOf(Arrays.hashCode(new Object[]{this.f16112m})), Integer.valueOf(Arrays.hashCode(new Object[]{this.f16113n})), Integer.valueOf(Arrays.hashCode(new Object[]{this.f16114o}))});
    }

    public final String toString() {
        androidx.appcompat.app.i1 i1Var = new androidx.appcompat.app.i1(getClass().getSimpleName(), 12);
        y0 y0Var = a1.f13747d;
        byte[] bArrK = this.f16110i.k();
        i1Var.f0(y0Var.c(bArrK.length, bArrK), "keyHandle");
        byte[] bArrK2 = this.f16111l.k();
        i1Var.f0(y0Var.c(bArrK2.length, bArrK2), "clientDataJSON");
        byte[] bArrK3 = this.f16112m.k();
        i1Var.f0(y0Var.c(bArrK3.length, bArrK3), "authenticatorData");
        byte[] bArrK4 = this.f16113n.k();
        i1Var.f0(y0Var.c(bArrK4.length, bArrK4), "signature");
        i1 i1Var2 = this.f16114o;
        byte[] bArrK5 = i1Var2 == null ? null : i1Var2.k();
        if (bArrK5 != null) {
            i1Var.f0(y0Var.c(bArrK5.length, bArrK5), "userHandle");
        }
        return i1Var.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.a0(parcel, 2, this.f16110i.k(), false);
        a.a.a0(parcel, 3, this.f16111l.k(), false);
        a.a.a0(parcel, 4, this.f16112m.k(), false);
        a.a.a0(parcel, 5, this.f16113n.k(), false);
        i1 i1Var = this.f16114o;
        a.a.a0(parcel, 6, i1Var == null ? null : i1Var.k(), false);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("clientDataJSON", c4.d.b(this.f16111l.k()));
            jSONObject.put("authenticatorData", c4.d.b(this.f16112m.k()));
            jSONObject.put("signature", c4.d.b(this.f16113n.k()));
            i1 i1Var = this.f16114o;
            if (i1Var == null) {
                return jSONObject;
            }
            jSONObject.put("userHandle", c4.d.b(i1Var == null ? null : i1Var.k()));
            return jSONObject;
        } catch (JSONException e5) {
            throw new RuntimeException("Error encoding AuthenticatorAssertionResponse to JSON object", e5);
        }
    }
}
