package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class u0 extends t3.a {
    public static final Parcelable.Creator<u0> CREATOR = new android.support.v4.media.d(26);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f16201i;

    public u0(boolean z) {
        this.f16201i = Boolean.valueOf(z).booleanValue();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof u0) && this.f16201i == ((u0) obj).f16201i;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.f16201i)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f16201i ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
