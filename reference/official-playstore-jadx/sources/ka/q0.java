package ka;

/* JADX INFO: loaded from: classes5.dex */
public class q0 extends a implements p0 {
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
