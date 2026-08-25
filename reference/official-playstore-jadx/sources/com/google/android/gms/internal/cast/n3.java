package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class n3 extends t3.a {
    public static final Parcelable.Creator<n3> CREATOR = new h3(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f13485i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f13486l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f13487m;

    public n3(boolean z, boolean z5, int i10) {
        this.f13485i = z;
        this.f13486l = z5;
        this.f13487m = i10;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f13485i ? 1 : 0);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f13486l ? 1 : 0);
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(this.f13487m);
        a.a.n0(iM0, parcel);
    }
}
