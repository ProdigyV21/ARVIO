package i4;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.internal.fido.i1;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class p0 extends t3.a {
    public static final Parcelable.Creator<p0> CREATOR = new k0(23);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f16159i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i1 f16160l;

    public p0(boolean z, i1 i1Var) {
        this.f16159i = z;
        this.f16160l = i1Var;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof p0)) {
            return false;
        }
        p0 p0Var = (p0) obj;
        return this.f16159i == p0Var.f16159i && com.google.android.gms.common.internal.t.l(this.f16160l, p0Var.f16160l);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.f16159i), this.f16160l});
    }

    public final String toString() {
        return a0.c.l("AuthenticationExtensionsPrfOutputs{", zza().toString(), "}");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f16159i ? 1 : 0);
        i1 i1Var = this.f16160l;
        a.a.a0(parcel, 2, i1Var == null ? null : i1Var.k(), false);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        try {
            JSONObject jSONObject = new JSONObject();
            if (this.f16159i) {
                jSONObject.put("enabled", true);
            }
            i1 i1Var = this.f16160l;
            byte[] bArrK = i1Var == null ? null : i1Var.k();
            if (bArrK != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("first", Base64.encodeToString(Arrays.copyOf(bArrK, 32), 11));
                if (bArrK.length == 64) {
                    jSONObject2.put("second", Base64.encodeToString(Arrays.copyOfRange(bArrK, 32, 64), 11));
                }
                jSONObject.put("results", jSONObject2);
            }
            return jSONObject;
        } catch (JSONException e5) {
            throw new RuntimeException("Error encoding AuthenticationExtensionsPrfOutputs to JSON object", e5);
        }
    }
}
