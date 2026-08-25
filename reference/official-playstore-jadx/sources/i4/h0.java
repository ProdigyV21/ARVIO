package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class h0 extends t3.a {
    public static final Parcelable.Creator<h0> CREATOR = new android.support.v4.media.d(27);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f16122i;

    public h0(long j10) {
        this.f16122i = Long.valueOf(j10).longValue();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof h0) && this.f16122i == ((h0) obj).f16122i;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f16122i)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 8);
        parcel.writeLong(this.f16122i);
        a.a.n0(iM0, parcel);
    }
}
