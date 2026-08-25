package ka;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c1 extends f0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f19563n = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f19564i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f19565l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public kotlin.collections.p f19566m;

    public abstract long D0();

    public final boolean J0() throws IllegalAccessException, InvocationTargetException {
        kotlin.collections.p pVar = this.f19566m;
        if (pVar == null) {
            return false;
        }
        w0 w0Var = (w0) (pVar.isEmpty() ? null : pVar.removeFirst());
        if (w0Var == null) {
            return false;
        }
        w0Var.run();
        return true;
    }

    @Override // ka.f0
    public final f0 limitedParallelism(int i10) {
        pa.a.a(i10);
        return this;
    }

    public final void q0(boolean z) {
        long j10 = this.f19564i - (z ? 4294967296L : 1L);
        this.f19564i = j10;
        if (j10 <= 0 && this.f19565l) {
            shutdown();
        }
    }

    public abstract void shutdown();

    public final void t0(w0 w0Var) {
        kotlin.collections.p pVar = this.f19566m;
        if (pVar == null) {
            pVar = new kotlin.collections.p();
            this.f19566m = pVar;
        }
        pVar.addLast(w0Var);
    }

    public final void u0(boolean z) {
        this.f19564i = (z ? 4294967296L : 1L) + this.f19564i;
        if (z) {
            return;
        }
        this.f19565l = true;
    }
}
