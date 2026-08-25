package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class p extends t3.a {
    public static final Parcelable.Creator<p> CREATOR = new s0(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16158i;

    public p(String str) {
        com.google.android.gms.common.internal.t.i(str);
        this.f16158i = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof p) {
            return this.f16158i.equals(((p) obj).f16158i);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16158i});
    }

    public final String toString() {
        return a0.c.p(new StringBuilder("FidoAppIdExtension{appid='"), this.f16158i, "'}");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, this.f16158i, false);
        a.a.n0(iM0, parcel);
    }
}
