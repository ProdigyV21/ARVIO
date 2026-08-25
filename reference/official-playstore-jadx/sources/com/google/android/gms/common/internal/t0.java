package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class t0 extends t3.a {
    public static final Parcelable.Creator<t0> CREATOR = new android.support.v4.media.d(18);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Bundle f12913i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public o3.d[] f12914l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f12915m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public i f12916n;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.Z(parcel, 1, this.f12913i, false);
        a.a.j0(parcel, 2, this.f12914l, i10);
        int i11 = this.f12915m;
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(i11);
        a.a.f0(parcel, 4, this.f12916n, i10, false);
        a.a.n0(iM0, parcel);
    }
}
