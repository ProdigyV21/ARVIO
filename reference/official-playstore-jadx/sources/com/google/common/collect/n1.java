package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class n1 extends o4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o4 f14066i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public o4 f14067l = c2.f13957n;

    public n1(i1 i1Var) {
        this.f14066i = i1Var.f14025o.values().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f14067l.hasNext() || this.f14066i.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.f14067l.hasNext()) {
            this.f14067l = ((c1) this.f14066i.next()).iterator();
        }
        return this.f14067l.next();
    }
}
