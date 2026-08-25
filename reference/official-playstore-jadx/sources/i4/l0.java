package i4;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.a2;
import com.google.android.gms.internal.fido.i1;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class l0 extends t3.a {
    public static final Parcelable.Creator<l0> CREATOR = new k0(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i1 f16134i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i1 f16135l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i1 f16136m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f16137n;

    public l0(i1 i1Var, i1 i1Var2, i1 i1Var3, int i10) {
        this.f16134i = i1Var;
        this.f16135l = i1Var2;
        this.f16136m = i1Var3;
        this.f16137n = i10;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof l0)) {
            return false;
        }
        l0 l0Var = (l0) obj;
        return com.google.android.gms.common.internal.t.l(this.f16134i, l0Var.f16134i) && com.google.android.gms.common.internal.t.l(this.f16135l, l0Var.f16135l) && com.google.android.gms.common.internal.t.l(this.f16136m, l0Var.f16136m) && this.f16137n == l0Var.f16137n;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16134i, this.f16135l, this.f16136m, Integer.valueOf(this.f16137n)});
    }

    public final String toString() {
        i1 i1Var = this.f16134i;
        String strB = c4.d.b(i1Var == null ? null : i1Var.k());
        i1 i1Var2 = this.f16135l;
        String strB2 = c4.d.b(i1Var2 == null ? null : i1Var2.k());
        i1 i1Var3 = this.f16136m;
        String strB3 = c4.d.b(i1Var3 != null ? i1Var3.k() : null);
        StringBuilder sbR = a2.r("HmacSecretExtension{coseKeyAgreement=", strB, ", saltEnc=", strB2, ", saltAuth=");
        sbR.append(strB3);
        sbR.append(", getPinUvAuthProtocol=");
        return androidx.compose.material3.d.j(this.f16137n, "}", sbR);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        i1 i1Var = this.f16134i;
        a.a.a0(parcel, 1, i1Var == null ? null : i1Var.k(), false);
        i1 i1Var2 = this.f16135l;
        a.a.a0(parcel, 2, i1Var2 == null ? null : i1Var2.k(), false);
        i1 i1Var3 = this.f16136m;
        a.a.a0(parcel, 3, i1Var3 != null ? i1Var3.k() : null, false);
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(this.f16137n);
        a.a.n0(iM0, parcel);
    }
}
