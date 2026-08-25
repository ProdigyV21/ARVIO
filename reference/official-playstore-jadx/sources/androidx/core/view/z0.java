package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class z0 implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2338i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Iterator f2339l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f2340m;

    public z0(h2 h2Var) {
        this.f2338i = 0;
        this.f2340m = new ArrayList();
        this.f2339l = h2Var;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f2338i) {
        }
        return this.f2339l.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f2338i) {
            case 0:
                Object next = this.f2339l.next();
                ArrayList arrayList = (ArrayList) this.f2340m;
                View view = (View) next;
                ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
                h2 h2Var = viewGroup != null ? new h2(viewGroup) : null;
                if (h2Var == null || !h2Var.hasNext()) {
                    while (!this.f2339l.hasNext() && !arrayList.isEmpty()) {
                        this.f2339l = (Iterator) kotlin.collections.x.w0(arrayList);
                        kotlin.collections.x.O0(arrayList);
                    }
                } else {
                    arrayList.add(this.f2339l);
                    this.f2339l = h2Var;
                }
                return next;
            default:
                return ((ga.t) this.f2340m).f15604b.invoke(this.f2339l.next());
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f2338i) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public z0(ga.t tVar) {
        this.f2338i = 1;
        this.f2340m = tVar;
        this.f2339l = tVar.f15603a.iterator();
    }
}
