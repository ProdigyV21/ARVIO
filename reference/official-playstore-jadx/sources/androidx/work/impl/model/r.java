package androidx.work.impl.model;

import android.database.Cursor;
import androidx.media3.exoplayer.offline.DownloadService;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.work.impl.WorkDatabase_Impl;
import java.util.ArrayList;
import y1.r0;

/* JADX INFO: loaded from: classes3.dex */
public final class r implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WorkDatabase_Impl f6944a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f6945b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i f6946c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i f6947d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i f6948e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i f6949f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i f6950g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final i f6951h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i f6952i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final i f6953j;
    public final i k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i f6954l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i f6955m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final i f6956n;

    public r(WorkDatabase_Impl workDatabase_Impl) {
        this.f6944a = workDatabase_Impl;
        this.f6945b = new c(workDatabase_Impl, 5);
        new i(workDatabase_Impl, 12);
        this.f6946c = new i(workDatabase_Impl, 13);
        this.f6947d = new i(workDatabase_Impl, 14);
        this.f6948e = new i(workDatabase_Impl, 15);
        this.f6949f = new i(workDatabase_Impl, 16);
        this.f6950g = new i(workDatabase_Impl, 17);
        this.f6951h = new i(workDatabase_Impl, 18);
        this.f6952i = new i(workDatabase_Impl, 19);
        this.f6953j = new i(workDatabase_Impl, 4);
        new i(workDatabase_Impl, 5);
        this.k = new i(workDatabase_Impl, 6);
        this.f6954l = new i(workDatabase_Impl, 7);
        this.f6955m = new i(workDatabase_Impl, 8);
        new i(workDatabase_Impl, 9);
        new i(workDatabase_Impl, 10);
        this.f6956n = new i(workDatabase_Impl, 11);
    }

    @Override // androidx.work.impl.model.q
    public final void a(String str) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6946c;
        c2.g gVarC = iVar.c();
        if (str == null) {
            gVarC.C0(1);
        } else {
            gVarC.e0(1, str);
        }
        workDatabase_Impl.c();
        try {
            gVarC.r();
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final void b(String str) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6949f;
        c2.g gVarC = iVar.c();
        if (str == null) {
            gVarC.C0(1);
        } else {
            gVarC.e0(1, str);
        }
        workDatabase_Impl.c();
        try {
            gVarC.r();
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final int c(long j10, String str) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6954l;
        c2.g gVarC = iVar.c();
        gVarC.n0(1, j10);
        if (str == null) {
            gVarC.C0(2);
        } else {
            gVarC.e0(2, str);
        }
        workDatabase_Impl.c();
        try {
            int iR = gVarC.r();
            workDatabase_Impl.o();
            return iR;
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final ArrayList d(long j10) throws Throwable {
        r0 r0Var;
        r0 r0VarK = r0.k(1, "SELECT * FROM workspec WHERE last_enqueue_time >= ? AND state IN (2, 3, 5) ORDER BY last_enqueue_time DESC");
        r0VarK.n0(1, j10);
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            int iO = a.a.O(cursorM, TtmlNode.ATTR_ID);
            int iO2 = a.a.O(cursorM, "state");
            int iO3 = a.a.O(cursorM, "worker_class_name");
            int iO4 = a.a.O(cursorM, "input_merger_class_name");
            int iO5 = a.a.O(cursorM, "input");
            int iO6 = a.a.O(cursorM, "output");
            int iO7 = a.a.O(cursorM, "initial_delay");
            int iO8 = a.a.O(cursorM, "interval_duration");
            int iO9 = a.a.O(cursorM, "flex_duration");
            int iO10 = a.a.O(cursorM, "run_attempt_count");
            int iO11 = a.a.O(cursorM, "backoff_policy");
            int iO12 = a.a.O(cursorM, "backoff_delay_duration");
            int iO13 = a.a.O(cursorM, "last_enqueue_time");
            r0Var = r0VarK;
            try {
                int iO14 = a.a.O(cursorM, "minimum_retention_duration");
                int iO15 = a.a.O(cursorM, "schedule_requested_at");
                int iO16 = a.a.O(cursorM, "run_in_foreground");
                int iO17 = a.a.O(cursorM, "out_of_quota_policy");
                int iO18 = a.a.O(cursorM, "period_count");
                int iO19 = a.a.O(cursorM, "generation");
                int iO20 = a.a.O(cursorM, "next_schedule_time_override");
                int iO21 = a.a.O(cursorM, "next_schedule_time_override_generation");
                int iO22 = a.a.O(cursorM, DownloadService.KEY_STOP_REASON);
                int iO23 = a.a.O(cursorM, "required_network_type");
                int iO24 = a.a.O(cursorM, "requires_charging");
                int iO25 = a.a.O(cursorM, "requires_device_idle");
                int iO26 = a.a.O(cursorM, "requires_battery_not_low");
                int iO27 = a.a.O(cursorM, "requires_storage_not_low");
                int iO28 = a.a.O(cursorM, "trigger_content_update_delay");
                int iO29 = a.a.O(cursorM, "trigger_max_content_delay");
                int iO30 = a.a.O(cursorM, "content_uri_triggers");
                int i10 = iO14;
                ArrayList arrayList = new ArrayList(cursorM.getCount());
                while (cursorM.moveToNext()) {
                    byte[] blob = null;
                    String string = cursorM.isNull(iO) ? null : cursorM.getString(iO);
                    int iO31 = ac.b.O(cursorM.getInt(iO2));
                    String string2 = cursorM.isNull(iO3) ? null : cursorM.getString(iO3);
                    String string3 = cursorM.isNull(iO4) ? null : cursorM.getString(iO4);
                    m2.h hVarA = m2.h.a(cursorM.isNull(iO5) ? null : cursorM.getBlob(iO5));
                    m2.h hVarA2 = m2.h.a(cursorM.isNull(iO6) ? null : cursorM.getBlob(iO6));
                    long j11 = cursorM.getLong(iO7);
                    long j12 = cursorM.getLong(iO8);
                    long j13 = cursorM.getLong(iO9);
                    int i11 = cursorM.getInt(iO10);
                    int iL = ac.b.L(cursorM.getInt(iO11));
                    long j14 = cursorM.getLong(iO12);
                    long j15 = cursorM.getLong(iO13);
                    int i12 = i10;
                    long j16 = cursorM.getLong(i12);
                    int i13 = iO13;
                    int i14 = iO15;
                    long j17 = cursorM.getLong(i14);
                    iO15 = i14;
                    int i15 = iO16;
                    boolean z = cursorM.getInt(i15) != 0;
                    iO16 = i15;
                    int i16 = iO17;
                    int iN = ac.b.N(cursorM.getInt(i16));
                    iO17 = i16;
                    int i17 = iO18;
                    int i18 = cursorM.getInt(i17);
                    iO18 = i17;
                    int i19 = iO19;
                    int i20 = cursorM.getInt(i19);
                    iO19 = i19;
                    int i21 = iO20;
                    long j18 = cursorM.getLong(i21);
                    iO20 = i21;
                    int i22 = iO21;
                    int i23 = cursorM.getInt(i22);
                    iO21 = i22;
                    int i24 = iO22;
                    int i25 = cursorM.getInt(i24);
                    iO22 = i24;
                    int i26 = iO23;
                    int iM = ac.b.M(cursorM.getInt(i26));
                    iO23 = i26;
                    int i27 = iO24;
                    boolean z5 = cursorM.getInt(i27) != 0;
                    iO24 = i27;
                    int i28 = iO25;
                    boolean z10 = cursorM.getInt(i28) != 0;
                    iO25 = i28;
                    int i29 = iO26;
                    boolean z11 = cursorM.getInt(i29) != 0;
                    iO26 = i29;
                    int i30 = iO27;
                    boolean z12 = cursorM.getInt(i30) != 0;
                    iO27 = i30;
                    int i31 = iO28;
                    long j19 = cursorM.getLong(i31);
                    iO28 = i31;
                    int i32 = iO29;
                    long j20 = cursorM.getLong(i32);
                    iO29 = i32;
                    int i33 = iO30;
                    if (!cursorM.isNull(i33)) {
                        blob = cursorM.getBlob(i33);
                    }
                    iO30 = i33;
                    arrayList.add(new p(string, iO31, string2, string3, hVarA, hVarA2, j11, j12, j13, new m2.f(iM, z5, z10, z11, z12, j19, j20, ac.b.e(blob)), i11, iL, j14, j15, j16, j17, z, iN, i18, i20, j18, i23, i25));
                    iO13 = i13;
                    i10 = i12;
                }
                cursorM.close();
                r0Var.l();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorM.close();
                r0Var.l();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            r0Var = r0VarK;
        }
    }

    @Override // androidx.work.impl.model.q
    public final void e(p pVar) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        workDatabase_Impl.c();
        try {
            this.f6945b.j(pVar);
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
        }
    }

    @Override // androidx.work.impl.model.q
    public final void f(int i10, String str) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.k;
        c2.g gVarC = iVar.c();
        if (str == null) {
            gVarC.C0(1);
        } else {
            gVarC.e0(1, str);
        }
        gVarC.n0(2, i10);
        workDatabase_Impl.c();
        try {
            gVarC.r();
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final ArrayList g() throws Throwable {
        r0 r0Var;
        r0 r0VarK = r0.k(0, "SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1");
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            int iO = a.a.O(cursorM, TtmlNode.ATTR_ID);
            int iO2 = a.a.O(cursorM, "state");
            int iO3 = a.a.O(cursorM, "worker_class_name");
            int iO4 = a.a.O(cursorM, "input_merger_class_name");
            int iO5 = a.a.O(cursorM, "input");
            int iO6 = a.a.O(cursorM, "output");
            int iO7 = a.a.O(cursorM, "initial_delay");
            int iO8 = a.a.O(cursorM, "interval_duration");
            int iO9 = a.a.O(cursorM, "flex_duration");
            int iO10 = a.a.O(cursorM, "run_attempt_count");
            int iO11 = a.a.O(cursorM, "backoff_policy");
            int iO12 = a.a.O(cursorM, "backoff_delay_duration");
            int iO13 = a.a.O(cursorM, "last_enqueue_time");
            r0Var = r0VarK;
            try {
                int iO14 = a.a.O(cursorM, "minimum_retention_duration");
                int iO15 = a.a.O(cursorM, "schedule_requested_at");
                int iO16 = a.a.O(cursorM, "run_in_foreground");
                int iO17 = a.a.O(cursorM, "out_of_quota_policy");
                int iO18 = a.a.O(cursorM, "period_count");
                int iO19 = a.a.O(cursorM, "generation");
                int iO20 = a.a.O(cursorM, "next_schedule_time_override");
                int iO21 = a.a.O(cursorM, "next_schedule_time_override_generation");
                int iO22 = a.a.O(cursorM, DownloadService.KEY_STOP_REASON);
                int iO23 = a.a.O(cursorM, "required_network_type");
                int iO24 = a.a.O(cursorM, "requires_charging");
                int iO25 = a.a.O(cursorM, "requires_device_idle");
                int iO26 = a.a.O(cursorM, "requires_battery_not_low");
                int iO27 = a.a.O(cursorM, "requires_storage_not_low");
                int iO28 = a.a.O(cursorM, "trigger_content_update_delay");
                int iO29 = a.a.O(cursorM, "trigger_max_content_delay");
                int iO30 = a.a.O(cursorM, "content_uri_triggers");
                int i10 = iO14;
                ArrayList arrayList = new ArrayList(cursorM.getCount());
                while (cursorM.moveToNext()) {
                    byte[] blob = null;
                    String string = cursorM.isNull(iO) ? null : cursorM.getString(iO);
                    int iO31 = ac.b.O(cursorM.getInt(iO2));
                    String string2 = cursorM.isNull(iO3) ? null : cursorM.getString(iO3);
                    String string3 = cursorM.isNull(iO4) ? null : cursorM.getString(iO4);
                    m2.h hVarA = m2.h.a(cursorM.isNull(iO5) ? null : cursorM.getBlob(iO5));
                    m2.h hVarA2 = m2.h.a(cursorM.isNull(iO6) ? null : cursorM.getBlob(iO6));
                    long j10 = cursorM.getLong(iO7);
                    long j11 = cursorM.getLong(iO8);
                    long j12 = cursorM.getLong(iO9);
                    int i11 = cursorM.getInt(iO10);
                    int iL = ac.b.L(cursorM.getInt(iO11));
                    long j13 = cursorM.getLong(iO12);
                    long j14 = cursorM.getLong(iO13);
                    int i12 = i10;
                    long j15 = cursorM.getLong(i12);
                    int i13 = iO13;
                    int i14 = iO15;
                    long j16 = cursorM.getLong(i14);
                    iO15 = i14;
                    int i15 = iO16;
                    boolean z = cursorM.getInt(i15) != 0;
                    iO16 = i15;
                    int i16 = iO17;
                    int iN = ac.b.N(cursorM.getInt(i16));
                    iO17 = i16;
                    int i17 = iO18;
                    int i18 = cursorM.getInt(i17);
                    iO18 = i17;
                    int i19 = iO19;
                    int i20 = cursorM.getInt(i19);
                    iO19 = i19;
                    int i21 = iO20;
                    long j17 = cursorM.getLong(i21);
                    iO20 = i21;
                    int i22 = iO21;
                    int i23 = cursorM.getInt(i22);
                    iO21 = i22;
                    int i24 = iO22;
                    int i25 = cursorM.getInt(i24);
                    iO22 = i24;
                    int i26 = iO23;
                    int iM = ac.b.M(cursorM.getInt(i26));
                    iO23 = i26;
                    int i27 = iO24;
                    boolean z5 = cursorM.getInt(i27) != 0;
                    iO24 = i27;
                    int i28 = iO25;
                    boolean z10 = cursorM.getInt(i28) != 0;
                    iO25 = i28;
                    int i29 = iO26;
                    boolean z11 = cursorM.getInt(i29) != 0;
                    iO26 = i29;
                    int i30 = iO27;
                    boolean z12 = cursorM.getInt(i30) != 0;
                    iO27 = i30;
                    int i31 = iO28;
                    long j18 = cursorM.getLong(i31);
                    iO28 = i31;
                    int i32 = iO29;
                    long j19 = cursorM.getLong(i32);
                    iO29 = i32;
                    int i33 = iO30;
                    if (!cursorM.isNull(i33)) {
                        blob = cursorM.getBlob(i33);
                    }
                    iO30 = i33;
                    arrayList.add(new p(string, iO31, string2, string3, hVarA, hVarA2, j10, j11, j12, new m2.f(iM, z5, z10, z11, z12, j18, j19, ac.b.e(blob)), i11, iL, j13, j14, j15, j16, z, iN, i18, i20, j17, i23, i25));
                    iO13 = i13;
                    i10 = i12;
                }
                cursorM.close();
                r0Var.l();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorM.close();
                r0Var.l();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            r0Var = r0VarK;
        }
    }

    @Override // androidx.work.impl.model.q
    public final int h(int i10, String str) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6947d;
        c2.g gVarC = iVar.c();
        gVarC.n0(1, ac.b.W(i10));
        if (str == null) {
            gVarC.C0(2);
        } else {
            gVarC.e0(2, str);
        }
        workDatabase_Impl.c();
        try {
            int iR = gVarC.r();
            workDatabase_Impl.o();
            return iR;
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final ArrayList i(String str) {
        r0 r0VarK = r0.k(1, "SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            ArrayList arrayList = new ArrayList(cursorM.getCount());
            while (cursorM.moveToNext()) {
                arrayList.add(cursorM.isNull(0) ? null : cursorM.getString(0));
            }
            return arrayList;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // androidx.work.impl.model.q
    public final int j(String str) {
        r0 r0VarK = r0.k(1, "SELECT state FROM workspec WHERE id=?");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            int iO = 0;
            if (cursorM.moveToFirst()) {
                Integer numValueOf = cursorM.isNull(0) ? null : Integer.valueOf(cursorM.getInt(0));
                if (numValueOf != null) {
                    iO = ac.b.O(numValueOf.intValue());
                }
            }
            return iO;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // androidx.work.impl.model.q
    public final p k(String str) throws Throwable {
        r0 r0Var;
        int iO;
        int iO2;
        int iO3;
        int iO4;
        int iO5;
        int iO6;
        int iO7;
        int iO8;
        int iO9;
        int iO10;
        int iO11;
        int iO12;
        int iO13;
        r0 r0VarK = r0.k(1, "SELECT * FROM workspec WHERE id=?");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            iO = a.a.O(cursorM, TtmlNode.ATTR_ID);
            iO2 = a.a.O(cursorM, "state");
            iO3 = a.a.O(cursorM, "worker_class_name");
            iO4 = a.a.O(cursorM, "input_merger_class_name");
            iO5 = a.a.O(cursorM, "input");
            iO6 = a.a.O(cursorM, "output");
            iO7 = a.a.O(cursorM, "initial_delay");
            iO8 = a.a.O(cursorM, "interval_duration");
            iO9 = a.a.O(cursorM, "flex_duration");
            iO10 = a.a.O(cursorM, "run_attempt_count");
            iO11 = a.a.O(cursorM, "backoff_policy");
            iO12 = a.a.O(cursorM, "backoff_delay_duration");
            iO13 = a.a.O(cursorM, "last_enqueue_time");
            r0Var = r0VarK;
        } catch (Throwable th) {
            th = th;
            r0Var = r0VarK;
        }
        try {
            int iO14 = a.a.O(cursorM, "minimum_retention_duration");
            int iO15 = a.a.O(cursorM, "schedule_requested_at");
            int iO16 = a.a.O(cursorM, "run_in_foreground");
            int iO17 = a.a.O(cursorM, "out_of_quota_policy");
            int iO18 = a.a.O(cursorM, "period_count");
            int iO19 = a.a.O(cursorM, "generation");
            int iO20 = a.a.O(cursorM, "next_schedule_time_override");
            int iO21 = a.a.O(cursorM, "next_schedule_time_override_generation");
            int iO22 = a.a.O(cursorM, DownloadService.KEY_STOP_REASON);
            int iO23 = a.a.O(cursorM, "required_network_type");
            int iO24 = a.a.O(cursorM, "requires_charging");
            int iO25 = a.a.O(cursorM, "requires_device_idle");
            int iO26 = a.a.O(cursorM, "requires_battery_not_low");
            int iO27 = a.a.O(cursorM, "requires_storage_not_low");
            int iO28 = a.a.O(cursorM, "trigger_content_update_delay");
            int iO29 = a.a.O(cursorM, "trigger_max_content_delay");
            int iO30 = a.a.O(cursorM, "content_uri_triggers");
            p pVar = null;
            byte[] blob = null;
            if (cursorM.moveToFirst()) {
                String string = cursorM.isNull(iO) ? null : cursorM.getString(iO);
                int iO31 = ac.b.O(cursorM.getInt(iO2));
                String string2 = cursorM.isNull(iO3) ? null : cursorM.getString(iO3);
                String string3 = cursorM.isNull(iO4) ? null : cursorM.getString(iO4);
                m2.h hVarA = m2.h.a(cursorM.isNull(iO5) ? null : cursorM.getBlob(iO5));
                m2.h hVarA2 = m2.h.a(cursorM.isNull(iO6) ? null : cursorM.getBlob(iO6));
                long j10 = cursorM.getLong(iO7);
                long j11 = cursorM.getLong(iO8);
                long j12 = cursorM.getLong(iO9);
                int i10 = cursorM.getInt(iO10);
                int iL = ac.b.L(cursorM.getInt(iO11));
                long j13 = cursorM.getLong(iO12);
                long j14 = cursorM.getLong(iO13);
                long j15 = cursorM.getLong(iO14);
                long j16 = cursorM.getLong(iO15);
                boolean z = cursorM.getInt(iO16) != 0;
                int iN = ac.b.N(cursorM.getInt(iO17));
                int i11 = cursorM.getInt(iO18);
                int i12 = cursorM.getInt(iO19);
                long j17 = cursorM.getLong(iO20);
                int i13 = cursorM.getInt(iO21);
                int i14 = cursorM.getInt(iO22);
                int iM = ac.b.M(cursorM.getInt(iO23));
                boolean z5 = cursorM.getInt(iO24) != 0;
                boolean z10 = cursorM.getInt(iO25) != 0;
                boolean z11 = cursorM.getInt(iO26) != 0;
                boolean z12 = cursorM.getInt(iO27) != 0;
                long j18 = cursorM.getLong(iO28);
                long j19 = cursorM.getLong(iO29);
                if (!cursorM.isNull(iO30)) {
                    blob = cursorM.getBlob(iO30);
                }
                pVar = new p(string, iO31, string2, string3, hVarA, hVarA2, j10, j11, j12, new m2.f(iM, z5, z10, z11, z12, j18, j19, ac.b.e(blob)), i10, iL, j13, j14, j15, j16, z, iN, i11, i12, j17, i13, i14);
            }
            cursorM.close();
            r0Var.l();
            return pVar;
        } catch (Throwable th2) {
            th = th2;
            cursorM.close();
            r0Var.l();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.q
    public final int l(String str) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6948e;
        c2.g gVarC = iVar.c();
        if (str == null) {
            gVarC.C0(1);
        } else {
            gVarC.e0(1, str);
        }
        workDatabase_Impl.c();
        try {
            int iR = gVarC.r();
            workDatabase_Impl.o();
            return iR;
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final ArrayList m(String str) {
        r0 r0VarK = r0.k(1, "SELECT output FROM workspec WHERE id IN\n             (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            ArrayList arrayList = new ArrayList(cursorM.getCount());
            while (cursorM.moveToNext()) {
                arrayList.add(m2.h.a(cursorM.isNull(0) ? null : cursorM.getBlob(0)));
            }
            return arrayList;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // androidx.work.impl.model.q
    public final int n() {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6955m;
        c2.g gVarC = iVar.c();
        workDatabase_Impl.c();
        try {
            int iR = gVarC.r();
            workDatabase_Impl.o();
            return iR;
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final ArrayList o() throws Throwable {
        r0 r0Var;
        int iO;
        int iO2;
        int iO3;
        int iO4;
        int iO5;
        int iO6;
        int iO7;
        int iO8;
        int iO9;
        int iO10;
        int iO11;
        int iO12;
        int iO13;
        r0 r0VarK = r0.k(1, "SELECT * FROM workspec WHERE state=0 ORDER BY last_enqueue_time LIMIT ?");
        r0VarK.n0(1, 200);
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            iO = a.a.O(cursorM, TtmlNode.ATTR_ID);
            iO2 = a.a.O(cursorM, "state");
            iO3 = a.a.O(cursorM, "worker_class_name");
            iO4 = a.a.O(cursorM, "input_merger_class_name");
            iO5 = a.a.O(cursorM, "input");
            iO6 = a.a.O(cursorM, "output");
            iO7 = a.a.O(cursorM, "initial_delay");
            iO8 = a.a.O(cursorM, "interval_duration");
            iO9 = a.a.O(cursorM, "flex_duration");
            iO10 = a.a.O(cursorM, "run_attempt_count");
            iO11 = a.a.O(cursorM, "backoff_policy");
            iO12 = a.a.O(cursorM, "backoff_delay_duration");
            iO13 = a.a.O(cursorM, "last_enqueue_time");
            r0Var = r0VarK;
        } catch (Throwable th) {
            th = th;
            r0Var = r0VarK;
        }
        try {
            int iO14 = a.a.O(cursorM, "minimum_retention_duration");
            int iO15 = a.a.O(cursorM, "schedule_requested_at");
            int iO16 = a.a.O(cursorM, "run_in_foreground");
            int iO17 = a.a.O(cursorM, "out_of_quota_policy");
            int iO18 = a.a.O(cursorM, "period_count");
            int iO19 = a.a.O(cursorM, "generation");
            int iO20 = a.a.O(cursorM, "next_schedule_time_override");
            int iO21 = a.a.O(cursorM, "next_schedule_time_override_generation");
            int iO22 = a.a.O(cursorM, DownloadService.KEY_STOP_REASON);
            int iO23 = a.a.O(cursorM, "required_network_type");
            int iO24 = a.a.O(cursorM, "requires_charging");
            int iO25 = a.a.O(cursorM, "requires_device_idle");
            int iO26 = a.a.O(cursorM, "requires_battery_not_low");
            int iO27 = a.a.O(cursorM, "requires_storage_not_low");
            int iO28 = a.a.O(cursorM, "trigger_content_update_delay");
            int iO29 = a.a.O(cursorM, "trigger_max_content_delay");
            int iO30 = a.a.O(cursorM, "content_uri_triggers");
            int i10 = iO14;
            ArrayList arrayList = new ArrayList(cursorM.getCount());
            while (cursorM.moveToNext()) {
                byte[] blob = null;
                String string = cursorM.isNull(iO) ? null : cursorM.getString(iO);
                int iO31 = ac.b.O(cursorM.getInt(iO2));
                String string2 = cursorM.isNull(iO3) ? null : cursorM.getString(iO3);
                String string3 = cursorM.isNull(iO4) ? null : cursorM.getString(iO4);
                m2.h hVarA = m2.h.a(cursorM.isNull(iO5) ? null : cursorM.getBlob(iO5));
                m2.h hVarA2 = m2.h.a(cursorM.isNull(iO6) ? null : cursorM.getBlob(iO6));
                long j10 = cursorM.getLong(iO7);
                long j11 = cursorM.getLong(iO8);
                long j12 = cursorM.getLong(iO9);
                int i11 = cursorM.getInt(iO10);
                int iL = ac.b.L(cursorM.getInt(iO11));
                long j13 = cursorM.getLong(iO12);
                long j14 = cursorM.getLong(iO13);
                int i12 = i10;
                long j15 = cursorM.getLong(i12);
                int i13 = iO13;
                int i14 = iO15;
                long j16 = cursorM.getLong(i14);
                iO15 = i14;
                int i15 = iO16;
                boolean z = cursorM.getInt(i15) != 0;
                iO16 = i15;
                int i16 = iO17;
                int iN = ac.b.N(cursorM.getInt(i16));
                iO17 = i16;
                int i17 = iO18;
                int i18 = cursorM.getInt(i17);
                iO18 = i17;
                int i19 = iO19;
                int i20 = cursorM.getInt(i19);
                iO19 = i19;
                int i21 = iO20;
                long j17 = cursorM.getLong(i21);
                iO20 = i21;
                int i22 = iO21;
                int i23 = cursorM.getInt(i22);
                iO21 = i22;
                int i24 = iO22;
                int i25 = cursorM.getInt(i24);
                iO22 = i24;
                int i26 = iO23;
                int iM = ac.b.M(cursorM.getInt(i26));
                iO23 = i26;
                int i27 = iO24;
                boolean z5 = cursorM.getInt(i27) != 0;
                iO24 = i27;
                int i28 = iO25;
                boolean z10 = cursorM.getInt(i28) != 0;
                iO25 = i28;
                int i29 = iO26;
                boolean z11 = cursorM.getInt(i29) != 0;
                iO26 = i29;
                int i30 = iO27;
                boolean z12 = cursorM.getInt(i30) != 0;
                iO27 = i30;
                int i31 = iO28;
                long j18 = cursorM.getLong(i31);
                iO28 = i31;
                int i32 = iO29;
                long j19 = cursorM.getLong(i32);
                iO29 = i32;
                int i33 = iO30;
                if (!cursorM.isNull(i33)) {
                    blob = cursorM.getBlob(i33);
                }
                iO30 = i33;
                arrayList.add(new p(string, iO31, string2, string3, hVarA, hVarA2, j10, j11, j12, new m2.f(iM, z5, z10, z11, z12, j18, j19, ac.b.e(blob)), i11, iL, j13, j14, j15, j16, z, iN, i18, i20, j17, i23, i25));
                iO13 = i13;
                i10 = i12;
            }
            cursorM.close();
            r0Var.l();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorM.close();
            r0Var.l();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.q
    public final void p(String str, m2.h hVar) throws Throwable {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6950g;
        c2.g gVarC = iVar.c();
        byte[] bArrB = m2.h.b(hVar);
        if (bArrB == null) {
            gVarC.C0(1);
        } else {
            gVarC.o0(1, bArrB);
        }
        if (str == null) {
            gVarC.C0(2);
        } else {
            gVarC.e0(2, str);
        }
        workDatabase_Impl.c();
        try {
            gVarC.r();
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final ArrayList q(String str) {
        r0 r0VarK = r0.k(1, "SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            r0VarK.C0(1);
        } else {
            r0VarK.e0(1, str);
        }
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            ArrayList arrayList = new ArrayList(cursorM.getCount());
            while (cursorM.moveToNext()) {
                String string = cursorM.isNull(0) ? null : cursorM.getString(0);
                int iO = ac.b.O(cursorM.getInt(1));
                o oVar = new o();
                oVar.f6920a = string;
                oVar.f6921b = iO;
                arrayList.add(oVar);
            }
            return arrayList;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // androidx.work.impl.model.q
    public final ArrayList r(int i10) throws Throwable {
        r0 r0Var;
        int iO;
        int iO2;
        int iO3;
        int iO4;
        int iO5;
        int iO6;
        int iO7;
        int iO8;
        int iO9;
        int iO10;
        int iO11;
        int iO12;
        int iO13;
        r0 r0VarK = r0.k(1, "SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY last_enqueue_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND LENGTH(content_uri_triggers)=0 AND state NOT IN (2, 3, 5))");
        r0VarK.n0(1, i10);
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            iO = a.a.O(cursorM, TtmlNode.ATTR_ID);
            iO2 = a.a.O(cursorM, "state");
            iO3 = a.a.O(cursorM, "worker_class_name");
            iO4 = a.a.O(cursorM, "input_merger_class_name");
            iO5 = a.a.O(cursorM, "input");
            iO6 = a.a.O(cursorM, "output");
            iO7 = a.a.O(cursorM, "initial_delay");
            iO8 = a.a.O(cursorM, "interval_duration");
            iO9 = a.a.O(cursorM, "flex_duration");
            iO10 = a.a.O(cursorM, "run_attempt_count");
            iO11 = a.a.O(cursorM, "backoff_policy");
            iO12 = a.a.O(cursorM, "backoff_delay_duration");
            iO13 = a.a.O(cursorM, "last_enqueue_time");
            r0Var = r0VarK;
        } catch (Throwable th) {
            th = th;
            r0Var = r0VarK;
        }
        try {
            int iO14 = a.a.O(cursorM, "minimum_retention_duration");
            int iO15 = a.a.O(cursorM, "schedule_requested_at");
            int iO16 = a.a.O(cursorM, "run_in_foreground");
            int iO17 = a.a.O(cursorM, "out_of_quota_policy");
            int iO18 = a.a.O(cursorM, "period_count");
            int iO19 = a.a.O(cursorM, "generation");
            int iO20 = a.a.O(cursorM, "next_schedule_time_override");
            int iO21 = a.a.O(cursorM, "next_schedule_time_override_generation");
            int iO22 = a.a.O(cursorM, DownloadService.KEY_STOP_REASON);
            int iO23 = a.a.O(cursorM, "required_network_type");
            int iO24 = a.a.O(cursorM, "requires_charging");
            int iO25 = a.a.O(cursorM, "requires_device_idle");
            int iO26 = a.a.O(cursorM, "requires_battery_not_low");
            int iO27 = a.a.O(cursorM, "requires_storage_not_low");
            int iO28 = a.a.O(cursorM, "trigger_content_update_delay");
            int iO29 = a.a.O(cursorM, "trigger_max_content_delay");
            int iO30 = a.a.O(cursorM, "content_uri_triggers");
            int i11 = iO14;
            ArrayList arrayList = new ArrayList(cursorM.getCount());
            while (cursorM.moveToNext()) {
                byte[] blob = null;
                String string = cursorM.isNull(iO) ? null : cursorM.getString(iO);
                int iO31 = ac.b.O(cursorM.getInt(iO2));
                String string2 = cursorM.isNull(iO3) ? null : cursorM.getString(iO3);
                String string3 = cursorM.isNull(iO4) ? null : cursorM.getString(iO4);
                m2.h hVarA = m2.h.a(cursorM.isNull(iO5) ? null : cursorM.getBlob(iO5));
                m2.h hVarA2 = m2.h.a(cursorM.isNull(iO6) ? null : cursorM.getBlob(iO6));
                long j10 = cursorM.getLong(iO7);
                long j11 = cursorM.getLong(iO8);
                long j12 = cursorM.getLong(iO9);
                int i12 = cursorM.getInt(iO10);
                int iL = ac.b.L(cursorM.getInt(iO11));
                long j13 = cursorM.getLong(iO12);
                long j14 = cursorM.getLong(iO13);
                int i13 = i11;
                long j15 = cursorM.getLong(i13);
                int i14 = iO13;
                int i15 = iO15;
                long j16 = cursorM.getLong(i15);
                iO15 = i15;
                int i16 = iO16;
                boolean z = cursorM.getInt(i16) != 0;
                iO16 = i16;
                int i17 = iO17;
                int iN = ac.b.N(cursorM.getInt(i17));
                iO17 = i17;
                int i18 = iO18;
                int i19 = cursorM.getInt(i18);
                iO18 = i18;
                int i20 = iO19;
                int i21 = cursorM.getInt(i20);
                iO19 = i20;
                int i22 = iO20;
                long j17 = cursorM.getLong(i22);
                iO20 = i22;
                int i23 = iO21;
                int i24 = cursorM.getInt(i23);
                iO21 = i23;
                int i25 = iO22;
                int i26 = cursorM.getInt(i25);
                iO22 = i25;
                int i27 = iO23;
                int iM = ac.b.M(cursorM.getInt(i27));
                iO23 = i27;
                int i28 = iO24;
                boolean z5 = cursorM.getInt(i28) != 0;
                iO24 = i28;
                int i29 = iO25;
                boolean z10 = cursorM.getInt(i29) != 0;
                iO25 = i29;
                int i30 = iO26;
                boolean z11 = cursorM.getInt(i30) != 0;
                iO26 = i30;
                int i31 = iO27;
                boolean z12 = cursorM.getInt(i31) != 0;
                iO27 = i31;
                int i32 = iO28;
                long j18 = cursorM.getLong(i32);
                iO28 = i32;
                int i33 = iO29;
                long j19 = cursorM.getLong(i33);
                iO29 = i33;
                int i34 = iO30;
                if (!cursorM.isNull(i34)) {
                    blob = cursorM.getBlob(i34);
                }
                iO30 = i34;
                arrayList.add(new p(string, iO31, string2, string3, hVarA, hVarA2, j10, j11, j12, new m2.f(iM, z5, z10, z11, z12, j18, j19, ac.b.e(blob)), i12, iL, j13, j14, j15, j16, z, iN, i19, i21, j17, i24, i26));
                iO13 = i14;
                i11 = i13;
            }
            cursorM.close();
            r0Var.l();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorM.close();
            r0Var.l();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.q
    public final void s(long j10, String str) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6951h;
        c2.g gVarC = iVar.c();
        gVarC.n0(1, j10);
        if (str == null) {
            gVarC.C0(2);
        } else {
            gVarC.e0(2, str);
        }
        workDatabase_Impl.c();
        try {
            gVarC.r();
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final void setStopReason(String str, int i10) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6956n;
        c2.g gVarC = iVar.c();
        gVarC.n0(1, i10);
        if (str == null) {
            gVarC.C0(2);
        } else {
            gVarC.e0(2, str);
        }
        workDatabase_Impl.c();
        try {
            gVarC.r();
            workDatabase_Impl.o();
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final ArrayList t() throws Throwable {
        r0 r0Var;
        r0 r0VarK = r0.k(0, "SELECT * FROM workspec WHERE state=1");
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            int iO = a.a.O(cursorM, TtmlNode.ATTR_ID);
            int iO2 = a.a.O(cursorM, "state");
            int iO3 = a.a.O(cursorM, "worker_class_name");
            int iO4 = a.a.O(cursorM, "input_merger_class_name");
            int iO5 = a.a.O(cursorM, "input");
            int iO6 = a.a.O(cursorM, "output");
            int iO7 = a.a.O(cursorM, "initial_delay");
            int iO8 = a.a.O(cursorM, "interval_duration");
            int iO9 = a.a.O(cursorM, "flex_duration");
            int iO10 = a.a.O(cursorM, "run_attempt_count");
            int iO11 = a.a.O(cursorM, "backoff_policy");
            int iO12 = a.a.O(cursorM, "backoff_delay_duration");
            int iO13 = a.a.O(cursorM, "last_enqueue_time");
            r0Var = r0VarK;
            try {
                int iO14 = a.a.O(cursorM, "minimum_retention_duration");
                int iO15 = a.a.O(cursorM, "schedule_requested_at");
                int iO16 = a.a.O(cursorM, "run_in_foreground");
                int iO17 = a.a.O(cursorM, "out_of_quota_policy");
                int iO18 = a.a.O(cursorM, "period_count");
                int iO19 = a.a.O(cursorM, "generation");
                int iO20 = a.a.O(cursorM, "next_schedule_time_override");
                int iO21 = a.a.O(cursorM, "next_schedule_time_override_generation");
                int iO22 = a.a.O(cursorM, DownloadService.KEY_STOP_REASON);
                int iO23 = a.a.O(cursorM, "required_network_type");
                int iO24 = a.a.O(cursorM, "requires_charging");
                int iO25 = a.a.O(cursorM, "requires_device_idle");
                int iO26 = a.a.O(cursorM, "requires_battery_not_low");
                int iO27 = a.a.O(cursorM, "requires_storage_not_low");
                int iO28 = a.a.O(cursorM, "trigger_content_update_delay");
                int iO29 = a.a.O(cursorM, "trigger_max_content_delay");
                int iO30 = a.a.O(cursorM, "content_uri_triggers");
                int i10 = iO14;
                ArrayList arrayList = new ArrayList(cursorM.getCount());
                while (cursorM.moveToNext()) {
                    byte[] blob = null;
                    String string = cursorM.isNull(iO) ? null : cursorM.getString(iO);
                    int iO31 = ac.b.O(cursorM.getInt(iO2));
                    String string2 = cursorM.isNull(iO3) ? null : cursorM.getString(iO3);
                    String string3 = cursorM.isNull(iO4) ? null : cursorM.getString(iO4);
                    m2.h hVarA = m2.h.a(cursorM.isNull(iO5) ? null : cursorM.getBlob(iO5));
                    m2.h hVarA2 = m2.h.a(cursorM.isNull(iO6) ? null : cursorM.getBlob(iO6));
                    long j10 = cursorM.getLong(iO7);
                    long j11 = cursorM.getLong(iO8);
                    long j12 = cursorM.getLong(iO9);
                    int i11 = cursorM.getInt(iO10);
                    int iL = ac.b.L(cursorM.getInt(iO11));
                    long j13 = cursorM.getLong(iO12);
                    long j14 = cursorM.getLong(iO13);
                    int i12 = i10;
                    long j15 = cursorM.getLong(i12);
                    int i13 = iO13;
                    int i14 = iO15;
                    long j16 = cursorM.getLong(i14);
                    iO15 = i14;
                    int i15 = iO16;
                    boolean z = cursorM.getInt(i15) != 0;
                    iO16 = i15;
                    int i16 = iO17;
                    int iN = ac.b.N(cursorM.getInt(i16));
                    iO17 = i16;
                    int i17 = iO18;
                    int i18 = cursorM.getInt(i17);
                    iO18 = i17;
                    int i19 = iO19;
                    int i20 = cursorM.getInt(i19);
                    iO19 = i19;
                    int i21 = iO20;
                    long j17 = cursorM.getLong(i21);
                    iO20 = i21;
                    int i22 = iO21;
                    int i23 = cursorM.getInt(i22);
                    iO21 = i22;
                    int i24 = iO22;
                    int i25 = cursorM.getInt(i24);
                    iO22 = i24;
                    int i26 = iO23;
                    int iM = ac.b.M(cursorM.getInt(i26));
                    iO23 = i26;
                    int i27 = iO24;
                    boolean z5 = cursorM.getInt(i27) != 0;
                    iO24 = i27;
                    int i28 = iO25;
                    boolean z10 = cursorM.getInt(i28) != 0;
                    iO25 = i28;
                    int i29 = iO26;
                    boolean z11 = cursorM.getInt(i29) != 0;
                    iO26 = i29;
                    int i30 = iO27;
                    boolean z12 = cursorM.getInt(i30) != 0;
                    iO27 = i30;
                    int i31 = iO28;
                    long j18 = cursorM.getLong(i31);
                    iO28 = i31;
                    int i32 = iO29;
                    long j19 = cursorM.getLong(i32);
                    iO29 = i32;
                    int i33 = iO30;
                    if (!cursorM.isNull(i33)) {
                        blob = cursorM.getBlob(i33);
                    }
                    iO30 = i33;
                    arrayList.add(new p(string, iO31, string2, string3, hVarA, hVarA2, j10, j11, j12, new m2.f(iM, z5, z10, z11, z12, j18, j19, ac.b.e(blob)), i11, iL, j13, j14, j15, j16, z, iN, i18, i20, j17, i23, i25));
                    iO13 = i13;
                    i10 = i12;
                }
                cursorM.close();
                r0Var.l();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorM.close();
                r0Var.l();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            r0Var = r0VarK;
        }
    }

    @Override // androidx.work.impl.model.q
    public final boolean u() {
        boolean z = false;
        r0 r0VarK = r0.k(0, "SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1");
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            if (cursorM.moveToFirst()) {
                if (cursorM.getInt(0) != 0) {
                    z = true;
                }
            }
            return z;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }

    @Override // androidx.work.impl.model.q
    public final ArrayList v() throws Throwable {
        r0 r0Var;
        r0 r0VarK = r0.k(0, "SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 AND LENGTH(content_uri_triggers)<>0 ORDER BY last_enqueue_time");
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            int iO = a.a.O(cursorM, TtmlNode.ATTR_ID);
            int iO2 = a.a.O(cursorM, "state");
            int iO3 = a.a.O(cursorM, "worker_class_name");
            int iO4 = a.a.O(cursorM, "input_merger_class_name");
            int iO5 = a.a.O(cursorM, "input");
            int iO6 = a.a.O(cursorM, "output");
            int iO7 = a.a.O(cursorM, "initial_delay");
            int iO8 = a.a.O(cursorM, "interval_duration");
            int iO9 = a.a.O(cursorM, "flex_duration");
            int iO10 = a.a.O(cursorM, "run_attempt_count");
            int iO11 = a.a.O(cursorM, "backoff_policy");
            int iO12 = a.a.O(cursorM, "backoff_delay_duration");
            int iO13 = a.a.O(cursorM, "last_enqueue_time");
            r0Var = r0VarK;
            try {
                int iO14 = a.a.O(cursorM, "minimum_retention_duration");
                int iO15 = a.a.O(cursorM, "schedule_requested_at");
                int iO16 = a.a.O(cursorM, "run_in_foreground");
                int iO17 = a.a.O(cursorM, "out_of_quota_policy");
                int iO18 = a.a.O(cursorM, "period_count");
                int iO19 = a.a.O(cursorM, "generation");
                int iO20 = a.a.O(cursorM, "next_schedule_time_override");
                int iO21 = a.a.O(cursorM, "next_schedule_time_override_generation");
                int iO22 = a.a.O(cursorM, DownloadService.KEY_STOP_REASON);
                int iO23 = a.a.O(cursorM, "required_network_type");
                int iO24 = a.a.O(cursorM, "requires_charging");
                int iO25 = a.a.O(cursorM, "requires_device_idle");
                int iO26 = a.a.O(cursorM, "requires_battery_not_low");
                int iO27 = a.a.O(cursorM, "requires_storage_not_low");
                int iO28 = a.a.O(cursorM, "trigger_content_update_delay");
                int iO29 = a.a.O(cursorM, "trigger_max_content_delay");
                int iO30 = a.a.O(cursorM, "content_uri_triggers");
                int i10 = iO14;
                ArrayList arrayList = new ArrayList(cursorM.getCount());
                while (cursorM.moveToNext()) {
                    byte[] blob = null;
                    String string = cursorM.isNull(iO) ? null : cursorM.getString(iO);
                    int iO31 = ac.b.O(cursorM.getInt(iO2));
                    String string2 = cursorM.isNull(iO3) ? null : cursorM.getString(iO3);
                    String string3 = cursorM.isNull(iO4) ? null : cursorM.getString(iO4);
                    m2.h hVarA = m2.h.a(cursorM.isNull(iO5) ? null : cursorM.getBlob(iO5));
                    m2.h hVarA2 = m2.h.a(cursorM.isNull(iO6) ? null : cursorM.getBlob(iO6));
                    long j10 = cursorM.getLong(iO7);
                    long j11 = cursorM.getLong(iO8);
                    long j12 = cursorM.getLong(iO9);
                    int i11 = cursorM.getInt(iO10);
                    int iL = ac.b.L(cursorM.getInt(iO11));
                    long j13 = cursorM.getLong(iO12);
                    long j14 = cursorM.getLong(iO13);
                    int i12 = i10;
                    long j15 = cursorM.getLong(i12);
                    int i13 = iO13;
                    int i14 = iO15;
                    long j16 = cursorM.getLong(i14);
                    iO15 = i14;
                    int i15 = iO16;
                    boolean z = cursorM.getInt(i15) != 0;
                    iO16 = i15;
                    int i16 = iO17;
                    int iN = ac.b.N(cursorM.getInt(i16));
                    iO17 = i16;
                    int i17 = iO18;
                    int i18 = cursorM.getInt(i17);
                    iO18 = i17;
                    int i19 = iO19;
                    int i20 = cursorM.getInt(i19);
                    iO19 = i19;
                    int i21 = iO20;
                    long j17 = cursorM.getLong(i21);
                    iO20 = i21;
                    int i22 = iO21;
                    int i23 = cursorM.getInt(i22);
                    iO21 = i22;
                    int i24 = iO22;
                    int i25 = cursorM.getInt(i24);
                    iO22 = i24;
                    int i26 = iO23;
                    int iM = ac.b.M(cursorM.getInt(i26));
                    iO23 = i26;
                    int i27 = iO24;
                    boolean z5 = cursorM.getInt(i27) != 0;
                    iO24 = i27;
                    int i28 = iO25;
                    boolean z10 = cursorM.getInt(i28) != 0;
                    iO25 = i28;
                    int i29 = iO26;
                    boolean z11 = cursorM.getInt(i29) != 0;
                    iO26 = i29;
                    int i30 = iO27;
                    boolean z12 = cursorM.getInt(i30) != 0;
                    iO27 = i30;
                    int i31 = iO28;
                    long j18 = cursorM.getLong(i31);
                    iO28 = i31;
                    int i32 = iO29;
                    long j19 = cursorM.getLong(i32);
                    iO29 = i32;
                    int i33 = iO30;
                    if (!cursorM.isNull(i33)) {
                        blob = cursorM.getBlob(i33);
                    }
                    iO30 = i33;
                    arrayList.add(new p(string, iO31, string2, string3, hVarA, hVarA2, j10, j11, j12, new m2.f(iM, z5, z10, z11, z12, j18, j19, ac.b.e(blob)), i11, iL, j13, j14, j15, j16, z, iN, i18, i20, j17, i23, i25));
                    iO13 = i13;
                    i10 = i12;
                }
                cursorM.close();
                r0Var.l();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorM.close();
                r0Var.l();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            r0Var = r0VarK;
        }
    }

    @Override // androidx.work.impl.model.q
    public final int w(String str) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6953j;
        c2.g gVarC = iVar.c();
        if (str == null) {
            gVarC.C0(1);
        } else {
            gVarC.e0(1, str);
        }
        workDatabase_Impl.c();
        try {
            int iR = gVarC.r();
            workDatabase_Impl.o();
            return iR;
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final int x(String str) {
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        i iVar = this.f6952i;
        c2.g gVarC = iVar.c();
        if (str == null) {
            gVarC.C0(1);
        } else {
            gVarC.e0(1, str);
        }
        workDatabase_Impl.c();
        try {
            int iR = gVarC.r();
            workDatabase_Impl.o();
            return iR;
        } finally {
            workDatabase_Impl.k();
            iVar.h(gVarC);
        }
    }

    @Override // androidx.work.impl.model.q
    public final int y() {
        r0 r0VarK = r0.k(0, "Select COUNT(*) FROM workspec WHERE LENGTH(content_uri_triggers)<>0 AND state NOT IN (2, 3, 5)");
        WorkDatabase_Impl workDatabase_Impl = this.f6944a;
        workDatabase_Impl.b();
        Cursor cursorM = workDatabase_Impl.m(r0VarK);
        try {
            return cursorM.moveToFirst() ? cursorM.getInt(0) : 0;
        } finally {
            cursorM.close();
            r0VarK.l();
        }
    }
}
