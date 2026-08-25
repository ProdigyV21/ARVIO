package e;

import androidx.activity.w;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class b implements DisposableEffectResult {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14933a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f14934b;

    public /* synthetic */ b(Object obj, int i10) {
        this.f14933a = i10;
        this.f14934b = obj;
    }

    @Override // androidx.compose.runtime.DisposableEffectResult
    public final void dispose() {
        t0 t0Var;
        switch (this.f14933a) {
            case 0:
                androidx.activity.result.j jVar = ((a) this.f14934b).f14932a;
                if (jVar != null) {
                    jVar.b();
                    t0Var = t0.f22605a;
                } else {
                    t0Var = null;
                }
                if (t0Var == null) {
                    throw new IllegalStateException("Launcher has not been initialized");
                }
                return;
            case 1:
                ((e) this.f14934b).e();
                return;
            case 2:
                ((i) this.f14934b).e();
                return;
            default:
                m mVar = (m) this.f14934b;
                SnapshotStateObserver snapshotStateObserver = mVar.f14960m;
                snapshotStateObserver.clear(mVar.f14959l);
                w wVar = mVar.f14958i;
                if (!wVar.c()) {
                    wVar.d();
                }
                snapshotStateObserver.clear();
                snapshotStateObserver.stop();
                return;
        }
    }
}
