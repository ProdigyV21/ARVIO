package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public final class h implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f1987i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Activity f1988l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1989m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1990n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1991o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1992p = false;

    public h(Activity activity) {
        this.f1988l = activity;
        this.f1989m = activity.hashCode();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        if (this.f1988l == activity) {
            this.f1988l = null;
            this.f1991o = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        if (!this.f1991o || this.f1992p || this.f1990n) {
            return;
        }
        Object obj = this.f1987i;
        try {
            Object obj2 = i.f1999c.get(activity);
            if (obj2 == obj && activity.hashCode() == this.f1989m) {
                i.f2003g.postAtFrontOfQueue(new com.google.common.util.concurrent.w0(i.f1998b.get(activity), obj2, 3));
                this.f1992p = true;
                this.f1987i = null;
            }
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        if (this.f1988l == activity) {
            this.f1990n = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
