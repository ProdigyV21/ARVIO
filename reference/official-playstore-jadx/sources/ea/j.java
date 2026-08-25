package ea;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements Iterator, s7.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ab.j f15092i;

    public j(Object[] objArr) {
        this.f15092i = new ab.j(objArr, 4);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f15092i.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.f15092i.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
