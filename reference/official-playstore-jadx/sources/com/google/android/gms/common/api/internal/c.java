package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
public final class c implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final c f12706o = new c();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AtomicBoolean f12707i = new AtomicBoolean();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AtomicBoolean f12708l = new AtomicBoolean();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f12709m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f12710n = false;

    public static void b(Application application) {
        c cVar = f12706o;
        synchronized (cVar) {
            try {
                if (!cVar.f12710n) {
                    application.registerActivityLifecycleCallbacks(cVar);
                    application.registerComponentCallbacks(cVar);
                    cVar.f12710n = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void a(b bVar) {
        synchronized (f12706o) {
            this.f12709m.add(bVar);
        }
    }

    public final void c(boolean z) {
        synchronized (f12706o) {
            try {
                Iterator it = this.f12709m.iterator();
                while (it.hasNext()) {
                    ((b) it.next()).a(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean zCompareAndSet = this.f12707i.compareAndSet(true, false);
        this.f12708l.set(true);
        if (zCompareAndSet) {
            c(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        boolean zCompareAndSet = this.f12707i.compareAndSet(true, false);
        this.f12708l.set(true);
        if (zCompareAndSet) {
            c(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i10) {
        if (i10 == 20 && this.f12707i.compareAndSet(false, true)) {
            this.f12708l.set(true);
            c(true);
        }
    }
}
