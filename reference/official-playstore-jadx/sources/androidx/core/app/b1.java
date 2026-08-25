package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.drawable.Icon;

/* JADX INFO: loaded from: classes3.dex */
public abstract class b1 {
    public static Notification.Action.Builder a(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
        return new Notification.Action.Builder(icon, charSequence, pendingIntent);
    }

    public static Notification.Builder b(Notification.Builder builder, Icon icon) {
        return builder.setLargeIcon(icon);
    }

    public static Notification.Builder c(Notification.Builder builder, Object obj) {
        return builder.setSmallIcon((Icon) obj);
    }
}
