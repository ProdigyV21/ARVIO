package z4;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import com.google.android.gms.common.internal.t;
import com.google.firebase.FirebaseCommonRegistrar;
import com.google.firebase.components.ComponentDiscoveryService;
import com.google.firebase.components.o;
import com.google.firebase.concurrent.ExecutorsRegistrar;
import com.google.firebase.concurrent.p;
import com.google.firebase.provider.FirebaseInitProvider;
import io.ktor.http.ContentDisposition;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class g {
    public static final Object k = new Object();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final t.e f23183l = new t.e(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f23184a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f23185b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final j f23186c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final com.google.firebase.components.j f23187d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicBoolean f23188e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AtomicBoolean f23189f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final o f23190g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final m5.b f23191h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final CopyOnWriteArrayList f23192i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final CopyOnWriteArrayList f23193j;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.util.List] */
    public g(Context context, String str, j jVar) {
        ?? arrayList;
        int i10 = 0;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f23188e = atomicBoolean;
        this.f23189f = new AtomicBoolean();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.f23192i = copyOnWriteArrayList;
        this.f23193j = new CopyOnWriteArrayList();
        this.f23184a = context;
        t.f(str);
        this.f23185b = str;
        this.f23186c = jVar;
        a aVar = FirebaseInitProvider.f14391i;
        Trace.beginSection("Firebase");
        Trace.beginSection("ComponentDiscovery");
        ArrayList arrayList2 = new ArrayList();
        Bundle bundle = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                Log.w("ComponentDiscovery", "Context has no PackageManager.");
            } else {
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, (Class<?>) ComponentDiscoveryService.class), 128);
                if (serviceInfo == null) {
                    Log.w("ComponentDiscovery", ComponentDiscoveryService.class + " has no service info.");
                } else {
                    bundle = serviceInfo.metaData;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("ComponentDiscovery", "Application info not found.");
        }
        if (bundle == null) {
            Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
            arrayList = Collections.EMPTY_LIST;
        } else {
            arrayList = new ArrayList();
            for (String str2 : bundle.keySet()) {
                if ("com.google.firebase.components.ComponentRegistrar".equals(bundle.get(str2)) && str2.startsWith("com.google.firebase.components:")) {
                    arrayList.add(str2.substring(31));
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new com.google.firebase.components.d((String) it.next(), i10));
        }
        Trace.endSection();
        Trace.beginSection("Runtime");
        p pVar = p.f14343i;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        arrayList3.addAll(arrayList2);
        int i11 = 1;
        arrayList3.add(new com.google.firebase.components.d(new FirebaseCommonRegistrar(), i11));
        arrayList3.add(new com.google.firebase.components.d(new ExecutorsRegistrar(), i11));
        arrayList4.add(com.google.firebase.components.b.c(context, Context.class, new Class[0]));
        arrayList4.add(com.google.firebase.components.b.c(this, g.class, new Class[0]));
        arrayList4.add(com.google.firebase.components.b.c(jVar, j.class, new Class[0]));
        v1.i iVar = new v1.i();
        if (ac.b.R(context) && FirebaseInitProvider.f14392l.get()) {
            arrayList4.add(com.google.firebase.components.b.c(aVar, a.class, new Class[0]));
        }
        com.google.firebase.components.j jVar2 = new com.google.firebase.components.j(arrayList3, arrayList4, iVar);
        this.f23187d = jVar2;
        Trace.endSection();
        this.f23190g = new o(new com.google.firebase.components.i(this, context, 2));
        this.f23191h = jVar2.e(l5.c.class);
        d dVar = new d() { // from class: z4.c
            @Override // z4.d
            public final void a(boolean z) {
                if (z) {
                    return;
                }
                ((l5.c) this.f23179a.f23191h.get()).b();
            }
        };
        a();
        if (atomicBoolean.get()) {
            com.google.android.gms.common.api.internal.c.f12706o.f12707i.get();
        }
        copyOnWriteArrayList.add(dVar);
        Trace.endSection();
    }

    public static g c() {
        g gVar;
        synchronized (k) {
            try {
                gVar = (g) f23183l.get("[DEFAULT]");
                if (gVar == null) {
                    throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + c4.g.a() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
                }
                ((l5.c) gVar.f23191h.get()).b();
            } catch (Throwable th) {
                throw th;
            }
        }
        return gVar;
    }

    public static g f(Context context) {
        synchronized (k) {
            try {
                if (f23183l.containsKey("[DEFAULT]")) {
                    return c();
                }
                j jVarA = j.a(context);
                if (jVarA == null) {
                    Log.w("FirebaseApp", "Default FirebaseApp failed to initialize because no default options were found. This usually means that com.google.gms:google-services was not applied to your gradle project.");
                    return null;
                }
                return g(context, jVarA);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static g g(Context context, j jVar) {
        g gVar;
        AtomicReference atomicReference = e.f23180a;
        if (context.getApplicationContext() instanceof Application) {
            Application application = (Application) context.getApplicationContext();
            AtomicReference atomicReference2 = e.f23180a;
            if (atomicReference2.get() == null) {
                e eVar = new e();
                while (true) {
                    if (atomicReference2.compareAndSet(null, eVar)) {
                        com.google.android.gms.common.api.internal.c.b(application);
                        com.google.android.gms.common.api.internal.c.f12706o.a(eVar);
                        break;
                    }
                    if (atomicReference2.get() != null) {
                        break;
                    }
                }
            }
        }
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (k) {
            t.e eVar2 = f23183l;
            t.k("FirebaseApp name [DEFAULT] already exists!", !eVar2.containsKey("[DEFAULT]"));
            t.j(context, "Application context cannot be null.");
            gVar = new g(context, "[DEFAULT]", jVar);
            eVar2.put("[DEFAULT]", gVar);
        }
        gVar.e();
        return gVar;
    }

    public final void a() {
        t.k("FirebaseApp was deleted", !this.f23189f.get());
    }

    public final Object b(Class cls) {
        a();
        return this.f23187d.a(cls);
    }

    public final String d() {
        StringBuilder sb2 = new StringBuilder();
        a();
        sb2.append(c4.d.b(this.f23185b.getBytes(Charset.defaultCharset())));
        sb2.append("+");
        a();
        sb2.append(c4.d.b(this.f23186c.f23200b.getBytes(Charset.defaultCharset())));
        return sb2.toString();
    }

    public final void e() {
        HashMap map;
        if (!ac.b.R(this.f23184a)) {
            StringBuilder sb2 = new StringBuilder("Device in Direct Boot Mode: postponing initialization of Firebase APIs for app ");
            a();
            sb2.append(this.f23185b);
            Log.i("FirebaseApp", sb2.toString());
            Context context = this.f23184a;
            AtomicReference atomicReference = f.f23181b;
            if (atomicReference.get() == null) {
                f fVar = new f(context);
                while (!atomicReference.compareAndSet(null, fVar)) {
                    if (atomicReference.get() != null) {
                        return;
                    }
                }
                context.registerReceiver(fVar, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                return;
            }
            return;
        }
        StringBuilder sb3 = new StringBuilder("Device unlocked: initializing all Firebase APIs for app ");
        a();
        sb3.append(this.f23185b);
        Log.i("FirebaseApp", sb3.toString());
        com.google.firebase.components.j jVar = this.f23187d;
        a();
        boolean zEquals = "[DEFAULT]".equals(this.f23185b);
        AtomicReference atomicReference2 = jVar.f14274f;
        Boolean boolValueOf = Boolean.valueOf(zEquals);
        while (true) {
            if (atomicReference2.compareAndSet(null, boolValueOf)) {
                synchronized (jVar) {
                    map = new HashMap(jVar.f14269a);
                }
                jVar.h(map, zEquals);
                break;
            } else if (atomicReference2.get() != null) {
                break;
            }
        }
        ((l5.c) this.f23191h.get()).b();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        gVar.a();
        return this.f23185b.equals(gVar.f23185b);
    }

    public final int hashCode() {
        return this.f23185b.hashCode();
    }

    public final String toString() {
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(this);
        cVar.w(this.f23185b, ContentDisposition.Parameters.Name);
        cVar.w(this.f23186c, "options");
        return cVar.toString();
    }
}
