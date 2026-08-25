package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends t3.a {
    public static final Parcelable.Creator<e> CREATOR = new k0(19);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f0 f16101i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o0 f16102l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f f16103m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final p0 f16104n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final String f16105o;

    public e(f0 f0Var, o0 o0Var, f fVar, p0 p0Var, String str) {
        this.f16101i = f0Var;
        this.f16102l = o0Var;
        this.f16103m = fVar;
        this.f16104n = p0Var;
        this.f16105o = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return com.google.android.gms.common.internal.t.l(this.f16101i, eVar.f16101i) && com.google.android.gms.common.internal.t.l(this.f16102l, eVar.f16102l) && com.google.android.gms.common.internal.t.l(this.f16103m, eVar.f16103m) && com.google.android.gms.common.internal.t.l(this.f16104n, eVar.f16104n) && com.google.android.gms.common.internal.t.l(this.f16105o, eVar.f16105o);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16101i, this.f16102l, this.f16103m, this.f16104n, this.f16105o});
    }

    public final String toString() {
        return a0.c.l("AuthenticationExtensionsClientOutputs{", zza().toString(), "}");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 1, this.f16101i, i10, false);
        a.a.f0(parcel, 2, this.f16102l, i10, false);
        a.a.f0(parcel, 3, this.f16103m, i10, false);
        a.a.f0(parcel, 4, this.f16104n, i10, false);
        a.a.g0(parcel, 5, this.f16105o, false);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        try {
            JSONObject jSONObject = new JSONObject();
            f fVar = this.f16103m;
            if (fVar != null) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("rk", fVar.f16108i);
                    jSONObject.put("credProps", jSONObject2);
                } catch (JSONException e5) {
                    throw new RuntimeException("Error encoding AuthenticationExtensionsCredPropsOutputs to JSON object", e5);
                }
            }
            f0 f0Var = this.f16101i;
            if (f0Var != null) {
                jSONObject.put("uvm", f0Var.d());
            }
            p0 p0Var = this.f16104n;
            if (p0Var != null) {
                jSONObject.put("prf", p0Var.zza());
            }
            String str = this.f16105o;
            if (str != null) {
                jSONObject.put("txAuthSimple", str);
            }
            return jSONObject;
        } catch (JSONException e6) {
            throw new RuntimeException("Error encoding AuthenticationExtensionsClientOutputs to JSON object", e6);
        }
    }
}
