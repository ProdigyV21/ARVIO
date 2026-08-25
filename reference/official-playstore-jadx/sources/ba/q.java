package ba;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class q implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7303i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f7304l = true;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7305m;

    public /* synthetic */ q(Object obj, int i10) {
        this.f7303i = i10;
        this.f7305m = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f7303i) {
        }
        return this.f7304l;
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f7303i) {
            case 0:
                if (!this.f7304l) {
                    throw new NoSuchElementException();
                }
                this.f7304l = false;
                return ((r) this.f7305m).f7306i;
            default:
                if (!this.f7304l) {
                    throw new NoSuchElementException();
                }
                this.f7304l = false;
                return this.f7305m;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f7303i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }
}
