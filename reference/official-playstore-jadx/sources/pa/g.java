package pa;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.c1;
import ka.m0;
import ka.r2;
import ka.w0;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends w0 implements f7.d, d7.d {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f21268r = AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "_reusableCancellableContinuation");

    @q7.w
    private volatile Object _reusableCancellableContinuation;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ka.f0 f21269n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final d7.d f21270o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f21271p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f21272q;

    public g(ka.f0 f0Var, d7.d dVar) {
        super(-1);
        this.f21269n = f0Var;
        this.f21270o = dVar;
        this.f21271p = a.f21250c;
        this.f21272q = dVar.getContext().fold(0, z.f21301i);
    }

    @Override // ka.w0
    public final void b(Object obj, CancellationException cancellationException) {
        if (obj instanceof ka.w) {
            throw null;
        }
    }

    @Override // f7.d
    public final f7.d getCallerFrame() {
        d7.d dVar = this.f21270o;
        if (dVar instanceof f7.d) {
            return (f7.d) dVar;
        }
        return null;
    }

    @Override // d7.d
    public final d7.j getContext() {
        return this.f21270o.getContext();
    }

    @Override // ka.w0
    public final Object k() {
        Object obj = this.f21271p;
        this.f21271p = a.f21250c;
        return obj;
    }

    @Override // d7.d
    public final void resumeWith(Object obj) {
        d7.d dVar = this.f21270o;
        d7.j context = dVar.getContext();
        Throwable thA = x6.d0.a(obj);
        Object vVar = thA == null ? obj : new ka.v(thA, false);
        ka.f0 f0Var = this.f21269n;
        if (f0Var.isDispatchNeeded(context)) {
            this.f21271p = vVar;
            this.f19650m = 0;
            f0Var.dispatch(context, this);
            return;
        }
        c1 c1VarA = r2.a();
        if (c1VarA.f19564i >= 4294967296L) {
            this.f21271p = vVar;
            this.f19650m = 0;
            c1VarA.t0(this);
            return;
        }
        c1VarA.u0(true);
        try {
            d7.j context2 = dVar.getContext();
            Object objB = c0.b(context2, this.f21272q);
            try {
                dVar.resumeWith(obj);
                while (c1VarA.J0()) {
                }
            } finally {
                c0.a(context2, objB);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.f21269n + ", " + m0.w(this.f21270o) + ']';
    }

    @Override // ka.w0
    public final d7.d g() {
        return this;
    }
}
