package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class z1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2890a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2891b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c0 f2892c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f2893d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashSet f2894e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2895f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2896g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final l1 f2897h;

    public z1(int i10, int i11, l1 l1Var, androidx.core.os.g gVar) {
        c0 c0Var = l1Var.f2773c;
        this.f2893d = new ArrayList();
        this.f2894e = new HashSet();
        this.f2895f = false;
        this.f2896g = false;
        this.f2890a = i10;
        this.f2891b = i11;
        this.f2892c = c0Var;
        gVar.a(new w(this, 3));
        this.f2897h = l1Var;
    }

    public final void a() {
        HashSet hashSet = this.f2894e;
        if (this.f2895f) {
            return;
        }
        this.f2895f = true;
        if (hashSet.isEmpty()) {
            b();
            return;
        }
        for (androidx.core.os.g gVar : new ArrayList(hashSet)) {
            synchronized (gVar) {
                try {
                    if (!gVar.f2121a) {
                        gVar.f2121a = true;
                        gVar.f2123c = true;
                        androidx.core.os.f fVar = gVar.f2122b;
                        if (fVar != null) {
                            try {
                                fVar.onCancel();
                            } catch (Throwable th) {
                                synchronized (gVar) {
                                    gVar.f2123c = false;
                                    gVar.notifyAll();
                                    throw th;
                                }
                            }
                        }
                        synchronized (gVar) {
                            gVar.f2123c = false;
                            gVar.notifyAll();
                        }
                    }
                } finally {
                }
            }
        }
    }

    public final void b() {
        if (!this.f2896g) {
            if (b1.G(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
            }
            this.f2896g = true;
            Iterator it = this.f2893d.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
        this.f2897h.j();
    }

    public final void c(int i10, int i11) {
        int iC = h.f0.c(i11);
        c0 c0Var = this.f2892c;
        if (iC == 0) {
            if (this.f2890a != 1) {
                if (b1.G(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + c0Var + " mFinalState = " + a2.F(this.f2890a) + " -> " + a2.F(i10) + ". ");
                }
                this.f2890a = i10;
                return;
            }
            return;
        }
        if (iC == 1) {
            if (this.f2890a == 1) {
                if (b1.G(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + c0Var + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + androidx.compose.material3.d.D(this.f2891b) + " to ADDING.");
                }
                this.f2890a = 2;
                this.f2891b = 2;
                return;
            }
            return;
        }
        if (iC != 2) {
            return;
        }
        if (b1.G(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: For fragment " + c0Var + " mFinalState = " + a2.F(this.f2890a) + " -> REMOVED. mLifecycleImpact  = " + androidx.compose.material3.d.D(this.f2891b) + " to REMOVING.");
        }
        this.f2890a = 1;
        this.f2891b = 3;
    }

    public final void d() {
        int i10 = this.f2891b;
        l1 l1Var = this.f2897h;
        if (i10 != 2) {
            if (i10 == 3) {
                c0 c0Var = l1Var.f2773c;
                View viewRequireView = c0Var.requireView();
                if (b1.G(2)) {
                    Log.v("FragmentManager", "Clearing focus " + viewRequireView.findFocus() + " on view " + viewRequireView + " for Fragment " + c0Var);
                }
                viewRequireView.clearFocus();
                return;
            }
            return;
        }
        c0 c0Var2 = l1Var.f2773c;
        View viewFindFocus = c0Var2.mView.findFocus();
        if (viewFindFocus != null) {
            c0Var2.setFocusedView(viewFindFocus);
            if (b1.G(2)) {
                Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + c0Var2);
            }
        }
        View viewRequireView2 = this.f2892c.requireView();
        if (viewRequireView2.getParent() == null) {
            l1Var.a();
            viewRequireView2.setAlpha(0.0f);
        }
        if (viewRequireView2.getAlpha() == 0.0f && viewRequireView2.getVisibility() == 0) {
            viewRequireView2.setVisibility(4);
        }
        viewRequireView2.setAlpha(c0Var2.getPostOnViewCreatedAlpha());
    }

    public final String toString() {
        return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + a2.F(this.f2890a) + "} {mLifecycleImpact = " + androidx.compose.material3.d.D(this.f2891b) + "} {mFragment = " + this.f2892c + "}";
    }
}
