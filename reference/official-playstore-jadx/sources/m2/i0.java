package m2;

import android.content.Context;
import androidx.fragment.app.a2;
import androidx.work.WorkerParameters;

/* JADX INFO: loaded from: classes3.dex */
public abstract class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f20206a = t.f("WorkerFactory");

    public abstract s a(Context context, String str, WorkerParameters workerParameters);

    public final s b(Context context, String str, WorkerParameters workerParameters) {
        Class clsAsSubclass;
        String str2 = f20206a;
        s sVarA = a(context, str, workerParameters);
        if (sVarA == null) {
            try {
                clsAsSubclass = Class.forName(str).asSubclass(s.class);
            } catch (Throwable th) {
                t.d().c(str2, "Invalid class: " + str, th);
                clsAsSubclass = null;
            }
            if (clsAsSubclass != null) {
                try {
                    sVarA = (s) clsAsSubclass.getDeclaredConstructor(Context.class, WorkerParameters.class).newInstance(context, workerParameters);
                } catch (Throwable th2) {
                    t.d().c(str2, "Could not instantiate " + str, th2);
                }
            }
        }
        if (sVarA == null || !sVarA.isUsed()) {
            return sVarA;
        }
        throw new IllegalStateException(a2.m("WorkerFactory (", getClass().getName(), ") returned an instance of a ListenableWorker (", str, ") which has already been invoked. createWorker() must always return a new instance of a ListenableWorker."));
    }
}
