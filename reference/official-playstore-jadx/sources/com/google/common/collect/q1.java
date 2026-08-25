package com.google.common.collect;

import com.google.common.collect.b3;
import j$.util.Objects;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class q1 extends o4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f14087i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f14088l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Iterator f14089m;

    public q1(o4 o4Var) {
        this.f14089m = o4Var;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f14087i > 0 || this.f14089m.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f14087i <= 0) {
            b3.a aVar = (b3.a) this.f14089m.next();
            this.f14088l = aVar.a();
            this.f14087i = aVar.getCount();
        }
        this.f14087i--;
        Object obj = this.f14088l;
        Objects.requireNonNull(obj);
        return obj;
    }
}
