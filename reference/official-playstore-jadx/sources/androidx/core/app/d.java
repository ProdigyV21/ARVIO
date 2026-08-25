package androidx.core.app;

import android.app.Activity;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d {
    public static boolean a(Activity activity, String str) {
        return activity.shouldShowRequestPermissionRationale(str);
    }
}
