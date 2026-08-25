package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f19684i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f19685l;

    public abstract void a();

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i10 = this.f19684i;
        if (i10 == 0) {
            this.f19684i = 3;
            a();
            return this.f19684i == 1;
        }
        if (i10 == 1) {
            return true;
        }
        if (i10 == 2) {
            return false;
        }
        throw new IllegalArgumentException("hasNext called when the iterator is in the FAILED state.");
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i10 = this.f19684i;
        if (i10 == 1) {
            this.f19684i = 0;
            return this.f19685l;
        }
        if (i10 != 2) {
            this.f19684i = 3;
            a();
            if (this.f19684i == 1) {
                this.f19684i = 0;
                return this.f19685l;
            }
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
