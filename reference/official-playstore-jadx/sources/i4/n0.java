package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class n0 extends t3.a {
    public static final Parcelable.Creator<n0> CREATOR = new k0(11);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16142i;

    public n0(String str) {
        this.f16142i = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof n0) {
            return com.google.android.gms.common.internal.t.l(this.f16142i, ((n0) obj).f16142i);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16142i});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, this.f16142i, false);
        a.a.n0(iM0, parcel);
    }
}
