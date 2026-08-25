package t;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Collection {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ e f21799i;

    public d(e eVar) {
        this.f21799i = eVar;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final void clear() {
        this.f21799i.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.f21799i.b(obj) >= 0;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.f21799i.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new a(this.f21799i, 1);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        e eVar = this.f21799i;
        int iB = eVar.b(obj);
        if (iB < 0) {
            return false;
        }
        eVar.i(iB);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        e eVar = this.f21799i;
        int i10 = eVar.f21849m;
        int i11 = 0;
        boolean z = false;
        while (i11 < i10) {
            if (collection.contains(eVar.k(i11))) {
                eVar.i(i11);
                i11--;
                i10--;
                z = true;
            }
            i11++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        e eVar = this.f21799i;
        int i10 = eVar.f21849m;
        int i11 = 0;
        boolean z = false;
        while (i11 < i10) {
            if (!collection.contains(eVar.k(i11))) {
                eVar.i(i11);
                i11--;
                i10--;
                z = true;
            }
            i11++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final int size() {
        return this.f21799i.f21849m;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        e eVar = this.f21799i;
        int i10 = eVar.f21849m;
        Object[] objArr = new Object[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            objArr[i11] = eVar.k(i11);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        e eVar = this.f21799i;
        int i10 = eVar.f21849m;
        if (objArr.length < i10) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i10);
        }
        for (int i11 = 0; i11 < i10; i11++) {
            objArr[i11] = eVar.k(i11);
        }
        if (objArr.length > i10) {
            objArr[i10] = null;
        }
        return objArr;
    }
}
