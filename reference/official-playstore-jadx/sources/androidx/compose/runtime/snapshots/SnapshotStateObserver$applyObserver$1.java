package androidx.compose.runtime.snapshots;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "", "applied", "Landroidx/compose/runtime/snapshots/Snapshot;", "<anonymous parameter 1>", "Lx6/t0;", "invoke", "(Ljava/util/Set;Landroidx/compose/runtime/snapshots/Snapshot;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SnapshotStateObserver$applyObserver$1 extends r implements p<Set<? extends Object>, Snapshot, t0> {
    final /* synthetic */ SnapshotStateObserver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapshotStateObserver$applyObserver$1(SnapshotStateObserver snapshotStateObserver) {
        super(2);
        this.this$0 = snapshotStateObserver;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Set<? extends Object>) obj, (Snapshot) obj2);
        return t0.f22605a;
    }

    public final void invoke(Set<? extends Object> set, Snapshot snapshot) {
        this.this$0.addChanges(set);
        if (this.this$0.drainChanges()) {
            this.this$0.sendNotifications();
        }
    }
}
