package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class a0 extends t3.a {
    public static final Parcelable.Creator<a0> CREATOR = new android.support.v4.media.d(12);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12830i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f12831l;

    public a0(int i10, List list) {
        this.f12830i = i10;
        this.f12831l = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12830i);
        a.a.k0(parcel, 2, this.f12831l, false);
        a.a.n0(iM0, parcel);
    }
}
