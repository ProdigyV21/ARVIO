package vc;

import com.google.android.gms.cast.CredentialsData;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import v1.i;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile int f22330a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final androidx.loader.app.d f22331b = new androidx.loader.app.d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final i f22332c = new i();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f22333d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f22334e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f22335f;

    static {
        String property;
        try {
            property = System.getProperty("slf4j.detectLoggerNameMismatch");
        } catch (SecurityException unused) {
            property = null;
        }
        f22333d = property == null ? false : property.equalsIgnoreCase("true");
        f22334e = new String[]{"1.6", "1.7"};
        f22335f = "org/slf4j/impl/StaticLoggerBinder.class";
    }

    public static final void a() {
        LinkedHashSet linkedHashSetB;
        try {
            try {
                try {
                    if (e()) {
                        linkedHashSetB = null;
                    } else {
                        linkedHashSetB = b();
                        h(linkedHashSetB);
                    }
                    StaticLoggerBinder.getSingleton();
                    f22330a = 3;
                    g(linkedHashSetB);
                    f();
                } catch (Exception e5) {
                    f22330a = 2;
                    xc.d.I0("Failed to instantiate SLF4J LoggerFactory", e5);
                    throw new IllegalStateException("Unexpected initialization failure", e5);
                }
            } catch (NoClassDefFoundError e6) {
                String message = e6.getMessage();
                if (message == null || (!message.contains("org/slf4j/impl/StaticLoggerBinder") && !message.contains("org.slf4j.impl.StaticLoggerBinder"))) {
                    f22330a = 2;
                    xc.d.I0("Failed to instantiate SLF4J LoggerFactory", e6);
                    throw e6;
                }
                f22330a = 4;
                xc.d.H0("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                xc.d.H0("Defaulting to no-operation (NOP) logger implementation");
                xc.d.H0("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
                f();
            } catch (NoSuchMethodError e10) {
                String message2 = e10.getMessage();
                if (message2 != null && message2.contains("org.slf4j.impl.StaticLoggerBinder.getSingleton()")) {
                    f22330a = 2;
                    xc.d.H0("slf4j-api 1.6.x (or later) is incompatible with this binding.");
                    xc.d.H0("Your binding is version 1.5.5 or earlier.");
                    xc.d.H0("Upgrade your binding to version 1.6.x.");
                }
                throw e10;
            }
        } catch (Throwable th) {
            f();
            throw th;
        }
    }

    public static LinkedHashSet b() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            ClassLoader classLoader = c.class.getClassLoader();
            String str = f22335f;
            Enumeration<URL> systemResources = classLoader == null ? ClassLoader.getSystemResources(str) : classLoader.getResources(str);
            while (systemResources.hasMoreElements()) {
                linkedHashSet.add(systemResources.nextElement());
            }
            return linkedHashSet;
        } catch (IOException e5) {
            xc.d.I0("Error getting resources from path", e5);
            return linkedHashSet;
        }
    }

    public static ILoggerFactory c() {
        if (f22330a == 0) {
            synchronized (c.class) {
                try {
                    if (f22330a == 0) {
                        f22330a = 1;
                        a();
                        if (f22330a == 3) {
                            i();
                        }
                    }
                } finally {
                }
            }
        }
        int i10 = f22330a;
        if (i10 == 1) {
            return f22331b;
        }
        if (i10 == 2) {
            throw new IllegalStateException("org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
        }
        if (i10 == 3) {
            return StaticLoggerBinder.getSingleton().getLoggerFactory();
        }
        if (i10 == 4) {
            return f22332c;
        }
        throw new IllegalStateException("Unreachable code");
    }

    public static b d(String str) {
        return c().a(str);
    }

    public static boolean e() {
        String property;
        try {
            property = System.getProperty("java.vendor.url");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            return false;
        }
        return property.toLowerCase().contains(CredentialsData.CREDENTIALS_TYPE_ANDROID);
    }

    public static void f() {
        androidx.loader.app.d dVar = f22331b;
        synchronized (dVar) {
            try {
                dVar.f3323l = true;
                for (xc.b bVar : new ArrayList(((HashMap) dVar.f3324m).values())) {
                    bVar.f22813l = d(bVar.f22812i);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        LinkedBlockingQueue linkedBlockingQueue = (LinkedBlockingQueue) f22331b.f3325n;
        int size = linkedBlockingQueue.size();
        ArrayList<wc.d> arrayList = new ArrayList(128);
        int i10 = 0;
        while (linkedBlockingQueue.drainTo(arrayList, 128) != 0) {
            for (wc.d dVar2 : arrayList) {
                if (dVar2 != null) {
                    xc.b bVar2 = dVar2.f22547a;
                    String str = bVar2.f22812i;
                    if (bVar2.f22813l == null) {
                        throw new IllegalStateException("Delegate logger cannot be null at this state.");
                    }
                    if (!(bVar2.f22813l instanceof xc.a)) {
                        if (!bVar2.j()) {
                            xc.d.H0(str);
                        } else if (bVar2.j()) {
                            try {
                                bVar2.f22815n.invoke(bVar2.f22813l, dVar2);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                            }
                        }
                    }
                }
                int i11 = i10 + 1;
                if (i10 == 0) {
                    if (dVar2.f22547a.j()) {
                        xc.d.H0("A number (" + size + ") of logging calls during the initialization phase have been intercepted and are");
                        xc.d.H0("now being replayed. These are subject to the filtering rules of the underlying logging system.");
                        xc.d.H0("See also http://www.slf4j.org/codes.html#replay");
                    } else if (!(dVar2.f22547a.f22813l instanceof xc.a)) {
                        xc.d.H0("The following set of substitute loggers may have been accessed");
                        xc.d.H0("during the initialization phase. Logging calls during this");
                        xc.d.H0("phase were not honored. However, subsequent logging calls to these");
                        xc.d.H0("loggers will work as normally expected.");
                        xc.d.H0("See also http://www.slf4j.org/codes.html#substituteLogger");
                    }
                }
                i10 = i11;
            }
            arrayList.clear();
        }
        androidx.loader.app.d dVar3 = f22331b;
        ((HashMap) dVar3.f3324m).clear();
        ((LinkedBlockingQueue) dVar3.f3325n).clear();
    }

    public static void g(LinkedHashSet linkedHashSet) {
        if (linkedHashSet == null || linkedHashSet.size() <= 1) {
            return;
        }
        xc.d.H0("Actual binding is of type [" + StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr() + "]");
    }

    public static void h(LinkedHashSet linkedHashSet) {
        if (linkedHashSet.size() > 1) {
            xc.d.H0("Class path contains multiple SLF4J bindings.");
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                xc.d.H0("Found binding in [" + ((URL) it.next()) + "]");
            }
            xc.d.H0("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    public static final void i() {
        try {
            String str = StaticLoggerBinder.REQUESTED_API_VERSION;
            boolean z = false;
            for (String str2 : f22334e) {
                if (str.startsWith(str2)) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
            xc.d.H0("The requested version " + str + " by your slf4j binding is not compatible with " + Arrays.asList(f22334e).toString());
            xc.d.H0("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
        } catch (NoSuchFieldError unused) {
        } catch (Throwable th) {
            xc.d.I0("Unexpected problem occured during version sanity check", th);
        }
    }
}
