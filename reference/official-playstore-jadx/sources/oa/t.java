package oa;

import ka.m0;
import x6.d0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends f7.c implements na.k {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final na.k f20854i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d7.j f20855l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f20856m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public d7.j f20857n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public d7.d f20858o;

    public t(na.k kVar, d7.j jVar) {
        super(d7.k.f14688i, q.f20851i);
        this.f20854i = kVar;
        this.f20855l = jVar;
        this.f20856m = ((Number) jVar.fold(0, s.f20853i)).intValue();
    }

    @Override // na.k
    public final Object emit(Object obj, d7.d dVar) {
        try {
            Object objG = g(dVar, obj);
            return objG == e7.a.f15033i ? objG : t0.f22605a;
        } catch (Throwable th) {
            this.f20857n = new n(dVar.getContext(), th);
            throw th;
        }
    }

    public final Object g(d7.d dVar, Object obj) {
        d7.j context = dVar.getContext();
        m0.i(context);
        d7.j jVar = this.f20857n;
        if (jVar != context) {
            if (jVar instanceof n) {
                throw new IllegalStateException(f4.f.y("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + ((n) jVar).f20849i + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString());
            }
            if (((Number) context.fold(0, new w(this))).intValue() != this.f20856m) {
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + this.f20855l + ",\n\t\tbut emission happened in " + context + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
            }
            this.f20857n = context;
        }
        this.f20858o = dVar;
        Object objInvoke = v.f20860a.invoke(this.f20854i, obj, this);
        if (!kotlin.jvm.internal.p.a(objInvoke, e7.a.f15033i)) {
            this.f20858o = null;
        }
        return objInvoke;
    }

    @Override // f7.a, f7.d
    public final f7.d getCallerFrame() {
        d7.d dVar = this.f20858o;
        if (dVar instanceof f7.d) {
            return (f7.d) dVar;
        }
        return null;
    }

    @Override // f7.c, d7.d
    public final d7.j getContext() {
        d7.j jVar = this.f20857n;
        return jVar == null ? d7.k.f14688i : jVar;
    }

    @Override // f7.a
    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Throwable thA = d0.a(obj);
        if (thA != null) {
            this.f20857n = new n(getContext(), thA);
        }
        d7.d dVar = this.f20858o;
        if (dVar != null) {
            dVar.resumeWith(obj);
        }
        return e7.a.f15033i;
    }
}
