package t;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class h0 implements Set, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21830i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f21831l;

    public /* synthetic */ h0(Object obj, int i10) {
        this.f21830i = i10;
        this.f21831l = obj;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(Object obj) {
        switch (this.f21830i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection collection) {
        switch (this.f21830i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        switch (this.f21830i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        switch (this.f21830i) {
            case 0:
                return ((z) this.f21831l).b(obj);
            default:
                return ((j0) this.f21831l).a(obj);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        switch (this.f21830i) {
            case 0:
                Collection collection2 = collection;
                z zVar = (z) this.f21831l;
                if (!collection2.isEmpty()) {
                    Iterator it = collection2.iterator();
                    while (it.hasNext()) {
                        if (!zVar.b(it.next())) {
                            break;
                        }
                    }
                    break;
                }
                break;
            default:
                j0 j0Var = (j0) this.f21831l;
                Iterator it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (!j0Var.a(it2.next())) {
                        break;
                    }
                }
                break;
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        switch (this.f21830i) {
            case 0:
                return ((z) this.f21831l).g();
            default:
                return ((j0) this.f21831l).b();
        }
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        switch (this.f21830i) {
            case 0:
                return f4.f.n(new g0((z) this.f21831l, null, 0));
            default:
                return f4.f.n(new g0((j0) this.f21831l, null, 1));
        }
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        switch (this.f21830i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection collection) {
        switch (this.f21830i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection collection) {
        switch (this.f21830i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        switch (this.f21830i) {
            case 0:
                return ((z) this.f21831l).f21885e;
            default:
                return ((j0) this.f21831l).f21838d;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        switch (this.f21830i) {
        }
        return kotlin.jvm.internal.o.a(this);
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        switch (this.f21830i) {
        }
        return kotlin.jvm.internal.o.b(this, objArr);
    }
}
