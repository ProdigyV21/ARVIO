package ga;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.collections.d0;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15568i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f15569l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Iterator f15570m;

    public d(Iterator it) {
        this.f15570m = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Iterator it;
        switch (this.f15568i) {
            case 0:
                break;
            case 1:
                return this.f15569l > 0 && this.f15570m.hasNext();
            default:
                return this.f15570m.hasNext();
        }
        while (true) {
            int i10 = this.f15569l;
            it = this.f15570m;
            if (i10 > 0 && it.hasNext()) {
                it.next();
                this.f15569l--;
            }
        }
        return it.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Iterator it;
        switch (this.f15568i) {
            case 0:
                break;
            case 1:
                int i10 = this.f15569l;
                if (i10 == 0) {
                    throw new NoSuchElementException();
                }
                this.f15569l = i10 - 1;
                return this.f15570m.next();
            default:
                int i11 = this.f15569l;
                this.f15569l = i11 + 1;
                if (i11 >= 0) {
                    return new d0(i11, this.f15570m.next());
                }
                t7.a.Q();
                throw null;
        }
        while (true) {
            int i12 = this.f15569l;
            it = this.f15570m;
            if (i12 > 0 && it.hasNext()) {
                it.next();
                this.f15569l--;
            }
        }
        return it.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f15568i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public d(e eVar, byte b10) {
        this.f15569l = eVar.f15573c;
        this.f15570m = eVar.f15572b.iterator();
    }

    public d(e eVar) {
        this.f15570m = eVar.f15572b.iterator();
        this.f15569l = eVar.f15573c;
    }
}
