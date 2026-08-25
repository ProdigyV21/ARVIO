package androidx.compose.animation.core;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "S", "invoke", "()Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class Transition$totalDurationNanos$2 extends r implements r7.a<Long> {
    final /* synthetic */ Transition<S> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Transition$totalDurationNanos$2(Transition<S> transition) {
        super(0);
        this.this$0 = transition;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // r7.a
    public final Long invoke() {
        SnapshotStateList snapshotStateList = ((Transition) this.this$0)._animations;
        int size = snapshotStateList.size();
        long jMax = 0;
        for (int i10 = 0; i10 < size; i10++) {
            jMax = Math.max(jMax, ((Transition.TransitionAnimationState) snapshotStateList.get(i10)).getDurationNanos$animation_core_release());
        }
        SnapshotStateList snapshotStateList2 = ((Transition) this.this$0)._transitions;
        int size2 = snapshotStateList2.size();
        for (int i11 = 0; i11 < size2; i11++) {
            jMax = Math.max(jMax, ((Transition) snapshotStateList2.get(i11)).getTotalDurationNanos());
        }
        return Long.valueOf(jMax);
    }
}
