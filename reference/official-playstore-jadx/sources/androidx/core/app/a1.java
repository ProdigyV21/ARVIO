package androidx.core.app;

import android.app.Notification;
import android.media.AudioAttributes;
import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a1 {
    public static Notification.Builder a(Notification.Builder builder, String str) {
        return builder.addPerson(str);
    }

    public static Notification.Builder b(Notification.Builder builder, String str) {
        return builder.setCategory(str);
    }

    public static Notification.Builder c(Notification.Builder builder, int i10) {
        return builder.setColor(i10);
    }

    public static Notification.Builder d(Notification.Builder builder, Notification notification) {
        return builder.setPublicVersion(notification);
    }

    public static Notification.Builder e(Notification.Builder builder, Uri uri, Object obj) {
        return builder.setSound(uri, (AudioAttributes) obj);
    }

    public static Notification.Builder f(Notification.Builder builder, int i10) {
        return builder.setVisibility(i10);
    }
}
