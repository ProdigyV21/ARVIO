package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class i extends t3.a {
    public static final Parcelable.Creator<i> CREATOR = new android.support.v4.media.d(19);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v f12853i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f12854l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f12855m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int[] f12856n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f12857o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int[] f12858p;

    public i(v vVar, boolean z, boolean z5, int[] iArr, int i10, int[] iArr2) {
        this.f12853i = vVar;
        this.f12854l = z;
        this.f12855m = z5;
        this.f12856n = iArr;
        this.f12857o = i10;
        this.f12858p = iArr2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 1, this.f12853i, i10, false);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f12854l ? 1 : 0);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f12855m ? 1 : 0);
        a.a.c0(parcel, 4, this.f12856n, false);
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(this.f12857o);
        a.a.c0(parcel, 6, this.f12858p, false);
        a.a.n0(iM0, parcel);
    }
}
