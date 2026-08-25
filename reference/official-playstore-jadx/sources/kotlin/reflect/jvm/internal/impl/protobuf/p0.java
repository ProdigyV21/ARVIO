package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes5.dex */
public final class p0 extends AbstractList implements RandomAccess, z {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y f19819i;

    public p0(y yVar) {
        this.f19819i = yVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.z
    public final g O(int i10) {
        return this.f19819i.O(i10);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i10) {
        return (String) this.f19819i.get(i10);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.z
    public final List i() {
        return Collections.unmodifiableList(this.f19819i.f19850i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        o0 o0Var = new o0();
        o0Var.f19816i = this.f19819i.iterator();
        return o0Var;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i10) {
        n0 n0Var = new n0();
        n0Var.f19814i = this.f19819i.listIterator(i10);
        return n0Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f19819i.size();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.z
    public final void u0(b0 b0Var) {
        throw new UnsupportedOperationException();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.z
    public final p0 l() {
        return this;
    }
}
