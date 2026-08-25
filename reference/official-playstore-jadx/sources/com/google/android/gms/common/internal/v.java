package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class v extends t3.a {
    public static final Parcelable.Creator<v> CREATOR = new android.support.v4.media.d(16);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12924i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f12925l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f12926m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f12927n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f12928o;

    public v(boolean z, int i10, boolean z5, int i11, int i12) {
        this.f12924i = i10;
        this.f12925l = z;
        this.f12926m = z5;
        this.f12927n = i11;
        this.f12928o = i12;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12924i);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f12925l ? 1 : 0);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f12926m ? 1 : 0);
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(this.f12927n);
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(this.f12928o);
        a.a.n0(iM0, parcel);
    }
}
