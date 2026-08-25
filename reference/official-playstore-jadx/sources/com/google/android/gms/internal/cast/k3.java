package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class k3 extends t3.a {
    public static final Parcelable.Creator<k3> CREATOR = new h3(2);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f13371i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f13372l;

    public k3(boolean z, int i10) {
        this.f13371i = z;
        this.f13372l = i10;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f13371i ? 1 : 0);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f13372l);
        a.a.n0(iM0, parcel);
    }
}
