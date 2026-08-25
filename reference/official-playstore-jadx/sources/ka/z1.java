package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class z1 extends l {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final f2 f19661s;

    public z1(d7.d dVar, f2 f2Var) {
        super(1, dVar);
        this.f19661s = f2Var;
    }

    @Override // ka.l
    public final Throwable q(f2 f2Var) {
        Throwable thB;
        Object objY = this.f19661s.y();
        return (!(objY instanceof b2) || (thB = ((b2) objY).b()) == null) ? objY instanceof v ? ((v) objY).f19647a : f2Var.getCancellationException() : thB;
    }

    @Override // ka.l
    public final String x() {
        return "AwaitContinuation";
    }
}
