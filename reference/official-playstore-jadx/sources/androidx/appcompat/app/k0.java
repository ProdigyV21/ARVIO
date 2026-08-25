package androidx.appcompat.app;

import android.app.Activity;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import j$.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class k0 {
    public static OnBackInvokedDispatcher a(Activity activity) {
        return activity.getOnBackInvokedDispatcher();
    }

    public static OnBackInvokedCallback b(Object obj, s0 s0Var) {
        Objects.requireNonNull(s0Var);
        androidx.activity.g0 g0Var = new androidx.activity.g0(s0Var, 1);
        j0.d(obj).registerOnBackInvokedCallback(1000000, g0Var);
        return g0Var;
    }

    public static void c(Object obj, Object obj2) {
        j0.d(obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
    }
}
