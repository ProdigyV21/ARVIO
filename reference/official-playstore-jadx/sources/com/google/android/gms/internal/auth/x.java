package com.google.android.gms.internal.auth;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class x extends t3.a {
    public static final Parcelable.Creator<x> CREATOR = new p(4);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f13140i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final PendingIntent f13141l;

    public x(String str, PendingIntent pendingIntent) {
        com.google.android.gms.common.internal.t.i(str);
        this.f13140i = str;
        com.google.android.gms.common.internal.t.i(pendingIntent);
        this.f13141l = pendingIntent;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(1);
        a.a.g0(parcel, 2, this.f13140i, false);
        a.a.f0(parcel, 3, this.f13141l, i10, false);
        a.a.n0(iM0, parcel);
    }
}
