package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class j3 extends t3.a {
    public static final Parcelable.Creator<j3> CREATOR;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f13337i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f13338l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f13339m;

    static {
        new j3(null, false, false);
        CREATOR = new h3(1);
    }

    public j3(ArrayList arrayList, boolean z, boolean z5) {
        this.f13337i = arrayList == null ? new ArrayList(0) : new ArrayList(arrayList);
        this.f13338l = z;
        this.f13339m = z5;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof j3)) {
            return false;
        }
        j3 j3Var = (j3) obj;
        return com.google.android.gms.common.internal.t.l(this.f13337i, j3Var.f13337i) && com.google.android.gms.common.internal.t.l(Boolean.valueOf(this.f13338l), Boolean.valueOf(j3Var.f13338l));
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f13337i, Boolean.valueOf(this.f13338l)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.k0(parcel, 1, new ArrayList(this.f13337i), false);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f13338l ? 1 : 0);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f13339m ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
