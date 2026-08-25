package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.appcompat.app.i1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import na.j1;

/* JADX INFO: loaded from: classes3.dex */
public abstract class s {
    public int A;
    public final ArrayList B;
    public final na.x0 C;
    public final na.r0 D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4760a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Activity f4761b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public h0 f4762c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Bundle f4763d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Parcelable[] f4764e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4765f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final kotlin.collections.p f4766g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final j1 f4767h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final j1 f4768i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final na.s0 f4769j;
    public final LinkedHashMap k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final LinkedHashMap f4770l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final LinkedHashMap f4771m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final LinkedHashMap f4772n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public androidx.lifecycle.y f4773o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public NavControllerViewModel f4774p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final CopyOnWriteArrayList f4775q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public androidx.lifecycle.q f4776r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final androidx.lifecycle.m f4777s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final androidx.activity.m0 f4778t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f4779u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final b1 f4780v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final LinkedHashMap f4781w;
    public kotlin.jvm.internal.r x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public m f4782y;
    public final LinkedHashMap z;

    public s(Context context) {
        Object next;
        this.f4760a = context;
        Iterator it = ga.r.M(context, b.f4522m).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((Context) next) instanceof Activity) {
                    break;
                }
            }
        }
        this.f4761b = (Activity) next;
        this.f4766g = new kotlin.collections.p();
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        this.f4767h = na.y0.b(zVar);
        j1 j1VarB = na.y0.b(zVar);
        this.f4768i = j1VarB;
        this.f4769j = na.y0.e(j1VarB);
        this.k = new LinkedHashMap();
        this.f4770l = new LinkedHashMap();
        this.f4771m = new LinkedHashMap();
        this.f4772n = new LinkedHashMap();
        this.f4775q = new CopyOnWriteArrayList();
        this.f4776r = androidx.lifecycle.q.f3267l;
        this.f4777s = new androidx.lifecycle.m(this, 1);
        this.f4778t = new androidx.activity.m0(this, 2);
        this.f4779u = true;
        b1 b1Var = new b1();
        this.f4780v = b1Var;
        this.f4781w = new LinkedHashMap();
        this.z = new LinkedHashMap();
        b1Var.a(new j0(b1Var));
        b1Var.a(new c(this.f4760a));
        this.B = new ArrayList();
        na.x0 x0VarA = na.y0.a(0, 2);
        this.C = x0VarA;
        this.D = new na.r0(x0VarA);
    }

    public static void j(s sVar, String str, n0 n0Var, int i10) {
        Object obj = null;
        if ((i10 & 2) != 0) {
            n0Var = null;
        }
        sVar.getClass();
        int i11 = d0.f4656r;
        Uri uri = Uri.parse("android-app://androidx.navigation/".concat(str));
        i1 i1Var = new i1(uri, obj, obj, 6);
        h0 h0Var = sVar.f4762c;
        if (h0Var == null) {
            throw new IllegalArgumentException(("Cannot navigate to " + i1Var + ". Navigation graph has not been set for NavController " + sVar + '.').toString());
        }
        c0 c0VarC = h0Var.c(i1Var);
        if (c0VarC == null) {
            throw new IllegalArgumentException("Navigation destination that matches request " + i1Var + " cannot be found in the navigation graph " + sVar.f4762c);
        }
        d0 d0Var = c0VarC.f4531i;
        Bundle bundleB = d0Var.b(c0VarC.f4532l);
        if (bundleB == null) {
            bundleB = new Bundle();
        }
        Intent intent = new Intent();
        intent.setDataAndType(uri, null);
        intent.setAction(null);
        bundleB.putParcelable("android-support-nav:controller:deepLinkIntent", intent);
        sVar.h(d0Var, bundleB, n0Var);
    }

    public static /* synthetic */ void n(s sVar, i iVar) {
        sVar.m(iVar, false, new kotlin.collections.p());
    }

    public final void a(d0 d0Var, Bundle bundle, i iVar, List list) {
        Object objPrevious;
        Object objPrevious2;
        d0 d0Var2 = iVar.f4681l;
        boolean z = d0Var2 instanceof d;
        boolean z5 = true;
        kotlin.collections.p pVar = this.f4766g;
        if (!z) {
            while (!pVar.isEmpty() && (((i) pVar.last()).f4681l instanceof d) && l(((i) pVar.last()).f4681l.f4662p, true, false)) {
            }
        }
        kotlin.collections.p<i> pVar2 = new kotlin.collections.p();
        boolean z10 = d0Var instanceof h0;
        Context context = this.f4760a;
        Object obj = null;
        if (z10) {
            d0 d0Var3 = d0Var2;
            do {
                d0Var3 = d0Var3.f4658l;
                if (d0Var3 != null) {
                    ListIterator listIterator = list.listIterator(list.size());
                    while (true) {
                        if (listIterator.hasPrevious()) {
                            objPrevious2 = listIterator.previous();
                            if (kotlin.jvm.internal.p.a(((i) objPrevious2).f4681l, d0Var3)) {
                                break;
                            }
                        } else {
                            objPrevious2 = null;
                            break;
                        }
                    }
                    i iVarJ = (i) objPrevious2;
                    if (iVarJ == null) {
                        iVarJ = a1.a.j(context, d0Var3, bundle, f(), this.f4774p);
                    }
                    pVar2.addFirst(iVarJ);
                    if (!pVar.isEmpty() && ((i) pVar.last()).f4681l == d0Var3) {
                        n(this, (i) pVar.last());
                    }
                }
                if (d0Var3 == null) {
                    break;
                }
            } while (d0Var3 != d0Var);
        }
        d0 d0Var4 = pVar2.isEmpty() ? d0Var2 : ((i) pVar2.first()).f4681l;
        while (d0Var4 != null && d(d0Var4.f4662p) != d0Var4) {
            d0Var4 = d0Var4.f4658l;
            if (d0Var4 != null) {
                Bundle bundle2 = (bundle == null || bundle.isEmpty() != z5) ? bundle : null;
                ListIterator listIterator2 = list.listIterator(list.size());
                while (true) {
                    if (listIterator2.hasPrevious()) {
                        objPrevious = listIterator2.previous();
                        if (kotlin.jvm.internal.p.a(((i) objPrevious).f4681l, d0Var4)) {
                            break;
                        }
                    } else {
                        objPrevious = null;
                        break;
                    }
                }
                i iVarJ2 = (i) objPrevious;
                if (iVarJ2 == null) {
                    iVarJ2 = a1.a.j(context, d0Var4, d0Var4.b(bundle2), f(), this.f4774p);
                }
                pVar2.addFirst(iVarJ2);
            }
            z5 = true;
        }
        if (!pVar2.isEmpty()) {
            d0Var2 = ((i) pVar2.first()).f4681l;
        }
        while (!pVar.isEmpty() && (((i) pVar.last()).f4681l instanceof h0) && ((h0) ((i) pVar.last()).f4681l).f(d0Var2.f4662p, false) == null) {
            n(this, (i) pVar.last());
        }
        i iVar2 = (i) pVar.c();
        if (iVar2 == null) {
            iVar2 = (i) pVar2.c();
        }
        if (!kotlin.jvm.internal.p.a(iVar2 != null ? iVar2.f4681l : null, this.f4762c)) {
            ListIterator listIterator3 = list.listIterator(list.size());
            while (true) {
                if (!listIterator3.hasPrevious()) {
                    break;
                }
                Object objPrevious3 = listIterator3.previous();
                if (kotlin.jvm.internal.p.a(((i) objPrevious3).f4681l, this.f4762c)) {
                    obj = objPrevious3;
                    break;
                }
            }
            i iVarJ3 = (i) obj;
            if (iVarJ3 == null) {
                h0 h0Var = this.f4762c;
                iVarJ3 = a1.a.j(context, h0Var, h0Var.b(bundle), f(), this.f4774p);
            }
            pVar2.addFirst(iVarJ3);
        }
        for (i iVar3 : pVar2) {
            Object obj2 = this.f4781w.get(this.f4780v.b(iVar3.f4681l.f4657i));
            if (obj2 == null) {
                throw new IllegalStateException(a0.c.p(new StringBuilder("NavigatorBackStack for "), d0Var.f4657i, " should already be created").toString());
            }
            ((k) obj2).a(iVar3);
        }
        pVar.addAll(pVar2);
        pVar.addLast(iVar);
        for (i iVar4 : kotlin.collections.x.H0(iVar, pVar2)) {
            h0 h0Var2 = iVar4.f4681l.f4658l;
            if (h0Var2 != null) {
                g(iVar4, e(h0Var2.f4662p));
            }
        }
    }

    public final boolean b() {
        kotlin.collections.p pVar;
        while (true) {
            pVar = this.f4766g;
            if (pVar.isEmpty() || !(((i) pVar.last()).f4681l instanceof h0)) {
                break;
            }
            n(this, (i) pVar.last());
        }
        i iVar = (i) pVar.e();
        ArrayList arrayList = this.B;
        if (iVar != null) {
            arrayList.add(iVar);
        }
        this.A++;
        r();
        int i10 = this.A - 1;
        this.A = i10;
        if (i10 == 0) {
            ArrayList<i> arrayList2 = new ArrayList(arrayList);
            arrayList.clear();
            for (i iVar2 : arrayList2) {
                for (l lVar : this.f4775q) {
                    d0 d0Var = iVar2.f4681l;
                    iVar2.a();
                    lVar.a();
                }
                this.C.b(iVar2);
            }
            ArrayList arrayList3 = new ArrayList(pVar);
            j1 j1Var = this.f4767h;
            j1Var.getClass();
            j1Var.h(null, arrayList3);
            ArrayList arrayListO = o();
            j1 j1Var2 = this.f4768i;
            j1Var2.getClass();
            j1Var2.h(null, arrayListO);
        }
        return iVar != null;
    }

    public final boolean c(ArrayList arrayList, d0 d0Var, boolean z, boolean z5) {
        s sVar;
        boolean z10;
        kotlin.jvm.internal.f0 f0Var = new kotlin.jvm.internal.f0();
        kotlin.collections.p pVar = new kotlin.collections.p();
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                sVar = this;
                z10 = z5;
                break;
            }
            a1 a1Var = (a1) it.next();
            kotlin.jvm.internal.f0 f0Var2 = new kotlin.jvm.internal.f0();
            i iVar = (i) this.f4766g.last();
            sVar = this;
            z10 = z5;
            sVar.f4782y = new m(f0Var2, f0Var, sVar, z10, pVar);
            a1Var.e(iVar, z10);
            sVar.f4782y = null;
            if (!f0Var2.f19738i) {
                break;
            }
            z5 = z10;
        }
        if (z10) {
            LinkedHashMap linkedHashMap = sVar.f4771m;
            if (!z) {
                ga.h hVar = new ga.h(new ga.c(ga.r.M(d0Var, b.f4523n), new n(this, 0), 1));
                while (hVar.hasNext()) {
                    Integer numValueOf = Integer.valueOf(((d0) hVar.next()).f4662p);
                    j jVar = (j) pVar.c();
                    linkedHashMap.put(numValueOf, jVar != null ? jVar.f4701i : null);
                }
            }
            if (!pVar.isEmpty()) {
                j jVar2 = (j) pVar.first();
                int i10 = jVar2.f4702l;
                String str = jVar2.f4701i;
                ga.h hVar2 = new ga.h(new ga.c(ga.r.M(d(i10), b.f4524o), new n(this, 1), 1));
                while (hVar2.hasNext()) {
                    linkedHashMap.put(Integer.valueOf(((d0) hVar2.next()).f4662p), str);
                }
                sVar.f4772n.put(str, pVar);
            }
        }
        s();
        return f0Var.f19738i;
    }

    public final d0 d(int i10) {
        d0 d0Var;
        h0 h0Var = this.f4762c;
        if (h0Var == null) {
            return null;
        }
        if (h0Var.f4662p == i10) {
            return h0Var;
        }
        i iVar = (i) this.f4766g.e();
        if (iVar == null || (d0Var = iVar.f4681l) == null) {
            d0Var = this.f4762c;
        }
        if (d0Var.f4662p == i10) {
            return d0Var;
        }
        return (d0Var instanceof h0 ? (h0) d0Var : d0Var.f4658l).f(i10, true);
    }

    public final i e(int i10) {
        Object objPrevious;
        kotlin.collections.p pVar = this.f4766g;
        ListIterator listIterator = pVar.listIterator(pVar.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                objPrevious = null;
                break;
            }
            objPrevious = listIterator.previous();
            if (((i) objPrevious).f4681l.f4662p == i10) {
                break;
            }
        }
        i iVar = (i) objPrevious;
        if (iVar != null) {
            return iVar;
        }
        StringBuilder sbS = a0.c.s(i10, "No destination with ID ", " is on the NavController's back stack. The current destination is ");
        i iVar2 = (i) pVar.e();
        sbS.append(iVar2 != null ? iVar2.f4681l : null);
        throw new IllegalArgumentException(sbS.toString().toString());
    }

    public final androidx.lifecycle.q f() {
        return this.f4773o == null ? androidx.lifecycle.q.f3268m : this.f4776r;
    }

    public final void g(i iVar, i iVar2) {
        this.k.put(iVar, iVar2);
        LinkedHashMap linkedHashMap = this.f4770l;
        if (linkedHashMap.get(iVar2) == null) {
            linkedHashMap.put(iVar2, new AtomicInteger(0));
        }
        ((AtomicInteger) linkedHashMap.get(iVar2)).incrementAndGet();
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x02f0 A[LOOP:1: B:138:0x02ea->B:140:0x02f0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0104 A[EDGE_INSN: B:171:0x0104->B:60:0x0104 BREAK  A[LOOP:7: B:15:0x004d->B:58:0x00f9], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0082 A[PHI: r15
      0x0082: PHI (r15v13 java.util.ListIterator) = 
      (r15v3 java.util.ListIterator)
      (r15v3 java.util.ListIterator)
      (r15v3 java.util.ListIterator)
      (r15v4 java.util.ListIterator)
     binds: [B:26:0x0080, B:30:0x0089, B:31:0x008b, B:173:0x0082] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f9 A[LOOP:7: B:15:0x004d->B:58:0x00f9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x016c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h(androidx.navigation.d0 r30, android.os.Bundle r31, androidx.navigation.n0 r32) {
        /*
            Method dump skipped, instruction units count: 779
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.s.h(androidx.navigation.d0, android.os.Bundle, androidx.navigation.n0):void");
    }

    public final void i(String str, r7.l lVar) {
        j(this, str, kotlin.reflect.b0.x(lVar), 4);
    }

    public final void k() {
        kotlin.collections.p pVar = this.f4766g;
        if (pVar.isEmpty()) {
            return;
        }
        i iVar = (i) pVar.e();
        if (l((iVar != null ? iVar.f4681l : null).f4662p, true, false)) {
            b();
        }
    }

    public final boolean l(int i10, boolean z, boolean z5) {
        d0 d0Var;
        kotlin.collections.p pVar = this.f4766g;
        if (pVar.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = kotlin.collections.x.P0(pVar).iterator();
        while (true) {
            if (!it.hasNext()) {
                d0Var = null;
                break;
            }
            d0Var = ((i) it.next()).f4681l;
            a1 a1VarB = this.f4780v.b(d0Var.f4657i);
            if (z || d0Var.f4662p != i10) {
                arrayList.add(a1VarB);
            }
            if (d0Var.f4662p == i10) {
                break;
            }
        }
        if (d0Var != null) {
            return c(arrayList, d0Var, z, z5);
        }
        int i11 = d0.f4656r;
        Log.i("NavController", "Ignoring popBackStack to destination " + k2.c.q(this.f4760a, i10) + " as it was not found on the current back stack");
        return false;
    }

    public final void m(i iVar, boolean z, kotlin.collections.p pVar) {
        NavControllerViewModel navControllerViewModel;
        na.s0 s0Var;
        Set set;
        kotlin.collections.p pVar2 = this.f4766g;
        i iVar2 = (i) pVar2.last();
        if (!kotlin.jvm.internal.p.a(iVar2, iVar)) {
            throw new IllegalStateException(("Attempted to pop " + iVar.f4681l + ", which is not the top of the back stack (" + iVar2.f4681l + ')').toString());
        }
        pVar2.removeLast();
        k kVar = (k) this.f4781w.get(this.f4780v.b(iVar2.f4681l.f4657i));
        boolean z5 = true;
        if ((kVar == null || (s0Var = kVar.f4711f) == null || (set = (Set) s0Var.f20648i.getValue()) == null || !set.contains(iVar2)) && !this.f4770l.containsKey(iVar2)) {
            z5 = false;
        }
        androidx.lifecycle.q qVar = iVar2.f4687r.f3198c;
        androidx.lifecycle.q qVar2 = androidx.lifecycle.q.f3268m;
        if (qVar.a(qVar2)) {
            if (z) {
                iVar2.b(qVar2);
                pVar.addFirst(new j(iVar2));
            }
            if (z5) {
                iVar2.b(qVar2);
            } else {
                iVar2.b(androidx.lifecycle.q.f3266i);
                q(iVar2);
            }
        }
        if (z || z5 || (navControllerViewModel = this.f4774p) == null) {
            return;
        }
        navControllerViewModel.clear(iVar2.f4685p);
    }

    public final ArrayList o() {
        androidx.lifecycle.q qVar;
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f4781w.values().iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            qVar = androidx.lifecycle.q.f3269n;
            if (!zHasNext) {
                break;
            }
            Iterable iterable = (Iterable) ((k) it.next()).f4711f.f20648i.getValue();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : iterable) {
                i iVar = (i) obj;
                if (!arrayList.contains(iVar) && !iVar.f4690u.a(qVar)) {
                    arrayList2.add(obj);
                }
            }
            kotlin.collections.x.b0(arrayList, arrayList2);
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : this.f4766g) {
            i iVar2 = (i) obj2;
            if (!arrayList.contains(iVar2) && iVar2.f4690u.a(qVar)) {
                arrayList3.add(obj2);
            }
        }
        kotlin.collections.x.b0(arrayList, arrayList3);
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : arrayList) {
            if (!(((i) obj3).f4681l instanceof h0)) {
                arrayList4.add(obj3);
            }
        }
        return arrayList4;
    }

    public final boolean p(int i10, Bundle bundle, n0 n0Var) {
        d0 d0Var;
        i iVar;
        d0 d0Var2;
        Bundle bundle2;
        Integer numValueOf = Integer.valueOf(i10);
        LinkedHashMap linkedHashMap = this.f4771m;
        int i11 = 0;
        if (!linkedHashMap.containsKey(numValueOf)) {
            return false;
        }
        String str = (String) linkedHashMap.get(Integer.valueOf(i10));
        kotlin.collections.x.j0(linkedHashMap.values(), new r(str, i11), true);
        kotlin.collections.p<j> pVar = (kotlin.collections.p) kotlin.jvm.internal.q0.c(this.f4772n).remove(str);
        ArrayList arrayList = new ArrayList();
        i iVar2 = (i) this.f4766g.e();
        if ((iVar2 == null || (d0Var = iVar2.f4681l) == null) && (d0Var = this.f4762c) == null) {
            throw new IllegalStateException("You must call setGraph() before calling getGraph()");
        }
        if (pVar != null) {
            for (j jVar : pVar) {
                int i12 = jVar.f4702l;
                d0 d0VarF = d0Var.f4662p == i12 ? d0Var : (d0Var instanceof h0 ? (h0) d0Var : d0Var.f4658l).f(i12, true);
                Context context = this.f4760a;
                if (d0VarF == null) {
                    int i13 = d0.f4656r;
                    throw new IllegalStateException(("Restore State failed: destination " + k2.c.q(context, jVar.f4702l) + " cannot be found from the current destination " + d0Var).toString());
                }
                androidx.lifecycle.q qVarF = f();
                NavControllerViewModel navControllerViewModel = this.f4774p;
                Bundle bundle3 = jVar.f4703m;
                if (bundle3 != null) {
                    bundle3.setClassLoader(context.getClassLoader());
                    bundle2 = bundle3;
                } else {
                    bundle2 = null;
                }
                arrayList.add(new i(context, d0VarF, bundle2, qVarF, navControllerViewModel, jVar.f4701i, jVar.f4704n));
                d0Var = d0VarF;
            }
        }
        ArrayList<List> arrayList2 = new ArrayList();
        ArrayList<i> arrayList3 = new ArrayList();
        for (Object obj : arrayList) {
            if (!(((i) obj).f4681l instanceof h0)) {
                arrayList3.add(obj);
            }
        }
        for (i iVar3 : arrayList3) {
            List list = (List) kotlin.collections.x.y0(arrayList2);
            if (kotlin.jvm.internal.p.a((list == null || (iVar = (i) kotlin.collections.x.w0(list)) == null || (d0Var2 = iVar.f4681l) == null) ? null : d0Var2.f4657i, iVar3.f4681l.f4657i)) {
                list.add(iVar3);
            } else {
                arrayList2.add(t7.a.G(iVar3));
            }
        }
        kotlin.jvm.internal.f0 f0Var = new kotlin.jvm.internal.f0();
        for (List list2 : arrayList2) {
            a1 a1VarB = this.f4780v.b(((i) kotlin.collections.x.m0(list2)).f4681l.f4657i);
            this.x = new o(f0Var, arrayList, new kotlin.jvm.internal.i0(), this, bundle, 0);
            a1VarB.d(list2, n0Var);
            this.x = null;
        }
        return f0Var.f19738i;
    }

    public final void q(i iVar) {
        i iVar2 = (i) this.k.remove(iVar);
        if (iVar2 == null) {
            return;
        }
        LinkedHashMap linkedHashMap = this.f4770l;
        AtomicInteger atomicInteger = (AtomicInteger) linkedHashMap.get(iVar2);
        Integer numValueOf = atomicInteger != null ? Integer.valueOf(atomicInteger.decrementAndGet()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            k kVar = (k) this.f4781w.get(this.f4780v.b(iVar2.f4681l.f4657i));
            if (kVar != null) {
                kVar.b(iVar2);
            }
            linkedHashMap.remove(iVar2);
        }
    }

    public final void r() {
        AtomicInteger atomicInteger;
        na.s0 s0Var;
        Set set;
        ArrayList<i> arrayList = new ArrayList(this.f4766g);
        if (arrayList.isEmpty()) {
            return;
        }
        d0 d0Var = ((i) kotlin.collections.x.w0(arrayList)).f4681l;
        ArrayList arrayList2 = new ArrayList();
        if (d0Var instanceof d) {
            Iterator it = kotlin.collections.x.P0(arrayList).iterator();
            while (it.hasNext()) {
                d0 d0Var2 = ((i) it.next()).f4681l;
                arrayList2.add(d0Var2);
                if (!(d0Var2 instanceof d) && !(d0Var2 instanceof h0)) {
                    break;
                }
            }
        }
        HashMap map = new HashMap();
        for (i iVar : kotlin.collections.x.P0(arrayList)) {
            androidx.lifecycle.q qVar = iVar.f4690u;
            d0 d0Var3 = iVar.f4681l;
            androidx.lifecycle.q qVar2 = androidx.lifecycle.q.f3270o;
            androidx.lifecycle.q qVar3 = androidx.lifecycle.q.f3269n;
            if (d0Var != null && d0Var3.f4662p == d0Var.f4662p) {
                if (qVar != qVar2) {
                    k kVar = (k) this.f4781w.get(this.f4780v.b(d0Var3.f4657i));
                    if (kotlin.jvm.internal.p.a((kVar == null || (s0Var = kVar.f4711f) == null || (set = (Set) s0Var.f20648i.getValue()) == null) ? null : Boolean.valueOf(set.contains(iVar)), Boolean.TRUE) || ((atomicInteger = (AtomicInteger) this.f4770l.get(iVar)) != null && atomicInteger.get() == 0)) {
                        map.put(iVar, qVar3);
                    } else {
                        map.put(iVar, qVar2);
                    }
                }
                d0 d0Var4 = (d0) kotlin.collections.x.o0(arrayList2);
                if (d0Var4 != null && d0Var4.f4662p == d0Var3.f4662p) {
                    kotlin.collections.x.N0(arrayList2);
                }
                d0Var = d0Var.f4658l;
            } else if (arrayList2.isEmpty() || d0Var3.f4662p != ((d0) kotlin.collections.x.m0(arrayList2)).f4662p) {
                iVar.b(androidx.lifecycle.q.f3268m);
            } else {
                d0 d0Var5 = (d0) kotlin.collections.x.N0(arrayList2);
                if (qVar == qVar2) {
                    iVar.b(qVar3);
                } else if (qVar != qVar3) {
                    map.put(iVar, qVar3);
                }
                h0 h0Var = d0Var5.f4658l;
                if (h0Var != null && !arrayList2.contains(h0Var)) {
                    arrayList2.add(h0Var);
                }
            }
        }
        for (i iVar2 : arrayList) {
            androidx.lifecycle.q qVar4 = (androidx.lifecycle.q) map.get(iVar2);
            if (qVar4 != null) {
                iVar2.b(qVar4);
            } else {
                iVar2.c();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.internal.m, r7.a] */
    public final void s() {
        int i10;
        boolean z = false;
        if (this.f4779u) {
            kotlin.collections.p pVar = this.f4766g;
            if (pVar == null || !pVar.isEmpty()) {
                Iterator it = pVar.iterator();
                i10 = 0;
                while (it.hasNext()) {
                    if (!(((i) it.next()).f4681l instanceof h0) && (i10 = i10 + 1) < 0) {
                        t7.a.P();
                        throw null;
                    }
                }
            } else {
                i10 = 0;
            }
            if (i10 > 1) {
                z = true;
            }
        }
        androidx.activity.m0 m0Var = this.f4778t;
        m0Var.f931a = z;
        ?? r02 = m0Var.f933c;
        if (r02 != 0) {
            r02.invoke();
        }
    }
}
