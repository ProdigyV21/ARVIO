package t;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class b0 implements Iterator, s7.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f21791i = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ga.n f21792l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ d0 f21793m;

    public b0(d0 d0Var) {
        this.f21793m = d0Var;
        this.f21792l = f4.f.n(new a0(d0Var, this, null));
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f21792l.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.f21792l.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i10 = this.f21791i;
        if (i10 != -1) {
            this.f21793m.k(i10);
            this.f21791i = -1;
        }
    }
}
