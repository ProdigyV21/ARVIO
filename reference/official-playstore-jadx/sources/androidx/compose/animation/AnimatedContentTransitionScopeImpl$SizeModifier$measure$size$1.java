package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: Add missing generic type declarations: [S] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/ui/unit/IntSize;", "S", "Landroidx/compose/animation/core/Transition$Segment;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AnimatedContentTransitionScopeImpl$SizeModifier$measure$size$1<S> extends r implements l<Transition.Segment<S>, FiniteAnimationSpec<IntSize>> {
    final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;
    final /* synthetic */ AnimatedContentTransitionScopeImpl<S>.SizeModifier this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimatedContentTransitionScopeImpl$SizeModifier$measure$size$1(AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl, AnimatedContentTransitionScopeImpl<S>.SizeModifier sizeModifier) {
        super(1);
        this.this$0 = animatedContentTransitionScopeImpl;
        this.this$1 = sizeModifier;
    }

    @Override // r7.l
    public final FiniteAnimationSpec<IntSize> invoke(Transition.Segment<S> segment) {
        FiniteAnimationSpec<IntSize> finiteAnimationSpecMo78createAnimationSpecTemP2vQ;
        State<IntSize> state = this.this$0.getTargetSizeMap$animation_release().get(segment.getInitialState());
        long packedValue = state != null ? state.getValue().getPackedValue() : IntSize.INSTANCE.m5849getZeroYbymL2g();
        State<IntSize> state2 = this.this$0.getTargetSizeMap$animation_release().get(segment.getTargetState());
        long packedValue2 = state2 != null ? state2.getValue().getPackedValue() : IntSize.INSTANCE.m5849getZeroYbymL2g();
        SizeTransform value = this.this$1.getSizeTransform().getValue();
        return (value == null || (finiteAnimationSpecMo78createAnimationSpecTemP2vQ = value.mo78createAnimationSpecTemP2vQ(packedValue, packedValue2)) == null) ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : finiteAnimationSpecMo78createAnimationSpecTemP2vQ;
    }
}
