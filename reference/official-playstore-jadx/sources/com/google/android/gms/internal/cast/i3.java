package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class i3 extends t3.a {
    public static final Parcelable.Creator<i3> CREATOR = new h3(0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f13309i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final byte[] f13310l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f13311m;

    public i3(String str, byte[] bArr, ArrayList arrayList) {
        this.f13309i = str;
        this.f13310l = bArr;
        this.f13311m = arrayList == null ? new ArrayList(0) : new ArrayList(arrayList);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof i3)) {
            return false;
        }
        i3 i3Var = (i3) obj;
        return com.google.android.gms.common.internal.t.l(this.f13309i, i3Var.f13309i) && com.google.android.gms.common.internal.t.l(this.f13310l, i3Var.f13310l) && com.google.android.gms.common.internal.t.l(this.f13311m, i3Var.f13311m);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f13309i, this.f13310l, this.f13311m});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, this.f13309i, false);
        a.a.a0(parcel, 2, this.f13310l, false);
        ArrayList arrayList = new ArrayList(this.f13311m);
        int iM02 = a.a.m0(3, parcel);
        int size = arrayList.size();
        parcel.writeInt(size);
        for (int i11 = 0; i11 < size; i11++) {
            parcel.writeInt(((Integer) arrayList.get(i11)).intValue());
        }
        a.a.n0(iM02, parcel);
        a.a.n0(iM0, parcel);
    }
}
