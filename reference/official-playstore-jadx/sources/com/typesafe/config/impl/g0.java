package com.typesafe.config.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class g0 extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f14538a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14539b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f14540c;

    public g0(int i10, ArrayList arrayList, boolean z) {
        this.f14538a = new ArrayList(arrayList);
        this.f14539b = i10;
        this.f14540c = z;
    }

    @Override // com.typesafe.config.impl.a
    public final Collection b() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f14538a.iterator();
        while (it.hasNext()) {
            arrayList.addAll(((a) it.next()).b());
        }
        return arrayList;
    }

    public final String c() {
        for (a aVar : this.f14538a) {
            if (aVar instanceof k0) {
                return (String) m2.b(((k0) aVar).f14565a).a();
            }
        }
        return null;
    }
}
