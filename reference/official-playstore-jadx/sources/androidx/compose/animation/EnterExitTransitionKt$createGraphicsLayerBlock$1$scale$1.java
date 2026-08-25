package androidx.compose.animation;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/EnterExitState;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EnterExitTransitionKt$createGraphicsLayerBlock$1$scale$1 extends r implements l<Transition.Segment<EnterExitState>, FiniteAnimationSpec<Float>> {
    final /* synthetic */ EnterTransition $enter;
    final /* synthetic */ ExitTransition $exit;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnterExitTransitionKt$createGraphicsLayerBlock$1$scale$1(EnterTransition enterTransition, ExitTransition exitTransition) {
        super(1);
        this.$enter = enterTransition;
        this.$exit = exitTransition;
    }

    @Override // r7.l
    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<EnterExitState> segment) {
        FiniteAnimationSpec<Float> animationSpec;
        FiniteAnimationSpec<Float> animationSpec2;
        EnterExitState enterExitState = EnterExitState.PreEnter;
        EnterExitState enterExitState2 = EnterExitState.Visible;
        if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
            Scale scale = this.$enter.getData().getScale();
            return (scale == null || (animationSpec2 = scale.getAnimationSpec()) == null) ? EnterExitTransitionKt.DefaultAlphaAndScaleSpring : animationSpec2;
        }
        if (!segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
            return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
        }
        Scale scale2 = this.$exit.getData().getScale();
        return (scale2 == null || (animationSpec = scale2.getAnimationSpec()) == null) ? EnterExitTransitionKt.DefaultAlphaAndScaleSpring : animationSpec;
    }
}
