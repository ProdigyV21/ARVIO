package ga;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15575i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Iterator f15576l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f15577m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f15578n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ m f15579o;

    public h(i iVar) {
        this.f15575i = 0;
        this.f15579o = iVar;
        this.f15576l = iVar.f15580a.iterator();
        this.f15577m = -1;
    }

    public void a() {
        Object next;
        i iVar = (i) this.f15579o;
        do {
            Iterator it = this.f15576l;
            if (!it.hasNext()) {
                this.f15577m = 0;
                return;
            }
            next = it.next();
        } while (((Boolean) iVar.f15582c.invoke(next)).booleanValue() != iVar.f15581b);
        this.f15578n = next;
        this.f15577m = 1;
    }

    public void b() {
        Iterator it = this.f15576l;
        if (it.hasNext()) {
            Object next = it.next();
            if (((Boolean) ((c) this.f15579o).f15567c.invoke(next)).booleanValue()) {
                this.f15577m = 1;
                this.f15578n = next;
                return;
            }
        }
        this.f15577m = 0;
    }

    public boolean c() {
        Iterator it;
        Iterator it2 = (Iterator) this.f15578n;
        if (it2 != null && it2.hasNext()) {
            this.f15577m = 1;
            return true;
        }
        do {
            Iterator it3 = this.f15576l;
            if (!it3.hasNext()) {
                this.f15577m = 2;
                this.f15578n = null;
                return false;
            }
            Object next = it3.next();
            j jVar = (j) this.f15579o;
            it = (Iterator) jVar.f15585c.invoke(jVar.f15584b.invoke(next));
        } while (!it.hasNext());
        this.f15578n = it;
        this.f15577m = 1;
        return true;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f15575i) {
            case 0:
                if (this.f15577m == -1) {
                    a();
                }
                return this.f15577m == 1;
            case 1:
                int i10 = this.f15577m;
                if (i10 == 1) {
                    return true;
                }
                if (i10 == 2) {
                    return false;
                }
                return c();
            default:
                if (this.f15577m == -1) {
                    b();
                }
                return this.f15577m == 1;
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f15575i) {
            case 0:
                if (this.f15577m == -1) {
                    a();
                }
                if (this.f15577m == 0) {
                    throw new NoSuchElementException();
                }
                Object obj = this.f15578n;
                this.f15578n = null;
                this.f15577m = -1;
                return obj;
            case 1:
                int i10 = this.f15577m;
                if (i10 == 2) {
                    throw new NoSuchElementException();
                }
                if (i10 == 0 && !c()) {
                    throw new NoSuchElementException();
                }
                this.f15577m = 0;
                return ((Iterator) this.f15578n).next();
            default:
                if (this.f15577m == -1) {
                    b();
                }
                if (this.f15577m == 0) {
                    throw new NoSuchElementException();
                }
                Object obj2 = this.f15578n;
                this.f15578n = null;
                this.f15577m = -1;
                return obj2;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f15575i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public h(j jVar) {
        this.f15575i = 1;
        this.f15579o = jVar;
        this.f15576l = jVar.f15583a.iterator();
    }

    public h(c cVar) {
        this.f15575i = 2;
        this.f15579o = cVar;
        this.f15576l = cVar.f15566b.iterator();
        this.f15577m = -1;
    }
}
