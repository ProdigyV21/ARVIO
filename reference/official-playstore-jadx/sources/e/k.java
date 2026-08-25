package e;

import a8.l0;
import androidx.activity.w;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import kotlin.jvm.internal.f0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k extends kotlin.jvm.internal.m implements r7.l {
    @Override // r7.l
    public final Object invoke(Object obj) {
        r7.a aVar = (r7.a) obj;
        m mVar = (m) this.receiver;
        f0 f0Var = new f0();
        SnapshotStateObserver snapshotStateObserver = mVar.f14960m;
        snapshotStateObserver.observeReads(aVar, mVar.f14961n, new l0(f0Var, aVar, 9));
        if (f0Var.f19738i) {
            snapshotStateObserver.clear(mVar.f14959l);
            w wVar = mVar.f14958i;
            if (!wVar.c()) {
                wVar.d();
            }
            snapshotStateObserver.clear();
            snapshotStateObserver.stop();
        }
        return t0.f22605a;
    }
}
