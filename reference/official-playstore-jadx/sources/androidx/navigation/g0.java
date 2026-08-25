package androidx.navigation;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
public final class g0 implements Iterator, s7.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4670i = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4671l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ h0 f4672m;

    public g0(h0 h0Var) {
        this.f4672m = h0Var;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f4670i + 1 < this.f4672m.f4676s.f();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f4671l = true;
        t.m0 m0Var = this.f4672m.f4676s;
        int i10 = this.f4670i + 1;
        this.f4670i = i10;
        return (d0) m0Var.g(i10);
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f4671l) {
            throw new IllegalStateException("You must call next() before you can remove an element");
        }
        t.m0 m0Var = this.f4672m.f4676s;
        ((d0) m0Var.g(this.f4670i)).f4658l = null;
        int i10 = this.f4670i;
        Object[] objArr = m0Var.f21853m;
        Object obj = objArr[i10];
        Object obj2 = t.q.f21862b;
        if (obj != obj2) {
            objArr[i10] = obj2;
            m0Var.f21851i = true;
        }
        this.f4670i = i10 - 1;
        this.f4671l = false;
    }
}
