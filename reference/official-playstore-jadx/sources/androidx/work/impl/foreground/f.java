package androidx.work.impl.foreground;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.Service;
import android.util.Log;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public abstract class f {
    public static void a(Service service, int i10, Notification notification, int i11) {
        try {
            service.startForeground(i10, notification, i11);
        } catch (ForegroundServiceStartNotAllowedException e5) {
            t tVarD = t.d();
            String str = SystemForegroundService.f6859p;
            if (tVarD.f20216a <= 5) {
                Log.w(str, "Unable to start foreground service", e5);
            }
        }
    }
}
