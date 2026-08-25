package ea;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class k implements Iterator, s7.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f15093i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f15094l = true;

    public k(Object obj) {
        this.f15093i = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f15094l;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.f15094l) {
            throw new NoSuchElementException();
        }
        this.f15094l = false;
        return this.f15093i;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
