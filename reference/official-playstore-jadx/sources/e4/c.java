package e4;

import a8.e;
import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f15031b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f15032a;

    static {
        c cVar = new c();
        cVar.f15032a = null;
        f15031b = cVar;
    }

    public static e a(Context context) {
        e eVar;
        c cVar = f15031b;
        synchronized (cVar) {
            try {
                if (cVar.f15032a == null) {
                    if (context.getApplicationContext() != null) {
                        context = context.getApplicationContext();
                    }
                    cVar.f15032a = new e(context, 15);
                }
                eVar = cVar.f15032a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return eVar;
    }
}
