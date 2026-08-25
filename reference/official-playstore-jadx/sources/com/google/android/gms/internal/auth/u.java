package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class u extends t3.a {
    public static final Parcelable.Creator<u> CREATOR = new p(2);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f13126i;

    public u(String str) {
        com.google.android.gms.common.internal.t.i(str);
        this.f13126i = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(1);
        a.a.g0(parcel, 2, this.f13126i, false);
        a.a.n0(iM0, parcel);
    }
}
