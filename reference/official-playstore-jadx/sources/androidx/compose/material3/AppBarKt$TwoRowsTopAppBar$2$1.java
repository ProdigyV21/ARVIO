package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AppBarKt$TwoRowsTopAppBar$2$1 extends r implements r7.a<t0> {
    final /* synthetic */ h0 $maxHeightPx;
    final /* synthetic */ h0 $pinnedHeightPx;
    final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppBarKt$TwoRowsTopAppBar$2$1(TopAppBarScrollBehavior topAppBarScrollBehavior, h0 h0Var, h0 h0Var2) {
        super(0);
        this.$scrollBehavior = topAppBarScrollBehavior;
        this.$pinnedHeightPx = h0Var;
        this.$maxHeightPx = h0Var2;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m1233invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m1233invoke() {
        TopAppBarState state;
        TopAppBarScrollBehavior topAppBarScrollBehavior = this.$scrollBehavior;
        Float fValueOf = (topAppBarScrollBehavior == null || (state = topAppBarScrollBehavior.getState()) == null) ? null : Float.valueOf(state.getHeightOffsetLimit());
        float f10 = this.$pinnedHeightPx.f19742i - this.$maxHeightPx.f19742i;
        if (fValueOf == null || fValueOf.floatValue() != f10) {
            TopAppBarScrollBehavior topAppBarScrollBehavior2 = this.$scrollBehavior;
            TopAppBarState state2 = topAppBarScrollBehavior2 != null ? topAppBarScrollBehavior2.getState() : null;
            if (state2 == null) {
                return;
            }
            state2.setHeightOffsetLimit(this.$pinnedHeightPx.f19742i - this.$maxHeightPx.f19742i);
        }
    }
}
