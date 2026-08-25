package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class s extends f2 implements r {
    @Override // ka.r
    public final boolean i(Throwable th) {
        return D(new v(th, false));
    }

    @Override // ka.p0
    public final Object k() throws Throwable {
        Object objY = y();
        if (objY instanceof p1) {
            throw new IllegalStateException("This job has not completed yet");
        }
        if (objY instanceof v) {
            throw ((v) objY).f19647a;
        }
        return m0.x(objY);
    }
}
