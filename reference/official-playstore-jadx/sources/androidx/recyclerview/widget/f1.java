package androidx.recyclerview.widget;

import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f4919a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList f4920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f4921c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f4922d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4923e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4924f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public e1 f4925g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f4926h;

    public f1(RecyclerView recyclerView) {
        this.f4926h = recyclerView;
        ArrayList arrayList = new ArrayList();
        this.f4919a = arrayList;
        this.f4920b = null;
        this.f4921c = new ArrayList();
        this.f4922d = Collections.unmodifiableList(arrayList);
        this.f4923e = 2;
        this.f4924f = 2;
    }

    public final void a(p1 p1Var, boolean z) {
        RecyclerView.g(p1Var);
        View view = p1Var.itemView;
        RecyclerView recyclerView = this.f4926h;
        r1 r1Var = recyclerView.f4858x0;
        if (r1Var != null) {
            q1 q1Var = r1Var.f5039l;
            androidx.core.view.b2.h(view, q1Var != null ? (androidx.core.view.b) q1Var.f5014l.remove(view) : null);
        }
        if (z) {
            RecyclerView.e eVar = recyclerView.f4859y;
            ArrayList arrayList = recyclerView.z;
            if (eVar != null) {
                eVar.a(p1Var);
            }
            int size = arrayList.size();
            for (int i10 = 0; i10 < size; i10++) {
                ((RecyclerView.e) arrayList.get(i10)).a(p1Var);
            }
            l0 l0Var = recyclerView.f4856w;
            if (l0Var != null) {
                l0Var.onViewRecycled(p1Var);
            }
            if (recyclerView.f4846q0 != null) {
                recyclerView.f4845q.l(p1Var);
            }
        }
        p1Var.mBindingAdapter = null;
        p1Var.mOwnerRecyclerView = null;
        e1 e1VarC = c();
        e1VarC.getClass();
        int itemViewType = p1Var.getItemViewType();
        ArrayList arrayList2 = e1VarC.a(itemViewType).f4905a;
        if (((d1) e1VarC.f4916a.get(itemViewType)).f4906b <= arrayList2.size()) {
            androidx.work.impl.t.d(p1Var.itemView);
        } else {
            p1Var.resetInternal();
            arrayList2.add(p1Var);
        }
    }

    public final int b(int i10) {
        RecyclerView recyclerView = this.f4926h;
        if (i10 >= 0 && i10 < recyclerView.f4846q0.b()) {
            return !recyclerView.f4846q0.f4979g ? i10 : recyclerView.f4841o.f(i10, 0);
        }
        StringBuilder sbS = a0.c.s(i10, "invalid position ", ". State item count is ");
        sbS.append(recyclerView.f4846q0.b());
        sbS.append(recyclerView.w());
        throw new IndexOutOfBoundsException(sbS.toString());
    }

    public final e1 c() {
        if (this.f4925g == null) {
            e1 e1Var = new e1();
            e1Var.f4916a = new SparseArray();
            e1Var.f4917b = 0;
            e1Var.f4918c = Collections.newSetFromMap(new IdentityHashMap());
            this.f4925g = e1Var;
            d();
        }
        return this.f4925g;
    }

    public final void d() {
        RecyclerView recyclerView;
        l0 l0Var;
        e1 e1Var = this.f4925g;
        if (e1Var == null || (l0Var = (recyclerView = this.f4926h).f4856w) == null || !recyclerView.D) {
            return;
        }
        e1Var.f4918c.add(l0Var);
    }

    public final void e(l0 l0Var, boolean z) {
        e1 e1Var = this.f4925g;
        if (e1Var != null) {
            SparseArray sparseArray = e1Var.f4916a;
            Set set = e1Var.f4918c;
            set.remove(l0Var);
            if (set.size() != 0 || z) {
                return;
            }
            for (int i10 = 0; i10 < sparseArray.size(); i10++) {
                ArrayList arrayList = ((d1) sparseArray.get(sparseArray.keyAt(i10))).f4905a;
                for (int i11 = 0; i11 < arrayList.size(); i11++) {
                    androidx.work.impl.t.d(((p1) arrayList.get(i11)).itemView);
                }
            }
        }
    }

    public final void f() {
        ArrayList arrayList = this.f4921c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            g(size);
        }
        arrayList.clear();
        if (RecyclerView.O0) {
            s sVar = this.f4926h.f4844p0;
            int[] iArr = (int[]) sVar.f5043d;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            sVar.f5042c = 0;
        }
    }

    public final void g(int i10) {
        ArrayList arrayList = this.f4921c;
        a((p1) arrayList.get(i10), true);
        arrayList.remove(i10);
    }

    public final void h(View view) {
        p1 p1VarG = RecyclerView.G(view);
        boolean zIsTmpDetached = p1VarG.isTmpDetached();
        RecyclerView recyclerView = this.f4926h;
        if (zIsTmpDetached) {
            recyclerView.removeDetachedView(view, false);
        }
        if (p1VarG.isScrap()) {
            p1VarG.unScrap();
        } else if (p1VarG.wasReturnedFromScrap()) {
            p1VarG.clearReturnedFromScrapFlag();
        }
        i(p1VarG);
        if (recyclerView.W == null || p1VarG.isRecyclable()) {
            return;
        }
        recyclerView.W.d(p1VarG);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0098, code lost:
    
        r6 = r6 - 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void i(androidx.recyclerview.widget.p1 r12) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.f1.i(androidx.recyclerview.widget.p1):void");
    }

    public final void j(View view) {
        u0 u0Var;
        p1 p1VarG = RecyclerView.G(view);
        boolean zHasAnyOfTheFlags = p1VarG.hasAnyOfTheFlags(12);
        RecyclerView recyclerView = this.f4926h;
        if (!zHasAnyOfTheFlags && p1VarG.isUpdated() && (u0Var = recyclerView.W) != null) {
            m mVar = (m) u0Var;
            if (p1VarG.getUnmodifiedPayloads().isEmpty() && mVar.f4989g && !p1VarG.isInvalid()) {
                if (this.f4920b == null) {
                    this.f4920b = new ArrayList();
                }
                p1VarG.setScrapContainer(this, true);
                this.f4920b.add(p1VarG);
                return;
            }
        }
        if (p1VarG.isInvalid() && !p1VarG.isRemoved() && !recyclerView.f4856w.hasStableIds()) {
            throw new IllegalArgumentException(androidx.fragment.app.a2.k(recyclerView, new StringBuilder("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.")));
        }
        p1VarG.setScrapContainer(this, false);
        this.f4919a.add(p1VarG);
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x04c3  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x04e0  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x04f9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x04fc  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.recyclerview.widget.p1 k(int r28, long r29) {
        /*
            Method dump skipped, instruction units count: 1314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.f1.k(int, long):androidx.recyclerview.widget.p1");
    }

    public final void l(p1 p1Var) {
        if (p1Var.mInChangeScrap) {
            this.f4920b.remove(p1Var);
        } else {
            this.f4919a.remove(p1Var);
        }
        p1Var.mScrapContainer = null;
        p1Var.mInChangeScrap = false;
        p1Var.clearReturnedFromScrapFlag();
    }

    public final void m() {
        z0 z0Var = this.f4926h.x;
        this.f4924f = this.f4923e + (z0Var != null ? z0Var.f5122j : 0);
        ArrayList arrayList = this.f4921c;
        for (int size = arrayList.size() - 1; size >= 0 && arrayList.size() > this.f4924f; size--) {
            g(size);
        }
    }
}
