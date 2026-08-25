package t;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Iterator, s7.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f21773i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f21774l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f21775m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f21776n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f21777o;

    public a(int i10) {
        this.f21773i = i10;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f21774l < this.f21773i;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object objH;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i10 = this.f21774l;
        switch (this.f21776n) {
            case 0:
                objH = ((e) this.f21777o).h(i10);
                break;
            case 1:
                objH = ((e) this.f21777o).k(i10);
                break;
            default:
                objH = ((f) this.f21777o).f21810l[i10];
                break;
        }
        this.f21774l++;
        this.f21775m = true;
        return objH;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f21775m) {
            throw new IllegalStateException("Call next() before removing an element.");
        }
        int i10 = this.f21774l - 1;
        this.f21774l = i10;
        switch (this.f21776n) {
            case 0:
                ((e) this.f21777o).i(i10);
                break;
            case 1:
                ((e) this.f21777o).i(i10);
                break;
            default:
                ((f) this.f21777o).a(i10);
                break;
        }
        this.f21773i--;
        this.f21775m = false;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(f fVar) {
        this(fVar.f21811m);
        this.f21776n = 2;
        this.f21777o = fVar;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(e eVar, int i10) {
        this(eVar.f21849m);
        this.f21776n = i10;
        switch (i10) {
            case 1:
                this.f21777o = eVar;
                this(eVar.f21849m);
                break;
            default:
                this.f21777o = eVar;
                break;
        }
    }
}
