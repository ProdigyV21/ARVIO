package z6;

import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.collections.j;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements ListIterator, s7.f {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f23207l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f23209n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final j f23210o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f23206i = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f23208m = -1;

    public a(c cVar, int i10) {
        this.f23210o = cVar;
        this.f23207l = i10;
        this.f23209n = ((AbstractList) cVar).modCount;
    }

    public void a() {
        if (((AbstractList) ((b) this.f23210o).f23215o).modCount != this.f23209n) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        switch (this.f23206i) {
            case 0:
                a();
                b bVar = (b) this.f23210o;
                int i10 = this.f23207l;
                this.f23207l = i10 + 1;
                bVar.add(i10, obj);
                this.f23208m = -1;
                this.f23209n = ((AbstractList) bVar).modCount;
                break;
            default:
                b();
                c cVar = (c) this.f23210o;
                int i11 = this.f23207l;
                this.f23207l = i11 + 1;
                cVar.add(i11, obj);
                this.f23208m = -1;
                this.f23209n = ((AbstractList) cVar).modCount;
                break;
        }
    }

    public void b() {
        if (((AbstractList) ((c) this.f23210o)).modCount != this.f23209n) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        switch (this.f23206i) {
            case 0:
                if (this.f23207l < ((b) this.f23210o).f23213m) {
                }
                break;
            default:
                if (this.f23207l < ((c) this.f23210o).f23218l) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        switch (this.f23206i) {
            case 0:
                if (this.f23207l > 0) {
                }
                break;
            default:
                if (this.f23207l > 0) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        switch (this.f23206i) {
            case 0:
                a();
                int i10 = this.f23207l;
                b bVar = (b) this.f23210o;
                if (i10 >= bVar.f23213m) {
                    throw new NoSuchElementException();
                }
                this.f23207l = i10 + 1;
                this.f23208m = i10;
                return bVar.f23211i[bVar.f23212l + i10];
            default:
                b();
                int i11 = this.f23207l;
                c cVar = (c) this.f23210o;
                if (i11 >= cVar.f23218l) {
                    throw new NoSuchElementException();
                }
                this.f23207l = i11 + 1;
                this.f23208m = i11;
                return cVar.f23217i[i11];
        }
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        switch (this.f23206i) {
        }
        return this.f23207l;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        switch (this.f23206i) {
            case 0:
                a();
                int i10 = this.f23207l;
                if (i10 <= 0) {
                    throw new NoSuchElementException();
                }
                int i11 = i10 - 1;
                this.f23207l = i11;
                this.f23208m = i11;
                b bVar = (b) this.f23210o;
                return bVar.f23211i[bVar.f23212l + i11];
            default:
                b();
                int i12 = this.f23207l;
                if (i12 <= 0) {
                    throw new NoSuchElementException();
                }
                int i13 = i12 - 1;
                this.f23207l = i13;
                this.f23208m = i13;
                return ((c) this.f23210o).f23217i[i13];
        }
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        int i10;
        switch (this.f23206i) {
            case 0:
                i10 = this.f23207l;
                break;
            default:
                i10 = this.f23207l;
                break;
        }
        return i10 - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        switch (this.f23206i) {
            case 0:
                b bVar = (b) this.f23210o;
                a();
                int i10 = this.f23208m;
                if (i10 == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                bVar.removeAt(i10);
                this.f23207l = this.f23208m;
                this.f23208m = -1;
                this.f23209n = ((AbstractList) bVar).modCount;
                return;
            default:
                c cVar = (c) this.f23210o;
                b();
                int i11 = this.f23208m;
                if (i11 == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                cVar.removeAt(i11);
                this.f23207l = this.f23208m;
                this.f23208m = -1;
                this.f23209n = ((AbstractList) cVar).modCount;
                return;
        }
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        switch (this.f23206i) {
            case 0:
                a();
                int i10 = this.f23208m;
                if (i10 == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                ((b) this.f23210o).set(i10, obj);
                return;
            default:
                b();
                int i11 = this.f23208m;
                if (i11 == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                ((c) this.f23210o).set(i11, obj);
                return;
        }
    }

    public a(b bVar, int i10) {
        this.f23210o = bVar;
        this.f23207l = i10;
        this.f23209n = ((AbstractList) bVar).modCount;
    }
}
