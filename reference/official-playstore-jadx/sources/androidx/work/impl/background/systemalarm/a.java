package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a {
    public static void a(AlarmManager alarmManager, int i10, long j10, PendingIntent pendingIntent) {
        alarmManager.setExact(i10, j10, pendingIntent);
    }
}
