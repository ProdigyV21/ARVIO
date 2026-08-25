package pa;

import java.util.Iterator;
import java.util.ServiceLoader;
import ka.h2;
import kotlinx.coroutines.internal.MainDispatcherFactory;

/* JADX INFO: loaded from: classes5.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h2 f21294a;

    static {
        String property;
        int i10 = y.f21300a;
        Object next = null;
        try {
            property = System.getProperty("kotlinx.coroutines.fast.service.loader");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property != null) {
            Boolean.parseBoolean(property);
        }
        Iterator it = ga.r.U(ga.r.E(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator())).iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                ((MainDispatcherFactory) next).getClass();
                do {
                    ((MainDispatcherFactory) it.next()).getClass();
                } while (it.hasNext());
            }
        }
        MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) next;
        if (mainDispatcherFactory == null) {
            throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'");
        }
        f21294a = mainDispatcherFactory.a();
    }
}
