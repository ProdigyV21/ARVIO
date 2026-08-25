package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class i0 extends t3.a {
    public static final Parcelable.Creator<i0> CREATOR = new android.support.v4.media.d(28);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f16126i;

    public i0(boolean z) {
        this.f16126i = Boolean.valueOf(z).booleanValue();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof i0) && this.f16126i == ((i0) obj).f16126i;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.f16126i)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f16126i ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
