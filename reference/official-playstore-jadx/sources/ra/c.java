package ra;

import java.util.concurrent.Executor;
import ka.f0;
import ka.j1;
import pa.y;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends j1 implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c f21424i = new c();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final f0 f21425l;

    static {
        l lVar = l.f21440i;
        int i10 = y.f21300a;
        if (64 >= i10) {
            i10 = 64;
        }
        f21425l = lVar.limitedParallelism(pa.a.j(i10, 12, "kotlinx.coroutines.io.parallelism"));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    @Override // ka.f0
    public final void dispatch(d7.j jVar, Runnable runnable) {
        f21425l.dispatch(jVar, runnable);
    }

    @Override // ka.f0
    public final void dispatchYield(d7.j jVar, Runnable runnable) {
        f21425l.dispatchYield(jVar, runnable);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        dispatch(d7.k.f14688i, runnable);
    }

    @Override // ka.f0
    public final f0 limitedParallelism(int i10) {
        return l.f21440i.limitedParallelism(i10);
    }

    @Override // ka.f0
    public final String toString() {
        return "Dispatchers.IO";
    }
}
