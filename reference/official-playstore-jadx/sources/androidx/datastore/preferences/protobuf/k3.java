package androidx.datastore.preferences.protobuf;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes3.dex */
public final class k3 extends AbstractList implements i1, RandomAccess {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h1 f2461i;

    public k3(h1 h1Var) {
        this.f2461i = h1Var;
    }

    @Override // androidx.datastore.preferences.protobuf.i1
    public final Object J0(int i10) {
        return this.f2461i.f2445l.get(i10);
    }

    @Override // androidx.datastore.preferences.protobuf.i1
    public final void b0(m mVar) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        return (String) this.f2461i.get(i10);
    }

    @Override // androidx.datastore.preferences.protobuf.i1
    public final List i() {
        return Collections.unmodifiableList(this.f2461i.f2445l);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        j3 j3Var = new j3();
        j3Var.f2455i = this.f2461i.iterator();
        return j3Var;
    }

    @Override // androidx.datastore.preferences.protobuf.i1
    public final i1 l() {
        return this;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i10) {
        i3 i3Var = new i3();
        i3Var.f2449i = this.f2461i.listIterator(i10);
        return i3Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f2461i.size();
    }
}
