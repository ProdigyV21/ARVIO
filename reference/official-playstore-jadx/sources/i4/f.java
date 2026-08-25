package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class f extends t3.a {
    public static final Parcelable.Creator<f> CREATOR = new k0(21);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f16108i;

    public f(boolean z) {
        this.f16108i = z;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof f) && this.f16108i == ((f) obj).f16108i;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.f16108i)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f16108i ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
