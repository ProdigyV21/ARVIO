package ea;

import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f15087i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f15088l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i f15089m;

    public h(i iVar) {
        this.f15089m = iVar;
        this.f15088l = ((AbstractList) iVar).modCount;
    }

    public final void a() {
        i iVar = this.f15089m;
        int i10 = ((AbstractList) iVar).modCount;
        int i11 = this.f15088l;
        if (i10 == i11) {
            return;
        }
        throw new ConcurrentModificationException("ModCount: " + ((AbstractList) iVar).modCount + "; expected: " + i11);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.f15087i;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f15087i) {
            throw new NoSuchElementException();
        }
        this.f15087i = true;
        a();
        return this.f15089m.f15091l;
    }

    @Override // java.util.Iterator
    public final void remove() {
        a();
        this.f15089m.clear();
    }
}
