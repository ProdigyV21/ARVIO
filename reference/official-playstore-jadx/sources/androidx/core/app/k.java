package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public abstract class k {
    public static int a(AppOpsManager appOpsManager, String str, int i10, String str2) {
        if (appOpsManager == null) {
            return 1;
        }
        return appOpsManager.checkOpNoThrow(str, i10, str2);
    }

    public static String b(Context context) {
        return context.getOpPackageName();
    }

    public static AppOpsManager c(Context context) {
        return (AppOpsManager) context.getSystemService(AppOpsManager.class);
    }
}
