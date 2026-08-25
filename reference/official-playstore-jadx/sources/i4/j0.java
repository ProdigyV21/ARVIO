package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class j0 extends t3.a {
    public static final Parcelable.Creator<j0> CREATOR = new k0(0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f16127i;

    public j0(String str) {
        com.google.android.gms.common.internal.t.i(str);
        this.f16127i = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j0) {
            return this.f16127i.equals(((j0) obj).f16127i);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16127i});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, this.f16127i, false);
        a.a.n0(iM0, parcel);
    }
}
