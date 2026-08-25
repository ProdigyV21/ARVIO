package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class q extends t3.a {
    public static final Parcelable.Creator<q> CREATOR = new android.support.v4.media.d(29);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f16161i;

    public q(boolean z) {
        this.f16161i = z;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof q) && this.f16161i == ((q) obj).f16161i;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.f16161i)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f16161i ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
