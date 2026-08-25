package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.g2;
import androidx.datastore.preferences.protobuf.w2;
import com.arvio.tv.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewGroup f2787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f2788b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f2789c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2790d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2791e = false;

    public n(ViewGroup viewGroup) {
        this.f2787a = viewGroup;
    }

    public static void a(View view, ArrayList arrayList) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (g2.b(viewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(viewGroup);
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = viewGroup.getChildAt(i10);
            if (childAt.getVisibility() == 0) {
                a(childAt, arrayList);
            }
        }
    }

    public static void e(t.e eVar, View view) {
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        String strK = androidx.core.view.k1.k(view);
        if (strK != null) {
            eVar.put(strK, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = viewGroup.getChildAt(i10);
                if (childAt.getVisibility() == 0) {
                    e(eVar, childAt);
                }
            }
        }
    }

    public static n h(ViewGroup viewGroup, b1 b1Var) {
        return i(viewGroup, b1Var.E());
    }

    public static n i(ViewGroup viewGroup, b2 b2Var) {
        Object tag = viewGroup.getTag(R.id.special_effects_controller_view_tag);
        if (tag instanceof n) {
            return (n) tag;
        }
        n nVarA = b2Var.a(viewGroup);
        viewGroup.setTag(R.id.special_effects_controller_view_tag, nVarA);
        return nVarA;
    }

    public static void k(t.e eVar, Collection collection) {
        Iterator it = ((w2) eVar.entrySet()).iterator();
        while (true) {
            t.c cVar = (t.c) it;
            if (!cVar.hasNext()) {
                return;
            }
            cVar.next();
            View view = (View) cVar.getValue();
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            if (!collection.contains(androidx.core.view.k1.k(view))) {
                cVar.remove();
            }
        }
    }

    public final void b(int i10, int i11, l1 l1Var) {
        synchronized (this.f2788b) {
            try {
                androidx.core.os.g gVar = new androidx.core.os.g();
                z1 z1VarF = f(l1Var.f2773c);
                if (z1VarF != null) {
                    z1VarF.c(i10, i11);
                    return;
                }
                z1 z1Var = new z1(i10, i11, l1Var, gVar);
                this.f2788b.add(z1Var);
                z1Var.f2893d.add(new y1(this, z1Var, 0));
                z1Var.f2893d.add(new y1(this, z1Var, 1));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:159:0x0485  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x058c  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x05aa  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x05b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(java.util.ArrayList r38, boolean r39) {
        /*
            Method dump skipped, instruction units count: 2448
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.n.c(java.util.ArrayList, boolean):void");
    }

    public final void d() {
        if (this.f2791e) {
            return;
        }
        ViewGroup viewGroup = this.f2787a;
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        if (!viewGroup.isAttachedToWindow()) {
            g();
            this.f2790d = false;
            return;
        }
        synchronized (this.f2788b) {
            try {
                if (!this.f2788b.isEmpty()) {
                    ArrayList<z1> arrayList = new ArrayList(this.f2789c);
                    this.f2789c.clear();
                    for (z1 z1Var : arrayList) {
                        if (b1.G(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + z1Var);
                        }
                        z1Var.a();
                        if (!z1Var.f2896g) {
                            this.f2789c.add(z1Var);
                        }
                    }
                    l();
                    ArrayList arrayList2 = new ArrayList(this.f2788b);
                    this.f2788b.clear();
                    this.f2789c.addAll(arrayList2);
                    if (b1.G(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Executing pending operations");
                    }
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        ((z1) it.next()).d();
                    }
                    c(arrayList2, this.f2790d);
                    this.f2790d = false;
                    if (b1.G(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Finished executing pending operations");
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final z1 f(c0 c0Var) {
        for (z1 z1Var : this.f2788b) {
            if (z1Var.f2892c.equals(c0Var) && !z1Var.f2895f) {
                return z1Var;
            }
        }
        return null;
    }

    public final void g() {
        if (b1.G(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Forcing all operations to complete");
        }
        ViewGroup viewGroup = this.f2787a;
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        boolean zIsAttachedToWindow = viewGroup.isAttachedToWindow();
        synchronized (this.f2788b) {
            try {
                l();
                Iterator it = this.f2788b.iterator();
                while (it.hasNext()) {
                    ((z1) it.next()).d();
                }
                for (z1 z1Var : new ArrayList(this.f2789c)) {
                    if (b1.G(2)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("SpecialEffectsController: ");
                        sb2.append(zIsAttachedToWindow ? "" : "Container " + this.f2787a + " is not attached to window. ");
                        sb2.append("Cancelling running operation ");
                        sb2.append(z1Var);
                        Log.v("FragmentManager", sb2.toString());
                    }
                    z1Var.a();
                }
                for (z1 z1Var2 : new ArrayList(this.f2788b)) {
                    if (b1.G(2)) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("SpecialEffectsController: ");
                        sb3.append(zIsAttachedToWindow ? "" : "Container " + this.f2787a + " is not attached to window. ");
                        sb3.append("Cancelling pending operation ");
                        sb3.append(z1Var2);
                        Log.v("FragmentManager", sb3.toString());
                    }
                    z1Var2.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void j() {
        synchronized (this.f2788b) {
            try {
                l();
                this.f2791e = false;
                int size = this.f2788b.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    z1 z1Var = (z1) this.f2788b.get(size);
                    int iC = a2.c(z1Var.f2892c.mView);
                    if (z1Var.f2890a == 2 && iC != 2) {
                        this.f2791e = z1Var.f2892c.isPostponed();
                        break;
                    }
                    size--;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void l() {
        for (z1 z1Var : this.f2788b) {
            if (z1Var.f2891b == 2) {
                z1Var.c(a2.b(z1Var.f2892c.requireView().getVisibility()), 1);
            }
        }
    }
}
