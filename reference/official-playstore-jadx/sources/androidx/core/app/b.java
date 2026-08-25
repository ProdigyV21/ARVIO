package androidx.core.app;

import android.app.Activity;
import android.app.SharedElementCallback;

/* JADX INFO: loaded from: classes3.dex */
public abstract class b {
    public static void a(Object obj) {
        ((SharedElementCallback.OnSharedElementsReadyListener) obj).onSharedElementsReady();
    }

    public static void b(Activity activity, String[] strArr, int i10) {
        activity.requestPermissions(strArr, i10);
    }

    public static boolean c(Activity activity, String str) {
        return activity.shouldShowRequestPermissionRationale(str);
    }
}
