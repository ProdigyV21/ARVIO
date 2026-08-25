package androidx.recyclerview.widget;

import android.database.Observable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class m0 extends Observable {
    public final boolean a() {
        return !((Observable) this).mObservers.isEmpty();
    }

    public final void b() {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            RecyclerView recyclerView = ((g1) ((n0) ((Observable) this).mObservers.get(size))).f4931a;
            recyclerView.f(null);
            recyclerView.f4846q0.f4978f = true;
            recyclerView.T(true);
            if (!recyclerView.f4841o.g()) {
                recyclerView.requestLayout();
            }
        }
    }

    public final void c(int i10, int i11) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            g1 g1Var = (g1) ((n0) ((Observable) this).mObservers.get(size));
            RecyclerView recyclerView = g1Var.f4931a;
            recyclerView.f(null);
            c cVar = recyclerView.f4841o;
            ArrayList arrayList = cVar.f4895b;
            if (i10 != i11) {
                arrayList.add(cVar.h(8, i10, i11, null));
                cVar.f4899f |= 8;
                if (arrayList.size() == 1) {
                    g1Var.a();
                }
            }
        }
    }

    public final void d(int i10, int i11, Object obj) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            g1 g1Var = (g1) ((n0) ((Observable) this).mObservers.get(size));
            RecyclerView recyclerView = g1Var.f4931a;
            recyclerView.f(null);
            c cVar = recyclerView.f4841o;
            ArrayList arrayList = cVar.f4895b;
            if (i11 >= 1) {
                arrayList.add(cVar.h(4, i10, i11, obj));
                cVar.f4899f = 4 | cVar.f4899f;
                if (arrayList.size() == 1) {
                    g1Var.a();
                }
            }
        }
    }

    public final void e(int i10, int i11) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            g1 g1Var = (g1) ((n0) ((Observable) this).mObservers.get(size));
            RecyclerView recyclerView = g1Var.f4931a;
            recyclerView.f(null);
            c cVar = recyclerView.f4841o;
            ArrayList arrayList = cVar.f4895b;
            if (i11 >= 1) {
                arrayList.add(cVar.h(1, i10, i11, null));
                cVar.f4899f |= 1;
                if (arrayList.size() == 1) {
                    g1Var.a();
                }
            }
        }
    }

    public final void f(int i10, int i11) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            g1 g1Var = (g1) ((n0) ((Observable) this).mObservers.get(size));
            RecyclerView recyclerView = g1Var.f4931a;
            recyclerView.f(null);
            c cVar = recyclerView.f4841o;
            ArrayList arrayList = cVar.f4895b;
            if (i11 >= 1) {
                arrayList.add(cVar.h(2, i10, i11, null));
                cVar.f4899f |= 2;
                if (arrayList.size() == 1) {
                    g1Var.a();
                }
            }
        }
    }

    public final void g() {
        l0 l0Var;
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            RecyclerView recyclerView = ((g1) ((n0) ((Observable) this).mObservers.get(size))).f4931a;
            if (recyclerView.f4839n != null && (l0Var = recyclerView.f4856w) != null && l0Var.canRestoreState()) {
                recyclerView.requestLayout();
            }
        }
    }
}
