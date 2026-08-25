package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.r;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public class RescheduleReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f6729a = t.f("RescheduleReceiver");

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        t.d().a(f6729a, "Received intent " + intent);
        try {
            r rVarZ = r.Z(context);
            BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
            rVarZ.getClass();
            synchronized (r.f6963m) {
                try {
                    BroadcastReceiver.PendingResult pendingResult = rVarZ.f6972i;
                    if (pendingResult != null) {
                        pendingResult.finish();
                    }
                    rVarZ.f6972i = pendingResultGoAsync;
                    if (rVarZ.f6971h) {
                        pendingResultGoAsync.finish();
                        rVarZ.f6972i = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (IllegalStateException e5) {
            t.d().c(f6729a, "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e5);
        }
    }
}
