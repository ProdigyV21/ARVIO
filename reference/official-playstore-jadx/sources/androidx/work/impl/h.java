package androidx.work.impl;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends z1.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f6889c = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f6890d;

    public h(Context context, int i10, int i11) {
        super(i10, i11);
        this.f6890d = context;
    }

    @Override // z1.b
    public final void a(d2.c cVar) {
        int i10 = this.f6889c;
        Context context = this.f6890d;
        switch (i10) {
            case 0:
                if (this.f23159b >= 10) {
                    cVar.i(new Object[]{"reschedule_needed", 1});
                    return;
                } else {
                    context.getSharedPreferences("androidx.work.util.preferences", 0).edit().putBoolean("reschedule_needed", true).apply();
                    return;
                }
            default:
                cVar.p("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
                SharedPreferences sharedPreferences = context.getSharedPreferences("androidx.work.util.preferences", 0);
                if (sharedPreferences.contains("reschedule_needed") || sharedPreferences.contains("last_cancel_all_time_ms")) {
                    long j10 = sharedPreferences.getLong("last_cancel_all_time_ms", 0L);
                    long j11 = sharedPreferences.getBoolean("reschedule_needed", false) ? 1L : 0L;
                    cVar.m();
                    try {
                        cVar.i(new Object[]{"last_cancel_all_time_ms", Long.valueOf(j10)});
                        cVar.i(new Object[]{"reschedule_needed", Long.valueOf(j11)});
                        sharedPreferences.edit().clear().apply();
                        cVar.C();
                    } finally {
                    }
                }
                SharedPreferences sharedPreferences2 = context.getSharedPreferences("androidx.work.util.id", 0);
                if (sharedPreferences2.contains("next_job_scheduler_id") || sharedPreferences2.contains("next_job_scheduler_id")) {
                    int i11 = sharedPreferences2.getInt("next_job_scheduler_id", 0);
                    int i12 = sharedPreferences2.getInt("next_alarm_manager_id", 0);
                    cVar.m();
                    try {
                        cVar.i(new Object[]{"next_job_scheduler_id", Integer.valueOf(i11)});
                        cVar.i(new Object[]{"next_alarm_manager_id", Integer.valueOf(i12)});
                        sharedPreferences2.edit().clear().apply();
                        cVar.C();
                        return;
                    } finally {
                    }
                }
                return;
        }
    }

    public h(Context context) {
        super(9, 10);
        this.f6890d = context;
    }
}
