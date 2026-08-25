package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.fido.i1;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class y extends t3.a {
    public static final Parcelable.Creator<y> CREATOR = new k0(9);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i1 f16208i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f16209l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f16210m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f16211n;

    public y(String str, String str2, String str3, byte[] bArr) {
        com.google.android.gms.common.internal.t.i(bArr);
        this.f16208i = i1.h(bArr.length, bArr);
        com.google.android.gms.common.internal.t.i(str);
        this.f16209l = str;
        this.f16210m = str2;
        com.google.android.gms.common.internal.t.i(str3);
        this.f16211n = str3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return com.google.android.gms.common.internal.t.l(this.f16208i, yVar.f16208i) && com.google.android.gms.common.internal.t.l(this.f16209l, yVar.f16209l) && com.google.android.gms.common.internal.t.l(this.f16210m, yVar.f16210m) && com.google.android.gms.common.internal.t.l(this.f16211n, yVar.f16211n);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16208i, this.f16209l, this.f16210m, this.f16211n});
    }

    public final String toString() {
        StringBuilder sbT = a0.c.t("PublicKeyCredentialUserEntity{\n id=", c4.d.b(this.f16208i.k()), ", \n name='");
        sbT.append(this.f16209l);
        sbT.append("', \n icon='");
        sbT.append(this.f16210m);
        sbT.append("', \n displayName='");
        return a0.c.p(sbT, this.f16211n, "'}");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.a0(parcel, 2, this.f16208i.k(), false);
        a.a.g0(parcel, 3, this.f16209l, false);
        a.a.g0(parcel, 4, this.f16210m, false);
        a.a.g0(parcel, 5, this.f16211n, false);
        a.a.n0(iM0, parcel);
    }
}
