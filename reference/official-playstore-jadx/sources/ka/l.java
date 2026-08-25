package ka;

import androidx.media3.common.C;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.CompletionHandlerException;

/* JADX INFO: loaded from: classes5.dex */
public class l extends w0 implements k, f7.d, y2 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f19605p = AtomicIntegerFieldUpdater.newUpdater(l.class, "_decisionAndIndex");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19606q = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "_state");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19607r = AtomicReferenceFieldUpdater.newUpdater(l.class, Object.class, "_parentHandle");

    @q7.w
    private volatile int _decisionAndIndex;

    @q7.w
    private volatile Object _parentHandle;

    @q7.w
    private volatile Object _state;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d7.d f19608n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final d7.j f19609o;

    public l(int i10, d7.d dVar) {
        super(i10);
        this.f19608n = dVar;
        this.f19609o = dVar.getContext();
        this._decisionAndIndex = 536870911;
        this._state = b.f19552i;
    }

    public static Object B(l2 l2Var, Object obj, int i10, r7.l lVar) {
        if (obj instanceof v) {
            return obj;
        }
        if (i10 != 1 && i10 != 2) {
            return obj;
        }
        if (lVar != null || (l2Var instanceof j)) {
            return new u(obj, l2Var instanceof j ? (j) l2Var : null, lVar, (Throwable) null, 16);
        }
        return obj;
    }

    public static void w(Object obj, Object obj2) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + obj + ", already has " + obj2).toString());
    }

    public final void A(f0 f0Var) {
        d7.d dVar = this.f19608n;
        pa.g gVar = dVar instanceof pa.g ? (pa.g) dVar : null;
        z(null, (gVar != null ? gVar.f21269n : null) == f0Var ? 4 : this.f19650m, x6.t0.f22605a);
    }

    public final c2.a C(Object obj, r7.l lVar) {
        c2.a aVar = m0.f19611a;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19606q;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof l2)) {
                return null;
            }
            Object objB = B((l2) obj2, obj, this.f19650m, lVar);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, objB)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    break;
                }
            }
            if (!v()) {
                o();
            }
            return aVar;
        }
    }

    @Override // ka.k
    public final c2.a a(Object obj, r7.l lVar) {
        return C(obj, lVar);
    }

    @Override // ka.w0
    public final void b(Object obj, CancellationException cancellationException) {
        CancellationException cancellationException2;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19606q;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof l2) {
                throw new IllegalStateException("Not completed");
            }
            if (obj2 instanceof v) {
                return;
            }
            if (!(obj2 instanceof u)) {
                cancellationException2 = cancellationException;
                u uVar = new u(obj2, (j) null, (r7.l) null, cancellationException2, 14);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, uVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        break;
                    }
                }
                return;
            }
            u uVar2 = (u) obj2;
            if (uVar2.f19641e != null) {
                throw new IllegalStateException("Must be called at most once");
            }
            u uVarA = u.a(uVar2, null, cancellationException, 15);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, uVarA)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    cancellationException2 = cancellationException;
                }
            }
            j jVar = uVar2.f19638b;
            if (jVar != null) {
                l(jVar, cancellationException);
            }
            r7.l lVar = uVar2.f19639c;
            if (lVar != null) {
                m(cancellationException, lVar);
                return;
            }
            return;
            cancellationException = cancellationException2;
        }
    }

    @Override // ka.k
    public final void c(r7.l lVar) {
        u(lVar instanceof j ? (j) lVar : new i(lVar, 2));
    }

    @Override // ka.k
    public final boolean cancel(Throwable th) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19606q;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof l2)) {
                return false;
            }
            m mVar = new m(this, th, (obj instanceof j) || (obj instanceof pa.w));
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, mVar)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            l2 l2Var = (l2) obj;
            if (l2Var instanceof j) {
                l((j) obj, th);
            } else if (l2Var instanceof pa.w) {
                n((pa.w) obj, th);
            }
            if (!v()) {
                o();
            }
            p(this.f19650m);
            return true;
        }
    }

    @Override // ka.k
    public final void d(Object obj, r7.l lVar) {
        z(lVar, this.f19650m, obj);
    }

    @Override // ka.y2
    public final void e(pa.w wVar, int i10) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i11;
        do {
            atomicIntegerFieldUpdater = f19605p;
            i11 = atomicIntegerFieldUpdater.get(this);
            if ((i11 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i11, ((i11 >> 29) << 29) + i10));
        u(wVar);
    }

    @Override // ka.k
    public final void f(Object obj) {
        p(this.f19650m);
    }

    @Override // ka.w0
    public final d7.d g() {
        return this.f19608n;
    }

    @Override // f7.d
    public final f7.d getCallerFrame() {
        d7.d dVar = this.f19608n;
        if (dVar instanceof f7.d) {
            return (f7.d) dVar;
        }
        return null;
    }

    @Override // d7.d
    public final d7.j getContext() {
        return this.f19609o;
    }

    @Override // ka.w0
    public final Throwable h(Object obj) {
        Throwable thH = super.h(obj);
        if (thH != null) {
            return thH;
        }
        return null;
    }

    @Override // ka.w0
    public final Object i(Object obj) {
        return obj instanceof u ? ((u) obj).f19637a : obj;
    }

    @Override // ka.k
    public final boolean isActive() {
        return f19606q.get(this) instanceof l2;
    }

    @Override // ka.w0
    public final Object k() {
        return f19606q.get(this);
    }

    public final void l(j jVar, Throwable th) {
        try {
            jVar.a(th);
        } catch (Throwable th2) {
            h0.a(this.f19609o, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void m(Throwable th, r7.l lVar) {
        try {
            lVar.invoke(th);
        } catch (Throwable th2) {
            h0.a(this.f19609o, new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public final void n(pa.w wVar, Throwable th) {
        d7.j jVar = this.f19609o;
        int i10 = f19605p.get(this) & 536870911;
        if (i10 == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
        try {
            wVar.g(i10, jVar);
        } catch (Throwable th2) {
            h0.a(jVar, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void o() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19607r;
        z0 z0Var = (z0) atomicReferenceFieldUpdater.get(this);
        if (z0Var == null) {
            return;
        }
        z0Var.dispose();
        atomicReferenceFieldUpdater.set(this, k2.f19604i);
    }

    public final void p(int i10) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i11;
        do {
            atomicIntegerFieldUpdater = f19605p;
            i11 = atomicIntegerFieldUpdater.get(this);
            int i12 = i11 >> 29;
            if (i12 != 0) {
                if (i12 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                boolean z = i10 == 4;
                d7.d dVar = this.f19608n;
                if (!z && (dVar instanceof pa.g)) {
                    boolean z5 = i10 == 1 || i10 == 2;
                    int i13 = this.f19650m;
                    if (z5 == (i13 == 1 || i13 == 2)) {
                        pa.g gVar = (pa.g) dVar;
                        f0 f0Var = gVar.f21269n;
                        d7.j context = gVar.f21270o.getContext();
                        if (f0Var.isDispatchNeeded(context)) {
                            f0Var.dispatch(context, this);
                            return;
                        }
                        c1 c1VarA = r2.a();
                        if (c1VarA.f19564i >= 4294967296L) {
                            c1VarA.t0(this);
                            return;
                        }
                        c1VarA.u0(true);
                        try {
                            m0.r(this, dVar, true);
                            do {
                            } while (c1VarA.J0());
                        } finally {
                            try {
                            } finally {
                            }
                        }
                        return;
                    }
                }
                m0.r(this, dVar, z);
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i11, 1073741824 + (536870911 & i11)));
    }

    public Throwable q(f2 f2Var) {
        return f2Var.getCancellationException();
    }

    public final Object r() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i10;
        v1 v1Var;
        boolean zV = v();
        do {
            atomicIntegerFieldUpdater = f19605p;
            i10 = atomicIntegerFieldUpdater.get(this);
            int i11 = i10 >> 29;
            if (i11 != 0) {
                if (i11 != 2) {
                    throw new IllegalStateException("Already suspended");
                }
                if (zV) {
                    y();
                }
                Object obj = f19606q.get(this);
                if (obj instanceof v) {
                    throw ((v) obj).f19647a;
                }
                int i12 = this.f19650m;
                if ((i12 != 1 && i12 != 2) || (v1Var = (v1) this.f19609o.get(u1.f19642i)) == null || v1Var.isActive()) {
                    return i(obj);
                }
                CancellationException cancellationException = v1Var.getCancellationException();
                b(obj, cancellationException);
                throw cancellationException;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i10, C.BUFFER_FLAG_LAST_SAMPLE + (536870911 & i10)));
        if (((z0) f19607r.get(this)) == null) {
            t();
        }
        if (zV) {
            y();
        }
        return e7.a.f15033i;
    }

    @Override // d7.d
    public final void resumeWith(Object obj) {
        Throwable thA = x6.d0.a(obj);
        if (thA != null) {
            obj = new v(thA, false);
        }
        z(null, this.f19650m, obj);
    }

    public final void s() {
        z0 z0VarT = t();
        if (z0VarT == null || (f19606q.get(this) instanceof l2)) {
            return;
        }
        z0VarT.dispose();
        f19607r.set(this, k2.f19604i);
    }

    public final z0 t() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        v1 v1Var = (v1) this.f19609o.get(u1.f19642i);
        if (v1Var == null) {
            return null;
        }
        z0 z0VarInvokeOnCompletion = v1Var.invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, new n(this));
        do {
            atomicReferenceFieldUpdater = f19607r;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, z0VarInvokeOnCompletion)) {
                break;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        return z0VarInvokeOnCompletion;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(x());
        sb2.append('(');
        sb2.append(m0.w(this.f19608n));
        sb2.append("){");
        Object obj = f19606q.get(this);
        sb2.append(obj instanceof l2 ? "Active" : obj instanceof m ? "Cancelled" : "Completed");
        sb2.append("}@");
        sb2.append(m0.j(this));
        return sb2.toString();
    }

    public final void u(l2 l2Var) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19606q;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof b) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, l2Var)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                return;
            }
            boolean z = true;
            if (obj instanceof j ? true : obj instanceof pa.w) {
                w(l2Var, obj);
                throw null;
            }
            if (obj instanceof v) {
                v vVar = (v) obj;
                if (!v.f19646b.compareAndSet(vVar, 0, 1)) {
                    w(l2Var, obj);
                    throw null;
                }
                if (obj instanceof m) {
                    Throwable th = vVar.f19647a;
                    if (l2Var instanceof j) {
                        l((j) l2Var, th);
                        return;
                    } else {
                        n((pa.w) l2Var, th);
                        return;
                    }
                }
                return;
            }
            if (obj instanceof u) {
                u uVar = (u) obj;
                if (uVar.f19638b != null) {
                    w(l2Var, obj);
                    throw null;
                }
                if (l2Var instanceof pa.w) {
                    return;
                }
                j jVar = (j) l2Var;
                Throwable th2 = uVar.f19641e;
                if (th2 != null) {
                    l(jVar, th2);
                    return;
                }
                u uVarA = u.a(uVar, jVar, null, 29);
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, obj, uVarA)) {
                        break;
                    } else if (atomicReferenceFieldUpdater.get(this) != obj) {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    return;
                }
            } else {
                if (l2Var instanceof pa.w) {
                    return;
                }
                u uVar2 = new u(obj, (j) l2Var, (r7.l) null, (Throwable) null, 28);
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, obj, uVar2)) {
                        break;
                    } else if (atomicReferenceFieldUpdater.get(this) != obj) {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    return;
                }
            }
        }
    }

    public final boolean v() {
        if (this.f19650m == 2) {
            return pa.g.f21268r.get((pa.g) this.f19608n) != null;
        }
        return false;
    }

    public String x() {
        return "CancellableContinuation";
    }

    public final void y() {
        d7.d dVar = this.f19608n;
        Throwable th = null;
        pa.g gVar = dVar instanceof pa.g ? (pa.g) dVar : null;
        if (gVar != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = pa.g.f21268r;
            loop0: while (true) {
                Object obj = atomicReferenceFieldUpdater.get(gVar);
                c2.a aVar = pa.a.f21251d;
                if (obj == aVar) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(gVar, aVar, this)) {
                        if (atomicReferenceFieldUpdater.get(gVar) != aVar) {
                            break;
                        }
                    }
                    break loop0;
                } else {
                    if (!(obj instanceof Throwable)) {
                        throw new IllegalStateException(("Inconsistent state " + obj).toString());
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(gVar, obj, null)) {
                        if (atomicReferenceFieldUpdater.get(gVar) != obj) {
                            throw new IllegalArgumentException("Failed requirement.");
                        }
                    }
                    th = (Throwable) obj;
                }
            }
            if (th == null) {
                return;
            }
            o();
            cancel(th);
        }
    }

    public final void z(r7.l lVar, int i10, Object obj) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19606q;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof l2) {
                Object objB = B((l2) obj2, obj, i10, lVar);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, objB)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        break;
                    }
                }
                if (!v()) {
                    o();
                }
                p(i10);
                return;
            }
            if (obj2 instanceof m) {
                m mVar = (m) obj2;
                if (m.f19610c.compareAndSet(mVar, 0, 1)) {
                    if (lVar != null) {
                        m(mVar.f19647a, lVar);
                        return;
                    }
                    return;
                }
            }
            throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
        }
    }
}
