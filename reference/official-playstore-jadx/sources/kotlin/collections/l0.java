package kotlin.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes5.dex */
public final class l0 extends j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f19696i;

    public l0(ArrayList arrayList) {
        this.f19696i = arrayList;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i10, Object obj) {
        this.f19696i.add(x.Z(i10, this), obj);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.f19696i.clear();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        return this.f19696i.get(x.Y(i10, this));
    }

    @Override // kotlin.collections.j
    public final int getSize() {
        return this.f19696i.size();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new k0(this, 0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return new k0(this, 0);
    }

    @Override // kotlin.collections.j
    public final Object removeAt(int i10) {
        return this.f19696i.remove(x.Y(i10, this));
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i10, Object obj) {
        return this.f19696i.set(x.Y(i10, this), obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i10) {
        return new k0(this, i10);
    }
}
