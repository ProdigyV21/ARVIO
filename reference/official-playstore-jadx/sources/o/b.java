package o;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.core.os.j;
import androidx.loader.content.f;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class b extends ac.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f20718d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ExecutorService f20719e = Executors.newFixedThreadPool(4, new f(1));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile Handler f20720f;

    public static Handler Z(Looper looper) {
        if (Build.VERSION.SDK_INT >= 28) {
            return j.b(looper);
        }
        try {
            return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException unused) {
            return new Handler(looper);
        } catch (InvocationTargetException unused2) {
            return new Handler(looper);
        }
    }
}
