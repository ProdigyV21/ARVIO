package com.google.common.collect;

import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes4.dex */
public final class n extends e implements ListIterator {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ o f14062o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(o oVar) {
        super(oVar);
        this.f14062o = oVar;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        o oVar = this.f14062o;
        boolean zIsEmpty = oVar.isEmpty();
        b().add(obj);
        oVar.f14075p.f14085p++;
        if (zIsEmpty) {
            oVar.a();
        }
    }

    public final ListIterator b() {
        a();
        return (ListIterator) this.f13971l;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return b().hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return b().nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return b().previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return b().previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        b().set(obj);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(o oVar, int i10) {
        super(oVar, ((List) oVar.f14052l).listIterator(i10));
        this.f14062o = oVar;
    }
}
