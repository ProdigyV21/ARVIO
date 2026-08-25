package i4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.fido.i1;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class q0 extends t3.a {
    public static final Parcelable.Creator<q0> CREATOR = new k0(29);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f16162i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i1 f16163l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i1 f16164m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final i1 f16165n;

    public q0(long j10, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        com.google.android.gms.common.internal.t.i(bArr);
        i1 i1VarH = i1.h(bArr.length, bArr);
        com.google.android.gms.common.internal.t.i(bArr2);
        i1 i1VarH2 = i1.h(bArr2.length, bArr2);
        com.google.android.gms.common.internal.t.i(bArr3);
        i1 i1VarH3 = i1.h(bArr3.length, bArr3);
        this.f16162i = j10;
        this.f16163l = i1VarH;
        this.f16164m = i1VarH2;
        this.f16165n = i1VarH3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof q0)) {
            return false;
        }
        q0 q0Var = (q0) obj;
        return this.f16162i == q0Var.f16162i && com.google.android.gms.common.internal.t.l(this.f16163l, q0Var.f16163l) && com.google.android.gms.common.internal.t.l(this.f16164m, q0Var.f16164m) && com.google.android.gms.common.internal.t.l(this.f16165n, q0Var.f16165n);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f16162i), this.f16163l, this.f16164m, this.f16165n});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 8);
        parcel.writeLong(this.f16162i);
        a.a.a0(parcel, 2, this.f16163l.k(), false);
        a.a.a0(parcel, 3, this.f16164m.k(), false);
        a.a.a0(parcel, 4, this.f16165n.k(), false);
        a.a.n0(iM0, parcel);
    }
}
