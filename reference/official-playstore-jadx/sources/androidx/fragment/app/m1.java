package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class m1 implements androidx.core.os.f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f2783i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f2784l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f2785m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f2786n;

    public m1() {
        this.f2783i = new ArrayList();
        this.f2784l = new HashMap();
        this.f2785m = new HashMap();
    }

    public void a(c0 c0Var) {
        if (((ArrayList) this.f2783i).contains(c0Var)) {
            throw new IllegalStateException("Fragment already added: " + c0Var);
        }
        synchronized (((ArrayList) this.f2783i)) {
            ((ArrayList) this.f2783i).add(c0Var);
        }
        c0Var.mAdded = true;
    }

    public c0 b(String str) {
        l1 l1Var = (l1) ((HashMap) this.f2784l).get(str);
        if (l1Var != null) {
            return l1Var.f2773c;
        }
        return null;
    }

    public c0 c(String str) {
        c0 c0VarFindFragmentByWho;
        for (l1 l1Var : ((HashMap) this.f2784l).values()) {
            if (l1Var != null && (c0VarFindFragmentByWho = l1Var.f2773c.findFragmentByWho(str)) != null) {
                return c0VarFindFragmentByWho;
            }
        }
        return null;
    }

    public ArrayList d() {
        ArrayList arrayList = new ArrayList();
        for (l1 l1Var : ((HashMap) this.f2784l).values()) {
            if (l1Var != null) {
                arrayList.add(l1Var);
            }
        }
        return arrayList;
    }

    public ArrayList e() {
        ArrayList arrayList = new ArrayList();
        for (l1 l1Var : ((HashMap) this.f2784l).values()) {
            if (l1Var != null) {
                arrayList.add(l1Var.f2773c);
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public List f() {
        ArrayList arrayList;
        if (((ArrayList) this.f2783i).isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        synchronized (((ArrayList) this.f2783i)) {
            arrayList = new ArrayList((ArrayList) this.f2783i);
        }
        return arrayList;
    }

    public void g(l1 l1Var) {
        c0 c0Var = l1Var.f2773c;
        String str = c0Var.mWho;
        HashMap map = (HashMap) this.f2784l;
        if (map.get(str) != null) {
            return;
        }
        map.put(c0Var.mWho, l1Var);
        if (c0Var.mRetainInstanceChangedWhileDetached) {
            if (c0Var.mRetainInstance) {
                ((FragmentManagerViewModel) this.f2786n).addRetainedFragment(c0Var);
            } else {
                ((FragmentManagerViewModel) this.f2786n).removeRetainedFragment(c0Var);
            }
            c0Var.mRetainInstanceChangedWhileDetached = false;
        }
        if (b1.G(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + c0Var);
        }
    }

    public void h(l1 l1Var) {
        c0 c0Var = l1Var.f2773c;
        if (c0Var.mRetainInstance) {
            ((FragmentManagerViewModel) this.f2786n).removeRetainedFragment(c0Var);
        }
        if (((l1) ((HashMap) this.f2784l).put(c0Var.mWho, null)) != null && b1.G(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + c0Var);
        }
    }

    @Override // androidx.core.os.f
    public void onCancel() {
        View view = (View) this.f2783i;
        view.clearAnimation();
        ((ViewGroup) this.f2784l).endViewTransition(view);
        ((k) this.f2785m).a();
        if (b1.G(2)) {
            Log.v("FragmentManager", "Animation from operation " + ((z1) this.f2786n) + " has been cancelled.");
        }
    }

    public m1(View view, ViewGroup viewGroup, k kVar, z1 z1Var) {
        this.f2783i = view;
        this.f2784l = viewGroup;
        this.f2785m = kVar;
        this.f2786n = z1Var;
    }
}
