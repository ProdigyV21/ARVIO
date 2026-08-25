package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.Attachment$UnsupportedAttachmentException;
import com.google.android.gms.fido.fido2.api.common.ResidentKeyRequirement$UnsupportedResidentKeyRequirementException;
import com.google.android.gms.fido.fido2.api.common.zzbc;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class k extends t3.a {
    public static final Parcelable.Creator<k> CREATOR = new k0(27);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b f16128i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Boolean f16129l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e0 f16130m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final a0 f16131n;

    public k(String str, Boolean bool, String str2, String str3) {
        b bVarA;
        a0 a0VarA = null;
        if (str == null) {
            bVarA = null;
        } else {
            try {
                bVarA = b.a(str);
            } catch (Attachment$UnsupportedAttachmentException | ResidentKeyRequirement$UnsupportedResidentKeyRequirementException | zzbc e5) {
                throw new IllegalArgumentException(e5);
            }
        }
        this.f16128i = bVarA;
        this.f16129l = bool;
        this.f16130m = str2 == null ? null : e0.a(str2);
        if (str3 != null) {
            a0VarA = a0.a(str3);
        }
        this.f16131n = a0VarA;
    }

    public final a0 d() {
        a0 a0Var = this.f16131n;
        if (a0Var != null) {
            return a0Var;
        }
        Boolean bool = this.f16129l;
        if (bool == null || !bool.booleanValue()) {
            return null;
        }
        return a0.RESIDENT_KEY_REQUIRED;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return com.google.android.gms.common.internal.t.l(this.f16128i, kVar.f16128i) && com.google.android.gms.common.internal.t.l(this.f16129l, kVar.f16129l) && com.google.android.gms.common.internal.t.l(this.f16130m, kVar.f16130m) && com.google.android.gms.common.internal.t.l(d(), kVar.d());
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16128i, this.f16129l, this.f16130m, d()});
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.f16128i);
        String strValueOf2 = String.valueOf(this.f16130m);
        String strValueOf3 = String.valueOf(this.f16131n);
        StringBuilder sbT = a0.c.t("AuthenticatorSelectionCriteria{\n attachment=", strValueOf, ", \n requireResidentKey=");
        sbT.append(this.f16129l);
        sbT.append(", \n requireUserVerification=");
        sbT.append(strValueOf2);
        sbT.append(", \n residentKeyRequirement=");
        return a0.c.p(sbT, strValueOf3, "\n }");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        b bVar = this.f16128i;
        a.a.g0(parcel, 2, bVar == null ? null : bVar.f16080i, false);
        a.a.Y(parcel, 3, this.f16129l);
        e0 e0Var = this.f16130m;
        a.a.g0(parcel, 4, e0Var == null ? null : e0Var.f16107i, false);
        a0 a0VarD = d();
        a.a.g0(parcel, 5, a0VarD != null ? a0VarD.f16078i : null, false);
        a.a.n0(iM0, parcel);
    }
}
