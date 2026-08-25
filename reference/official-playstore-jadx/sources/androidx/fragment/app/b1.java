package androidx.fragment.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.arvio.tv.R;
import j$.util.DesugarCollections;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public abstract class b1 implements i1 {
    public androidx.activity.result.j A;
    public androidx.activity.result.j B;
    public androidx.activity.result.j C;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public ArrayList J;
    public ArrayList K;
    public ArrayList L;
    public FragmentManagerViewModel M;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2668b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList f2670d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList f2671e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public androidx.activity.l0 f2673g;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final p0 f2680o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final p0 f2681p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final p0 f2682q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final p0 f2683r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public m0 f2686u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public j0 f2687v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public c0 f2688w;
    public c0 x;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f2667a = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m1 f2669c = new m1();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final o0 f2672f = new o0(this);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final androidx.activity.m0 f2674h = new androidx.activity.m0(this, 1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AtomicInteger f2675i = new AtomicInteger();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Map f2676j = DesugarCollections.synchronizedMap(new HashMap());
    public final Map k = DesugarCollections.synchronizedMap(new HashMap());

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Map f2677l = DesugarCollections.synchronizedMap(new HashMap());

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final g f2678m = new g(this);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final CopyOnWriteArrayList f2679n = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final r0 f2684s = new r0(this);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f2685t = -1;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final s0 f2689y = new s0(this);
    public final t0 z = new t0();
    public ArrayDeque D = new ArrayDeque();
    public final h N = new h(this, 4);

    /* JADX WARN: Type inference failed for: r0v13, types: [androidx.fragment.app.p0] */
    /* JADX WARN: Type inference failed for: r0v14, types: [androidx.fragment.app.p0] */
    /* JADX WARN: Type inference failed for: r0v15, types: [androidx.fragment.app.p0] */
    /* JADX WARN: Type inference failed for: r0v16, types: [androidx.fragment.app.p0] */
    public b1() {
        final int i10 = 0;
        this.f2680o = new p0.a(this) { // from class: androidx.fragment.app.p0

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ b1 f2821b;

            {
                this.f2821b = this;
            }

            @Override // p0.a
            public final void accept(Object obj) {
                switch (i10) {
                    case 0:
                        Configuration configuration = (Configuration) obj;
                        b1 b1Var = this.f2821b;
                        if (b1Var.I()) {
                            b1Var.h(false, configuration);
                        }
                        break;
                    case 1:
                        Integer num = (Integer) obj;
                        b1 b1Var2 = this.f2821b;
                        if (b1Var2.I() && num.intValue() == 80) {
                            b1Var2.l(false);
                            break;
                        }
                        break;
                    case 2:
                        androidx.core.app.s sVar = (androidx.core.app.s) obj;
                        b1 b1Var3 = this.f2821b;
                        if (b1Var3.I()) {
                            b1Var3.m(sVar.f2042a, false);
                        }
                        break;
                    default:
                        androidx.core.app.x1 x1Var = (androidx.core.app.x1) obj;
                        b1 b1Var4 = this.f2821b;
                        if (b1Var4.I()) {
                            b1Var4.r(x1Var.f2075a, false);
                        }
                        break;
                }
            }
        };
        final int i11 = 1;
        this.f2681p = new p0.a(this) { // from class: androidx.fragment.app.p0

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ b1 f2821b;

            {
                this.f2821b = this;
            }

            @Override // p0.a
            public final void accept(Object obj) {
                switch (i11) {
                    case 0:
                        Configuration configuration = (Configuration) obj;
                        b1 b1Var = this.f2821b;
                        if (b1Var.I()) {
                            b1Var.h(false, configuration);
                        }
                        break;
                    case 1:
                        Integer num = (Integer) obj;
                        b1 b1Var2 = this.f2821b;
                        if (b1Var2.I() && num.intValue() == 80) {
                            b1Var2.l(false);
                            break;
                        }
                        break;
                    case 2:
                        androidx.core.app.s sVar = (androidx.core.app.s) obj;
                        b1 b1Var3 = this.f2821b;
                        if (b1Var3.I()) {
                            b1Var3.m(sVar.f2042a, false);
                        }
                        break;
                    default:
                        androidx.core.app.x1 x1Var = (androidx.core.app.x1) obj;
                        b1 b1Var4 = this.f2821b;
                        if (b1Var4.I()) {
                            b1Var4.r(x1Var.f2075a, false);
                        }
                        break;
                }
            }
        };
        final int i12 = 2;
        this.f2682q = new p0.a(this) { // from class: androidx.fragment.app.p0

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ b1 f2821b;

            {
                this.f2821b = this;
            }

            @Override // p0.a
            public final void accept(Object obj) {
                switch (i12) {
                    case 0:
                        Configuration configuration = (Configuration) obj;
                        b1 b1Var = this.f2821b;
                        if (b1Var.I()) {
                            b1Var.h(false, configuration);
                        }
                        break;
                    case 1:
                        Integer num = (Integer) obj;
                        b1 b1Var2 = this.f2821b;
                        if (b1Var2.I() && num.intValue() == 80) {
                            b1Var2.l(false);
                            break;
                        }
                        break;
                    case 2:
                        androidx.core.app.s sVar = (androidx.core.app.s) obj;
                        b1 b1Var3 = this.f2821b;
                        if (b1Var3.I()) {
                            b1Var3.m(sVar.f2042a, false);
                        }
                        break;
                    default:
                        androidx.core.app.x1 x1Var = (androidx.core.app.x1) obj;
                        b1 b1Var4 = this.f2821b;
                        if (b1Var4.I()) {
                            b1Var4.r(x1Var.f2075a, false);
                        }
                        break;
                }
            }
        };
        final int i13 = 3;
        this.f2683r = new p0.a(this) { // from class: androidx.fragment.app.p0

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ b1 f2821b;

            {
                this.f2821b = this;
            }

            @Override // p0.a
            public final void accept(Object obj) {
                switch (i13) {
                    case 0:
                        Configuration configuration = (Configuration) obj;
                        b1 b1Var = this.f2821b;
                        if (b1Var.I()) {
                            b1Var.h(false, configuration);
                        }
                        break;
                    case 1:
                        Integer num = (Integer) obj;
                        b1 b1Var2 = this.f2821b;
                        if (b1Var2.I() && num.intValue() == 80) {
                            b1Var2.l(false);
                            break;
                        }
                        break;
                    case 2:
                        androidx.core.app.s sVar = (androidx.core.app.s) obj;
                        b1 b1Var3 = this.f2821b;
                        if (b1Var3.I()) {
                            b1Var3.m(sVar.f2042a, false);
                        }
                        break;
                    default:
                        androidx.core.app.x1 x1Var = (androidx.core.app.x1) obj;
                        b1 b1Var4 = this.f2821b;
                        if (b1Var4.I()) {
                            b1Var4.r(x1Var.f2075a, false);
                        }
                        break;
                }
            }
        };
    }

    public static boolean G(int i10) {
        return Log.isLoggable("FragmentManager", i10);
    }

    public static boolean H(c0 c0Var) {
        if (c0Var.mHasMenu && c0Var.mMenuVisible) {
            return true;
        }
        boolean zH = false;
        for (c0 c0Var2 : c0Var.mChildFragmentManager.f2669c.e()) {
            if (c0Var2 != null) {
                zH = H(c0Var2);
            }
            if (zH) {
                return true;
            }
        }
        return false;
    }

    public static boolean J(c0 c0Var) {
        if (c0Var == null) {
            return true;
        }
        b1 b1Var = c0Var.mFragmentManager;
        return c0Var.equals(b1Var.x) && J(b1Var.f2688w);
    }

    public static void Y(c0 c0Var) {
        if (G(2)) {
            Log.v("FragmentManager", "show: " + c0Var);
        }
        if (c0Var.mHidden) {
            c0Var.mHidden = false;
            c0Var.mHiddenChanged = !c0Var.mHiddenChanged;
        }
    }

    public final c0 A(int i10) {
        m1 m1Var = this.f2669c;
        ArrayList arrayList = (ArrayList) m1Var.f2783i;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            c0 c0Var = (c0) arrayList.get(size);
            if (c0Var != null && c0Var.mFragmentId == i10) {
                return c0Var;
            }
        }
        for (l1 l1Var : ((HashMap) m1Var.f2784l).values()) {
            if (l1Var != null) {
                c0 c0Var2 = l1Var.f2773c;
                if (c0Var2.mFragmentId == i10) {
                    return c0Var2;
                }
            }
        }
        return null;
    }

    public final c0 B(String str) {
        m1 m1Var = this.f2669c;
        ArrayList arrayList = (ArrayList) m1Var.f2783i;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            c0 c0Var = (c0) arrayList.get(size);
            if (c0Var != null && str.equals(c0Var.mTag)) {
                return c0Var;
            }
        }
        for (l1 l1Var : ((HashMap) m1Var.f2784l).values()) {
            if (l1Var != null) {
                c0 c0Var2 = l1Var.f2773c;
                if (str.equals(c0Var2.mTag)) {
                    return c0Var2;
                }
            }
        }
        return null;
    }

    public final ViewGroup C(c0 c0Var) {
        ViewGroup viewGroup = c0Var.mContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (c0Var.mContainerId <= 0 || !this.f2687v.c()) {
            return null;
        }
        View viewB = this.f2687v.b(c0Var.mContainerId);
        if (viewB instanceof ViewGroup) {
            return (ViewGroup) viewB;
        }
        return null;
    }

    public final s0 D() {
        c0 c0Var = this.f2688w;
        return c0Var != null ? c0Var.mFragmentManager.D() : this.f2689y;
    }

    public final b2 E() {
        c0 c0Var = this.f2688w;
        return c0Var != null ? c0Var.mFragmentManager.E() : this.z;
    }

    public final void F(c0 c0Var) {
        if (G(2)) {
            Log.v("FragmentManager", "hide: " + c0Var);
        }
        if (c0Var.mHidden) {
            return;
        }
        c0Var.mHidden = true;
        c0Var.mHiddenChanged = true ^ c0Var.mHiddenChanged;
        X(c0Var);
    }

    public final boolean I() {
        c0 c0Var = this.f2688w;
        if (c0Var == null) {
            return true;
        }
        return c0Var.isAdded() && this.f2688w.getParentFragmentManager().I();
    }

    public final void K(int i10, boolean z) {
        m0 m0Var;
        if (this.f2686u == null && i10 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i10 != this.f2685t) {
            this.f2685t = i10;
            m1 m1Var = this.f2669c;
            HashMap map = (HashMap) m1Var.f2784l;
            Iterator it = ((ArrayList) m1Var.f2783i).iterator();
            while (it.hasNext()) {
                l1 l1Var = (l1) map.get(((c0) it.next()).mWho);
                if (l1Var != null) {
                    l1Var.j();
                }
            }
            for (l1 l1Var2 : map.values()) {
                if (l1Var2 != null) {
                    l1Var2.j();
                    c0 c0Var = l1Var2.f2773c;
                    if (c0Var.mRemoving && !c0Var.isInBackStack()) {
                        if (c0Var.mBeingSaved && !((HashMap) m1Var.f2785m).containsKey(c0Var.mWho)) {
                            l1Var2.m();
                        }
                        m1Var.h(l1Var2);
                    }
                }
            }
            for (l1 l1Var3 : m1Var.d()) {
                c0 c0Var2 = l1Var3.f2773c;
                if (c0Var2.mDeferStart) {
                    if (this.f2668b) {
                        this.I = true;
                    } else {
                        c0Var2.mDeferStart = false;
                        l1Var3.j();
                    }
                }
            }
            if (this.E && (m0Var = this.f2686u) != null && this.f2685t == 7) {
                ((g0) m0Var).f2731o.invalidateMenu();
                this.E = false;
            }
        }
    }

    public final void L() {
        if (this.f2686u == null) {
            return;
        }
        this.F = false;
        this.G = false;
        this.M.setIsStateSaved(false);
        for (c0 c0Var : this.f2669c.f()) {
            if (c0Var != null) {
                c0Var.noteStateNotSaved();
            }
        }
    }

    public final boolean M() {
        return N(-1, 0);
    }

    public final boolean N(int i10, int i11) {
        x(false);
        w(true);
        c0 c0Var = this.x;
        if (c0Var != null && i10 < 0 && c0Var.getChildFragmentManager().M()) {
            return true;
        }
        boolean zO = O(this.J, this.K, i10, i11);
        if (zO) {
            this.f2668b = true;
            try {
                Q(this.J, this.K);
            } finally {
                d();
            }
        }
        a0();
        boolean z = this.I;
        m1 m1Var = this.f2669c;
        if (z) {
            this.I = false;
            for (l1 l1Var : m1Var.d()) {
                c0 c0Var2 = l1Var.f2773c;
                if (c0Var2.mDeferStart) {
                    if (this.f2668b) {
                        this.I = true;
                    } else {
                        c0Var2.mDeferStart = false;
                        l1Var.j();
                    }
                }
            }
        }
        ((HashMap) m1Var.f2784l).values().removeAll(Collections.singleton(null));
        return zO;
    }

    public final boolean O(ArrayList arrayList, ArrayList arrayList2, int i10, int i11) {
        boolean z = (i11 & 1) != 0;
        ArrayList arrayList3 = this.f2670d;
        int size = -1;
        if (arrayList3 != null && !arrayList3.isEmpty()) {
            if (i10 < 0) {
                size = z ? 0 : this.f2670d.size() - 1;
            } else {
                int size2 = this.f2670d.size() - 1;
                while (size2 >= 0) {
                    a aVar = (a) this.f2670d.get(size2);
                    if (i10 >= 0 && i10 == aVar.f2662r) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    size = size2;
                } else if (z) {
                    size = size2;
                    while (size > 0) {
                        a aVar2 = (a) this.f2670d.get(size - 1);
                        if (i10 < 0 || i10 != aVar2.f2662r) {
                            break;
                        }
                        size--;
                    }
                } else if (size2 != this.f2670d.size() - 1) {
                    size = size2 + 1;
                }
            }
        }
        if (size < 0) {
            return false;
        }
        for (int size3 = this.f2670d.size() - 1; size3 >= size; size3--) {
            arrayList.add((a) this.f2670d.remove(size3));
            arrayList2.add(Boolean.TRUE);
        }
        return true;
    }

    public final void P(c0 c0Var) {
        if (G(2)) {
            Log.v("FragmentManager", "remove: " + c0Var + " nesting=" + c0Var.mBackStackNesting);
        }
        boolean zIsInBackStack = c0Var.isInBackStack();
        if (c0Var.mDetached && zIsInBackStack) {
            return;
        }
        m1 m1Var = this.f2669c;
        synchronized (((ArrayList) m1Var.f2783i)) {
            ((ArrayList) m1Var.f2783i).remove(c0Var);
        }
        c0Var.mAdded = false;
        if (H(c0Var)) {
            this.E = true;
        }
        c0Var.mRemoving = true;
        X(c0Var);
    }

    public final void Q(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        int size = arrayList.size();
        int i10 = 0;
        int i11 = 0;
        while (i10 < size) {
            if (!((a) arrayList.get(i10)).f2818o) {
                if (i11 != i10) {
                    z(arrayList, arrayList2, i11, i10);
                }
                i11 = i10 + 1;
                if (((Boolean) arrayList2.get(i10)).booleanValue()) {
                    while (i11 < size && ((Boolean) arrayList2.get(i11)).booleanValue() && !((a) arrayList.get(i11)).f2818o) {
                        i11++;
                    }
                }
                z(arrayList, arrayList2, i10, i11);
                i10 = i11 - 1;
            }
            i10++;
        }
        if (i11 != size) {
            z(arrayList, arrayList2, i11, size);
        }
    }

    public final void R(Parcelable parcelable) {
        g gVar;
        int i10;
        boolean z;
        int i11;
        l1 l1Var;
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3 = (Bundle) parcelable;
        for (String str : bundle3.keySet()) {
            if (str.startsWith("result_") && (bundle2 = bundle3.getBundle(str)) != null) {
                bundle2.setClassLoader(this.f2686u.f2780l.getClassLoader());
                this.k.put(str.substring(7), bundle2);
            }
        }
        ArrayList<j1> arrayList = new ArrayList();
        for (String str2 : bundle3.keySet()) {
            if (str2.startsWith("fragment_") && (bundle = bundle3.getBundle(str2)) != null) {
                bundle.setClassLoader(this.f2686u.f2780l.getClassLoader());
                arrayList.add((j1) bundle.getParcelable("state"));
            }
        }
        m1 m1Var = this.f2669c;
        HashMap map = (HashMap) m1Var.f2785m;
        HashMap map2 = (HashMap) m1Var.f2784l;
        map.clear();
        for (j1 j1Var : arrayList) {
            map.put(j1Var.f2748l, j1Var);
        }
        e1 e1Var = (e1) bundle3.getParcelable("state");
        if (e1Var == null) {
            return;
        }
        map2.clear();
        Iterator it = e1Var.f2715i.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            gVar = this.f2678m;
            i10 = 2;
            if (!zHasNext) {
                break;
            }
            j1 j1Var2 = (j1) ((HashMap) m1Var.f2785m).remove((String) it.next());
            if (j1Var2 != null) {
                c0 c0VarFindRetainedFragmentByWho = this.M.findRetainedFragmentByWho(j1Var2.f2748l);
                if (c0VarFindRetainedFragmentByWho != null) {
                    if (G(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + c0VarFindRetainedFragmentByWho);
                    }
                    l1Var = new l1(gVar, m1Var, c0VarFindRetainedFragmentByWho, j1Var2);
                } else {
                    l1Var = new l1(this.f2678m, this.f2669c, this.f2686u.f2780l.getClassLoader(), D(), j1Var2);
                }
                c0 c0Var = l1Var.f2773c;
                c0Var.mFragmentManager = this;
                if (G(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + c0Var.mWho + "): " + c0Var);
                }
                l1Var.k(this.f2686u.f2780l.getClassLoader());
                m1Var.g(l1Var);
                l1Var.f2775e = this.f2685t;
            }
        }
        Iterator<c0> it2 = this.M.getRetainedFragments().iterator();
        while (true) {
            z = true;
            if (!it2.hasNext()) {
                break;
            }
            c0 next = it2.next();
            if (map2.get(next.mWho) == null) {
                if (G(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + next + " that was not found in the set of active Fragments " + e1Var.f2715i);
                }
                this.M.removeRetainedFragment(next);
                next.mFragmentManager = this;
                l1 l1Var2 = new l1(gVar, m1Var, next);
                l1Var2.f2775e = 1;
                l1Var2.j();
                next.mRemoving = true;
                l1Var2.j();
            }
        }
        ArrayList<String> arrayList2 = e1Var.f2716l;
        ((ArrayList) m1Var.f2783i).clear();
        if (arrayList2 != null) {
            for (String str3 : arrayList2) {
                c0 c0VarB = m1Var.b(str3);
                if (c0VarB == null) {
                    throw new IllegalStateException(a0.c.l("No instantiated fragment for (", str3, ")"));
                }
                if (G(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str3 + "): " + c0VarB);
                }
                m1Var.a(c0VarB);
            }
        }
        if (e1Var.f2717m != null) {
            this.f2670d = new ArrayList(e1Var.f2717m.length);
            int i12 = 0;
            while (true) {
                c[] cVarArr = e1Var.f2717m;
                if (i12 >= cVarArr.length) {
                    break;
                }
                c cVar = cVarArr[i12];
                ArrayList arrayList3 = cVar.f2691l;
                a aVar = new a(this);
                int[] iArr = cVar.f2690i;
                int i13 = 0;
                int i14 = 0;
                while (i13 < iArr.length) {
                    n1 n1Var = new n1();
                    int i15 = i13 + 1;
                    int i16 = i10;
                    n1Var.f2794a = iArr[i13];
                    if (G(i16)) {
                        Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i14 + " base fragment #" + iArr[i15]);
                    }
                    n1Var.f2801h = androidx.lifecycle.q.values()[cVar.f2692m[i14]];
                    n1Var.f2802i = androidx.lifecycle.q.values()[cVar.f2693n[i14]];
                    int i17 = i13 + 2;
                    n1Var.f2796c = iArr[i15] != 0 ? z : false;
                    int i18 = iArr[i17];
                    n1Var.f2797d = i18;
                    int i19 = iArr[i13 + 3];
                    n1Var.f2798e = i19;
                    int i20 = i13 + 5;
                    int i21 = iArr[i13 + 4];
                    n1Var.f2799f = i21;
                    i13 += 6;
                    int[] iArr2 = iArr;
                    int i22 = iArr2[i20];
                    n1Var.f2800g = i22;
                    aVar.f2806b = i18;
                    aVar.f2807c = i19;
                    aVar.f2808d = i21;
                    aVar.f2809e = i22;
                    aVar.b(n1Var);
                    i14++;
                    i10 = i16;
                    iArr = iArr2;
                    z = true;
                }
                int i23 = i10;
                aVar.f2810f = cVar.f2694o;
                aVar.f2812h = cVar.f2695p;
                aVar.f2811g = true;
                aVar.f2813i = cVar.f2697r;
                aVar.f2814j = cVar.f2698s;
                aVar.k = cVar.f2699t;
                aVar.f2815l = cVar.f2700u;
                aVar.f2816m = cVar.f2701v;
                aVar.f2817n = cVar.f2702w;
                aVar.f2818o = cVar.x;
                aVar.f2662r = cVar.f2696q;
                for (int i24 = 0; i24 < arrayList3.size(); i24++) {
                    String str4 = (String) arrayList3.get(i24);
                    if (str4 != null) {
                        ((n1) aVar.f2805a.get(i24)).f2795b = m1Var.b(str4);
                    }
                }
                aVar.d(1);
                if (G(i23)) {
                    StringBuilder sbS = a0.c.s(i12, "restoreAllState: back stack #", " (index ");
                    sbS.append(aVar.f2662r);
                    sbS.append("): ");
                    sbS.append(aVar);
                    Log.v("FragmentManager", sbS.toString());
                    PrintWriter printWriter = new PrintWriter(new x1());
                    aVar.f("  ", printWriter, false);
                    printWriter.close();
                }
                this.f2670d.add(aVar);
                i12++;
                i10 = i23;
                z = true;
            }
            i11 = 0;
        } else {
            i11 = 0;
            this.f2670d = null;
        }
        this.f2675i.set(e1Var.f2718n);
        String str5 = e1Var.f2719o;
        if (str5 != null) {
            c0 c0VarB2 = m1Var.b(str5);
            this.x = c0VarB2;
            q(c0VarB2);
        }
        ArrayList arrayList4 = e1Var.f2720p;
        if (arrayList4 != null) {
            for (int i25 = i11; i25 < arrayList4.size(); i25++) {
                this.f2676j.put((String) arrayList4.get(i25), (d) e1Var.f2721q.get(i25));
            }
        }
        this.D = new ArrayDeque(e1Var.f2722r);
    }

    public final Bundle S() {
        int i10;
        ArrayList arrayList;
        c[] cVarArr;
        int size;
        Bundle bundle = new Bundle();
        Iterator it = e().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            n nVar = (n) it.next();
            if (nVar.f2791e) {
                if (G(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: Forcing postponed operations");
                }
                nVar.f2791e = false;
                nVar.d();
            }
        }
        Iterator it2 = e().iterator();
        while (it2.hasNext()) {
            ((n) it2.next()).g();
        }
        x(true);
        this.F = true;
        this.M.setIsStateSaved(true);
        m1 m1Var = this.f2669c;
        m1Var.getClass();
        HashMap map = (HashMap) m1Var.f2784l;
        ArrayList arrayList2 = new ArrayList(map.size());
        for (l1 l1Var : map.values()) {
            if (l1Var != null) {
                c0 c0Var = l1Var.f2773c;
                l1Var.m();
                arrayList2.add(c0Var.mWho);
                if (G(2)) {
                    Log.v("FragmentManager", "Saved state of " + c0Var + ": " + c0Var.mSavedFragmentState);
                }
            }
        }
        m1 m1Var2 = this.f2669c;
        m1Var2.getClass();
        ArrayList<j1> arrayList3 = new ArrayList(((HashMap) m1Var2.f2785m).values());
        if (!arrayList3.isEmpty()) {
            m1 m1Var3 = this.f2669c;
            synchronized (((ArrayList) m1Var3.f2783i)) {
                try {
                    if (((ArrayList) m1Var3.f2783i).isEmpty()) {
                        arrayList = null;
                    } else {
                        arrayList = new ArrayList(((ArrayList) m1Var3.f2783i).size());
                        for (c0 c0Var2 : (ArrayList) m1Var3.f2783i) {
                            arrayList.add(c0Var2.mWho);
                            if (G(2)) {
                                Log.v("FragmentManager", "saveAllState: adding fragment (" + c0Var2.mWho + "): " + c0Var2);
                            }
                        }
                    }
                } finally {
                }
            }
            ArrayList arrayList4 = this.f2670d;
            if (arrayList4 == null || (size = arrayList4.size()) <= 0) {
                cVarArr = null;
            } else {
                cVarArr = new c[size];
                for (i10 = 0; i10 < size; i10++) {
                    cVarArr[i10] = new c((a) this.f2670d.get(i10));
                    if (G(2)) {
                        StringBuilder sbS = a0.c.s(i10, "saveAllState: adding back stack #", ": ");
                        sbS.append(this.f2670d.get(i10));
                        Log.v("FragmentManager", sbS.toString());
                    }
                }
            }
            e1 e1Var = new e1();
            e1Var.f2719o = null;
            ArrayList arrayList5 = new ArrayList();
            e1Var.f2720p = arrayList5;
            ArrayList arrayList6 = new ArrayList();
            e1Var.f2721q = arrayList6;
            e1Var.f2715i = arrayList2;
            e1Var.f2716l = arrayList;
            e1Var.f2717m = cVarArr;
            e1Var.f2718n = this.f2675i.get();
            c0 c0Var3 = this.x;
            if (c0Var3 != null) {
                e1Var.f2719o = c0Var3.mWho;
            }
            arrayList5.addAll(this.f2676j.keySet());
            arrayList6.addAll(this.f2676j.values());
            e1Var.f2722r = new ArrayList(this.D);
            bundle.putParcelable("state", e1Var);
            for (String str : this.k.keySet()) {
                bundle.putBundle(androidx.compose.material3.d.C("result_", str), (Bundle) this.k.get(str));
            }
            for (j1 j1Var : arrayList3) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("state", j1Var);
                bundle.putBundle("fragment_" + j1Var.f2748l, bundle2);
            }
        } else if (G(2)) {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return bundle;
        }
        return bundle;
    }

    public final void T() {
        synchronized (this.f2667a) {
            try {
                if (this.f2667a.size() == 1) {
                    this.f2686u.f2781m.removeCallbacks(this.N);
                    this.f2686u.f2781m.post(this.N);
                    a0();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void U(c0 c0Var, boolean z) {
        ViewGroup viewGroupC = C(c0Var);
        if (viewGroupC == null || !(viewGroupC instanceof k0)) {
            return;
        }
        ((k0) viewGroupC).setDrawDisappearingViewsLast(!z);
    }

    public final void V(c0 c0Var, androidx.lifecycle.q qVar) {
        if (c0Var.equals(this.f2669c.b(c0Var.mWho)) && (c0Var.mHost == null || c0Var.mFragmentManager == this)) {
            c0Var.mMaxState = qVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + c0Var + " is not an active fragment of FragmentManager " + this);
    }

    public final void W(c0 c0Var) {
        if (c0Var != null) {
            if (!c0Var.equals(this.f2669c.b(c0Var.mWho)) || (c0Var.mHost != null && c0Var.mFragmentManager != this)) {
                throw new IllegalArgumentException("Fragment " + c0Var + " is not an active fragment of FragmentManager " + this);
            }
        }
        c0 c0Var2 = this.x;
        this.x = c0Var;
        q(c0Var2);
        q(this.x);
    }

    public final void X(c0 c0Var) {
        ViewGroup viewGroupC = C(c0Var);
        if (viewGroupC != null) {
            if (c0Var.getPopExitAnim() + c0Var.getPopEnterAnim() + c0Var.getExitAnim() + c0Var.getEnterAnim() > 0) {
                if (viewGroupC.getTag(R.id.visible_removing_fragment_view_tag) == null) {
                    viewGroupC.setTag(R.id.visible_removing_fragment_view_tag, c0Var);
                }
                ((c0) viewGroupC.getTag(R.id.visible_removing_fragment_view_tag)).setPopDirection(c0Var.getPopDirection());
            }
        }
    }

    public final void Z(IllegalStateException illegalStateException) {
        Log.e("FragmentManager", illegalStateException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new x1());
        m0 m0Var = this.f2686u;
        if (m0Var == null) {
            try {
                u("  ", null, printWriter, new String[0]);
                throw illegalStateException;
            } catch (Exception e5) {
                Log.e("FragmentManager", "Failed dumping state", e5);
                throw illegalStateException;
            }
        }
        try {
            ((g0) m0Var).f2731o.dump("  ", null, printWriter, new String[0]);
            throw illegalStateException;
        } catch (Exception e6) {
            Log.e("FragmentManager", "Failed dumping state", e6);
            throw illegalStateException;
        }
    }

    public final l1 a(c0 c0Var) {
        String str = c0Var.mPreviousWho;
        if (str != null) {
            h1.c.c(c0Var, str);
        }
        if (G(2)) {
            Log.v("FragmentManager", "add: " + c0Var);
        }
        l1 l1VarF = f(c0Var);
        c0Var.mFragmentManager = this;
        m1 m1Var = this.f2669c;
        m1Var.g(l1VarF);
        if (!c0Var.mDetached) {
            m1Var.a(c0Var);
            c0Var.mRemoving = false;
            if (c0Var.mView == null) {
                c0Var.mHiddenChanged = false;
            }
            if (H(c0Var)) {
                this.E = true;
            }
        }
        return l1VarF;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.internal.m, r7.a] */
    /* JADX WARN: Type inference failed for: r1v10, types: [kotlin.jvm.internal.m, r7.a] */
    public final void a0() {
        synchronized (this.f2667a) {
            try {
                if (!this.f2667a.isEmpty()) {
                    androidx.activity.m0 m0Var = this.f2674h;
                    m0Var.f931a = true;
                    ?? r1 = m0Var.f933c;
                    if (r1 != 0) {
                        r1.invoke();
                    }
                    return;
                }
                androidx.activity.m0 m0Var2 = this.f2674h;
                ArrayList arrayList = this.f2670d;
                m0Var2.f931a = (arrayList != null ? arrayList.size() : 0) > 0 && J(this.f2688w);
                ?? r02 = m0Var2.f933c;
                if (r02 != 0) {
                    r02.invoke();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b(m0 m0Var, j0 j0Var, c0 c0Var) {
        if (this.f2686u != null) {
            throw new IllegalStateException("Already attached");
        }
        this.f2686u = m0Var;
        this.f2687v = j0Var;
        this.f2688w = c0Var;
        CopyOnWriteArrayList copyOnWriteArrayList = this.f2679n;
        if (c0Var != null) {
            copyOnWriteArrayList.add(new u0(c0Var));
        } else if (m0Var instanceof g1) {
            copyOnWriteArrayList.add((g1) m0Var);
        }
        if (this.f2688w != null) {
            a0();
        }
        if (m0Var instanceof androidx.activity.n0) {
            androidx.activity.n0 n0Var = (androidx.activity.n0) m0Var;
            androidx.activity.l0 onBackPressedDispatcher = n0Var.getOnBackPressedDispatcher();
            this.f2673g = onBackPressedDispatcher;
            androidx.lifecycle.y yVar = n0Var;
            if (c0Var != null) {
                yVar = c0Var;
            }
            onBackPressedDispatcher.a(yVar, this.f2674h);
        }
        if (c0Var != null) {
            this.M = c0Var.mFragmentManager.M.getChildNonConfig(c0Var);
        } else if (m0Var instanceof androidx.lifecycle.j1) {
            this.M = FragmentManagerViewModel.getInstance(((androidx.lifecycle.j1) m0Var).getViewModelStore());
        } else {
            this.M = new FragmentManagerViewModel(false);
        }
        this.M.setIsStateSaved(this.F || this.G);
        this.f2669c.f2786n = this.M;
        Object obj = this.f2686u;
        if ((obj instanceof b2.g) && c0Var == null) {
            b2.e savedStateRegistry = ((b2.g) obj).getSavedStateRegistry();
            savedStateRegistry.c("android:support:fragments", new d0(this, 1));
            Bundle bundleA = savedStateRegistry.a("android:support:fragments");
            if (bundleA != null) {
                R(bundleA);
            }
        }
        Object obj2 = this.f2686u;
        if (obj2 instanceof androidx.activity.result.l) {
            androidx.activity.result.k activityResultRegistry = ((androidx.activity.result.l) obj2).getActivityResultRegistry();
            String strC = androidx.compose.material3.d.C("FragmentManager:", c0Var != null ? a0.c.p(new StringBuilder(), c0Var.mWho, ":") : "");
            this.A = activityResultRegistry.d(androidx.compose.material3.d.m(strC, "StartActivityForResult"), new w0(3), new q0(this, 1));
            this.B = activityResultRegistry.d(androidx.compose.material3.d.m(strC, "StartIntentSenderForResult"), new w0(0), new q0(this, 2));
            this.C = activityResultRegistry.d(androidx.compose.material3.d.m(strC, "RequestPermissions"), new w0(2), new q0(this, 0));
        }
        Object obj3 = this.f2686u;
        if (obj3 instanceof androidx.core.content.k) {
            ((androidx.core.content.k) obj3).addOnConfigurationChangedListener(this.f2680o);
        }
        Object obj4 = this.f2686u;
        if (obj4 instanceof androidx.core.content.l) {
            ((androidx.core.content.l) obj4).addOnTrimMemoryListener(this.f2681p);
        }
        Object obj5 = this.f2686u;
        if (obj5 instanceof androidx.core.app.q1) {
            ((androidx.core.app.q1) obj5).addOnMultiWindowModeChangedListener(this.f2682q);
        }
        Object obj6 = this.f2686u;
        if (obj6 instanceof androidx.core.app.s1) {
            ((androidx.core.app.s1) obj6).addOnPictureInPictureModeChangedListener(this.f2683r);
        }
        Object obj7 = this.f2686u;
        if ((obj7 instanceof androidx.core.view.c0) && c0Var == null) {
            ((androidx.core.view.c0) obj7).addMenuProvider(this.f2684s);
        }
    }

    public final void c(c0 c0Var) {
        if (G(2)) {
            Log.v("FragmentManager", "attach: " + c0Var);
        }
        if (c0Var.mDetached) {
            c0Var.mDetached = false;
            if (c0Var.mAdded) {
                return;
            }
            this.f2669c.a(c0Var);
            if (G(2)) {
                Log.v("FragmentManager", "add from attach: " + c0Var);
            }
            if (H(c0Var)) {
                this.E = true;
            }
        }
    }

    public final void d() {
        this.f2668b = false;
        this.K.clear();
        this.J.clear();
    }

    public final HashSet e() {
        HashSet hashSet = new HashSet();
        Iterator it = this.f2669c.d().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = ((l1) it.next()).f2773c.mContainer;
            if (viewGroup != null) {
                hashSet.add(n.i(viewGroup, E()));
            }
        }
        return hashSet;
    }

    public final l1 f(c0 c0Var) {
        String str = c0Var.mWho;
        m1 m1Var = this.f2669c;
        l1 l1Var = (l1) ((HashMap) m1Var.f2784l).get(str);
        if (l1Var != null) {
            return l1Var;
        }
        l1 l1Var2 = new l1(this.f2678m, m1Var, c0Var);
        l1Var2.k(this.f2686u.f2780l.getClassLoader());
        l1Var2.f2775e = this.f2685t;
        return l1Var2;
    }

    public final void g(c0 c0Var) {
        if (G(2)) {
            Log.v("FragmentManager", "detach: " + c0Var);
        }
        if (c0Var.mDetached) {
            return;
        }
        c0Var.mDetached = true;
        if (c0Var.mAdded) {
            if (G(2)) {
                Log.v("FragmentManager", "remove from detach: " + c0Var);
            }
            m1 m1Var = this.f2669c;
            synchronized (((ArrayList) m1Var.f2783i)) {
                ((ArrayList) m1Var.f2783i).remove(c0Var);
            }
            c0Var.mAdded = false;
            if (H(c0Var)) {
                this.E = true;
            }
            X(c0Var);
        }
    }

    public final void h(boolean z, Configuration configuration) {
        if (z && (this.f2686u instanceof androidx.core.content.k)) {
            Z(new IllegalStateException("Do not call dispatchConfigurationChanged() on host. Host implements OnConfigurationChangedProvider and automatically dispatches configuration changes to fragments."));
            throw null;
        }
        for (c0 c0Var : this.f2669c.f()) {
            if (c0Var != null) {
                c0Var.performConfigurationChanged(configuration);
                if (z) {
                    c0Var.mChildFragmentManager.h(true, configuration);
                }
            }
        }
    }

    public final boolean i(MenuItem menuItem) {
        if (this.f2685t < 1) {
            return false;
        }
        for (c0 c0Var : this.f2669c.f()) {
            if (c0Var != null && c0Var.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public final boolean j(Menu menu, MenuInflater menuInflater) {
        if (this.f2685t < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z = false;
        for (c0 c0Var : this.f2669c.f()) {
            if (c0Var != null && c0Var.isMenuVisible() && c0Var.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(c0Var);
                z = true;
            }
        }
        if (this.f2671e != null) {
            for (int i10 = 0; i10 < this.f2671e.size(); i10++) {
                c0 c0Var2 = (c0) this.f2671e.get(i10);
                if (arrayList == null || !arrayList.contains(c0Var2)) {
                    c0Var2.onDestroyOptionsMenu();
                }
            }
        }
        this.f2671e = arrayList;
        return z;
    }

    public final void k() {
        boolean zIsChangingConfigurations = true;
        this.H = true;
        x(true);
        Iterator it = e().iterator();
        while (it.hasNext()) {
            ((n) it.next()).g();
        }
        m0 m0Var = this.f2686u;
        boolean z = m0Var instanceof androidx.lifecycle.j1;
        m1 m1Var = this.f2669c;
        if (z) {
            zIsChangingConfigurations = ((FragmentManagerViewModel) m1Var.f2786n).isCleared();
        } else {
            h0 h0Var = m0Var.f2780l;
            if (androidx.compose.material3.d.B(h0Var)) {
                zIsChangingConfigurations = true ^ h0Var.isChangingConfigurations();
            }
        }
        if (zIsChangingConfigurations) {
            Iterator it2 = this.f2676j.values().iterator();
            while (it2.hasNext()) {
                Iterator it3 = ((d) it2.next()).f2703i.iterator();
                while (it3.hasNext()) {
                    ((FragmentManagerViewModel) m1Var.f2786n).clearNonConfigState((String) it3.next());
                }
            }
        }
        t(-1);
        Object obj = this.f2686u;
        if (obj instanceof androidx.core.content.l) {
            ((androidx.core.content.l) obj).removeOnTrimMemoryListener(this.f2681p);
        }
        Object obj2 = this.f2686u;
        if (obj2 instanceof androidx.core.content.k) {
            ((androidx.core.content.k) obj2).removeOnConfigurationChangedListener(this.f2680o);
        }
        Object obj3 = this.f2686u;
        if (obj3 instanceof androidx.core.app.q1) {
            ((androidx.core.app.q1) obj3).removeOnMultiWindowModeChangedListener(this.f2682q);
        }
        Object obj4 = this.f2686u;
        if (obj4 instanceof androidx.core.app.s1) {
            ((androidx.core.app.s1) obj4).removeOnPictureInPictureModeChangedListener(this.f2683r);
        }
        Object obj5 = this.f2686u;
        if ((obj5 instanceof androidx.core.view.c0) && this.f2688w == null) {
            ((androidx.core.view.c0) obj5).removeMenuProvider(this.f2684s);
        }
        this.f2686u = null;
        this.f2687v = null;
        this.f2688w = null;
        if (this.f2673g != null) {
            this.f2674h.e();
            this.f2673g = null;
        }
        androidx.activity.result.j jVar = this.A;
        if (jVar != null) {
            jVar.b();
            this.B.b();
            this.C.b();
        }
    }

    public final void l(boolean z) {
        if (z && (this.f2686u instanceof androidx.core.content.l)) {
            Z(new IllegalStateException("Do not call dispatchLowMemory() on host. Host implements OnTrimMemoryProvider and automatically dispatches low memory callbacks to fragments."));
            throw null;
        }
        for (c0 c0Var : this.f2669c.f()) {
            if (c0Var != null) {
                c0Var.performLowMemory();
                if (z) {
                    c0Var.mChildFragmentManager.l(true);
                }
            }
        }
    }

    public final void m(boolean z, boolean z5) {
        if (z5 && (this.f2686u instanceof androidx.core.app.q1)) {
            Z(new IllegalStateException("Do not call dispatchMultiWindowModeChanged() on host. Host implements OnMultiWindowModeChangedProvider and automatically dispatches multi-window mode changes to fragments."));
            throw null;
        }
        for (c0 c0Var : this.f2669c.f()) {
            if (c0Var != null) {
                c0Var.performMultiWindowModeChanged(z);
                if (z5) {
                    c0Var.mChildFragmentManager.m(z, true);
                }
            }
        }
    }

    public final void n() {
        for (c0 c0Var : this.f2669c.e()) {
            if (c0Var != null) {
                c0Var.onHiddenChanged(c0Var.isHidden());
                c0Var.mChildFragmentManager.n();
            }
        }
    }

    public final boolean o(MenuItem menuItem) {
        if (this.f2685t < 1) {
            return false;
        }
        for (c0 c0Var : this.f2669c.f()) {
            if (c0Var != null && c0Var.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public final void p(Menu menu) {
        if (this.f2685t < 1) {
            return;
        }
        for (c0 c0Var : this.f2669c.f()) {
            if (c0Var != null) {
                c0Var.performOptionsMenuClosed(menu);
            }
        }
    }

    public final void q(c0 c0Var) {
        if (c0Var != null) {
            if (c0Var.equals(this.f2669c.b(c0Var.mWho))) {
                c0Var.performPrimaryNavigationFragmentChanged();
            }
        }
    }

    public final void r(boolean z, boolean z5) {
        if (z5 && (this.f2686u instanceof androidx.core.app.s1)) {
            Z(new IllegalStateException("Do not call dispatchPictureInPictureModeChanged() on host. Host implements OnPictureInPictureModeChangedProvider and automatically dispatches picture-in-picture mode changes to fragments."));
            throw null;
        }
        for (c0 c0Var : this.f2669c.f()) {
            if (c0Var != null) {
                c0Var.performPictureInPictureModeChanged(z);
                if (z5) {
                    c0Var.mChildFragmentManager.r(z, true);
                }
            }
        }
    }

    public final boolean s(Menu menu) {
        boolean z = false;
        if (this.f2685t < 1) {
            return false;
        }
        for (c0 c0Var : this.f2669c.f()) {
            if (c0Var != null && c0Var.isMenuVisible() && c0Var.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public final void t(int i10) {
        try {
            this.f2668b = true;
            for (l1 l1Var : ((HashMap) this.f2669c.f2784l).values()) {
                if (l1Var != null) {
                    l1Var.f2775e = i10;
                }
            }
            K(i10, false);
            Iterator it = e().iterator();
            while (it.hasNext()) {
                ((n) it.next()).g();
            }
            this.f2668b = false;
            x(true);
        } catch (Throwable th) {
            this.f2668b = false;
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(128);
        sb2.append("FragmentManager{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append(" in ");
        c0 c0Var = this.f2688w;
        if (c0Var != null) {
            sb2.append(c0Var.getClass().getSimpleName());
            sb2.append("{");
            sb2.append(Integer.toHexString(System.identityHashCode(this.f2688w)));
            sb2.append("}");
        } else {
            m0 m0Var = this.f2686u;
            if (m0Var != null) {
                sb2.append(m0Var.getClass().getSimpleName());
                sb2.append("{");
                sb2.append(Integer.toHexString(System.identityHashCode(this.f2686u)));
                sb2.append("}");
            } else {
                sb2.append("null");
            }
        }
        sb2.append("}}");
        return sb2.toString();
    }

    public final void u(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String strM = androidx.compose.material3.d.m(str, "    ");
        m1 m1Var = this.f2669c;
        ArrayList arrayList = (ArrayList) m1Var.f2783i;
        String strM2 = androidx.compose.material3.d.m(str, "    ");
        HashMap map = (HashMap) m1Var.f2784l;
        if (!map.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (l1 l1Var : map.values()) {
                printWriter.print(str);
                if (l1Var != null) {
                    c0 c0Var = l1Var.f2773c;
                    printWriter.println(c0Var);
                    c0Var.dump(strM2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size3 = arrayList.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i10 = 0; i10 < size3; i10++) {
                c0 c0Var2 = (c0) arrayList.get(i10);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i10);
                printWriter.print(": ");
                printWriter.println(c0Var2.toString());
            }
        }
        ArrayList arrayList2 = this.f2671e;
        if (arrayList2 != null && (size2 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i11 = 0; i11 < size2; i11++) {
                c0 c0Var3 = (c0) this.f2671e.get(i11);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i11);
                printWriter.print(": ");
                printWriter.println(c0Var3.toString());
            }
        }
        ArrayList arrayList3 = this.f2670d;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i12 = 0; i12 < size; i12++) {
                a aVar = (a) this.f2670d.get(i12);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i12);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.f(strM, printWriter, true);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.f2675i.get());
        synchronized (this.f2667a) {
            try {
                int size4 = this.f2667a.size();
                if (size4 > 0) {
                    printWriter.print(str);
                    printWriter.println("Pending Actions:");
                    for (int i13 = 0; i13 < size4; i13++) {
                        Object obj = (z0) this.f2667a.get(i13);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i13);
                        printWriter.print(": ");
                        printWriter.println(obj);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f2686u);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f2687v);
        if (this.f2688w != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f2688w);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f2685t);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.F);
        printWriter.print(" mStopped=");
        printWriter.print(this.G);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.H);
        if (this.E) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.E);
        }
    }

    public final void v(z0 z0Var, boolean z) {
        if (!z) {
            if (this.f2686u == null) {
                if (!this.H) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            if (this.F || this.G) {
                throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
            }
        }
        synchronized (this.f2667a) {
            try {
                if (this.f2686u == null) {
                    if (!z) {
                        throw new IllegalStateException("Activity has been destroyed");
                    }
                } else {
                    this.f2667a.add(z0Var);
                    T();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void w(boolean z) {
        if (this.f2668b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.f2686u == null) {
            if (!this.H) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.f2686u.f2781m.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z && (this.F || this.G)) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.J == null) {
            this.J = new ArrayList();
            this.K = new ArrayList();
        }
    }

    public final boolean x(boolean z) {
        boolean zA;
        w(z);
        boolean z5 = false;
        while (true) {
            ArrayList arrayList = this.J;
            ArrayList arrayList2 = this.K;
            synchronized (this.f2667a) {
                if (this.f2667a.isEmpty()) {
                    zA = false;
                } else {
                    try {
                        int size = this.f2667a.size();
                        zA = false;
                        for (int i10 = 0; i10 < size; i10++) {
                            zA |= ((z0) this.f2667a.get(i10)).a(arrayList, arrayList2);
                        }
                    } finally {
                    }
                }
            }
            if (!zA) {
                break;
            }
            this.f2668b = true;
            try {
                Q(this.J, this.K);
                d();
                z5 = true;
            } catch (Throwable th) {
                d();
                throw th;
            }
        }
        a0();
        if (this.I) {
            this.I = false;
            for (l1 l1Var : this.f2669c.d()) {
                c0 c0Var = l1Var.f2773c;
                if (c0Var.mDeferStart) {
                    if (this.f2668b) {
                        this.I = true;
                    } else {
                        c0Var.mDeferStart = false;
                        l1Var.j();
                    }
                }
            }
        }
        ((HashMap) this.f2669c.f2784l).values().removeAll(Collections.singleton(null));
        return z5;
    }

    public final void y(a aVar, boolean z) {
        if (z && (this.f2686u == null || this.H)) {
            return;
        }
        w(z);
        aVar.a(this.J, this.K);
        this.f2668b = true;
        try {
            Q(this.J, this.K);
            d();
            a0();
            boolean z5 = this.I;
            m1 m1Var = this.f2669c;
            if (z5) {
                this.I = false;
                for (l1 l1Var : m1Var.d()) {
                    c0 c0Var = l1Var.f2773c;
                    if (c0Var.mDeferStart) {
                        if (this.f2668b) {
                            this.I = true;
                        } else {
                            c0Var.mDeferStart = false;
                            l1Var.j();
                        }
                    }
                }
            }
            ((HashMap) m1Var.f2784l).values().removeAll(Collections.singleton(null));
        } catch (Throwable th) {
            d();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0227 A[PHI: r14
      0x0227: PHI (r14v14 int) = (r14v13 int), (r14v15 int) binds: [B:100:0x0217, B:105:0x0223] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x017f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void z(java.util.ArrayList r24, java.util.ArrayList r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 1188
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.b1.z(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }
}
