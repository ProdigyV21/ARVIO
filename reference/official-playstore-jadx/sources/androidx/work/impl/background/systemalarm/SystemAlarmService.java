package androidx.work.impl.background.systemalarm;

import android.content.Intent;
import android.os.PowerManager;
import androidx.lifecycle.c0;
import androidx.work.impl.utils.n;
import androidx.work.impl.utils.o;
import java.util.LinkedHashMap;
import java.util.Map;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public class SystemAlarmService extends c0 implements i {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f6730n = t.f("SystemAlarmService");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public j f6731l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f6732m;

    public final void a() {
        this.f6732m = true;
        t.d().a(f6730n, "All commands completed in dispatcher");
        String str = n.f7052a;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        synchronized (o.f7053a) {
            linkedHashMap.putAll(o.f7054b);
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            PowerManager.WakeLock wakeLock = (PowerManager.WakeLock) entry.getKey();
            String str2 = (String) entry.getValue();
            if (wakeLock != null && wakeLock.isHeld()) {
                t.d().g(n.f7052a, "WakeLock held for " + str2);
            }
        }
        stopSelf();
    }

    @Override // androidx.lifecycle.c0, android.app.Service
    public final void onCreate() {
        super.onCreate();
        j jVar = new j(this);
        this.f6731l = jVar;
        if (jVar.f6772s != null) {
            t.d().b(j.f6763u, "A completion listener for SystemAlarmDispatcher already exists.");
        } else {
            jVar.f6772s = this;
        }
        this.f6732m = false;
    }

    @Override // androidx.lifecycle.c0, android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        this.f6732m = true;
        j jVar = this.f6731l;
        jVar.getClass();
        t.d().a(j.f6763u, "Destroying SystemAlarmDispatcher");
        jVar.f6767n.i(jVar);
        jVar.f6772s = null;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i10, int i11) {
        super.onStartCommand(intent, i10, i11);
        if (this.f6732m) {
            t.d().e(f6730n, "Re-initializing SystemAlarmDispatcher after a request to shut-down.");
            j jVar = this.f6731l;
            jVar.getClass();
            t tVarD = t.d();
            String str = j.f6763u;
            tVarD.a(str, "Destroying SystemAlarmDispatcher");
            jVar.f6767n.i(jVar);
            jVar.f6772s = null;
            j jVar2 = new j(this);
            this.f6731l = jVar2;
            if (jVar2.f6772s != null) {
                t.d().b(str, "A completion listener for SystemAlarmDispatcher already exists.");
            } else {
                jVar2.f6772s = this;
            }
            this.f6732m = false;
        }
        if (intent == null) {
            return 3;
        }
        this.f6731l.a(intent, i11);
        return 3;
    }
}
