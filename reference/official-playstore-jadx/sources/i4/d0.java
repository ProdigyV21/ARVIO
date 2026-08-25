package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class d0 extends t3.a {
    public static final Parcelable.Creator<d0> CREATOR = new k0(15);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f16100i;

    public d0(boolean z) {
        this.f16100i = z;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof d0) && this.f16100i == ((d0) obj).f16100i;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.f16100i)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f16100i ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
