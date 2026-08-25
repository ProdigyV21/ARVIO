package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class r0 extends t3.a {
    public static final Parcelable.Creator<r0> CREATOR = new s0(0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f16174i;

    public r0(ArrayList arrayList) {
        com.google.android.gms.common.internal.t.i(arrayList);
        this.f16174i = arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r0)) {
            return false;
        }
        List list = ((r0) obj).f16174i;
        List list2 = this.f16174i;
        return list2.containsAll(list) && list.containsAll(list2);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{new HashSet(this.f16174i)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.k0(parcel, 1, this.f16174i, false);
        a.a.n0(iM0, parcel);
    }
}
