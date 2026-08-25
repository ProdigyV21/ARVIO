package pa;

/* JADX INFO: loaded from: classes5.dex */
public abstract class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f21299a = 0;

    static {
        Object c0Var;
        Object c0Var2;
        Exception exc = new Exception();
        String simpleName = a.a.class.getSimpleName();
        StackTraceElement stackTraceElement = exc.getStackTrace()[0];
        new StackTraceElement("_COROUTINE.".concat(simpleName), "_", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
        try {
            c0Var = f7.a.class.getCanonicalName();
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (x6.d0.a(c0Var) != null) {
            c0Var = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        try {
            c0Var2 = x.class.getCanonicalName();
        } catch (Throwable th2) {
            c0Var2 = new x6.c0(th2);
        }
        if (x6.d0.a(c0Var2) != null) {
            c0Var2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
    }
}
