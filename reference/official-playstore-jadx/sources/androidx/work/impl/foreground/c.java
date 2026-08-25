package androidx.work.impl.foreground;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.fragment.app.a2;
import androidx.work.impl.l;
import androidx.work.impl.model.j;
import androidx.work.impl.model.p;
import androidx.work.impl.r;
import androidx.work.impl.utils.m;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import ka.v1;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements androidx.work.impl.constraints.f, androidx.work.impl.d {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String f6864t = t.f("SystemFgDispatcher");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r f6865i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.b f6866l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f6867m = new Object();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public j f6868n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final LinkedHashMap f6869o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final HashMap f6870p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final HashMap f6871q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final androidx.work.impl.constraints.j f6872r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public SystemForegroundService f6873s;

    public c(Context context) {
        r rVarZ = r.Z(context);
        this.f6865i = rVarZ;
        this.f6866l = rVarZ.f6967d;
        this.f6868n = null;
        this.f6869o = new LinkedHashMap();
        this.f6871q = new HashMap();
        this.f6870p = new HashMap();
        this.f6872r = new androidx.work.impl.constraints.j(rVarZ.f6973j);
        rVarZ.f6969f.b(this);
    }

    public static Intent a(Context context, j jVar, m2.j jVar2) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction("ACTION_NOTIFY");
        intent.putExtra("KEY_NOTIFICATION_ID", jVar2.f20207a);
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", jVar2.f20208b);
        intent.putExtra("KEY_NOTIFICATION", jVar2.f20209c);
        intent.putExtra("KEY_WORKSPEC_ID", jVar.f6914a);
        intent.putExtra("KEY_GENERATION", jVar.f6915b);
        return intent;
    }

    public static Intent b(Context context, j jVar, m2.j jVar2) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction("ACTION_START_FOREGROUND");
        intent.putExtra("KEY_WORKSPEC_ID", jVar.f6914a);
        intent.putExtra("KEY_GENERATION", jVar.f6915b);
        intent.putExtra("KEY_NOTIFICATION_ID", jVar2.f20207a);
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", jVar2.f20208b);
        intent.putExtra("KEY_NOTIFICATION", jVar2.f20209c);
        return intent;
    }

    @Override // androidx.work.impl.d
    public final void c(j jVar, boolean z) {
        Map.Entry entry;
        synchronized (this.f6867m) {
            try {
                v1 v1Var = ((p) this.f6870p.remove(jVar)) != null ? (v1) this.f6871q.remove(jVar) : null;
                if (v1Var != null) {
                    v1Var.cancel((CancellationException) null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        m2.j jVar2 = (m2.j) this.f6869o.remove(jVar);
        if (jVar.equals(this.f6868n)) {
            if (this.f6869o.size() > 0) {
                Iterator it = this.f6869o.entrySet().iterator();
                Object next = it.next();
                while (true) {
                    entry = (Map.Entry) next;
                    if (!it.hasNext()) {
                        break;
                    } else {
                        next = it.next();
                    }
                }
                this.f6868n = (j) entry.getKey();
                if (this.f6873s != null) {
                    m2.j jVar3 = (m2.j) entry.getValue();
                    this.f6873s.c(jVar3.f20207a, jVar3.f20208b, jVar3.f20209c);
                    this.f6873s.a(jVar3.f20207a);
                }
            } else {
                this.f6868n = null;
            }
        }
        SystemForegroundService systemForegroundService = this.f6873s;
        if (jVar2 == null || systemForegroundService == null) {
            return;
        }
        t.d().a(f6864t, "Removing Notification (id: " + jVar2.f20207a + ", workSpecId: " + jVar + ", notificationType: " + jVar2.f20208b);
        systemForegroundService.a(jVar2.f20207a);
    }

    public final void d(Intent intent) {
        int i10 = 0;
        int intExtra = intent.getIntExtra("KEY_NOTIFICATION_ID", 0);
        int intExtra2 = intent.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        j jVar = new j(stringExtra, intent.getIntExtra("KEY_GENERATION", 0));
        Notification notification = (Notification) intent.getParcelableExtra("KEY_NOTIFICATION");
        t tVarD = t.d();
        StringBuilder sbO = a2.o("Notifying with (id:", intExtra, ", workSpecId: ", stringExtra, ", notificationType :");
        sbO.append(intExtra2);
        sbO.append(")");
        tVarD.a(f6864t, sbO.toString());
        if (notification == null || this.f6873s == null) {
            return;
        }
        m2.j jVar2 = new m2.j(intExtra, intExtra2, notification);
        LinkedHashMap linkedHashMap = this.f6869o;
        linkedHashMap.put(jVar, jVar2);
        if (this.f6868n == null) {
            this.f6868n = jVar;
            this.f6873s.c(intExtra, intExtra2, notification);
            return;
        }
        SystemForegroundService systemForegroundService = this.f6873s;
        systemForegroundService.f6860l.post(new android.support.v4.os.e(systemForegroundService, intExtra, notification, 4));
        if (intExtra2 == 0 || Build.VERSION.SDK_INT < 29) {
            return;
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            i10 |= ((m2.j) ((Map.Entry) it.next()).getValue()).f20208b;
        }
        m2.j jVar3 = (m2.j) linkedHashMap.get(this.f6868n);
        if (jVar3 != null) {
            this.f6873s.c(jVar3.f20207a, i10, jVar3.f20209c);
        }
    }

    @Override // androidx.work.impl.constraints.f
    public final void e(p pVar, androidx.work.impl.constraints.d dVar) {
        if (dVar instanceof androidx.work.impl.constraints.c) {
            String str = pVar.f6922a;
            t.d().a(f6864t, "Constraints unmet for WorkSpec " + str);
            j jVarN = a.a.N(pVar);
            r rVar = this.f6865i;
            rVar.f6967d.d(new m(rVar.f6969f, new l(jVarN), true, -512));
        }
    }

    public final void f() {
        this.f6873s = null;
        synchronized (this.f6867m) {
            try {
                Iterator it = this.f6871q.values().iterator();
                while (it.hasNext()) {
                    ((v1) it.next()).cancel((CancellationException) null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f6865i.f6969f.i(this);
    }
}
