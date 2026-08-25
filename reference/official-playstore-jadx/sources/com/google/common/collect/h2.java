package com.google.common.collect;

import java.util.AbstractList;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes4.dex */
public final class h2 extends l4 implements ListIterator {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f14021l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AbstractList f14022m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h2(AbstractList abstractList, ListIterator listIterator, int i10) {
        super(listIterator);
        this.f14021l = i10;
        this.f14022m = abstractList;
    }

    @Override // com.google.common.collect.l4
    public final Object a(Object obj) {
        switch (this.f14021l) {
            case 0:
                return ((i2) this.f14022m).f14028l.apply(obj);
            default:
                return ((j2) this.f14022m).f14033l.apply(obj);
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return ((ListIterator) this.f14050i).hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return ((ListIterator) this.f14050i).nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return a(((ListIterator) this.f14050i).previous());
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return ((ListIterator) this.f14050i).previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
