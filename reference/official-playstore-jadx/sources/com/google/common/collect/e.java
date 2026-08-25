package com.google.common.collect;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class e implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13970i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Iterator f13971l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f13972m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f13973n;

    public e(m mVar) {
        this.f13973n = mVar;
        Collection collection = mVar.f14052l;
        this.f13972m = collection;
        this.f13971l = collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    public void a() {
        m mVar = (m) this.f13973n;
        mVar.b();
        if (mVar.f14052l != ((Collection) this.f13972m)) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f13970i) {
            case 0:
                break;
            case 1:
                break;
            default:
                a();
                break;
        }
        return this.f13971l.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f13970i) {
            case 0:
                Map.Entry entry = (Map.Entry) this.f13971l.next();
                this.f13972m = (Collection) entry.getValue();
                return ((f) this.f13973n).b(entry);
            case 1:
                Map.Entry entry2 = (Map.Entry) this.f13971l.next();
                this.f13972m = entry2;
                return entry2.getKey();
            default:
                a();
                return this.f13971l.next();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f13970i) {
            case 0:
                ac.b.t(((Collection) this.f13972m) != null, "no calls to next() since the last call to remove()");
                this.f13971l.remove();
                ((f) this.f13973n).f13986n.f14085p -= ((Collection) this.f13972m).size();
                ((Collection) this.f13972m).clear();
                this.f13972m = null;
                break;
            case 1:
                ac.b.t(((Map.Entry) this.f13972m) != null, "no calls to next() since the last call to remove()");
                Collection collection = (Collection) ((Map.Entry) this.f13972m).getValue();
                this.f13971l.remove();
                ((g) this.f13973n).f14006l.f14085p -= collection.size();
                collection.clear();
                this.f13972m = null;
                break;
            default:
                this.f13971l.remove();
                m mVar = (m) this.f13973n;
                q qVar = mVar.f14055o;
                qVar.f14085p--;
                mVar.c();
                break;
        }
    }

    public e(o oVar, ListIterator listIterator) {
        this.f13973n = oVar;
        this.f13972m = oVar.f14052l;
        this.f13971l = listIterator;
    }

    public e(g gVar, Iterator it) {
        this.f13971l = it;
        this.f13973n = gVar;
    }

    public e(f fVar) {
        this.f13973n = fVar;
        this.f13971l = fVar.f13985m.entrySet().iterator();
    }
}
