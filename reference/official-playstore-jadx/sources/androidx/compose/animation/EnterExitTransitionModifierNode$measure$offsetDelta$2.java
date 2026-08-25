package androidx.compose.animation;

import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntOffset;", "it", "Landroidx/compose/animation/EnterExitState;", "invoke-Bjo55l4", "(Landroidx/compose/animation/EnterExitState;)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EnterExitTransitionModifierNode$measure$offsetDelta$2 extends r implements l<EnterExitState, IntOffset> {
    final /* synthetic */ long $target;
    final /* synthetic */ EnterExitTransitionModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnterExitTransitionModifierNode$measure$offsetDelta$2(EnterExitTransitionModifierNode enterExitTransitionModifierNode, long j10) {
        super(1);
        this.this$0 = enterExitTransitionModifierNode;
        this.$target = j10;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return IntOffset.m5793boximpl(m60invokeBjo55l4((EnterExitState) obj));
    }

    /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
    public final long m60invokeBjo55l4(EnterExitState enterExitState) {
        return this.this$0.m58targetOffsetByStateoFUgxo0(enterExitState, this.$target);
    }
}
