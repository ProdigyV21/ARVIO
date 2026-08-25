package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class i extends t3.a {
    public static final Parcelable.Creator<i> CREATOR = new android.support.v4.media.d(21);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12968i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f12969l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a f12970m;

    public i(a aVar, String str) {
        this.f12968i = 1;
        this.f12969l = str;
        this.f12970m = aVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12968i);
        a.a.g0(parcel, 2, this.f12969l, false);
        a.a.f0(parcel, 3, this.f12970m, i10, false);
        a.a.n0(iM0, parcel);
    }

    public i(a aVar, String str, int i10) {
        this.f12968i = i10;
        this.f12969l = str;
        this.f12970m = aVar;
    }
}
