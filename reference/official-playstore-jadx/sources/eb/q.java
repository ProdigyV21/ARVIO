package eb;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class q implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final db.b f15170i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d0 f15171l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ya.c f15172m;

    public q(db.b bVar, d0 d0Var, ya.h hVar) {
        this.f15170i = bVar;
        this.f15171l = d0Var;
        this.f15172m = hVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f15171l.w() != 10;
    }

    @Override // java.util.Iterator
    public final Object next() {
        ya.c cVar = this.f15172m;
        return new f0(this.f15170i, 1, this.f15171l, cVar.getDescriptor(), null).q(cVar);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
