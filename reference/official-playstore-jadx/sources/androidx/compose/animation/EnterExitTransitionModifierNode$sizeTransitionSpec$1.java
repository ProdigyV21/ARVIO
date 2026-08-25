package androidx.compose.animation;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/EnterExitState;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EnterExitTransitionModifierNode$sizeTransitionSpec$1 extends r implements l<Transition.Segment<EnterExitState>, FiniteAnimationSpec<IntSize>> {
    final /* synthetic */ EnterExitTransitionModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnterExitTransitionModifierNode$sizeTransitionSpec$1(EnterExitTransitionModifierNode enterExitTransitionModifierNode) {
        super(1);
        this.this$0 = enterExitTransitionModifierNode;
    }

    @Override // r7.l
    public final FiniteAnimationSpec<IntSize> invoke(Transition.Segment<EnterExitState> segment) {
        EnterExitState enterExitState = EnterExitState.PreEnter;
        EnterExitState enterExitState2 = EnterExitState.Visible;
        FiniteAnimationSpec<IntSize> animationSpec = null;
        if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
            ChangeSize changeSize = this.this$0.getEnter().getData().getChangeSize();
            if (changeSize != null) {
                animationSpec = changeSize.getAnimationSpec();
            }
        } else if (segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
            ChangeSize changeSize2 = this.this$0.getExit().getData().getChangeSize();
            if (changeSize2 != null) {
                animationSpec = changeSize2.getAnimationSpec();
            }
        } else {
            animationSpec = EnterExitTransitionKt.DefaultSizeAnimationSpec;
        }
        return animationSpec == null ? EnterExitTransitionKt.DefaultSizeAnimationSpec : animationSpec;
    }
}
