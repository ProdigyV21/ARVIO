package androidx.work.impl.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.media3.common.C;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements Runnable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final String f7003o = m2.t.f("ForceStopRunnable");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final long f7004p = TimeUnit.DAYS.toMillis(3650);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f7005i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final androidx.work.impl.r f7006l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f f7007m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f7008n = 0;

    public d(Context context, androidx.work.impl.r rVar) {
        this.f7005i = context.getApplicationContext();
        this.f7006l = rVar;
        this.f7007m = rVar.f6970g;
    }

    public static void c(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        int i10 = Build.VERSION.SDK_INT >= 31 ? 167772160 : C.BUFFER_FLAG_FIRST_SAMPLE;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) ForceStopRunnable$BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, -1, intent, i10);
        long jCurrentTimeMillis = System.currentTimeMillis() + f7004p;
        if (alarmManager != null) {
            alarmManager.setExact(0, jCurrentTimeMillis, broadcast);
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            Method dump skipped, instruction units count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.d.a():void");
    }

    public final boolean b() {
        this.f7006l.f6965b.getClass();
        boolean zIsEmpty = TextUtils.isEmpty(null);
        String str = f7003o;
        if (zIsEmpty) {
            m2.t.d().a(str, "The default process name was not specified.");
            return true;
        }
        boolean zA = k.a(this.f7005i);
        m2.t.d().a(str, "Is default app process = " + zA);
        return zA;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.f7005i;
        String str = f7003o;
        androidx.work.impl.r rVar = this.f7006l;
        try {
            if (!b()) {
                return;
            }
            while (true) {
                try {
                    xc.d.t0(context);
                    m2.t.d().a(str, "Performing cleanup operations.");
                    try {
                        a();
                        return;
                    } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteDiskIOException | SQLiteTableLockedException e5) {
                        int i10 = this.f7008n + 1;
                        this.f7008n = i10;
                        if (i10 >= 3) {
                            String str2 = ac.b.R(context) ? "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store." : "WorkManager can't be accessed from direct boot, because credential encrypted storage isn't accessible.\nDon't access or initialise WorkManager from directAware components. See https://developer.android.com/training/articles/direct-boot";
                            m2.t.d().c(str, str2, e5);
                            IllegalStateException illegalStateException = new IllegalStateException(str2, e5);
                            rVar.f6965b.getClass();
                            throw illegalStateException;
                        }
                        long j10 = ((long) i10) * 300;
                        String str3 = "Retrying after " + j10;
                        if (m2.t.d().f20216a <= 3) {
                            Log.d(str, str3, e5);
                        }
                        try {
                            Thread.sleep(((long) this.f7008n) * 300);
                        } catch (InterruptedException unused) {
                        }
                    }
                } catch (SQLiteException e6) {
                    m2.t.d().b(str, "Unexpected SQLite exception during migrations");
                    IllegalStateException illegalStateException2 = new IllegalStateException("Unexpected SQLite exception during migrations", e6);
                    rVar.f6965b.getClass();
                    throw illegalStateException2;
                }
            }
        } finally {
            rVar.b0();
        }
    }
}
