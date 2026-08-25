package z6;

import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends kotlin.collections.i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f f23239i;

    public h(f fVar) {
        this.f23239i = fVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.f23239i.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.f23239i.containsValue(obj);
    }

    @Override // kotlin.collections.i
    public final int getSize() {
        return this.f23239i.f23232s;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return this.f23239i.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        f fVar = this.f23239i;
        fVar.getClass();
        return new d(fVar, 2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object obj) {
        int i10;
        f fVar = this.f23239i;
        fVar.e();
        int i11 = fVar.f23229p;
        while (true) {
            i10 = -1;
            i11--;
            if (i11 >= 0) {
                if (fVar.f23226m[i11] >= 0 && p.a(fVar.f23225l[i11], obj)) {
                    i10 = i11;
                    break;
                }
            } else {
                break;
            }
        }
        if (i10 < 0) {
            return false;
        }
        fVar.m(i10);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        this.f23239i.e();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        this.f23239i.e();
        return super.retainAll(collection);
    }
}
