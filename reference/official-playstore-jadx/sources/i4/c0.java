package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.TokenBinding$UnsupportedTokenBindingStatusException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class c0 extends t3.a {
    public static final Parcelable.Creator<c0> CREATOR = new k0(13);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b0 f16086i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f16087l;

    static {
        new c0("supported", null);
        new c0("not-supported", null);
    }

    public c0(String str, String str2) {
        com.google.android.gms.common.internal.t.i(str);
        try {
            this.f16086i = b0.a(str);
            this.f16087l = str2;
        } catch (TokenBinding$UnsupportedTokenBindingStatusException e5) {
            throw new IllegalArgumentException(e5);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c0)) {
            return false;
        }
        c0 c0Var = (c0) obj;
        return com.google.android.gms.internal.fido.b.h(this.f16086i, c0Var.f16086i) && com.google.android.gms.internal.fido.b.h(this.f16087l, c0Var.f16087l);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16086i, this.f16087l});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, this.f16086i.f16082i, false);
        a.a.g0(parcel, 3, this.f16087l, false);
        a.a.n0(iM0, parcel);
    }
}
