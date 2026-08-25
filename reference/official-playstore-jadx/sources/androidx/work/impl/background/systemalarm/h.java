package androidx.work.impl.background.systemalarm;

import android.content.Intent;
import android.os.PowerManager;
import androidx.work.impl.utils.l;
import androidx.work.impl.utils.n;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class h implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6761i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j f6762l;

    public /* synthetic */ h(j jVar, int i10) {
        this.f6761i = i10;
        this.f6762l = jVar;
    }

    private final void a() {
        androidx.work.impl.utils.taskexecutor.c cVarA;
        h hVar;
        synchronized (this.f6762l.f6770q) {
            j jVar = this.f6762l;
            jVar.f6771r = (Intent) jVar.f6770q.get(0);
        }
        Intent intent = this.f6762l.f6771r;
        if (intent != null) {
            String action = intent.getAction();
            int intExtra = this.f6762l.f6771r.getIntExtra("KEY_START_ID", 0);
            t tVarD = t.d();
            String str = j.f6763u;
            tVarD.a(str, "Processing command " + this.f6762l.f6771r + ", " + intExtra);
            PowerManager.WakeLock wakeLockA = n.a(this.f6762l.f6764i, action + " (" + intExtra + ")");
            try {
                t.d().a(str, "Acquiring operation wake lock (" + action + ") " + wakeLockA);
                wakeLockA.acquire();
                j jVar2 = this.f6762l;
                jVar2.f6769p.b(jVar2.f6771r, intExtra, jVar2);
                t.d().a(str, "Releasing operation wake lock (" + action + ") " + wakeLockA);
                wakeLockA.release();
                cVarA = this.f6762l.f6765l.a();
                hVar = new h(this.f6762l, 1);
            } catch (Throwable th) {
                try {
                    t tVarD2 = t.d();
                    String str2 = j.f6763u;
                    tVarD2.c(str2, "Unexpected error in onHandleIntent", th);
                    t.d().a(str2, "Releasing operation wake lock (" + action + ") " + wakeLockA);
                    wakeLockA.release();
                    cVarA = this.f6762l.f6765l.a();
                    hVar = new h(this.f6762l, 1);
                } catch (Throwable th2) {
                    t.d().a(j.f6763u, "Releasing operation wake lock (" + action + ") " + wakeLockA);
                    wakeLockA.release();
                    this.f6762l.f6765l.a().execute(new h(this.f6762l, 1));
                    throw th2;
                }
            }
            cVarA.execute(hVar);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f6761i) {
            case 0:
                a();
                return;
            default:
                j jVar = this.f6762l;
                t tVarD = t.d();
                String str = j.f6763u;
                tVarD.a(str, "Checking if commands are complete.");
                j.b();
                synchronized (jVar.f6770q) {
                    try {
                        if (jVar.f6771r != null) {
                            t.d().a(str, "Removing command " + jVar.f6771r);
                            if (!((Intent) jVar.f6770q.remove(0)).equals(jVar.f6771r)) {
                                throw new IllegalStateException("Dequeue-d command is not the first.");
                            }
                            jVar.f6771r = null;
                        }
                        l lVarC = jVar.f6765l.c();
                        if (!jVar.f6769p.a() && jVar.f6770q.isEmpty() && !lVarC.a()) {
                            t.d().a(str, "No more commands & intents.");
                            SystemAlarmService systemAlarmService = jVar.f6772s;
                            if (systemAlarmService != null) {
                                systemAlarmService.a();
                            }
                        } else if (!jVar.f6770q.isEmpty()) {
                            jVar.e();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return;
        }
    }
}
