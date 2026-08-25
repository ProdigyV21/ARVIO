package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class v extends t3.a {
    public static final Parcelable.Creator<v> CREATOR = new p(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f13134i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final byte[] f13135l;

    public v(String str, byte[] bArr) {
        com.google.android.gms.common.internal.t.i(str);
        this.f13134i = str;
        com.google.android.gms.common.internal.t.i(bArr);
        this.f13135l = bArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(1);
        a.a.g0(parcel, 2, this.f13134i, false);
        a.a.a0(parcel, 3, this.f13135l, false);
        a.a.n0(iM0, parcel);
    }
}
