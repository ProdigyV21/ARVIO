package com.google.android.gms.internal.fido;

import j$.util.SortedSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.NavigableSet;

/* JADX INFO: loaded from: classes4.dex */
public abstract class w extends s implements NavigableSet, g0, SortedSet {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Comparator f13831n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public transient w f13832o;

    public w(Comparator comparator) {
        this.f13831n = comparator;
    }

    public static d0 p(Comparator comparator) {
        if (z.f13850l.equals(comparator)) {
            return d0.f13769q;
        }
        m mVar = q.f13808l;
        return new d0(b0.f13755o, comparator);
    }

    public final void addFirst(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final void addLast(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.SortedSet, com.google.android.gms.internal.fido.g0
    public final Comparator comparator() {
        return this.f13831n;
    }

    public abstract Object first();

    public final Object getFirst() {
        return first();
    }

    public final Object getLast() {
        return last();
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final java.util.SortedSet headSet(Object obj) {
        obj.getClass();
        d0 d0Var = (d0) this;
        return d0Var.s(0, d0Var.q(obj, false));
    }

    public abstract Object last();

    @Override // java.util.NavigableSet
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final w descendingSet() {
        w wVarP = this.f13832o;
        if (wVarP == null) {
            d0 d0Var = (d0) this;
            Comparator comparatorReverseOrder = Collections.reverseOrder(d0Var.f13831n);
            wVarP = d0Var.isEmpty() ? p(comparatorReverseOrder) : new d0(d0Var.f13770p.f(), comparatorReverseOrder);
            this.f13832o = wVarP;
            wVarP.f13832o = this;
        }
        return wVarP;
    }

    @Override // java.util.NavigableSet
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public final d0 subSet(Object obj, boolean z, Object obj2, boolean z5) {
        obj.getClass();
        obj2.getClass();
        if (this.f13831n.compare(obj, obj2) > 0) {
            throw new IllegalArgumentException();
        }
        d0 d0Var = (d0) this;
        d0 d0VarS = d0Var.s(d0Var.r(obj, z), d0Var.f13770p.size());
        return d0VarS.s(0, d0VarS.q(obj2, z5));
    }

    @Override // java.util.NavigableSet
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    public final Object removeFirst() {
        throw new UnsupportedOperationException();
    }

    public final Object removeLast() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* bridge */ /* synthetic */ java.util.SortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final java.util.SortedSet tailSet(Object obj) {
        obj.getClass();
        d0 d0Var = (d0) this;
        return d0Var.s(d0Var.r(obj, true), d0Var.f13770p.size());
    }

    @Override // java.util.NavigableSet
    public final NavigableSet headSet(Object obj, boolean z) {
        obj.getClass();
        d0 d0Var = (d0) this;
        return d0Var.s(0, d0Var.q(obj, z));
    }

    @Override // java.util.NavigableSet
    public final NavigableSet tailSet(Object obj, boolean z) {
        obj.getClass();
        d0 d0Var = (d0) this;
        return d0Var.s(d0Var.r(obj, z), d0Var.f13770p.size());
    }
}
