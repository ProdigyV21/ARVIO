package ka;

import d7.j;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.CompletionHandlerException;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.TimeoutCancellationException;

/* JADX INFO: loaded from: classes5.dex */
public class f2 implements v1, q, n2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19587i = AtomicReferenceFieldUpdater.newUpdater(f2.class, Object.class, "_state");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19588l = AtomicReferenceFieldUpdater.newUpdater(f2.class, Object.class, "_parentHandle");

    @q7.w
    private volatile Object _parentHandle;

    @q7.w
    private volatile Object _state;

    public f2(boolean z) {
        this._state = z ? m0.f19620j : m0.f19619i;
    }

    public static p H(pa.m mVar) {
        while (mVar.isRemoved()) {
            mVar = mVar.getPrevNode();
        }
        while (true) {
            mVar = mVar.getNextNode();
            if (!mVar.isRemoved()) {
                if (mVar instanceof p) {
                    return (p) mVar;
                }
                if (mVar instanceof i2) {
                    return null;
                }
            }
        }
    }

    public static String M(Object obj) {
        if (!(obj instanceof b2)) {
            return obj instanceof p1 ? ((p1) obj).isActive() ? "Active" : "New" : obj instanceof v ? "Cancelled" : "Completed";
        }
        b2 b2Var = (b2) obj;
        return b2Var.c() ? "Cancelling" : b2Var.d() ? "Completing" : "Active";
    }

    public static CancellationException N(f2 f2Var, Throwable th) {
        CancellationException cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
        return cancellationException == null ? new JobCancellationException(f2Var.p(), th, f2Var) : cancellationException;
    }

    public final void B(v1 v1Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19588l;
        k2 k2Var = k2.f19604i;
        if (v1Var == null) {
            atomicReferenceFieldUpdater.set(this, k2Var);
            return;
        }
        v1Var.start();
        o oVarAttachChild = v1Var.attachChild(this);
        atomicReferenceFieldUpdater.set(this, oVarAttachChild);
        if (isCompleted()) {
            oVarAttachChild.dispose();
            atomicReferenceFieldUpdater.set(this, k2Var);
        }
    }

    public boolean C() {
        return this instanceof g;
    }

    public final boolean D(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object objO;
        do {
            objO = O(y(), obj);
            if (objO == m0.f19614d) {
                return false;
            }
            if (objO == m0.f19615e) {
                return true;
            }
        } while (objO == m0.f19616f);
        g(objO);
        return true;
    }

    public final Object E(Object obj) {
        Object objO;
        do {
            objO = O(y(), obj);
            if (objO == m0.f19614d) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                v vVar = obj instanceof v ? (v) obj : null;
                throw new IllegalStateException(str, vVar != null ? vVar.f19647a : null);
            }
        } while (objO == m0.f19616f);
        return objO;
    }

    public String F() {
        return getClass().getSimpleName();
    }

    public final void I(i2 i2Var, Throwable th) throws IllegalAccessException, InvocationTargetException {
        CompletionHandlerException completionHandlerException = null;
        for (pa.m nextNode = (pa.m) i2Var.getNext(); !kotlin.jvm.internal.p.a(nextNode, i2Var); nextNode = nextNode.getNextNode()) {
            if (nextNode instanceof w1) {
                y1 y1Var = (y1) nextNode;
                try {
                    y1Var.invoke(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        com.google.common.util.concurrent.r0.a(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + y1Var + " for " + this, th2);
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            A(completionHandlerException);
        }
        n(th);
    }

    public final int L(Object obj) {
        boolean z = obj instanceof b1;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19587i;
        if (z) {
            if (((b1) obj).f19554i) {
                return 0;
            }
            b1 b1Var = m0.f19620j;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, b1Var)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    return -1;
                }
            }
            K();
            return 1;
        }
        if (!(obj instanceof o1)) {
            return 0;
        }
        i2 i2Var = ((o1) obj).f19626i;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, i2Var)) {
            if (atomicReferenceFieldUpdater.get(this) != obj) {
                return -1;
            }
        }
        K();
        return 1;
    }

    public final Object O(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        if (!(obj instanceof p1)) {
            return m0.f19614d;
        }
        if (((obj instanceof b1) || (obj instanceof y1)) && !(obj instanceof p) && !(obj2 instanceof v)) {
            p1 p1Var = (p1) obj;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19587i;
            Object q1Var = obj2 instanceof p1 ? new q1((p1) obj2) : obj2;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, p1Var, q1Var)) {
                if (atomicReferenceFieldUpdater.get(this) != p1Var) {
                    return m0.f19616f;
                }
            }
            J(obj2);
            r(p1Var, obj2);
            return obj2;
        }
        p1 p1Var2 = (p1) obj;
        i2 i2VarX = x(p1Var2);
        if (i2VarX == null) {
            return m0.f19616f;
        }
        p pVarH = null;
        b2 b2Var = p1Var2 instanceof b2 ? (b2) p1Var2 : null;
        if (b2Var == null) {
            b2Var = new b2(i2VarX, null);
        }
        synchronized (b2Var) {
            if (b2Var.d()) {
                return m0.f19614d;
            }
            b2.f19555l.set(b2Var, 1);
            if (b2Var != p1Var2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f19587i;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, p1Var2, b2Var)) {
                    if (atomicReferenceFieldUpdater2.get(this) != p1Var2) {
                        return m0.f19616f;
                    }
                }
            }
            boolean zC = b2Var.c();
            v vVar = obj2 instanceof v ? (v) obj2 : null;
            if (vVar != null) {
                b2Var.a(vVar.f19647a);
            }
            Throwable thB = b2Var.b();
            if (zC) {
                thB = null;
            }
            if (thB != null) {
                I(i2VarX, thB);
            }
            p pVar = p1Var2 instanceof p ? (p) p1Var2 : null;
            if (pVar == null) {
                i2 list = p1Var2.getList();
                if (list != null) {
                    pVarH = H(list);
                }
            } else {
                pVarH = pVar;
            }
            if (pVarH != null) {
                while (pVarH.f19627l.invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, new a2(this, b2Var, pVarH, obj2)) == k2.f19604i) {
                    pVarH = H(pVarH);
                    if (pVarH == null) {
                    }
                }
                return m0.f19615e;
            }
            return t(b2Var, obj2);
        }
    }

    public boolean R(Object obj) {
        return D(obj);
    }

    @Override // ka.n2
    public final CancellationException T() {
        Throwable thB;
        Object objY = y();
        if (objY instanceof b2) {
            thB = ((b2) objY).b();
        } else if (objY instanceof v) {
            thB = ((v) objY).f19647a;
        } else {
            if (objY instanceof p1) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + objY).toString());
            }
            thB = null;
        }
        CancellationException cancellationException = thB instanceof CancellationException ? (CancellationException) thB : null;
        return cancellationException == null ? new JobCancellationException("Parent job is ".concat(M(objY)), thB, this) : cancellationException;
    }

    @Override // ka.v1
    public final o attachChild(q qVar) {
        return (o) invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, new p(qVar));
    }

    @Override // ka.q
    public final void b0(f2 f2Var) throws IllegalAccessException, InvocationTargetException {
        l(f2Var);
    }

    @Override // ka.v1
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    @Override // d7.j
    public final Object fold(Object obj, r7.p pVar) {
        return pVar.invoke(obj, this);
    }

    @Override // d7.j
    public final j.a get(j.b bVar) {
        return d7.i.a(this, bVar);
    }

    @Override // ka.v1
    public final CancellationException getCancellationException() {
        Object objY = y();
        if (!(objY instanceof b2)) {
            if (!(objY instanceof p1)) {
                return objY instanceof v ? N(this, ((v) objY).f19647a) : new JobCancellationException(getClass().getSimpleName().concat(" has completed normally"), null, this);
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        Throwable thB = ((b2) objY).b();
        if (thB == null) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        String strConcat = getClass().getSimpleName().concat(" is cancelling");
        CancellationException cancellationException = thB instanceof CancellationException ? (CancellationException) thB : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        if (strConcat == null) {
            strConcat = p();
        }
        return new JobCancellationException(strConcat, thB, this);
    }

    @Override // ka.v1
    public final ga.m getChildren() {
        return new ga.p(new d2(null, this));
    }

    @Override // d7.j.a
    public final j.b getKey() {
        return u1.f19642i;
    }

    @Override // ka.v1
    public final sa.b getOnJoin() {
        kotlin.jvm.internal.q0.e(3, e2.f19581i);
        return new io.sentry.util.l(25);
    }

    @Override // ka.v1
    public final v1 getParent() {
        o oVar = (o) f19588l.get(this);
        if (oVar != null) {
            return oVar.getParent();
        }
        return null;
    }

    public void h(Object obj) {
        g(obj);
    }

    @Override // ka.v1
    public final z0 invokeOnCompletion(r7.l lVar) {
        return invokeOnCompletion(false, true, lVar);
    }

    @Override // ka.v1
    public boolean isActive() {
        Object objY = y();
        return (objY instanceof p1) && ((p1) objY).isActive();
    }

    @Override // ka.v1
    public final boolean isCancelled() {
        Object objY = y();
        if (objY instanceof v) {
            return true;
        }
        return (objY instanceof b2) && ((b2) objY).c();
    }

    @Override // ka.v1
    public final boolean isCompleted() {
        return !(y() instanceof p1);
    }

    public final Object j(d7.d dVar) {
        Object objY;
        do {
            objY = y();
            if (!(objY instanceof p1)) {
                if (objY instanceof v) {
                    throw ((v) objY).f19647a;
                }
                return m0.x(objY);
            }
        } while (L(objY) < 0);
        z1 z1Var = new z1(t7.a.A(dVar), this);
        z1Var.s();
        z1Var.c(new i(invokeOnCompletion(false, true, new a1(z1Var, 3)), 1));
        return z1Var.r();
    }

    @Override // ka.v1
    public final Object join(d7.d dVar) {
        Object objY;
        x6.t0 t0Var;
        do {
            objY = y();
            boolean z = objY instanceof p1;
            t0Var = x6.t0.f22605a;
            if (!z) {
                m0.i(dVar.getContext());
                return t0Var;
            }
        } while (L(objY) < 0);
        l lVar = new l(1, t7.a.A(dVar));
        lVar.s();
        lVar.c(new i(invokeOnCompletion(false, true, new a1(lVar, 4)), 1));
        Object objR = lVar.r();
        e7.a aVar = e7.a.f15033i;
        if (objR != aVar) {
            objR = t0Var;
        }
        return objR == aVar ? objR : t0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003a A[PHI: r0
      0x003a: PHI (r0v1 java.lang.Object) = (r0v0 java.lang.Object), (r0v12 java.lang.Object) binds: [B:3:0x0008, B:16:0x0036] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean l(java.lang.Object r10) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ka.f2.l(java.lang.Object):boolean");
    }

    public void m(CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        l(cancellationException);
    }

    @Override // d7.j
    public final d7.j minusKey(j.b bVar) {
        return d7.i.b(this, bVar);
    }

    public final boolean n(Throwable th) {
        if (!C()) {
            boolean z = th instanceof CancellationException;
            o oVar = (o) f19588l.get(this);
            return (oVar == null || oVar == k2.f19604i) ? z : oVar.i(th) || z;
        }
        return true;
    }

    public Object o(d7.d dVar) {
        return j(dVar);
    }

    public String p() {
        return "Job was cancelled";
    }

    @Override // ka.v1
    public final v1 plus(v1 v1Var) {
        return v1Var;
    }

    public boolean q(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return l(th) && v();
    }

    public final void r(p1 p1Var, Object obj) throws IllegalAccessException, InvocationTargetException {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19588l;
        o oVar = (o) atomicReferenceFieldUpdater.get(this);
        if (oVar != null) {
            oVar.dispose();
            atomicReferenceFieldUpdater.set(this, k2.f19604i);
        }
        CompletionHandlerException completionHandlerException = null;
        v vVar = obj instanceof v ? (v) obj : null;
        Throwable th = vVar != null ? vVar.f19647a : null;
        if (p1Var instanceof y1) {
            try {
                ((y1) p1Var).invoke(th);
                return;
            } catch (Throwable th2) {
                A(new CompletionHandlerException("Exception in completion handler " + p1Var + " for " + this, th2));
                return;
            }
        }
        i2 list = p1Var.getList();
        if (list != null) {
            for (pa.m nextNode = (pa.m) list.getNext(); !kotlin.jvm.internal.p.a(nextNode, list); nextNode = nextNode.getNextNode()) {
                if (nextNode instanceof y1) {
                    y1 y1Var = (y1) nextNode;
                    try {
                        y1Var.invoke(th);
                    } catch (Throwable th3) {
                        if (completionHandlerException != null) {
                            com.google.common.util.concurrent.r0.a(completionHandlerException, th3);
                        } else {
                            completionHandlerException = new CompletionHandlerException("Exception in completion handler " + y1Var + " for " + this, th3);
                        }
                    }
                }
            }
            if (completionHandlerException != null) {
                A(completionHandlerException);
            }
        }
    }

    public final Throwable s(Object obj) {
        if (!(obj == null ? true : obj instanceof Throwable)) {
            return ((n2) obj).T();
        }
        Throwable th = (Throwable) obj;
        return th == null ? new JobCancellationException(p(), null, this) : th;
    }

    @Override // ka.v1
    public final boolean start() {
        int iL;
        do {
            iL = L(y());
            if (iL == 0) {
                return false;
            }
        } while (iL != 1);
        return true;
    }

    public final Object t(b2 b2Var, Object obj) throws IllegalAccessException, InvocationTargetException {
        Throwable thU;
        v vVar = obj instanceof v ? (v) obj : null;
        Throwable th = vVar != null ? vVar.f19647a : null;
        synchronized (b2Var) {
            b2Var.c();
            ArrayList<Throwable> arrayListE = b2Var.e(th);
            thU = u(b2Var, arrayListE);
            if (thU != null && arrayListE.size() > 1) {
                Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(arrayListE.size()));
                for (Throwable th2 : arrayListE) {
                    if (th2 != thU && th2 != thU && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                        com.google.common.util.concurrent.r0.a(thU, th2);
                    }
                }
            }
        }
        if (thU != null && thU != th) {
            obj = new v(thU, false);
        }
        if (thU != null && (n(thU) || z(thU))) {
            v vVar2 = (v) obj;
            vVar2.getClass();
            v.f19646b.compareAndSet(vVar2, 0, 1);
        }
        J(obj);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19587i;
        Object q1Var = obj instanceof p1 ? new q1((p1) obj) : obj;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, b2Var, q1Var) && atomicReferenceFieldUpdater.get(this) == b2Var) {
        }
        r(b2Var, obj);
        return obj;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(F() + '{' + M(y()) + '}');
        sb2.append('@');
        sb2.append(m0.j(this));
        return sb2.toString();
    }

    public final Throwable u(b2 b2Var, ArrayList arrayList) {
        Object next;
        Object obj = null;
        if (arrayList.isEmpty()) {
            if (b2Var.c()) {
                return new JobCancellationException(p(), null, this);
            }
            return null;
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (!(((Throwable) next) instanceof CancellationException)) {
                break;
            }
        }
        Throwable th = (Throwable) next;
        if (th != null) {
            return th;
        }
        Throwable th2 = (Throwable) arrayList.get(0);
        if (th2 instanceof TimeoutCancellationException) {
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next2 = it2.next();
                Throwable th3 = (Throwable) next2;
                if (th3 != th2 && (th3 instanceof TimeoutCancellationException)) {
                    obj = next2;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    public boolean v() {
        return true;
    }

    public boolean w() {
        return this instanceof s;
    }

    public final i2 x(p1 p1Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        i2 list = p1Var.getList();
        if (list != null) {
            return list;
        }
        if (p1Var instanceof b1) {
            return new i2();
        }
        if (!(p1Var instanceof y1)) {
            throw new IllegalStateException(("State should have list: " + p1Var).toString());
        }
        y1 y1Var = (y1) p1Var;
        y1Var.addOneIfEmpty(new i2());
        pa.m nextNode = y1Var.getNextNode();
        do {
            atomicReferenceFieldUpdater = f19587i;
            if (atomicReferenceFieldUpdater.compareAndSet(this, y1Var, nextNode)) {
                return null;
            }
        } while (atomicReferenceFieldUpdater.get(this) == y1Var);
        return null;
    }

    public final Object y() {
        while (true) {
            Object obj = f19587i.get(this);
            if (!(obj instanceof pa.s)) {
                return obj;
            }
            ((pa.s) obj).a(this);
        }
    }

    public boolean z(Throwable th) {
        return false;
    }

    @Override // ka.v1
    public /* synthetic */ boolean cancel(Throwable th) throws IllegalAccessException, InvocationTargetException {
        m(th != null ? N(this, th) : new JobCancellationException(p(), null, this));
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x00e1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00e7  */
    @Override // ka.v1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final ka.z0 invokeOnCompletion(boolean r11, boolean r12, r7.l r13) {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ka.f2.invokeOnCompletion(boolean, boolean, r7.l):ka.z0");
    }

    @Override // d7.j
    public final d7.j plus(d7.j jVar) {
        return d7.h.a(this, jVar);
    }

    @Override // ka.v1
    public void cancel(CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(p(), null, this);
        }
        m(cancellationException);
    }

    public void K() {
    }

    public void A(CompletionHandlerException completionHandlerException) {
        throw completionHandlerException;
    }

    public void J(Object obj) {
    }

    public void g(Object obj) {
    }
}
