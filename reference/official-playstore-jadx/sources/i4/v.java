package i4;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.a2;
import com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType$UnsupportedPublicKeyCredTypeException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class v extends t3.a {
    public static final Parcelable.Creator<v> CREATOR = new k0(6);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final x f16202i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final l f16203l;

    public v(String str, int i10) {
        com.google.android.gms.common.internal.t.i(str);
        try {
            this.f16202i = x.a(str);
            try {
                this.f16203l = l.a(i10);
            } catch (COSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException e5) {
                throw new IllegalArgumentException(e5);
            }
        } catch (PublicKeyCredentialType$UnsupportedPublicKeyCredTypeException e6) {
            throw new IllegalArgumentException(e6);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return this.f16202i.equals(vVar.f16202i) && this.f16203l.equals(vVar.f16203l);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16202i, this.f16203l});
    }

    public final String toString() {
        return a2.m("PublicKeyCredentialParameters{\n type=", String.valueOf(this.f16202i), ", \n algorithm=", String.valueOf(this.f16203l), "\n }");
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [i4.a, java.lang.Enum] */
    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        this.f16202i.getClass();
        a.a.g0(parcel, 2, "public-key", false);
        a.a.d0(parcel, 3, Integer.valueOf(this.f16203l.f16133i.a()));
        a.a.n0(iM0, parcel);
    }
}
