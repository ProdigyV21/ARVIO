package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class g0 extends t3.a {
    public static final Parcelable.Creator<g0> CREATOR = new k0(18);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f16115i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final short f16116l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final short f16117m;

    public g0(int i10, short s10, short s11) {
        this.f16115i = i10;
        this.f16116l = s10;
        this.f16117m = s11;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof g0)) {
            return false;
        }
        g0 g0Var = (g0) obj;
        return this.f16115i == g0Var.f16115i && this.f16116l == g0Var.f16116l && this.f16117m == g0Var.f16117m;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f16115i), Short.valueOf(this.f16116l), Short.valueOf(this.f16117m)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f16115i);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f16116l);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f16117m);
        a.a.n0(iM0, parcel);
    }
}
