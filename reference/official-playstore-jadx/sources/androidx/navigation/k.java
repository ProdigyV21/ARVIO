package androidx.navigation;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import na.h1;
import na.j1;

/* JADX INFO: loaded from: classes3.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReentrantLock f4706a = new ReentrantLock(true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j1 f4707b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j1 f4708c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4709d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final na.s0 f4710e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final na.s0 f4711f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a1 f4712g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ s f4713h;

    public k(l0 l0Var, a1 a1Var) {
        this.f4713h = l0Var;
        j1 j1VarB = na.y0.b(kotlin.collections.z.f19728i);
        this.f4707b = j1VarB;
        j1 j1VarB2 = na.y0.b(kotlin.collections.b0.f19686i);
        this.f4708c = j1VarB2;
        this.f4710e = na.y0.e(j1VarB);
        this.f4711f = na.y0.e(j1VarB2);
        this.f4712g = a1Var;
    }

    public final void a(i iVar) {
        ReentrantLock reentrantLock = this.f4706a;
        reentrantLock.lock();
        try {
            j1 j1Var = this.f4707b;
            ArrayList arrayListH0 = kotlin.collections.x.H0(iVar, (Collection) j1Var.getValue());
            j1Var.getClass();
            j1Var.h(null, arrayListH0);
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void b(i iVar) {
        NavControllerViewModel navControllerViewModel;
        String str = iVar.f4685p;
        s sVar = this.f4713h;
        LinkedHashMap linkedHashMap = sVar.z;
        j1 j1Var = sVar.f4768i;
        boolean zA = kotlin.jvm.internal.p.a(linkedHashMap.get(iVar), Boolean.TRUE);
        j1 j1Var2 = this.f4708c;
        j1Var2.h(null, kotlin.collections.q0.Z((Set) j1Var2.getValue(), iVar));
        sVar.z.remove(iVar);
        kotlin.collections.p pVar = sVar.f4766g;
        if (pVar.contains(iVar)) {
            if (this.f4709d) {
                return;
            }
            sVar.r();
            j1 j1Var3 = sVar.f4767h;
            ArrayList arrayList = new ArrayList(pVar);
            j1Var3.getClass();
            j1Var3.h(null, arrayList);
            ArrayList arrayListO = sVar.o();
            j1Var.getClass();
            j1Var.h(null, arrayListO);
            return;
        }
        sVar.q(iVar);
        if (iVar.f4687r.f3198c.a(androidx.lifecycle.q.f3268m)) {
            iVar.b(androidx.lifecycle.q.f3266i);
        }
        if (pVar == null || !pVar.isEmpty()) {
            Iterator it = pVar.iterator();
            while (it.hasNext()) {
                if (kotlin.jvm.internal.p.a(((i) it.next()).f4685p, str)) {
                    break;
                }
            }
            if (!zA && (navControllerViewModel = sVar.f4774p) != null) {
                navControllerViewModel.clear(str);
            }
        } else if (!zA) {
            navControllerViewModel.clear(str);
        }
        sVar.r();
        ArrayList arrayListO2 = sVar.o();
        j1Var.getClass();
        j1Var.h(null, arrayListO2);
    }

    public final void c(i iVar, boolean z) {
        s sVar = this.f4713h;
        a1 a1VarB = sVar.f4780v.b(iVar.f4681l.f4657i);
        if (!a1VarB.equals(this.f4712g)) {
            ((k) sVar.f4781w.get(a1VarB)).c(iVar, z);
            return;
        }
        m mVar = sVar.f4782y;
        if (mVar != null) {
            mVar.invoke(iVar);
            d(iVar);
            return;
        }
        a8.l0 l0Var = new a8.l0(this, iVar, z);
        kotlin.collections.p pVar = sVar.f4766g;
        int iIndexOf = pVar.indexOf(iVar);
        if (iIndexOf < 0) {
            Log.i("NavController", "Ignoring pop of " + iVar + " as it was not found on the current back stack");
            return;
        }
        int i10 = iIndexOf + 1;
        if (i10 != pVar.size()) {
            sVar.l(((i) pVar.get(i10)).f4681l.f4662p, true, false);
        }
        s.n(sVar, iVar);
        l0Var.invoke();
        sVar.s();
        sVar.b();
    }

    public final void d(i iVar) {
        ReentrantLock reentrantLock = this.f4706a;
        reentrantLock.lock();
        try {
            j1 j1Var = this.f4707b;
            Iterable iterable = (Iterable) j1Var.getValue();
            ArrayList arrayList = new ArrayList();
            for (Object obj : iterable) {
                if (kotlin.jvm.internal.p.a((i) obj, iVar)) {
                    break;
                } else {
                    arrayList.add(obj);
                }
            }
            j1Var.getClass();
            j1Var.h(null, arrayList);
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void e(i iVar, boolean z) {
        Object objPrevious;
        j1 j1Var = this.f4708c;
        Iterable iterable = (Iterable) j1Var.getValue();
        boolean z5 = iterable instanceof Collection;
        na.s0 s0Var = this.f4710e;
        if (z5 && ((Collection) iterable).isEmpty()) {
            j1Var.h(null, kotlin.collections.q0.c0((Set) j1Var.getValue(), iVar));
            h1 h1Var = s0Var.f20648i;
            h1 h1Var2 = s0Var.f20648i;
            List list = (List) h1Var.getValue();
            ListIterator listIterator = list.listIterator(list.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    objPrevious = null;
                    break;
                }
                objPrevious = listIterator.previous();
                i iVar2 = (i) objPrevious;
                if (!kotlin.jvm.internal.p.a(iVar2, iVar) && ((List) h1Var2.getValue()).lastIndexOf(iVar2) < ((List) h1Var2.getValue()).lastIndexOf(iVar)) {
                    break;
                }
            }
            i iVar3 = (i) objPrevious;
            if (iVar3 != null) {
                j1Var.h(null, kotlin.collections.q0.c0((Set) j1Var.getValue(), iVar3));
            }
            c(iVar, z);
        } else {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((i) it.next()) == iVar) {
                    Iterable iterable2 = (Iterable) s0Var.f20648i.getValue();
                    if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                        Iterator it2 = iterable2.iterator();
                        while (it2.hasNext()) {
                            if (((i) it2.next()) == iVar) {
                            }
                        }
                    }
                }
            }
        }
        this.f4713h.z.put(iVar, Boolean.valueOf(z));
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [kotlin.jvm.internal.r, r7.l] */
    public final void f(i iVar) {
        s sVar = this.f4713h;
        a1 a1VarB = sVar.f4780v.b(iVar.f4681l.f4657i);
        if (!a1VarB.equals(this.f4712g)) {
            Object obj = sVar.f4781w.get(a1VarB);
            if (obj == null) {
                throw new IllegalStateException(a0.c.p(new StringBuilder("NavigatorBackStack for "), iVar.f4681l.f4657i, " should already be created").toString());
            }
            ((k) obj).f(iVar);
            return;
        }
        ?? r02 = sVar.x;
        if (r02 != 0) {
            r02.invoke(iVar);
            a(iVar);
        } else {
            Log.i("NavController", "Ignoring add of destination " + iVar.f4681l + " outside of the call to navigate(). ");
        }
    }
}
