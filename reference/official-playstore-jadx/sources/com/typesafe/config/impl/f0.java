package com.typesafe.config.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class f0 extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f14530a;

    public f0(ArrayList arrayList) {
        this.f14530a = new ArrayList(arrayList);
    }

    @Override // com.typesafe.config.impl.a
    public final Collection b() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f14530a.iterator();
        while (it.hasNext()) {
            arrayList.addAll(((a) it.next()).b());
        }
        return arrayList;
    }

    public final c2 c() {
        c2 c2Var;
        for (a aVar : this.f14530a) {
            if ((aVar instanceof l0) && ((c2Var = ((l0) aVar).f14568a) == m2.f14590j || c2Var == m2.f14585e || c2Var == m2.f14584d)) {
                return c2Var;
            }
        }
        return null;
    }
}
