package ga;

import java.util.Iterator;
import java.util.NoSuchElementException;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends o implements Iterator, d7.d, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15593i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f15594l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Iterator f15595m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public d7.d f15596n;

    @Override // ga.o
    public final void b(d7.d dVar, Object obj) {
        this.f15594l = obj;
        this.f15593i = 3;
        this.f15596n = dVar;
    }

    public final RuntimeException e() {
        int i10 = this.f15593i;
        if (i10 == 4) {
            return new NoSuchElementException();
        }
        if (i10 == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.f15593i);
    }

    @Override // d7.d
    public final d7.j getContext() {
        return d7.k.f14688i;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        while (true) {
            int i10 = this.f15593i;
            if (i10 != 0) {
                if (i10 != 1) {
                    if (i10 == 2 || i10 == 3) {
                        return true;
                    }
                    if (i10 == 4) {
                        return false;
                    }
                    throw e();
                }
                if (this.f15595m.hasNext()) {
                    this.f15593i = 2;
                    return true;
                }
                this.f15595m = null;
            }
            this.f15593i = 5;
            d7.d dVar = this.f15596n;
            this.f15596n = null;
            dVar.resumeWith(t0.f22605a);
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i10 = this.f15593i;
        if (i10 == 0 || i10 == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        }
        if (i10 == 2) {
            this.f15593i = 1;
            return this.f15595m.next();
        }
        if (i10 != 3) {
            throw e();
        }
        this.f15593i = 0;
        Object obj = this.f15594l;
        this.f15594l = null;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // d7.d
    public final void resumeWith(Object obj) {
        k2.c.G(obj);
        this.f15593i = 4;
    }
}
