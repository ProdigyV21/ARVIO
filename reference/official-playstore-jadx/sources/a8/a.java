package a8;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f133a = 0;

    static {
        Object c0Var;
        try {
            c0Var = Class.forName("java.lang.ClassValue");
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (!(c0Var instanceof x6.c0)) {
            c0Var = Boolean.TRUE;
        }
        Object obj = Boolean.FALSE;
        if (c0Var instanceof x6.c0) {
            c0Var = obj;
        }
    }
}
