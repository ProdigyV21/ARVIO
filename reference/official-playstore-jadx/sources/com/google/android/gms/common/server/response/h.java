package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class h extends t3.a {
    public static final Parcelable.Creator<h> CREATOR = new android.support.v4.media.d(23);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12965i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f12966l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f12967m;

    public h(int i10, String str, ArrayList arrayList) {
        this.f12965i = i10;
        this.f12966l = str;
        this.f12967m = arrayList;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12965i);
        a.a.g0(parcel, 2, this.f12966l, false);
        a.a.k0(parcel, 3, this.f12967m, false);
        a.a.n0(iM0, parcel);
    }

    public h(String str, Map map) {
        ArrayList arrayList;
        this.f12965i = 1;
        this.f12966l = str;
        if (map == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (String str2 : map.keySet()) {
                arrayList.add(new i((a) map.get(str2), str2));
            }
        }
        this.f12967m = arrayList;
    }
}
