package ka;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes5.dex */
public final class e1 extends f1 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final t2 f19580m;

    public e1(long j10, t2 t2Var) {
        super(j10);
        this.f19580m = t2Var;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, InvocationTargetException {
        this.f19580m.run();
    }

    @Override // ka.f1
    public final String toString() {
        return super.toString() + this.f19580m;
    }
}
