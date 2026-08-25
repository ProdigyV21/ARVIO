package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.WorkDatabase;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f6733a = t.f("Alarms");

    public static void a(Context context, androidx.work.impl.model.j jVar, int i10) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        String str = c.f6734p;
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        c.e(intent, jVar);
        PendingIntent service = PendingIntent.getService(context, i10, intent, 603979776);
        if (service == null || alarmManager == null) {
            return;
        }
        t.d().a(f6733a, "Cancelling existing alarm with (workSpecId, systemId) (" + jVar + ", " + i10 + ")");
        alarmManager.cancel(service);
    }

    public static void b(Context context, WorkDatabase workDatabase, androidx.work.impl.model.j jVar, long j10) {
        androidx.work.impl.model.h hVarP = workDatabase.p();
        androidx.work.impl.model.g gVarD = hVarP.d(jVar);
        if (gVarD != null) {
            int i10 = gVarD.f6912c;
            a(context, jVar, i10);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            String str = c.f6734p;
            Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
            intent.setAction("ACTION_DELAY_MET");
            c.e(intent, jVar);
            PendingIntent service = PendingIntent.getService(context, i10, intent, 201326592);
            if (alarmManager != null) {
                a.a(alarmManager, 0, j10, service);
                return;
            }
            return;
        }
        int iIntValue = ((Number) workDatabase.n(new l5.b(new androidx.work.impl.utils.f(workDatabase), 2))).intValue();
        hVarP.k(new androidx.work.impl.model.g(jVar.f6914a, jVar.f6915b, iIntValue));
        AlarmManager alarmManager2 = (AlarmManager) context.getSystemService("alarm");
        String str2 = c.f6734p;
        Intent intent2 = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent2.setAction("ACTION_DELAY_MET");
        c.e(intent2, jVar);
        PendingIntent service2 = PendingIntent.getService(context, iIntValue, intent2, 201326592);
        if (alarmManager2 != null) {
            a.a(alarmManager2, 0, j10, service2);
        }
    }
}
