package androidx.core.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public final class p1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2036d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static n1 f2039g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f2040a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final NotificationManager f2041b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f2035c = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static HashSet f2037e = new HashSet();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object f2038f = new Object();

    public p1(Context context) {
        this.f2040a = context;
        this.f2041b = (NotificationManager) context.getSystemService("notification");
    }

    public final void a(int i10, Notification notification) {
        NotificationManager notificationManager = this.f2041b;
        Bundle bundle = notification.extras;
        if (bundle == null || !bundle.getBoolean("android.support.useSideChannel")) {
            notificationManager.notify(null, i10, notification);
            return;
        }
        k1 k1Var = new k1(this.f2040a.getPackageName(), i10, notification);
        synchronized (f2038f) {
            try {
                if (f2039g == null) {
                    f2039g = new n1(this.f2040a.getApplicationContext());
                }
                f2039g.f2026l.obtainMessage(0, k1Var).sendToTarget();
            } catch (Throwable th) {
                throw th;
            }
        }
        notificationManager.cancel(null, i10);
    }
}
