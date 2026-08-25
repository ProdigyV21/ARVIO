package com.google.common.collect;

import j$.util.Objects;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class c implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Iterator f13944i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f13945l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Collection f13946m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Iterator f13947n = d2.f13965i;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ q f13948o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f13949p;

    public c(q qVar, int i10) {
        this.f13949p = i10;
        this.f13948o = qVar;
        this.f13944i = qVar.f14084o.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13944i.hasNext() || this.f13947n.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.f13947n.hasNext()) {
            Map.Entry entry = (Map.Entry) this.f13944i.next();
            this.f13945l = entry.getKey();
            Collection collection = (Collection) entry.getValue();
            this.f13946m = collection;
            this.f13947n = collection.iterator();
        }
        Object obj = this.f13945l;
        Object next = this.f13947n.next();
        switch (this.f13949p) {
            case 0:
                return next;
            default:
                return new d1(obj, next);
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f13947n.remove();
        Collection collection = this.f13946m;
        Objects.requireNonNull(collection);
        if (collection.isEmpty()) {
            this.f13944i.remove();
        }
        q qVar = this.f13948o;
        qVar.f14085p--;
    }
}
