package f7;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c extends a {
    private final d7.j _context;
    private transient d7.d<Object> intercepted;

    public c(d7.j jVar, d7.d dVar) {
        super(dVar);
        this._context = jVar;
    }

    @Override // d7.d
    public d7.j getContext() {
        return this._context;
    }

    public final d7.d<Object> intercepted() {
        d7.d dVarInterceptContinuation = this.intercepted;
        if (dVarInterceptContinuation == null) {
            d7.g gVar = (d7.g) getContext().get(d7.f.f14687i);
            if (gVar == null || (dVarInterceptContinuation = gVar.interceptContinuation(this)) == null) {
                dVarInterceptContinuation = this;
            }
            this.intercepted = dVarInterceptContinuation;
        }
        return dVarInterceptContinuation;
    }

    @Override // f7.a
    public void releaseIntercepted() {
        d7.d<Object> dVar = this.intercepted;
        if (dVar != null && dVar != this) {
            ((d7.g) getContext().get(d7.f.f14687i)).releaseInterceptedContinuation(dVar);
        }
        this.intercepted = b.f15289i;
    }

    public c(d7.d dVar) {
        this(dVar != null ? dVar.getContext() : null, dVar);
    }
}
