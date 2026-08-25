package eb;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class p implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final db.b f15165i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d0 f15166l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ya.c f15167m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f15168n = true;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f15169o;

    public p(db.b bVar, d0 d0Var, ya.h hVar) {
        this.f15165i = bVar;
        this.f15166l = d0Var;
        this.f15167m = hVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f15169o) {
            return false;
        }
        d0 d0Var = this.f15166l;
        if (d0Var.w() != 9) {
            if (d0Var.w() != 10 || this.f15169o) {
                return true;
            }
            d0Var.t((byte) 9);
            throw null;
        }
        this.f15169o = true;
        d0Var.i((byte) 9);
        if (d0Var.w() == 10) {
            return false;
        }
        if (d0Var.w() != 8) {
            d0Var.q();
            return false;
        }
        a.s(d0Var, "There is a start of the new array after the one parsed to sequence. ARRAY_WRAPPED mode doesn't merge consecutive arrays.\nIf you need to parse a stream of arrays, please use WHITESPACE_SEPARATED mode instead.", 0, 6);
        throw null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        boolean z = this.f15168n;
        d0 d0Var = this.f15166l;
        if (z) {
            this.f15168n = false;
        } else {
            d0Var.j(',');
        }
        ya.c cVar = this.f15167m;
        return new f0(this.f15165i, 1, d0Var, cVar.getDescriptor(), null).q(cVar);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
