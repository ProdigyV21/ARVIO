package la;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import x6.c0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f20078a = 0;
    private static volatile Choreographer choreographer;

    static {
        Object c0Var;
        try {
            c0Var = new d(a(Looper.getMainLooper()));
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        if (c0Var instanceof c0) {
            c0Var = null;
        }
    }

    public static final Handler a(Looper looper) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (Handler) Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        }
        try {
            return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
        } catch (NoSuchMethodException unused) {
            return new Handler(looper);
        }
    }
}
