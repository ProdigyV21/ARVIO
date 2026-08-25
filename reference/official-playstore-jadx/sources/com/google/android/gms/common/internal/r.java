package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class r extends t3.a {
    public static final Parcelable.Creator<r> CREATOR = new android.support.v4.media.d(13);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12898i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f12899l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f12900m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f12901n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f12902o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f12903p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f12904q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f12905r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f12906s;

    public r(int i10, int i11, int i12, long j10, long j11, String str, String str2, int i13, int i14) {
        this.f12898i = i10;
        this.f12899l = i11;
        this.f12900m = i12;
        this.f12901n = j10;
        this.f12902o = j11;
        this.f12903p = str;
        this.f12904q = str2;
        this.f12905r = i13;
        this.f12906s = i14;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12898i);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f12899l);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f12900m);
        a.a.l0(parcel, 4, 8);
        parcel.writeLong(this.f12901n);
        a.a.l0(parcel, 5, 8);
        parcel.writeLong(this.f12902o);
        a.a.g0(parcel, 6, this.f12903p, false);
        a.a.g0(parcel, 7, this.f12904q, false);
        a.a.l0(parcel, 8, 4);
        parcel.writeInt(this.f12905r);
        a.a.l0(parcel, 9, 4);
        parcel.writeInt(this.f12906s);
        a.a.n0(iM0, parcel);
    }
}
