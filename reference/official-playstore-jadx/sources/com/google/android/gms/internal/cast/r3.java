package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class r3 extends t3.a {
    public static final Parcelable.Creator<r3> CREATOR = new h3(4);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f13582i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f13583l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f13584m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f13585n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final String f13586o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f13587p;

    public r3(int i10, boolean z, ArrayList arrayList, int i11, String str, boolean z5) {
        ArrayList arrayList2 = new ArrayList();
        this.f13584m = arrayList2;
        this.f13582i = i10;
        this.f13583l = z;
        if (arrayList != null) {
            arrayList2.addAll(arrayList);
        }
        this.f13585n = i11;
        this.f13586o = str;
        this.f13587p = z5;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f13582i);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f13583l ? 1 : 0);
        a.a.i0(parcel, this.f13584m, 4);
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(this.f13585n);
        a.a.g0(parcel, 6, this.f13586o, false);
        a.a.l0(parcel, 7, 4);
        parcel.writeInt(this.f13587p ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
