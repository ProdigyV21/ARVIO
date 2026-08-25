package androidx.core.app;

import android.app.NotificationManager;

/* JADX INFO: loaded from: classes3.dex */
public abstract class i1 {
    public static boolean a(NotificationManager notificationManager) {
        return notificationManager.areNotificationsEnabled();
    }

    public static int b(NotificationManager notificationManager) {
        return notificationManager.getImportance();
    }
}
