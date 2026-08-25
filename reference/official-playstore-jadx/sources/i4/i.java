package i4;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.app.i1;
import com.google.android.gms.fido.fido2.api.common.ErrorCode$UnsupportedErrorCodeException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class i extends j {
    public static final Parcelable.Creator<i> CREATOR = new k0(26);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o f16123i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f16124l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f16125m;

    public i(int i10, String str, int i11) {
        try {
            this.f16123i = o.a(i10);
            this.f16124l = str;
            this.f16125m = i11;
        } catch (ErrorCode$UnsupportedErrorCodeException e5) {
            throw new IllegalArgumentException(e5);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return com.google.android.gms.common.internal.t.l(this.f16123i, iVar.f16123i) && com.google.android.gms.common.internal.t.l(this.f16124l, iVar.f16124l) && com.google.android.gms.common.internal.t.l(Integer.valueOf(this.f16125m), Integer.valueOf(iVar.f16125m));
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16123i, this.f16124l, Integer.valueOf(this.f16125m)});
    }

    public final String toString() {
        i1 i1Var = new i1(getClass().getSimpleName(), 12);
        String strValueOf = String.valueOf(this.f16123i.f16155i);
        com.google.android.gms.internal.fido.f fVar = new com.google.android.gms.internal.fido.f(11);
        ((i1) i1Var.f1061n).f1061n = fVar;
        i1Var.f1061n = fVar;
        fVar.f1060m = strValueOf;
        fVar.f1059l = "errorCode";
        String str = this.f16124l;
        if (str != null) {
            i1Var.f0(str, "errorMessage");
        }
        return i1Var.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.f16123i.f16155i;
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(i11);
        a.a.g0(parcel, 3, this.f16124l, false);
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(this.f16125m);
        a.a.n0(iM0, parcel);
    }
}
