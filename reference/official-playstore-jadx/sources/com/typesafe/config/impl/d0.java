package com.typesafe.config.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d0 extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f14517a;

    public d0(Collection collection) {
        this.f14517a = new ArrayList(collection);
    }

    @Override // com.typesafe.config.impl.a
    public final Collection b() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f14517a.iterator();
        while (it.hasNext()) {
            arrayList.addAll(((a) it.next()).b());
        }
        return arrayList;
    }
}
