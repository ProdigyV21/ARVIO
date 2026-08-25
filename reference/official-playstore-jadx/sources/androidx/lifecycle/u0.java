package androidx.lifecycle;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;
import androidx.lifecycle.t0;

/* JADX INFO: loaded from: classes3.dex */
public abstract class u0 {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, p pVar) {
        if (activity instanceof b0) {
            ((b0) activity).getLifecycle().c(pVar);
        } else if (activity instanceof y) {
            r lifecycle = ((y) activity).getLifecycle();
            if (lifecycle instanceof a0) {
                ((a0) lifecycle).c(pVar);
            }
        }
    }

    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            t0.b.Companion.getClass();
            v0.a(activity);
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new t0(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }
}
