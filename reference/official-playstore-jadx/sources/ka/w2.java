package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class w2 extends pa.v {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ThreadLocal f19651o;
    private volatile boolean threadLocalIsSet;

    /* JADX WARN: Illegal instructions before constructor call */
    public w2(d7.j jVar, d7.d dVar) {
        x2 x2Var = x2.f19657i;
        super(jVar.get(x2Var) == null ? jVar.plus(x2Var) : jVar, dVar);
        this.f19651o = new ThreadLocal();
        if (dVar.getContext().get(d7.f.f14687i) instanceof f0) {
            return;
        }
        Object objB = pa.c0.b(jVar, null);
        pa.c0.a(jVar, objB);
        W(jVar, objB);
    }

    public final boolean U() {
        boolean z = this.threadLocalIsSet && this.f19651o.get() == null;
        this.f19651o.remove();
        return !z;
    }

    public final void W(d7.j jVar, Object obj) {
        this.threadLocalIsSet = true;
        this.f19651o.set(new x6.x(jVar, obj));
    }

    @Override // pa.v, ka.f2
    public final void h(Object obj) {
        if (this.threadLocalIsSet) {
            x6.x xVar = (x6.x) this.f19651o.get();
            if (xVar != null) {
                pa.c0.a((d7.j) xVar.f22608i, xVar.f22609l);
            }
            this.f19651o.remove();
        }
        Object objQ = m0.q(obj);
        d7.d dVar = this.f21296n;
        d7.j context = dVar.getContext();
        Object objB = pa.c0.b(context, null);
        w2 w2VarC = objB != pa.c0.f21257a ? c0.c(dVar, context, objB) : null;
        try {
            this.f21296n.resumeWith(objQ);
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
}
