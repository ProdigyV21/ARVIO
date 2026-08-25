package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager;

/* JADX INFO: loaded from: classes3.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f7052a = m2.t.f("WakeLocks");

    public static final PowerManager.WakeLock a(Context context, String str) {
        PowerManager powerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        String strConcat = "WorkManager: ".concat(str);
        PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, strConcat);
        synchronized (o.f7053a) {
        }
        return wakeLockNewWakeLock;
    }
}
