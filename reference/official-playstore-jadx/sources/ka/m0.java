package ka;

import d7.j;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlinx.coroutines.TimeoutCancellationException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c2.a f19611a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c2.a f19612b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c2.a f19613c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c2.a f19614d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c2.a f19615e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c2.a f19616f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c2.a f19617g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final c2.a f19618h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b1 f19619i = new b1(false);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final b1 f19620j = new b1(true);

    static {
        int i10 = 5;
        f19611a = new c2.a("RESUME_TOKEN", i10);
        f19612b = new c2.a("REMOVED_TASK", i10);
        f19613c = new c2.a("CLOSED_EMPTY", i10);
        f19614d = new c2.a("COMPLETING_ALREADY", i10);
        f19615e = new c2.a("COMPLETING_WAITING_CHILDREN", i10);
        f19616f = new c2.a("COMPLETING_RETRY", i10);
        f19617g = new c2.a("TOO_LATE_TO_CANCEL", i10);
        f19618h = new c2.a("SEALED", i10);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object A(long r5, r7.p r7, d7.d r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof ka.u2
            if (r0 == 0) goto L13
            r0 = r8
            ka.u2 r0 = (ka.u2) r0
            int r1 = r0.f19645m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f19645m = r1
            goto L18
        L13:
            ka.u2 r0 = new ka.u2
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f19644l
            int r1 = r0.f19645m
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            kotlin.jvm.internal.k0 r5 = r0.f19643i
            k2.c.G(r8)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L27
            return r8
        L27:
            r6 = move-exception
            goto L57
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r8)
            r3 = 0
            int r8 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r8 > 0) goto L3b
            goto L5d
        L3b:
            kotlin.jvm.internal.k0 r8 = new kotlin.jvm.internal.k0
            r8.<init>()
            r0.f19643i = r8     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L55
            r0.f19645m = r2     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L55
            ka.t2 r1 = new ka.t2     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L55
            r1.<init>(r5, r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L55
            r8.f19746i = r1     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L55
            java.lang.Object r5 = v(r1, r7)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L55
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L54
            return r6
        L54:
            return r5
        L55:
            r6 = move-exception
            r5 = r8
        L57:
            ka.v1 r7 = r6.f19976i
            java.lang.Object r5 = r5.f19746i
            if (r7 != r5) goto L5f
        L5d:
            r5 = 0
            return r5
        L5f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: ka.m0.A(long, r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final Object B(d7.d dVar) {
        Object obj;
        d7.j context = dVar.getContext();
        i(context);
        d7.d dVarA = t7.a.A(dVar);
        pa.g gVar = dVarA instanceof pa.g ? (pa.g) dVarA : null;
        e7.a aVar = e7.a.f15033i;
        x6.t0 t0Var = x6.t0.f22605a;
        if (gVar == null) {
            obj = t0Var;
        } else {
            f0 f0Var = gVar.f21269n;
            if (f0Var.isDispatchNeeded(context)) {
                gVar.f21271p = t0Var;
                gVar.f19650m = 1;
                f0Var.dispatchYield(context, gVar);
            } else {
                a3 a3Var = new a3(a3.f19550l);
                d7.j jVarPlus = context.plus(a3Var);
                gVar.f21271p = t0Var;
                gVar.f19650m = 1;
                f0Var.dispatchYield(jVarPlus, gVar);
                if (a3Var.f19551i) {
                    c1 c1VarA = r2.a();
                    kotlin.collections.p pVar = c1VarA.f19566m;
                    if (!(pVar != null ? pVar.isEmpty() : true)) {
                        if (c1VarA.f19564i >= 4294967296L) {
                            gVar.f21271p = t0Var;
                            gVar.f19650m = 1;
                            c1VarA.t0(gVar);
                        } else {
                            c1VarA.u0(true);
                            try {
                                gVar.run();
                                do {
                                } while (c1VarA.J0());
                            } finally {
                                try {
                                } finally {
                                }
                            }
                        }
                    }
                    obj = t0Var;
                }
            }
            obj = aVar;
        }
        return obj == aVar ? obj : t0Var;
    }

    public static final CancellationException a(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    public static s b() {
        s sVar = new s(true);
        sVar.B(null);
        return sVar;
    }

    public static x1 c() {
        return new x1(null);
    }

    public static p2 d() {
        return new p2(null);
    }

    public static q0 e(int i10, d7.j jVar, k0 k0Var, r7.p pVar) {
        if ((i10 & 1) != 0) {
            jVar = d7.k.f14688i;
        }
        q0 q0Var = new q0(c0.b(k0Var, jVar), true);
        q0Var.S(1, q0Var, pVar);
        return q0Var;
    }

    public static final Object f(List list, d7.d dVar) {
        if (list.isEmpty()) {
            return kotlin.collections.z.f19728i;
        }
        p0[] p0VarArr = (p0[]) list.toArray(new p0[0]);
        e eVar = new e(p0VarArr);
        l lVar = new l(1, t7.a.A(dVar));
        lVar.s();
        int length = p0VarArr.length;
        c[] cVarArr = new c[length];
        for (int i10 = 0; i10 < length; i10++) {
            p0 p0Var = p0VarArr[i10];
            p0Var.start();
            c cVar = new c(eVar, lVar);
            cVar.f19561m = p0Var.invokeOnCompletion(cVar);
            cVarArr[i10] = cVar;
        }
        d dVar2 = new d(cVarArr);
        for (int i11 = 0; i11 < length; i11++) {
            c cVar2 = cVarArr[i11];
            cVar2.getClass();
            c.f19559o.set(cVar2, dVar2);
        }
        if (l.f19606q.get(lVar) instanceof l2) {
            lVar.c(dVar2);
        } else {
            dVar2.b();
        }
        return lVar.r();
    }

    public static final void g(d7.j jVar, CancellationException cancellationException) {
        v1 v1Var = (v1) jVar.get(u1.f19642i);
        if (v1Var != null) {
            v1Var.cancel(cancellationException);
        }
    }

    public static final Object h(v1 v1Var, f7.j jVar) {
        v1Var.cancel((CancellationException) null);
        Object objJoin = v1Var.join(jVar);
        return objJoin == e7.a.f15033i ? objJoin : x6.t0.f22605a;
    }

    public static final void i(d7.j jVar) {
        v1 v1Var = (v1) jVar.get(u1.f19642i);
        if (v1Var != null && !v1Var.isActive()) {
            throw v1Var.getCancellationException();
        }
    }

    public static final String j(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final v1 k(d7.j jVar) {
        v1 v1Var = (v1) jVar.get(u1.f19642i);
        if (v1Var != null) {
            return v1Var;
        }
        throw new IllegalStateException(("Current context doesn't contain Job in it: " + jVar).toString());
    }

    public static final l l(d7.d dVar) {
        l lVar;
        l lVar2;
        if (!(dVar instanceof pa.g)) {
            return new l(1, dVar);
        }
        pa.g gVar = (pa.g) dVar;
        c2.a aVar = pa.a.f21251d;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = pa.g.f21268r;
        loop0: while (true) {
            Object obj = atomicReferenceFieldUpdater.get(gVar);
            lVar = null;
            if (obj == null) {
                atomicReferenceFieldUpdater.set(gVar, aVar);
                lVar2 = null;
                break;
            }
            if (obj instanceof l) {
                while (!atomicReferenceFieldUpdater.compareAndSet(gVar, obj, aVar)) {
                    if (atomicReferenceFieldUpdater.get(gVar) != obj) {
                        break;
                    }
                }
                lVar2 = (l) obj;
                break loop0;
            }
            if (obj != aVar && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (lVar2 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = l.f19606q;
            Object obj2 = atomicReferenceFieldUpdater2.get(lVar2);
            if (!(obj2 instanceof u) || ((u) obj2).f19640d == null) {
                l.f19605p.set(lVar2, 536870911);
                atomicReferenceFieldUpdater2.set(lVar2, b.f19552i);
                lVar = lVar2;
            } else {
                lVar2.o();
            }
            if (lVar != null) {
                return lVar;
            }
        }
        return new l(2, dVar);
    }

    public static final boolean m(d7.j jVar) {
        v1 v1Var = (v1) jVar.get(u1.f19642i);
        if (v1Var != null) {
            return v1Var.isActive();
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object n(java.util.ArrayList r4, f7.c r5) {
        /*
            boolean r0 = r5 instanceof ka.f
            if (r0 == 0) goto L13
            r0 = r5
            ka.f r0 = (ka.f) r0
            int r1 = r0.f19584m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f19584m = r1
            goto L18
        L13:
            ka.f r0 = new ka.f
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.f19583l
            int r1 = r0.f19584m
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            java.util.Iterator r4 = r0.f19582i
            k2.c.G(r5)
            goto L36
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            k2.c.G(r5)
            java.util.Iterator r4 = r4.iterator()
        L36:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L4f
            java.lang.Object r5 = r4.next()
            ka.v1 r5 = (ka.v1) r5
            r0.f19582i = r4
            r0.f19584m = r2
            java.lang.Object r5 = r5.join(r0)
            e7.a r1 = e7.a.f15033i
            if (r5 != r1) goto L36
            return r1
        L4f:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: ka.m0.n(java.util.ArrayList, f7.c):java.lang.Object");
    }

    public static final o2 o(int i10, d7.j jVar, k0 k0Var, r7.p pVar) {
        d7.j jVarB = c0.b(k0Var, jVar);
        o2 g2Var = i10 == 2 ? new g2(jVarB, pVar) : new o2(jVarB, true);
        g2Var.S(i10, g2Var, pVar);
        return g2Var;
    }

    public static /* synthetic */ o2 p(k0 k0Var, d7.j jVar, int i10, r7.p pVar, int i11) {
        if ((i11 & 1) != 0) {
            jVar = d7.k.f14688i;
        }
        if ((i11 & 2) != 0) {
            i10 = 1;
        }
        return o(i10, jVar, k0Var, pVar);
    }

    public static final Object q(Object obj) {
        return obj instanceof v ? new x6.c0(((v) obj).f19647a) : obj;
    }

    public static final void r(l lVar, d7.d dVar, boolean z) {
        Object obj = l.f19606q.get(lVar);
        Throwable thH = lVar.h(obj);
        Object c0Var = thH != null ? new x6.c0(thH) : lVar.i(obj);
        if (!z) {
            dVar.resumeWith(c0Var);
            return;
        }
        pa.g gVar = (pa.g) dVar;
        d7.d dVar2 = gVar.f21270o;
        Object obj2 = gVar.f21272q;
        d7.j context = dVar2.getContext();
        Object objB = pa.c0.b(context, obj2);
        w2 w2VarC = objB != pa.c0.f21257a ? c0.c(dVar2, context, objB) : null;
        try {
            dVar2.resumeWith(c0Var);
            if (w2VarC == null || w2VarC.U()) {
                pa.c0.a(context, objB);
            }
        } catch (Throwable th) {
            if (w2VarC == null || w2VarC.U()) {
                pa.c0.a(context, objB);
            }
            throw th;
        }
    }

    public static final Object s(d7.j jVar, r7.p pVar) throws Throwable {
        c1 c1VarA;
        d7.j jVarA;
        Thread threadCurrentThread = Thread.currentThread();
        j.b bVar = d7.f.f14687i;
        d7.g gVar = (d7.g) jVar.get(bVar);
        d7.k kVar = d7.k.f14688i;
        if (gVar == null) {
            c1VarA = r2.a();
            jVarA = c0.a(kVar, jVar.plus(c1VarA), true);
            ra.d dVar = x0.f19653b;
            if (jVarA != dVar && jVarA.get(bVar) == null) {
                jVarA = jVarA.plus(dVar);
            }
        } else {
            if (gVar instanceof c1) {
            }
            c1VarA = (c1) r2.f19629a.get();
            jVarA = c0.a(kVar, jVar, true);
            ra.d dVar2 = x0.f19653b;
            if (jVarA != dVar2 && jVarA.get(bVar) == null) {
                jVarA = jVarA.plus(dVar2);
            }
        }
        g gVar2 = new g(jVarA, threadCurrentThread, c1VarA);
        gVar2.S(1, gVar2, pVar);
        c1 c1Var = gVar2.f19590o;
        if (c1Var != null) {
            int i10 = c1.f19563n;
            c1Var.u0(false);
        }
        while (!Thread.interrupted()) {
            try {
                long jD0 = c1Var != null ? c1Var.D0() : Long.MAX_VALUE;
                if (gVar2.isCompleted()) {
                    if (c1Var != null) {
                        int i11 = c1.f19563n;
                        c1Var.q0(false);
                    }
                    Object objX = x(gVar2.y());
                    v vVar = objX instanceof v ? (v) objX : null;
                    if (vVar == null) {
                        return objX;
                    }
                    throw vVar.f19647a;
                }
                LockSupport.parkNanos(gVar2, jD0);
            } catch (Throwable th) {
                if (c1Var != null) {
                    int i12 = c1.f19563n;
                    c1Var.q0(false);
                }
                throw th;
            }
        }
        InterruptedException interruptedException = new InterruptedException();
        gVar2.l(interruptedException);
        throw interruptedException;
    }

    public static Object u(r7.a aVar, d7.d dVar) {
        return y(d7.k.f14688i, new androidx.lifecycle.s(aVar, null), dVar);
    }

    public static final Object v(t2 t2Var, r7.p pVar) throws Throwable {
        Object vVar;
        Object objE;
        t2Var.invokeOnCompletion(new a1(s0.b(t2Var.f21296n.getContext()).l(t2Var.f19636o, t2Var, t2Var.f19541m), 0));
        try {
            kotlin.jvm.internal.q0.e(2, pVar);
            vVar = pVar.invoke(t2Var, t2Var);
        } catch (Throwable th) {
            vVar = new v(th, false);
        }
        e7.a aVar = e7.a.f15033i;
        if (vVar == aVar || (objE = t2Var.E(vVar)) == f19615e) {
            return aVar;
        }
        if (objE instanceof v) {
            Throwable th2 = ((v) objE).f19647a;
            if (!(th2 instanceof TimeoutCancellationException) || ((TimeoutCancellationException) th2).f19976i != t2Var) {
                throw th2;
            }
            if (vVar instanceof v) {
                throw ((v) vVar).f19647a;
            }
        } else {
            vVar = x(objE);
        }
        return vVar;
    }

    public static final String w(d7.d dVar) {
        Object c0Var;
        if (dVar instanceof pa.g) {
            return dVar.toString();
        }
        try {
            c0Var = dVar + '@' + j(dVar);
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (x6.d0.a(c0Var) != null) {
            c0Var = dVar.getClass().getName() + '@' + j(dVar);
        }
        return (String) c0Var;
    }

    public static final Object x(Object obj) {
        p1 p1Var;
        q1 q1Var = obj instanceof q1 ? (q1) obj : null;
        return (q1Var == null || (p1Var = q1Var.f19628a) == null) ? obj : p1Var;
    }

    public static final Object y(d7.j jVar, r7.p pVar, d7.d dVar) throws Throwable {
        d7.j context = dVar.getContext();
        d7.j jVarPlus = !((Boolean) jVar.fold(Boolean.FALSE, b0.f19553i)).booleanValue() ? context.plus(jVar) : c0.a(context, jVar, false);
        i(jVarPlus);
        if (jVarPlus == context) {
            pa.v vVar = new pa.v(jVarPlus, dVar);
            return kotlin.reflect.b0.F(vVar, vVar, pVar);
        }
        d7.f fVar = d7.f.f14687i;
        if (kotlin.jvm.internal.p.a(jVarPlus.get(fVar), context.get(fVar))) {
            w2 w2Var = new w2(jVarPlus, dVar);
            d7.j jVar2 = w2Var.f19541m;
            Object objB = pa.c0.b(jVar2, null);
            try {
                return kotlin.reflect.b0.F(w2Var, w2Var, pVar);
            } finally {
                pa.c0.a(jVar2, objB);
            }
        }
        v0 v0Var = new v0(jVarPlus, dVar);
        k2.c.F(pVar, v0Var, v0Var);
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = v0.f19648o;
        do {
            int i10 = atomicIntegerFieldUpdater.get(v0Var);
            if (i10 != 0) {
                if (i10 != 2) {
                    throw new IllegalStateException("Already suspended");
                }
                Object objX = x(v0Var.y());
                if (objX instanceof v) {
                    throw ((v) objX).f19647a;
                }
                return objX;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(v0Var, 0, 1));
        return e7.a.f15033i;
    }

    public static final Object z(long j10, r7.p pVar, f7.c cVar) {
        if (j10 > 0) {
            return v(new t2(j10, cVar), pVar);
        }
        throw new TimeoutCancellationException(null, "Timed out immediately");
    }
}
