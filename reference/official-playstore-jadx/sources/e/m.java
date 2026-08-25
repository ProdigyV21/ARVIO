package e;

import a8.l0;
import androidx.activity.w;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import kotlin.jvm.internal.f0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class m implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final w f14958i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r7.a f14959l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SnapshotStateObserver f14960m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final k f14961n;

    public m(w wVar, r7.a aVar) {
        boolean z;
        this.f14958i = wVar;
        this.f14959l = aVar;
        SnapshotStateObserver snapshotStateObserver = new SnapshotStateObserver(l.f14957i);
        snapshotStateObserver.start();
        this.f14960m = snapshotStateObserver;
        k kVar = new k(1, 0, m.class, this, "observeReporter", "observeReporter(Lkotlin/jvm/functions/Function0;)V");
        this.f14961n = kVar;
        synchronized (wVar.f1021c) {
            if (wVar.f1024f) {
                z = true;
            } else {
                wVar.f1025g.add(this);
                z = false;
            }
        }
        if (z) {
            invoke();
        }
        if (wVar.c()) {
            return;
        }
        wVar.a();
        f0 f0Var = new f0();
        snapshotStateObserver.observeReads(aVar, kVar, new l0(f0Var, aVar, 9));
        if (f0Var.f19738i) {
            snapshotStateObserver.clear(aVar);
            if (!wVar.c()) {
                wVar.d();
            }
            snapshotStateObserver.clear();
            snapshotStateObserver.stop();
        }
    }

    @Override // r7.a
    public final Object invoke() {
        SnapshotStateObserver snapshotStateObserver = this.f14960m;
        snapshotStateObserver.clear();
        snapshotStateObserver.stop();
        return t0.f22605a;
    }
}
