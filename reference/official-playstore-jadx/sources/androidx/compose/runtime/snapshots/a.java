package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.snapshots.Snapshot;
import r7.l;
import r7.p;
import x6.o;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements ObserverHandle {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1888a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ o f1889b;

    public /* synthetic */ a(o oVar, int i10) {
        this.f1888a = i10;
        this.f1889b = oVar;
    }

    @Override // androidx.compose.runtime.snapshots.ObserverHandle
    public final void dispose() {
        switch (this.f1888a) {
            case 0:
                Snapshot.Companion.registerApplyObserver$lambda$6((p) this.f1889b);
                break;
            default:
                Snapshot.Companion.registerGlobalWriteObserver$lambda$9((l) this.f1889b);
                break;
        }
    }
}
