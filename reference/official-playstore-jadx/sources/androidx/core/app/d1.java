package androidx.core.app;

import android.app.Notification;
import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d1 {
    public static Notification.Builder a(Context context, String str) {
        return new Notification.Builder(context, str);
    }

    public static Notification.Builder b(Notification.Builder builder, int i10) {
        return builder.setBadgeIconType(i10);
    }

    public static Notification.Builder c(Notification.Builder builder, boolean z) {
        return builder.setColorized(z);
    }

    public static Notification.Builder d(Notification.Builder builder, int i10) {
        return builder.setGroupAlertBehavior(i10);
    }

    public static Notification.Builder e(Notification.Builder builder, CharSequence charSequence) {
        return builder.setSettingsText(charSequence);
    }

    public static Notification.Builder f(Notification.Builder builder, String str) {
        return builder.setShortcutId(str);
    }

    public static Notification.Builder g(Notification.Builder builder, long j10) {
        return builder.setTimeoutAfter(j10);
    }
}
