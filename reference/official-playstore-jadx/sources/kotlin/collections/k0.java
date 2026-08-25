package kotlin.collections;

import java.util.ListIterator;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 implements ListIterator, s7.f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ListIterator f19694i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f19695l;

    public k0(l0 l0Var, int i10) {
        this.f19695l = l0Var;
        this.f19694i = l0Var.f19696i.listIterator(x.Z(i10, l0Var));
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        ListIterator listIterator = this.f19694i;
        listIterator.add(obj);
        listIterator.previous();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f19694i.hasPrevious();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f19694i.hasNext();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return this.f19694i.previous();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return t7.a.w(this.f19695l) - this.f19694i.previousIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return this.f19694i.next();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return t7.a.w(this.f19695l) - this.f19694i.nextIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        this.f19694i.remove();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        this.f19694i.set(obj);
    }
}
