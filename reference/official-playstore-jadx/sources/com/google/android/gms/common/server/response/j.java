package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.t;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class j extends t3.a {
    public static final Parcelable.Creator<j> CREATOR = new android.support.v4.media.d(22);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12971i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final HashMap f12972l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f12973m;

    public j(int i10, String str, ArrayList arrayList) {
        this.f12971i = i10;
        HashMap map = new HashMap();
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            h hVar = (h) arrayList.get(i11);
            String str2 = hVar.f12966l;
            ArrayList arrayList2 = hVar.f12967m;
            HashMap map2 = new HashMap();
            t.i(arrayList2);
            int size2 = arrayList2.size();
            for (int i12 = 0; i12 < size2; i12++) {
                i iVar = (i) arrayList2.get(i12);
                map2.put(iVar.f12969l, iVar.f12970m);
            }
            map.put(str2, map2);
        }
        this.f12972l = map;
        t.i(str);
        this.f12973m = str;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Map map3 = (Map) map.get((String) it.next());
            Iterator it2 = map3.keySet().iterator();
            while (it2.hasNext()) {
                ((a) map3.get((String) it2.next())).f12956t = this;
            }
        }
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        HashMap map = this.f12972l;
        for (String str : map.keySet()) {
            sb2.append(str);
            sb2.append(":\n");
            Map map2 = (Map) map.get(str);
            for (String str2 : map2.keySet()) {
                sb2.append("  ");
                sb2.append(str2);
                sb2.append(": ");
                sb2.append(map2.get(str2));
            }
        }
        return sb2.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12971i);
        ArrayList arrayList = new ArrayList();
        HashMap map = this.f12972l;
        for (String str : map.keySet()) {
            arrayList.add(new h(str, (Map) map.get(str)));
        }
        a.a.k0(parcel, 2, arrayList, false);
        a.a.g0(parcel, 3, this.f12973m, false);
        a.a.n0(iM0, parcel);
    }
}
