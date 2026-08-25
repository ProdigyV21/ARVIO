package androidx.work.impl.background.systemalarm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.l;
import androidx.work.impl.model.p;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import m2.t;
import m2.u;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements androidx.work.impl.d {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f6734p = t.f("CommandHandler");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f6735i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final HashMap f6736l = new HashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f6737m = new Object();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final m2.a f6738n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f6739o;

    public c(Context context, u uVar, io.sentry.internal.debugmeta.c cVar) {
        this.f6735i = context;
        this.f6738n = uVar;
        this.f6739o = cVar;
    }

    public static androidx.work.impl.model.j d(Intent intent) {
        return new androidx.work.impl.model.j(intent.getStringExtra("KEY_WORKSPEC_ID"), intent.getIntExtra("KEY_WORKSPEC_GENERATION", 0));
    }

    public static void e(Intent intent, androidx.work.impl.model.j jVar) {
        intent.putExtra("KEY_WORKSPEC_ID", jVar.f6914a);
        intent.putExtra("KEY_WORKSPEC_GENERATION", jVar.f6915b);
    }

    public final boolean a() {
        boolean z;
        synchronized (this.f6737m) {
            z = !this.f6736l.isEmpty();
        }
        return z;
    }

    public final void b(Intent intent, int i10, j jVar) {
        List<l> listF;
        String action = intent.getAction();
        int i11 = 3;
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            t.d().a(f6734p, "Handling constraints changed " + intent);
            Context context = this.f6735i;
            e eVar = new e(context, this.f6738n, i10, jVar);
            ArrayList<p> arrayListG = jVar.f6768o.f6966c.t().g();
            String str = d.f6740a;
            Iterator it = arrayListG.iterator();
            boolean z = false;
            boolean z5 = false;
            boolean z10 = false;
            boolean z11 = false;
            while (it.hasNext()) {
                m2.f fVar = ((p) it.next()).f6931j;
                z |= fVar.f20191d;
                z5 |= fVar.f20189b;
                z10 |= fVar.f20192e;
                z11 |= fVar.f20188a != 1;
                if (z && z5 && z10 && z11) {
                    break;
                }
            }
            String str2 = ConstraintProxyUpdateReceiver.f6728a;
            Intent intent2 = new Intent("androidx.work.impl.background.systemalarm.UpdateProxies");
            intent2.setComponent(new ComponentName(context, (Class<?>) ConstraintProxyUpdateReceiver.class));
            intent2.putExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", z).putExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", z5).putExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", z10).putExtra("KEY_NETWORK_STATE_PROXY_ENABLED", z11);
            context.sendBroadcast(intent2);
            ArrayList<p> arrayList = new ArrayList(arrayListG.size());
            long jCurrentTimeMillis = eVar.f6742a.currentTimeMillis();
            for (p pVar : arrayListG) {
                if (jCurrentTimeMillis >= pVar.a() && (!pVar.b() || eVar.f6744c.a(pVar))) {
                    arrayList.add(pVar);
                }
            }
            for (p pVar2 : arrayList) {
                String str3 = pVar2.f6922a;
                androidx.work.impl.model.j jVarN = a.a.N(pVar2);
                Intent intent3 = new Intent(context, (Class<?>) SystemAlarmService.class);
                intent3.setAction("ACTION_DELAY_MET");
                e(intent3, jVarN);
                t.d().a(e.f6741d, "Creating a delay_met command for workSpec with id (" + str3 + ")");
                jVar.f6765l.a().execute(new android.support.v4.os.e(eVar.f6743b, i11, jVar, intent3));
            }
            return;
        }
        if ("ACTION_RESCHEDULE".equals(action)) {
            t.d().a(f6734p, "Handling reschedule " + intent + ", " + i10);
            jVar.f6768o.c0();
            return;
        }
        Bundle extras = intent.getExtras();
        String[] strArr = {"KEY_WORKSPEC_ID"};
        if (extras == null || extras.isEmpty() || extras.get(strArr[0]) == null) {
            t.d().b(f6734p, "Invalid request for " + action + " , requires KEY_WORKSPEC_ID .");
            return;
        }
        if ("ACTION_SCHEDULE_WORK".equals(action)) {
            Context context2 = this.f6735i;
            androidx.work.impl.model.j jVarD = d(intent);
            t tVarD = t.d();
            String str4 = f6734p;
            tVarD.a(str4, "Handling schedule work for " + jVarD);
            WorkDatabase workDatabase = jVar.f6768o.f6966c;
            workDatabase.c();
            try {
                p pVarK = workDatabase.t().k(jVarD.f6914a);
                if (pVarK == null) {
                    t.d().g(str4, "Skipping scheduling " + jVarD + " because it's no longer in the DB");
                    return;
                }
                if (g8.b.b(pVarK.f6923b)) {
                    t.d().g(str4, "Skipping scheduling " + jVarD + "because it is finished.");
                    return;
                }
                long jA = pVarK.a();
                if (pVarK.b()) {
                    t.d().a(str4, "Opportunistically setting an alarm for " + jVarD + "at " + jA);
                    b.b(context2, workDatabase, jVarD, jA);
                    Intent intent4 = new Intent(context2, (Class<?>) SystemAlarmService.class);
                    intent4.setAction("ACTION_CONSTRAINTS_CHANGED");
                    jVar.f6765l.a().execute(new android.support.v4.os.e(i10, i11, jVar, intent4));
                } else {
                    t.d().a(str4, "Setting up Alarms for " + jVarD + "at " + jA);
                    b.b(context2, workDatabase, jVarD, jA);
                }
                workDatabase.o();
                return;
            } finally {
                workDatabase.k();
            }
        }
        if ("ACTION_DELAY_MET".equals(action)) {
            synchronized (this.f6737m) {
                try {
                    androidx.work.impl.model.j jVarD2 = d(intent);
                    t tVarD2 = t.d();
                    String str5 = f6734p;
                    tVarD2.a(str5, "Handing delay met for " + jVarD2);
                    if (this.f6736l.containsKey(jVarD2)) {
                        t.d().a(str5, "WorkSpec " + jVarD2 + " is is already being handled for ACTION_DELAY_MET");
                    } else {
                        g gVar = new g(this.f6735i, i10, jVar, this.f6739o.I(jVarD2));
                        this.f6736l.put(jVarD2, gVar);
                        gVar.f();
                    }
                } finally {
                }
            }
            return;
        }
        if (!"ACTION_STOP_WORK".equals(action)) {
            if (!"ACTION_EXECUTION_COMPLETED".equals(action)) {
                t.d().g(f6734p, "Ignoring intent " + intent);
                return;
            }
            androidx.work.impl.model.j jVarD3 = d(intent);
            boolean z12 = intent.getExtras().getBoolean("KEY_NEEDS_RESCHEDULE");
            t.d().a(f6734p, "Handling onExecutionCompleted " + intent + ", " + i10);
            c(jVarD3, z12);
            return;
        }
        io.sentry.internal.debugmeta.c cVar = this.f6739o;
        Bundle extras2 = intent.getExtras();
        String string = extras2.getString("KEY_WORKSPEC_ID");
        if (extras2.containsKey("KEY_WORKSPEC_GENERATION")) {
            int i12 = extras2.getInt("KEY_WORKSPEC_GENERATION");
            ArrayList arrayList2 = new ArrayList(1);
            l lVarE = cVar.E(new androidx.work.impl.model.j(string, i12));
            listF = arrayList2;
            if (lVarE != null) {
                arrayList2.add(lVarE);
                listF = arrayList2;
            }
        } else {
            listF = cVar.F(string);
        }
        for (l lVar : listF) {
            t.d().a(f6734p, "Handing stopWork work for " + string);
            jVar.f6773t.n(lVar);
            Context context3 = this.f6735i;
            WorkDatabase workDatabase2 = jVar.f6768o.f6966c;
            androidx.work.impl.model.j jVar2 = lVar.f6896a;
            String str6 = b.f6733a;
            androidx.work.impl.model.h hVarP = workDatabase2.p();
            androidx.work.impl.model.g gVarD = hVarP.d(jVar2);
            if (gVarD != null) {
                b.a(context3, jVar2, gVarD.f6912c);
                t.d().a(b.f6733a, "Removing SystemIdInfo for workSpecId (" + jVar2 + ")");
                hVarP.h(jVar2);
            }
            jVar.c(lVar.f6896a, false);
        }
    }

    @Override // androidx.work.impl.d
    public final void c(androidx.work.impl.model.j jVar, boolean z) {
        synchronized (this.f6737m) {
            try {
                g gVar = (g) this.f6736l.remove(jVar);
                this.f6739o.E(jVar);
                if (gVar != null) {
                    gVar.g(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
