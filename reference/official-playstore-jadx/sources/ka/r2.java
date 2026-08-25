package ka;

/* JADX INFO: loaded from: classes5.dex */
public abstract class r2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f19629a = new ThreadLocal();

    public static c1 a() {
        ThreadLocal threadLocal = f19629a;
        c1 c1Var = (c1) threadLocal.get();
        if (c1Var != null) {
            return c1Var;
        }
        h hVar = new h(Thread.currentThread());
        threadLocal.set(hVar);
        return hVar;
    }
}
