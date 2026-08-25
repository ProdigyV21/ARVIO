package w5;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
public final class o0 implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final o0 f22474i = new o0();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static boolean f22475l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static androidx.emoji2.text.e0 f22476m;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        androidx.emoji2.text.e0 e0Var = f22476m;
        if (e0Var != null) {
            e0Var.B(2);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        t0 t0Var;
        androidx.emoji2.text.e0 e0Var = f22476m;
        if (e0Var != null) {
            e0Var.B(1);
            t0Var = t0.f22605a;
        } else {
            t0Var = null;
        }
        if (t0Var == null) {
            f22475l = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
