package androidx.work.impl.foreground;

import android.app.Notification;
import android.app.Service;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e {
    public static void a(Service service, int i10, Notification notification, int i11) {
        service.startForeground(i10, notification, i11);
    }
}
