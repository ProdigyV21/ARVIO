package i4;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.internal.fido.i1;
import com.google.android.gms.internal.fido.x1;
import com.google.android.gms.internal.fido.y1;
import io.ktor.http.LinkHeader;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class r extends t3.a {
    public static final Parcelable.Creator<r> CREATOR = new k0(4);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16166i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f16167l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i1 f16168m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final h f16169n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final g f16170o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final i f16171p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final e f16172q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f16173r;

    public r(String str, String str2, byte[] bArr, h hVar, g gVar, i iVar, e eVar, String str3) {
        i1 i1VarH = bArr == null ? null : i1.h(bArr.length, bArr);
        boolean z = false;
        com.google.android.gms.common.internal.t.a("Must provide a response object.", (hVar != null && gVar == null && iVar == null) || (hVar == null && gVar != null && iVar == null) || (hVar == null && gVar == null && iVar != null));
        if (iVar != null || (str != null && i1VarH != null)) {
            z = true;
        }
        com.google.android.gms.common.internal.t.a("Must provide id and rawId if not an error response.", z);
        this.f16166i = str;
        this.f16167l = str2;
        this.f16168m = i1VarH;
        this.f16169n = hVar;
        this.f16170o = gVar;
        this.f16171p = iVar;
        this.f16172q = eVar;
        this.f16173r = str3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return com.google.android.gms.common.internal.t.l(this.f16166i, rVar.f16166i) && com.google.android.gms.common.internal.t.l(this.f16167l, rVar.f16167l) && com.google.android.gms.common.internal.t.l(this.f16168m, rVar.f16168m) && com.google.android.gms.common.internal.t.l(this.f16169n, rVar.f16169n) && com.google.android.gms.common.internal.t.l(this.f16170o, rVar.f16170o) && com.google.android.gms.common.internal.t.l(this.f16171p, rVar.f16171p) && com.google.android.gms.common.internal.t.l(this.f16172q, rVar.f16172q) && com.google.android.gms.common.internal.t.l(this.f16173r, rVar.f16173r);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16166i, this.f16167l, this.f16168m, this.f16170o, this.f16169n, this.f16171p, this.f16172q, this.f16173r});
    }

    public final String toString() {
        i1 i1Var = this.f16168m;
        String strB = c4.d.b(i1Var == null ? null : i1Var.k());
        String strValueOf = String.valueOf(this.f16169n);
        String strValueOf2 = String.valueOf(this.f16170o);
        String strValueOf3 = String.valueOf(this.f16171p);
        String strValueOf4 = String.valueOf(this.f16172q);
        StringBuilder sbR = a2.r("PublicKeyCredential{\n id='", this.f16166i, "', \n type='", this.f16167l, "', \n rawId=");
        y.a.i(sbR, strB, ", \n registerResponse=", strValueOf, ", \n signResponse=");
        y.a.i(sbR, strValueOf2, ", \n errorResponse=", strValueOf3, ", \n extensionsClientOutputs=");
        return a2.n(sbR, strValueOf4, ", \n authenticatorAttachment='", this.f16173r, "'}");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        ((y1) x1.f13847l.f13848i.f13787i).zza();
        throw null;
    }

    public final JSONObject zza() {
        JSONObject jSONObjectZza;
        try {
            JSONObject jSONObject = new JSONObject();
            i1 i1Var = this.f16168m;
            if (i1Var != null && i1Var.k().length > 0) {
                jSONObject.put("rawId", c4.d.b(i1Var.k()));
            }
            String str = this.f16173r;
            if (str != null) {
                jSONObject.put("authenticatorAttachment", str);
            }
            String str2 = this.f16167l;
            i iVar = this.f16171p;
            if (str2 != null && iVar == null) {
                jSONObject.put(LinkHeader.Parameters.Type, str2);
            }
            String str3 = this.f16166i;
            if (str3 != null) {
                jSONObject.put(TtmlNode.ATTR_ID, str3);
            }
            String str4 = "response";
            g gVar = this.f16170o;
            boolean z = true;
            if (gVar != null) {
                jSONObjectZza = gVar.zza();
            } else {
                h hVar = this.f16169n;
                if (hVar != null) {
                    jSONObjectZza = hVar.zza();
                } else {
                    z = false;
                    if (iVar != null) {
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("code", iVar.f16123i.f16155i);
                            String str5 = iVar.f16124l;
                            if (str5 != null) {
                                jSONObject2.put("message", str5);
                            }
                            jSONObjectZza = jSONObject2;
                            str4 = "error";
                        } catch (JSONException e5) {
                            throw new RuntimeException("Error encoding AuthenticatorErrorResponse to JSON object", e5);
                        }
                    } else {
                        jSONObjectZza = null;
                    }
                }
            }
            if (jSONObjectZza != null) {
                jSONObject.put(str4, jSONObjectZza);
            }
            e eVar = this.f16172q;
            if (eVar != null) {
                jSONObject.put("clientExtensionResults", eVar.zza());
                return jSONObject;
            }
            if (z) {
                jSONObject.put("clientExtensionResults", new JSONObject());
            }
            return jSONObject;
        } catch (JSONException e6) {
            throw new RuntimeException("Error encoding PublicKeyCredential to JSON object", e6);
        }
    }
}
