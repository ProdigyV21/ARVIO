package h8;

import g8.j0;
import ga.p;
import ga.r;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.y;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15864i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f15865l;

    public /* synthetic */ i(Object obj, int i10) {
        this.f15864i = i10;
        this.f15865l = obj;
    }

    @Override // h8.h
    public final boolean isEmpty() {
        switch (this.f15864i) {
            case 0:
                return ((List) this.f15865l).isEmpty();
            case 1:
                List list = (List) this.f15865l;
                if (list == null || !list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        if (!((h) it.next()).isEmpty()) {
                            return false;
                        }
                    }
                }
                return true;
            default:
                return false;
        }
    }

    @Override // java.lang.Iterable
    public final Iterator<b> iterator() {
        switch (this.f15864i) {
            case 0:
                return ((List) this.f15865l).iterator();
            case 1:
                return new ga.h(r.L(new p((List) this.f15865l, 3), k.f15870i));
            default:
                return y.f19727i;
        }
    }

    @Override // h8.h
    public final b j(f9.c cVar) {
        b next;
        switch (this.f15864i) {
            case 0:
                Iterator<b> it = iterator();
                while (true) {
                    if (it.hasNext()) {
                        next = it.next();
                        if (kotlin.jvm.internal.p.a(next.c(), cVar)) {
                        }
                    } else {
                        next = null;
                    }
                }
                return next;
            case 1:
                return (b) r.K(r.Q(new p((List) this.f15865l, 3), new j0(cVar, 1)));
            default:
                if (cVar.equals((f9.c) this.f15865l)) {
                    return x8.b.f22638a;
                }
                return null;
        }
    }

    public String toString() {
        switch (this.f15864i) {
            case 0:
                return ((List) this.f15865l).toString();
            default:
                return super.toString();
        }
    }

    @Override // h8.h
    public final boolean x(f9.c cVar) {
        switch (this.f15864i) {
            case 0:
                if (j(cVar) != null) {
                }
                break;
            case 1:
                Iterator it = ((List) this.f15865l).iterator();
                while (it.hasNext()) {
                    if (((h) it.next()).x(cVar)) {
                        break;
                    }
                }
                break;
            default:
                if (j(cVar) != null) {
                }
                break;
        }
        return false;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public i(h[] hVarArr) {
        this(kotlin.collections.r.o0(hVarArr), 1);
        this.f15864i = 1;
    }
}
