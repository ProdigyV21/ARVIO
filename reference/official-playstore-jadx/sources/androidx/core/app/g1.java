package androidx.core.app;

import android.app.Notification;

/* JADX INFO: loaded from: classes3.dex */
public abstract class g1 {
    public static Notification.Action.Builder a(Notification.Action.Builder builder, boolean z) {
        return builder.setAuthenticationRequired(z);
    }

    public static Notification.Builder b(Notification.Builder builder, int i10) {
        return builder.setForegroundServiceBehavior(i10);
    }
}
