package z6;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.l;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends l implements Serializable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final i f23240l;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f f23241i;

    static {
        f fVar = f.x;
        f23240l = new i(f.x);
    }

    public i(f fVar) {
        this.f23241i = fVar;
    }

    @Override // kotlin.collections.l, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        return this.f23241i.b(obj) >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        this.f23241i.e();
        return super.addAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.f23241i.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f23241i.containsKey(obj);
    }

    @Override // kotlin.collections.l
    public final int getSize() {
        return this.f23241i.f23232s;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.f23241i.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        f fVar = this.f23241i;
        fVar.getClass();
        return new d(fVar, 1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        f fVar = this.f23241i;
        fVar.e();
        int i10 = fVar.i(obj);
        if (i10 < 0) {
            return false;
        }
        fVar.m(i10);
        return true;
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        this.f23241i.e();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        this.f23241i.e();
        return super.retainAll(collection);
    }

    public i() {
        this(new f());
    }
}
