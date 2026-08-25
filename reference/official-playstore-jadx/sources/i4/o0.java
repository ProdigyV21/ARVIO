package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.fido.i1;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class o0 extends t3.a {
    public static final Parcelable.Creator<o0> CREATOR = new k0(22);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i1 f16156i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i1 f16157l;

    public o0(i1 i1Var, i1 i1Var2) {
        this.f16156i = i1Var;
        this.f16157l = i1Var2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof o0)) {
            return false;
        }
        o0 o0Var = (o0) obj;
        return com.google.android.gms.common.internal.t.l(this.f16156i, o0Var.f16156i) && com.google.android.gms.common.internal.t.l(this.f16157l, o0Var.f16157l);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16156i, this.f16157l});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        i1 i1Var = this.f16156i;
        a.a.a0(parcel, 1, i1Var == null ? null : i1Var.k(), false);
        i1 i1Var2 = this.f16157l;
        a.a.a0(parcel, 2, i1Var2 != null ? i1Var2.k() : null, false);
        a.a.n0(iM0, parcel);
    }
}
