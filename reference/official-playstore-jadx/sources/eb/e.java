package eb;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f15127a;

    static {
        Object c0Var;
        try {
            c0Var = kotlin.text.u.R(System.getProperty("kotlinx.serialization.json.pool.size"));
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = null;
        }
        Integer num = (Integer) c0Var;
        f15127a = num != null ? num.intValue() : 2097152;
    }
}
