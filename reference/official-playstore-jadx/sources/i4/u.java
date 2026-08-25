package i4;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.a2;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType$UnsupportedPublicKeyCredTypeException;
import com.google.android.gms.internal.fido.i1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class u extends t3.a {
    public static final Parcelable.Creator<u> CREATOR;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final x f16198i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i1 f16199l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f16200m;

    static {
        com.google.android.gms.internal.fido.s.f(2, com.google.android.gms.internal.fido.b.f13753m, com.google.android.gms.internal.fido.b.f13754n);
        CREATOR = new k0(5);
    }

    public u(String str, byte[] bArr, ArrayList arrayList) {
        i1 i1Var = i1.f13784m;
        i1 i1VarH = i1.h(bArr.length, bArr);
        com.google.android.gms.common.internal.t.i(str);
        try {
            this.f16198i = x.a(str);
            this.f16199l = i1VarH;
            this.f16200m = arrayList;
        } catch (PublicKeyCredentialType$UnsupportedPublicKeyCredTypeException e5) {
            throw new IllegalArgumentException(e5);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        List list = uVar.f16200m;
        if (!this.f16198i.equals(uVar.f16198i) || !com.google.android.gms.common.internal.t.l(this.f16199l, uVar.f16199l)) {
            return false;
        }
        List list2 = this.f16200m;
        if (list2 == null && list == null) {
            return true;
        }
        return list2 != null && list != null && list2.containsAll(list) && list.containsAll(list2);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16198i, this.f16199l, this.f16200m});
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.f16198i);
        String strB = c4.d.b(this.f16199l.k());
        return a0.c.p(a2.r("PublicKeyCredentialDescriptor{\n type=", strValueOf, ", \n id=", strB, ", \n transports="), String.valueOf(this.f16200m), "}");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        this.f16198i.getClass();
        a.a.g0(parcel, 2, "public-key", false);
        a.a.a0(parcel, 3, this.f16199l.k(), false);
        a.a.k0(parcel, 4, this.f16200m, false);
        a.a.n0(iM0, parcel);
    }
}
